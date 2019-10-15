package com.kioea.www.regexutil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kioea.www.stringutil.StringUtil;

/**
 * @author 作者:sekift
 * @author E-mail:sekiftlyz@gmail.com
 * @version 创建时间：2014-8-9 上午01:05:12 
 * 类说明:[正则表达式类]
 * 正则总览：^([^]{}?*+|)$
 */
public class PatternUtil {

	/**
	 * 匹配输入的Object
	 * 
	 *   返回是否匹配
	 * 
	 * @param content
	 * @param constants
	 * @return boolean
	 */
	public static boolean patternAll(Object content, String constants) {
		String str = object2string(content);
		if (!StringUtil.hasText(str)) {
			return false;
		}
		return str.matches(constants);
	}

	/**
	 * 匹配输入的String
	 * 
	 *   返回匹配的项
	 * 
	 * @param content
	 * @param type
	 * @return List
	 */
	public static List<String> patternAllByList(Object content, String type) {
		String str = object2string(content);
		List<String> list = null;
		list = new ArrayList<String>();
		Matcher m = Pattern.compile(type).matcher(str);
		while (m.find()) {
			list.add(m.group());
		}
		return list;
	}

	/**
	 * toString
	 * 
	 *    将Object转换成String
	 * @param content
	 * @return
	 */
	public static String object2string(Object content) {
		return String.valueOf(content);
	}

}
