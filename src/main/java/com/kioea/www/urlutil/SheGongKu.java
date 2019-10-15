package com.kioea.www.urlutil;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 社工库查询
 * @author:sekift
 * @time:2015-3-25 下午04:52:14
 * @version:
 */
public class SheGongKu {
	public static String getSheGongKu(String key, int page, boolean encode, boolean showPage) {
		// http://shegongku.us/index.php/Index/search/key/%E6%96%87%E5%B3%B0/vcode/p/15
		StringBuffer url = null;
		url = new StringBuffer("http://shegongku.us/index.php/Index/search/key/" + key);
		if (page <= 1) {
			url = url.append("/vcode/");
		} else if (page > 1) {
			url = url.append("/p/" + (page - 1) * 15);
		}
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "text/html, */*; q=0.01");
		headers.put("Accept-Encoding", "gzip,deflate,sdch");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8");
		headers.put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36");
		headers.put("X-Requested-With", "XMLHttpRequest");
		headers.put("Connection", "keep-alive");
		headers.put("Cookie",
				"__cfduid=dd75846bcd7a6e696e240229aa138b9681427266229; PHPSESSID=27hhb1nrbm40kmt7j6rv6v0at2");
		headers.put("Host", "shegongku.us");
		headers.put("Referer", "http://shegongku.us/");
		String response = null;
		response = HttpUtil.get(url.toString(), null, headers, 10 * 3600, 10 * 3600, "utf-8");
		if (encode == true) {
			StringBuffer sb = new StringBuffer();
			Document doc = Jsoup.parse(response);
			Elements eles = doc.getElementsByTag("td");

			if (showPage == true) {
				Elements links = doc.getElementsByTag("a");
				for (Element link : links) {
					// System.out.println(link.attr("href"));
					sb.append(link.attr("href") + "\r\n");
				}
			}
			for (Element ele : eles) {
				if (!ele.text().equals("下一页")) {
					sb.append(ele.text() + "\r\n");
				}
			}
			return sb.toString();
		}
		return response;
	}

}
