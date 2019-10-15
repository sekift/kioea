package com.kioea.www.configutil;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kioea.www.constructeutil.MapUtil;

public class ConfigUtil {

	private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
	
	/**
	 * 应用配置
	 */
	private static XmlProperties appConfig = null;

	/**
	 * 初始化操作
	 */
	static {

		// 加载应用配置数据
		appConfig = new XmlProperties();
		appConfig.setSourceURL(ConfigUtil.class
				.getResource("/config/proxool.xml"));
		appConfig.setTimingReload(true);
		appConfig.initialize();
	}

	/**
	 * 获取应用配置值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getConfigValue(String key) {
		Object result = null;
		try {
			if (appConfig != null) {
				result = appConfig.getValue(key);
			}
		} catch (Exception e) {
			logger.error("获取应用配置值出错！", e);
		}
		return result;
	}

	/**
	 * 获取二级结点配置值
	 * 
	 * @param item
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getConfigValue(String item, String key,
			String defaultValue) {
		String result = null;
		try {
			Map<?, ?> map = (Map<?, ?>) ConfigUtil.getConfigValue(item);
			result = MapUtil.getParameter(map, key, defaultValue);
		} catch (Exception e) {
			logger.error("获取二级结点配置值出错！", e);
		}
		return result;
	}
	
	/**
	 * 获取二级结点配置值INT
	 * 
	 * @param item
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getIntConfigValue(String item, String key,
			int defaultValue) {
		int result = defaultValue;
		try {
			Map<?, ?> map = (Map<?, ?>) ConfigUtil.getConfigValue(item);
			result = MapUtil.getIntParameter(map, key, defaultValue);
		} catch (Exception e) {
			logger.error("获取二级结点配置值出错！", e);
		}
		return result;
	}
}
