package com.kioea.www.textutil;

import org.ansj.splitWord.analysis.ToAnalysis;

/**
 * 
 * @author:sekift
 * @time:2014-8-13 下午03:45:03
 * @version:
 */
public class Ansj_seg {
	public static void main(String args[]) {
		String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!";
		System.out.println(ToAnalysis.parse(str));

	}
}
