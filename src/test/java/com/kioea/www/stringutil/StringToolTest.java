package com.kioea.www.stringutil;

import com.kioea.www.stringutil.StringTool;

/**
 * 
 * @author:luyz
 * @time:2016-7-13 上午11:10:59
 * @version:
 */
public class StringToolTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "aaaacccabccc";
		System.out.println(StringTool.matchCount("aa", str));
	}

}
