package com.kioea.www.jsonutil;

import java.util.HashMap;
import java.util.Map;

/**
 * JSON响应对象
 * 
 * @author
 * @date
 */
public class JsonResult {

	/**
	 * 创建实例
	 * 
	 * @param response
	 *            Json响应
	 * @return
	 */
	public static JsonResult newInstance(String result) {
		return new JsonResult(result);
	}

	/**
	 * 数据Map
	 */
	private Map<Object, Object> dataMap = null;

	/**
	 * 结果代码
	 */
	private int countKey = 0;

	public Map<Object, Object> getDataMap() {
		return dataMap;
	}

	public int getCountKey() {
		return countKey;
	}

	public int getCountValue() {
		return countValue;
	}

	public String getResult() {
		return result;
	}

	/**
	 * 结果信息
	 */
	private int countValue = 0;

	/**
	 * Json响应
	 */
	private String result = null;

	/**
	 * 构造函数
	 */
	private JsonResult() {
	}

	/**
	 * 构造函数，初始化Json响应
	 * 
	 * @param response
	 */
	private JsonResult(String result) {
		this.result = result;
		parseResult();
	}

	/**
	 * 解析Json响应
	 */
	@SuppressWarnings("unchecked")
	private void parseResult() {

		// 判断是否为空
		if (result == null || "".equals(result.trim())) {
			return;
		}

		try {
			// 将Json字符串转成Map
			Map<Object, Object> responseMap = JsonUtils.toBean(result, HashMap.class);

			// 获取countKey
			if (responseMap.containsKey("countKey")) {
				countKey = (Integer) responseMap.get("countKey");
			}

			// 获取countValue
			if (responseMap.containsKey("countValue")) {
				countValue = (Integer) responseMap.get("countValue");
			}

			// 获取data
			if (responseMap.containsKey("data")) {
				dataMap = (Map<Object, Object>) responseMap.get("data");
			}

		} catch (Exception e) {
		}
	}

}
