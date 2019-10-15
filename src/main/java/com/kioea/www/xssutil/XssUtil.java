package com.kioea.www.xssutil;

/**
 * 
 * @author:sekift
 * @time:2015-12-3 上午10:01:59
 * @version:
 */
public class XssUtil {
	public static String cleanXSS(String value) {
		if (value == null) {
			return null;
		}
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
		return value;
	}

}
