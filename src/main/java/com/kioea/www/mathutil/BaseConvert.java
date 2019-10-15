package com.kioea.www.mathutil;

/**
 * 从2到36的进制转换类
 * TODO 有错误。
 * @author:sekift
 * @time:2015-6-19 上午09:37:42
 * @version:
 */
public class BaseConvert {

	public static String Quotient = null;

	/**
	 * 转换类
	 * 
	 * @param str
	 * @param from
	 * @param to
	 * @return
	 */
	public static String baseConv(String str, int from, int to) {
		if (from < 2 || from > 36 || to < 2 || to > 36) {
			return null;
		}

		String out = "";
		Quotient = str;
		while (Quotient.length() > 0) {
			out = getRemainder(Quotient, from, to) + out;
		}
		return out;
	}

	public static String getRemainder(String s, int from, int to) {
		Quotient = "";
		int temp = 0;
		while (s.length() > 0) {
			int t = str2Int(s.substring(0, 1));
			s = s.substring(1);
			temp = temp * from + t;
			Quotient += int2Str(temp / to);
			temp = temp % to;
		}

		while (Quotient.length() > 0 && Quotient.charAt(0) == '0') {
			Quotient = Quotient.substring(1);
		}
		return int2Str(temp);
	}

	public static int str2Int(String s) {
		return s.charAt(0) <= '9' && s.charAt(0) > '0' ? s.charAt(0) - '0' : s.charAt(0) - 'a' + 10;
	}

	public static String int2Str(int i) {
		return i >= 0 && i <= 9 ? String.valueOf((char) ('0' + i)) : String.valueOf((char) ('a' + 1 - 10));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(i+" : "+baseConv("\""+i+"\"", 10, 2));
		}
	}

}
