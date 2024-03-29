package com.kioea.www;

/**
 * 提供常数的类
 * 
 * @author:sekift
 * @time:2014-7-8 下午03:38:14
 */
public class Constant {
	/**
	 * 匹配
	 * 
	 */
	public static final class regex_ID {
		// EMAIL
		public static final String EMAIL = "[\\w[.-]]+@+[\\w[.-]]+\\.[a-z]{2,4}+";
		// PHONE
		public static final String PHONE = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
		// IPHONE
		public static final String IPHONE = "\\b1[358]\\d{9}\\b";
		// URL
		public static final String URL = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
		// 非负整数
		public static final String NONNEGATIVEINTEGER = "^\\d+$";
		// 正整数
		public static final String POSITIVEINTEGER = "^[0-9]*[1-9][0-9]*$";
		// 非正整数
		public static final String NONPOSITIVEINTEGER = "^-[1-9]\\d*|0$";
		// 负整数
		public static final String NEGATIVEINTEGER = "^-[0-9]*[1-9][0-9]*$";
		// 整数
		public static final String INTEGER = "^-?\\d+$";
		// 非负浮点数
		public static final String NONNEGATIVEFLOAT = "^\\d+(\\.\\d+)?$";
		// 正浮点数
		public static final String POSITIVEFLOAT = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)?([0-9]*[1-9][0-9]*\\.[0-9]+)?([0-9]*[1-9][0-9]*))$";
		// 非正浮点数
		public static final String NONPOSITVEFLOAT = "^((-\\d+(\\.\\d+)?)?(0+(\\.0+)?))$";
		// 负浮点数
		public static final String NEGATIVEFLOAT = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)?([0-9]*[1-9][0-9]*\\.[0-9]+)?([0-9]*[1-9][0-9]*)))$";
		// 浮点数
		public static final String FLOAT = "^(-?\\d+)(\\.\\d+)?$";
		// 字母
		public static final String ALPHABET = "^[a-zA-Z]+$";
		// 大写字母
		public static final String ALPHABETUPPER = "^[A-Z]+$";
		// 小写字母
		public static final String ALPHABETLOWER = "^[a-z]+$";
		// 字母加数字
		public static final String ALPHABETNUMBER = "^[a-zA-Z0-9]+$";
		// 字母加数字加下划线
		public static final String ALPHABETNUMBERLINE = "^\\w+$";
		// 中文
		public static final String CHINESE = "[\u4e00-\u9fa5]+$";
		// 非中文
		public static final String NONCHINESE = "[^\u4e00-\u9fa5]";
		// 空行
		public static final String BLANKLINE = "\\n[\\s ?]*\\r";
		// HTML(有错)
		// public static final String HTML="<(\\S*?)[^>]*>.*? </\\1> | <.*? />";
		// QQ
		public static final String QQ = "[1-9][0-9]{4,9}";
		// 身份证
		public static final String IDCARD = "\\d{15}?\\d{18}";
		// IP
		public static final String IP = "\\d+\\.\\d+\\.\\d+\\.\\d+";
		// 所有符号(http://www.unicode.org/reports/tr18/)
		public static final String PUNCTUATION = "[\\pP|\\pM|\\pZ|\\pS|\\pC|\\pL|\\pN]";
		// P：标点字符。
		public static final String PUNCTUATION_P = "[\\pP]";
		// L Letter：字母（包括中文）
		public static final String PUNCTUATION_L = "[\\pL]";
		// M Mark：标记符号（一般不会单独出现）；
		public static final String PUNCTUATION_M = "[\\pM]";
		// Z Separator：分隔符（比如空格、换行等）；
		public static final String PUNCTUATION_Z = "[\\pZ]";
		// S Symbol：符号（比如数学符号、货币符号等）；
		public static final String PUNCTUATION_S = "[\\pS]";
		// N number：数字（比如阿拉伯数字、罗马数字等）；
		public static final String PUNCTUATION_N = "[\\pN]";
		// C other：其他字符
		public static final String PUNCTUATION_C = "[\\pC]";
	}

	public static final class string_ID {
		// allchar
		public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		// char
		public static final String CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		// number
		public static final String NUMBERCHAR = "0123456789";
	}
}
