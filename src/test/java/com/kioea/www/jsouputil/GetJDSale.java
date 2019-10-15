package com.kioea.www.jsouputil;

import org.jsoup.nodes.Element;

import com.kioea.www.jsouputil.JsoupUtil;

/**
 * 获取京东通知栏信息
 * @author:sekift
 * @time:2015-2-6 下午05:05:11
 * @version:
 */
public class GetJDSale {

	/**
	 * @param eles
	 */
	public static void getSale(Element eles){ 
		Element ele = eles.getElementsByTag("li").first();
		System.out.print(ele.select("a[href]"));
		System.out.println(ele.select("span"));
	}
	
	
	public static void main(String[] args) {
		Element eles = JsoupUtil.getByAttrId("http://www.jd.com/moreSubject.aspx", "news_div");
		getSale(eles);
		Element eles1 = JsoupUtil.getByAttrId("http://www.jd.com/moreSubject.aspx", "active_div");
		getSale(eles1);
	}
}
