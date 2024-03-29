package com.kioea.www.encryptutil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密有关的工具类
 * 
 * @author:sekift
 * @time:2015-3-10 下午03:50:31
 * @version:
 */
public class EncryptMD5 {

	public static String encryptMD5(String content, String charset) {
		StringBuilder result = new StringBuilder();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(content.getBytes(charset));
			byte[] byteDigest = md.digest();
			for (byte b : byteDigest) {
				String tmpStr = Integer.toHexString(0xFF & b);
				if (tmpStr.length() == 1) {
					result.append("0").append(tmpStr);
				} else {
					result.append(tmpStr);
				}
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Charset not supported", e);
		}
		return result.toString();
	}

}
