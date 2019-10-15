package com.kioea.www.urlutil;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * qiushibaike
 * 
 * @author:sekift
 * @time:2015-12-22 下午04:52:14
 * @version:
 */
public class qiushibaike {
	public static String getSheGongKu(int page, boolean encode, boolean showPage) {
		// http://shegongku.us/index.php/Index/search/key/%E6%96%87%E5%B3%B0/vcode/p/15
		StringBuffer url = null;
		url = new StringBuffer("http://www.qiushibaike.com/textnew");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "text/html, */*; q=0.01");
		headers.put("Accept-Encoding", "gzip,deflate,sdch");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8");
		headers
				.put("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36");
		headers.put("X-Requested-With", "XMLHttpRequest");
		headers.put("Connection", "keep-alive");
		headers
				.put(
						"Cookie",
						"_qqq_uuid_=\"2|1:0|10:1450774611|10:_qqq_uuid_|56:NWQ2ZTk2YTIwZWJlODZiNWFlM2Y2ZTc4YzIxOGVhMzJjZWM1OTMwZA"
								+ "==|1b5d7b6facba9c949f37c17dbbd78e5bbeb51c27cd699c0e5622d61a7d808050\"; gr_user_id=cf430ac1-b448-48d1-"
								+ "851b-5dd71cc0b43f");
		headers.put("Host", "www.qiushibaike.com");
		headers.put("Referer", "http://www.qiushibaike.com/");
		String response = null;
		response = HttpUtil.get(url.toString(), null, headers, 10 * 3600, 10 * 3600, "utf-8");
		if (encode == true) {
			StringBuffer sb = new StringBuffer();
			Document doc = Jsoup.parse(response);
			Elements eles = doc.getElementsByClass("content");
			for(Element ele:eles){
				System.out.println(ele.text());
			}
			return sb.toString();
		}
		return response;
	}
	
	public static void main(String args[]){
		getSheGongKu(1,true,true);
	}

}
