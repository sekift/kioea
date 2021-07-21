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

	/**
	 * 使用数字，22个大写英文字母(不包含 ILOU 字符)
	 * https://tools.ietf.org/html/rfc4648
	 */
	private static final char[] DIGITS_32 = "0123456789ABCDEFGHJKMNPQRSTVWXYZ"
			.toCharArray();

	private static final int DIGITS32_LENGTH = DIGITS_32.length;

	/**
	 * long类型转为32进制，指定了使用的字符，参考Long.toUnsignedString0
	 *
	 * @param val
	 * @return
	 */
	private static String digits32(long val) {
		// 32=2^5=二进制100000
		int shift = 5;
		// numberOfLeadingZeros 获取long值从高位连续为0的个数，比如val=0，则返回64
		// 此处mag=long值二进制减去高位0之后的长度
		int mag = Long.SIZE - Long.numberOfLeadingZeros(val);
		int len = Math.max(((mag + (shift - 1)) / shift), 1);
		char[] buf = new char[len];
		do {
			// &31相当于%32
			buf[--len] = DIGITS_32[((int) val) & 31];
			val >>>= shift;
		} while (val != 0 && len > 0);
		return new String(buf);
	}

	/**
	 * 32进制转换为long类型
	 *
	 * @param val
	 * @return
	 */
	private static Long convert32(String val) {
		char[] num = val.toCharArray();
		int numLen = num.length;
		long res = 0L;
		int flag, i, j;
		for (i = 0; i < numLen; i++) {
			flag = 1;
			for (j = 0; j < DIGITS32_LENGTH; j++) {
				if (DIGITS_32[j] == num[i]) {
					Double powD = Math.pow(DIGITS32_LENGTH, numLen - 1 - i);
					res += j * powD.longValue();
					flag = 0;
				}
			}
			if (flag == 1) {
				break;
			}
		}
		return res;
	}

}
