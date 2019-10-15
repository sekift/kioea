package com.kioea.www.photoutil.photoprocess.util;

/**
 * 
 * @author:Administrator
 * @time:2015-12-29 上午10:27:56
 * @version:
 */
public class ProcessUtil {
	
	/**
	 * 归一化处理 从0-255
	 * @param value
	 * @return
	 */
	public static int clamp(int value) {
		return value < 0 ? 0 : (value > 255 ? 255 : value);
	}

}
