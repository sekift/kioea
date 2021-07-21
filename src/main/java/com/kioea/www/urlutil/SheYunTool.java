package com.kioea.www.urlutil;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * sheyun.org
 * 
 * @author:Administrator
 * @time:2015-9-21 下午05:33:17
 * @version:
 */
public class SheYunTool {
	public static Map<String, String> headers = new HashMap<String, String>();

	static {
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:37.0) Gecko/20100101 Firefox/37.0");
		headers.put("X-Requested-With", "XMLHttpRequest");
		headers.put("Cache-Control", "max-age=0");
		headers.put("Connection", "keep-alive");
		headers.put("Cookie", "__cfduid=d4854317800bbd46031be9a94170210381441616865; "
				+ "visid_incap_592155=L+Y+yR2JT3W4IzlykNwulOFT7VUAAAAAQUIPAAAAAAAr80wX9dbCyEJCfVgXE4DO; "
				+ "incap_ses_32_592155=Oz4MccCMRnIk8juJDLBxAO1b7VUAAAAA85v7hx4zsu6yp1EQH3hT6w==; "
				+ "incap_ses_224_592155=roazYiKF+CgUVeUxstAbA9xY7VUAAAAA271HtNFVMlXl60MesEV+gw==; "
				+ "yunsuo_session_verify=c09a1402152c9f0830d");
		// headers.put("Host", "api.sheyun.org");
		headers.put("Host", "www.sheyun.org");
		headers.put("Referer", "http://www.sheyun.org/qun.html");
	}

	/**
	 * http://api.sheyun.org/api.php?so=需要查询的关键词 so=313143468 so：搜索字符串
	 */
	public static String getMessage(String key) {
		String url = "http://api.sheyun.org/api.php";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("so", key);
		String response = HttpUtil.get(url, params, headers, 10 * 3600, 10 * 3600, "utf-8");
		return response;
	}

	/**
	 * 开房记录 search=313143468&vip=1&act=1 search：搜索字符串；vip：vip；act：是qq号还是群号
	 */
	public static String getKFResult(String key) {
		String url = "http://www.sheyun.org/kf.php";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("search", key);
		params.put("vip", 1);
		String response = HttpUtil.post(url, params, headers, 10 * 3600, 10 * 3600, "utf-8");
		return response;
	}

	/**
	 * 获取群关系，isGroups为true表示搜索群 search=313143468&vip=1&act=1
	 * search：搜索字符串；vip：vip；act：是qq号还是群号
	 */
	public static String getQQGroupsRelationship(String key, boolean isGroups) {
		String url = "http://www.sheyun.org/qun.php";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("search", key);
		params.put("vip", 1);
		params.put("act", 1);
		if (isGroups) {
			params.put("act", 2);
		}
		String response = HttpUtil.post(url, params, headers, 10 * 3600, 10 * 3600, "utf-8");
		return response;
	}

	public static String getKFResultFilterByJsoup(String key) {
		String response = getKFResult(key);
		Document doc = getDoc(response);
		return doc.select("tr").text();
	}

	public static String getQQGroupsRelationshipFilterByJsoup(String key, boolean isGroups) {
		String response = getQQGroupsRelationship(key, isGroups);
		Document doc = getDoc(response);
		return doc.select("tr td:eq(1)").text();
	}

	private static Document getDoc(String response) {
		String head = "<html><head></head><body>";
		String footer = "</body></html>";
		String html = head + response + footer;
		Document doc = Jsoup.parse(html);
		return doc;
	}

	public static void main(String[] args) {
		System.out.println(getQQGroupsRelationshipFilterByJsoup("675956", false));
		System.out.println(getKFResultFilterByJsoup("卢宇"));
		System.out.println(getMessage("574919797"));
	}

}
