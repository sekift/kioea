package com.kioea.www.configutil;

import java.util.LinkedList;

/**
 * 
 * @author:sekift
 * @time:2014-8-15 下午03:59:26
 * @version:
 */
public class Test {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// LinkedList<String> list = (LinkedList<String>)
		// ConfigUtil.getConfigValue("proxool");
		for (int i = 0; i < 20; i++) {
			System.out.println((LinkedList<String>) ConfigUtil.getConfigValue("proxool"));
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
