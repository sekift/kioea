package com.kioea.www.stringutil;

/**
 * char[]与 string[]、string之间的互相转换
 * @author:sekift
 * @time:2014-7-31 上午10:12:14
 * @version:
 */
public class StringCharge {

	/**
	 * string2ascii
	 * @param str
	 * @return
	 */
	public static int string2ascii(String str) {
		return (int)StringCharge.string2char(str);
	}

	/**
	 * ascii2string
	 * @param str
	 * @return
	 */
	public static String ascii2string(int number){
		return String.valueOf((char)number);
	}
	
	public static char byte2char(byte[] b) {
		char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
		return c;
	}

	public static byte[] char2byte(char c) {
		byte[] b = new byte[2];
		b[0] = (byte) ((c & 0xFF00) >> 8);
		b[1] = (byte) (c & 0xFF);
		return b;
	}

	/**
	 * chararray2string
	 * @param ch
	 * @return
	 */
	public static String chararray2string(char[] ch) {
		return new String(ch);
	}

	/**
	 * string2chararray
	 * @param str
	 * @return
	 */
	public static char[] string2chararray(String str) {
		return str.toCharArray();
	}

	/**
	 * stringarray2string
	 * @param str
	 * @return
	 */
	public static String stringarray2string(String[] str) {
		StringBuffer sb = new StringBuffer();
		for (String s : str) {
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * string2stringarray
	 * @param str
	 * @return
	 */
	public static String[] string2stringarray(String str) {
		return chararray2stringarray(string2chararray(str));

	}

	/**
	 * chararray2stringarray
	 * @param ch
	 * @return
	 */
	public static String[] chararray2stringarray(char[] ch) {
		String[] str = new String[ch.length];
		for (int i = 0; i < str.length; i++) {
			str[i] = ch[i] + "";
		}
		return str;
	}

	/**
	 * stringarray2chararray
	 * @param str
	 * @return
	 */
	public static char[] stringarray2chararray(String[] str) {
		return string2chararray(stringarray2string(str));
	}

	/**
	 * char2string
	 * @param ch
	 * @return
	 */
	public static String char2string(char ch) {
		return String.valueOf(ch);
	}

	/**
	 * string2char
	 * @param str
	 * @return
	 */
	public static char string2char(String str) {
		return (char) str.charAt(0);
	}
}
