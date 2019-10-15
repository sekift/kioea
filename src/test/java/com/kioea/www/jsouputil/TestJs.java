package com.kioea.www.jsouputil;

import org.jsoup.nodes.Document;

import com.kioea.www.jsouputil.JsoupUtil;

/**
 * 
 * @author:luyz
 * @time:2017-8-28 上午09:59:15
 * @version:
 */
public class TestJs {
	public static void main(String args[]){
		Document result = JsoupUtil.getDocByConnect("http://www.qiushibaike.com/8hr/page/1/");
		System.out.println(result);
		
		
	}

}
