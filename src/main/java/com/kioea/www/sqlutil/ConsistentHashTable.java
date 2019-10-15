package com.kioea.www.sqlutil;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kioea.www.configutil.ReloadableProperties;
import com.kioea.www.configutil.XmlUtil;
import com.kioea.www.sqlutil.locate.HashAlgorithms;
import com.kioea.www.sqlutil.locate.NodeLocator;
import com.kioea.www.sqlutil.locate.NodeLocators;
import com.kioea.www.sqlutil.locate.NodeLocators.ConsistentHashLocator;

/**
 * 
 * @author:sekift
 * @time:2015-4-17 下午03:59:25
 * @version:
 */
public class ConsistentHashTable {

	private static TableConfig config = null;

	private static Map<String, ConsistentTable> cts = null;
	
	private static Logger logger = LoggerFactory
	.getLogger(ConsistentHashTable.class);
	
	/**
	 * 初始化
	 */
	static {
		try {
			config = new TableConfig("/config/consistent_hash_table.xml");
			config.setTimingReload(true);
			config.initialize();
		} catch (Exception e) {
			logger.error("Table config initialize error!", e);
		}
		logger.debug("table config:{}", config);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map<String, ConsistentTable> getCts() {
		if (null == cts) {
			Map<String, ConsistentTable> ncts = new HashMap<String, ConsistentTable>();
			for (String k : config.getProperties().keySet()) {
				Map<String, Object> tc = (Map<String, Object>) config
						.getProperties().get(k);
				Map is = (Map) tc.get("items");
				List pt = (List) is.get("item");
				ConsistentTable ct = new ConsistentTable(k, pt);
				ncts.put(k, ct);
			}
			logger.debug("ConsistentTable(s):{}", ncts);
			cts = ncts;
		}
		return cts;
	}

	
	/**
	 * 根据表名和主键定位到物理表
	 * 
	 * @param tbName
	 * @param pk
	 * @return
	 */
	public static TableItem getTable(String tbName, String pk) {
		TableItem tb = null;
		try {
			ConsistentTable ct = getCts().get(tbName);
			tb = ct.getLocator().locate(pk, NodeLocator.NULL_STRATEGY);
		} catch (Exception e) {
			logger.error("定位物理表出错！", e);
		}
		return tb;
	}
	
	/**
	 * 一个分表 的配置项
	 */
	public static class TableItem {
		private Long no = null;
		private String table = null;
		private Long mod = null;

		public Long getNo() {
			return no;
		}

		public String getTable() {
			return table;
		}


		public void setNo(Long no) {
			this.no = no;
		}

		public void setTable(String table) {
			this.table = table;
		}

		public Long getMod() {
			return mod;
		}

		public void setMod(Long mod) {
			this.mod = mod;
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder(120);
			sb.append("@TableItem{");
			sb.append(" no:").append(no);
			sb.append(", mod:").append(mod);
			sb.append(", table:").append(table);
			sb.append("}");
			return sb.toString();
		}
	}

	/**
	 * 一致性hash表
	 * 
	 */
	public static class ConsistentTable {

		/**
		 * 日志实例
		 */
		private static Logger logger = LoggerFactory
				.getLogger(ConsistentTable.class);

		/**
		 * 逻辑表配置
		 */
		private List<Map<String, String>> config = null;

		/**
		 * 物理表项定位器
		 */
		private ConsistentHashLocator<TableItem> locator = null;
//		private ModLocator<TableItem> locator = null;

		/**
		 * 逻辑表名
		 */
		private String tablename = null;

		/**
		 * 构造方法，逻辑表名，逻辑表配置
		 * 
		 * @param tbName
		 * @param tc
		 */
		public ConsistentTable(String tbName, List<Map<String, String>> tc) {

			this.tablename = tbName;
			this.config = tc;
			TreeMap<Long, TableItem> nodes = new TreeMap<Long, TableItem>();
			for (Map<String, String> i : tc) {
				TableItem ti = new TableItem();
				ti.setNo(Long.valueOf(i.get("no")));
				ti.setMod(Long.valueOf(i.get("mod")));
				ti.setTable(i.get("table"));
				nodes.put(ti.getNo(), ti);
			}
			locator = new NodeLocators.ConsistentHashLocator<TableItem>();//选择算法
//			locator = new ModLocator<TableItem>();//选择算法
			locator.setHashAlgorithm(HashAlgorithms.KEMATA_HASH);//选择Hash算法
			locator.setNodes(nodes);

			logger.debug("locator:{}", locator);
		}

		/**
		 * 获取分表配置项定位器
		 * 
		 * @return
		 */
		public ConsistentHashLocator<TableItem> getLocator() {
			return this.locator;
		}
//		public ModLocator<TableItem> getLocator() {
//			return this.locator;
//		}

		/**
		 * 获取逻辑表名
		 * 
		 * @return the tablename
		 */
		public String getTablename() {
			return tablename;
		}

		/**
		 * 获取逻辑表配置
		 * 
		 * @return the config
		 */
		public List<Map<String, String>> getConfig() {
			return config;
		}
	}
	
	/**
	 * 分库分表配置解析类
	 * 
	 */
	public static class TableConfig extends ReloadableProperties{
		/**
		 * 日志实例
		 */
		private static Logger logger = LoggerFactory
				.getLogger(TableConfig.class);

		/**
		 * 文件名
		 */
		private String configFileName=null;
		
		public TableConfig(String cn){
			this.configFileName=cn;
		}
		
		@Override
		protected URL getSourceURL() {
			return this.getClass().getResource(configFileName);
		}

		@Override
		protected Map reload(InputStream in, Map tarStorage) {
			try {
				Map rtv = XmlUtil.toMap(in);
				
				//更新配置文件后删除原来的表信息，用以重新生成表信息
				cts = null;

				return rtv;
			} catch (Exception ex) {
				logger.error("解析[{}]文件异常[{}]", this.configFileName, ex);
				return tarStorage;
			}
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(120);
			sb.append("@TableConfig{");
			sb.append(" props:").append(props);
			sb.append("}");
			return sb.toString();
		}
	}
}

