package com.kioea.www.configutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 可重新加载配置信息的抽象类
 * @memo: 在获取key时,判断初始化资源文件如果修改,重新初始化配置信息
 */
@SuppressWarnings({"unchecked"})
public abstract class ReloadableProperties {

	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
			
	/**
	 * 定时扫描服务
	 */
	private static ScheduledExecutorService service = ThreadPools.newScheduledExecutorService(1, "重新加载文件定时扫描线程(AbstractReloadableProperties)");
 
	/**
	 * 保存配置信息的map
	 */
	protected Map<String, Object> props = new ConcurrentHashMap<String, Object>(100);
	
	/**
	 * 配置来源URL
	 */
	private URL src = null;
	
	/**
	 * url 表达式中的protocol
	 */
	private String protocol = null;
	
	/**
	 * 配置来源对象, 根据protocol的不同而不同
	 */
	private File targetSrc = null;
			
	/**
	 * 记录文件最后一次修改时间
	 */
	private long lastModifiedTime = 0;
	
	/**
	 * jar文件中目标zip entry的最后修改时间
	 */
	private long jarLastModifiedTime = 0;
	
	/**
	 * 是否定时扫描并加载文件
	 */
	private boolean timingReload = false;
		
	/**
	 * 获取配置信息
	 * @param key 配置的key
	 * @return 配置信息
	 */
	public <T> T getValue(Object key) {
		return (T)props.get(key);
	}
	
	/**
	 * 返回是否定时扫描文件
	 * @return true,如果设置了定时扫描文件
	 */
	public final boolean getTimingReload() {		
		return timingReload;
	}
	
	/**
	 * 设置定时扫描文件
	 * @param flag 
	 */
	public final void setTimingReload(boolean flag) {		
		timingReload = flag;
	}
		
	/**
	 * 返回配置文件对象
	 * @return 文件
	 */
	protected abstract URL getSourceURL();
	
	/**
	 * 返回扫描文件间隔(单位: 毫秒)
	 * @return 毫秒数
	 */
	protected long getScanInterval() {		
		return 15000l;
	}
	 	
	/**
	 * 重新加载配置信息
	 * @param in -- 重新加载输入流
	 * @param tarStorage -- 配置保存的map
	 * @return -- 最新的配置信息map容器
	 */
	protected abstract Map reload(InputStream in, Map tarStorage);
		
	/**
	 * 初始化
	 */
	public final void initialize() {
		
		logger.info("开始初始化配置文件[{}], 执行类[{}]", this.getSourceURL(), this.getClass().getName());		
		src = getSourceURL();
		if (null == src) {
			String errorMsg = "无法定位配置文件, 配置文件对象为null异常";
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}
		
		protocol = src.getProtocol();
		if ("file".equals(protocol)) { 
			targetSrc = new File(src.getPath()); 
			lastModifiedTime = targetSrc.lastModified();
			FileInputStream fin = null;
			try {
				fin = new FileInputStream(targetSrc);
				props = reload(fin, props);
			} catch (Exception iex) {
				throw new RuntimeException("重新加载配置异常", iex);
			} finally {
				if (null != fin) {
					try { fin.close(); } catch (Exception ioex) { }
				}
			}
		} else if ("jar".equals(protocol)) {
			String[] info = src.getPath().split("\\!");
			targetSrc = new File(info[0].substring(5)); 
			lastModifiedTime = targetSrc.lastModified();
			JarFile jf = null;
			try {
				jf = new JarFile(targetSrc, false, ZipFile.OPEN_READ);
				ZipEntry ze = jf.getEntry(info[1].substring(1));
				jarLastModifiedTime = ze.getTime();
				props = reload(jf.getInputStream(ze), props);
			} catch (Exception ex) {
				throw new RuntimeException("创建jar文件异常", ex);
			} finally {
				if (null != jf) {
					try { jf.close(); } catch (IOException e) { }
				}
			}
		}
			
		long interval = getScanInterval();
		
		// 定义重新加载的任务.
		Runnable task = new Runnable() {

			public void run() { 
				// logger.debug("定时扫描[{}]配置文件调度执行.", ReloadableProperties.this.src);
				try {  
					long lmt = targetSrc.lastModified(); 
					if (0 == lmt) {
						logger.error("URL[{}]不存在!", targetSrc); 
						return;
					}
					// 如果配置文件已经被修改了
					if(lastModifiedTime < lmt || 0 == lastModifiedTime) {
						lastModifiedTime = lmt;
						logger.debug("配置源[{}]发生变化", targetSrc); 	
						if ("file".equals(protocol)) { 
							logger.debug("使用直接文件系统加载配置");
							FileInputStream fin = null;
							try {
								fin = new FileInputStream(targetSrc);
								logger.debug("使用文件流,重新加载配置开始......");
								props = reload(fin, props);
								logger.debug("使用文件流,重新加载配置结束.");
							} catch (Exception iex) {
								logger.error("重新加载配置异常, iex:{}", iex);
							} finally {
								if (null != fin) {
									try { fin.close(); } catch (Exception ioex) { }
								}
							}
						} else if ("jar".equals(protocol)) {
							logger.debug("使用jar文件加载配置");
							String[] info = src.getPath().split("\\!");
							JarFile jf = null;
							try {
								jf = new JarFile(targetSrc, false, ZipFile.OPEN_READ);
								ZipEntry ze = jf.getEntry(info[1].substring(1));
								long jlmt = ze.getTime();
								if (jarLastModifiedTime < jlmt || 0 == jarLastModifiedTime) {
									jarLastModifiedTime = jlmt;
									logger.debug("使用jar文件ZipEntry流,重新加载配置开始......");
									props = reload(jf.getInputStream(ze), props);
									logger.debug("使用jar文件ZipEntry流,重新加载配置结束.");
								}
							} catch (Exception ex) {
								throw new RuntimeException("创建jar文件异常", ex);
							} finally {
								if (null != jf) {
									try { jf.close(); } catch (IOException e) { }
								}
							}
						}
					}
				} catch (Exception ex) {
					logger.error("重新检查配置文件变化调度任务异常. ex:{}", ex);
				}				
				if (timingReload) { // 重新调度
					long interval = getScanInterval();
					service.schedule(this, interval, TimeUnit.MILLISECONDS);
				}
			}				
		};
		service.schedule(task, interval, TimeUnit.MILLISECONDS); 
	}

	public Map<String, Object> getProperties() {
		
		return this.props;
	}
}
