package com.kioea.www.encryptutil;

/**
 * 
 * @author:sekift
 * @time:2019-4-8 上午09:46:25
 * @version:
 */
public class JSBase64 {
	private static final String _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

	/**
	 * 定义加密方法
	 * 
	 * @param input
	 * @return
	 */
	public static String encode(String input) {
		StringBuilder output = new StringBuilder();
		int chr1 = 0, chr2 = 0, chr3 = 0, enc1 = 0, enc2 = 0, enc3 = 0, enc4 = 0;
		int i = 0;
		input = encodeUtf8(new StringBuilder(input)).toString();
		while (i < input.length()) {
			try {
				chr1 = (char) input.codePointAt(i++);
			} catch (Exception e) {
				chr1 = 0;
			}

			try {
				chr2 = (char) input.codePointAt(i++);
			} catch (Exception e) {
				chr2 = 0;
			}

			try {
				chr3 = (char) input.codePointAt(i++);
			} catch (Exception e) {
				chr3 = 0;
			}
			enc1 = (chr1 >> 2);
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (chr2 == 0) {
				enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
				enc3 = enc4 = 64;
			} else if (chr3 == 0) {
				enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
				enc4 = 64;
			}
			output.append(_keyStr.charAt(enc1)).append(_keyStr.charAt(enc2)).append(_keyStr.charAt(enc3)).append(
					_keyStr.charAt(enc4));
		}
		return output.toString();
	}

	/**
	 * 定义解密的方法
	 * 
	 * @param input
	 * @return
	 */
	public static String decode(String input) {
		StringBuilder output = new StringBuilder();
		char enc1, enc2, enc3, enc4;
		String chr1, chr2, chr3;
		int i = 0;
		input = input.replace("", "");
		while (i < input.length()) {
			enc1 = (char) _keyStr.indexOf(input.charAt(i++));
			enc2 = (char) _keyStr.indexOf(input.charAt(i++));
			enc3 = (char) _keyStr.indexOf(input.charAt(i++));
			enc4 = (char) _keyStr.indexOf(input.charAt(i++));
			chr1 = String.valueOf((char) ((enc1 << 2) | (enc2 >> 4)));
			chr2 = String.valueOf((char) (((enc2 & 15) << 4) | (enc3 >> 2)));
			chr3 = String.valueOf((char) (((enc3 & 3) << 6) | enc4));
			output.append(String.valueOf(chr1));
			if (enc3 != 64) {
				output.append(String.valueOf(chr2));
			}
			if (enc4 != 64) {
				output.append(String.valueOf(chr3));
			}
		}
		return encodeUtf8(output).toString();
	}

	/**
	 * utf-8处理
	 * 
	 * @param str
	 * @return
	 */
	public static StringBuilder encodeUtf8(StringBuilder str) {
		// str = str.replace("\r\n", "\n");
		StringBuilder utfText = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c < 128) {
				utfText.append(String.valueOf(c));
			} else if (c >= 128 && c < 2048) {
				utfText.append(String.valueOf(c >> 6));
				utfText.append(String.valueOf(c & 63));
			} else {
				utfText.append(String.valueOf(c >> 12));
				utfText.append(String.valueOf(((c >> 6) & 63) | 128));
				utfText.append(String.valueOf((c & 63) | 128));
			}
		}
		return utfText;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 加密
		String str = "tlist=tl=12980,1550626663,1,86400&tc=4";
		System.out.println("加密前：" + str);
		System.out.println("加密后：" + encode(str));
		System.out.println("解密后：" + decode(encode(str)));

	}

}
