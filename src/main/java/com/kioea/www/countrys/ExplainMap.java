package com.kioea.www.countrys;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import com.kioea.www.CloseUtil;
import com.kioea.www.configutil.XmlUtil;

/**
 * 根据国家啥的获取城市
 * 
 * @author:sekift
 * @time:2014-9-29 下午03:09:40
 * @version:
 */
public class ExplainMap {

	/**
	 * 配置
	 */
	private static Map<?, ?> config = null;

	private static final String C_FILE = ExplainMap.class.getResource("/LocList.xml").toString().substring(5);

	/**
	 * 初始化操作
	 */
	static {
		// 加载配置数据
		FileInputStream in = null;
		try {
			in = new FileInputStream(new File(C_FILE));
			config = XmlUtil.toMap(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.closeSilently(in);
		}
	}

	/**
	 * 
	 */
	public static String getCountryName(String name) {
		try {
			System.out.println(config);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		getCountryName("中国");
	}
}
