package com.kioea.www.jsouputil;

import com.kioea.www.urlutil.URLConnectUtil;

/**
 * 
 * @author:sekift
 * @time:2015-2-6 上午11:29:58
 * @version:
 */
public class SOFJsoupTest {

	public static void main(String[] args) {
		for (int i = 11; i < 22; i++) {
			String url = "http://stackoverflow.com/questions/" + i;
			System.out.println(i + " : " + URLConnectUtil.getStatus(url));
		}
//		String url = "http://stackoverflow.com/questions/4";
//		Document doc = JsoupUtil.getDocByConnect(url);
//		System.out.println(doc);
	}

}