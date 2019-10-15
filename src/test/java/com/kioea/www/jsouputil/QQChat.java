package com.kioea.www.jsouputil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 
 * @author:sekift
 * @time:2014-8-20 下午04:23:07
 * @version:
 */
public class QQChat {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
			Document doc = Jsoup.connect("http://wpa.qq.com/msgrd?v=3&uin=57491978&site=qq&menu=yes")
					.timeout(6000 * 3).get();
			System.out.println(doc);
	}

}
