package com.kioea.www.urlutil;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author:Administrator
 * @time:2015-7-28 下午04:00:42
 * @version:
 */
public class GetDomainUtil {

	/**
	 * 获取域名
	 *   有问题
	 * @param urlString
	 * @return
	 */
	public static String getDomainName(String urlString) {
		if (urlString == null)
			return "";

		String url = urlString;
		if (url.indexOf("http://") > -1)
			url = url.replace("http://", "");

		if (url.indexOf("https://") > -1)
			url = url.replace("https://", "");

		int j = url.indexOf("/");
		if (j > -1) {
			url = url.substring(0, j);
		}
		if (url.length() > 50) {
			url = url.substring(0, 50);
		}
		return url;
	}

	/**
	 * 获取根域名
	 * 
	 * @param domain
	 *            域名
	 * @return 根域名
	 */
	private static String getRootDomain(String domain) {
		URL url = null;
		try {
			url = new URL(domain);
			domain = url.getHost();
		} catch (MalformedURLException e) {
			return domain;
		}
		url = null;
		String[] strs = domain.split("[.]");
		int len = strs.length;
		if (len > 2) {
			return strs[len - 2] + "." + strs[len - 1];
		} else {
			return domain;
		}
	}

	public static void main(String args[]) {
		System.out.println(getDomainName("http://www.baidu.com"));
		System.out.println(getDomainName("http://www.baidu.com:8080/fef/fefe/"));
		System.out.println(getDomainName("http://www.baidu.com?fefe=fefe&aew=123"));
		System.out.println(getDomainName("http://abc.age.baidu.com/fef/fefe.do?fe=33&e4e=234"));
		System.out.println(getDomainName("ftp://abc.baidu.com/fef/fefe.do?fe=33&e4e=234"));
		System.out.println(getDomainName("www.baidu.com/fef/fefe.do?fe=33&e4e=234"));
		System.out.println();
		System.out.println(getRootDomain("http://www.baidu.com:8080/fef/fefe.do?fe=33&e4e=234"));
		System.out.println(getRootDomain("http://www.baidu.com/fef/fefe.do?fe=33&e4e=234"));
		System.out.println(getRootDomain("http://www.baidu.com/fef/fefe.do?fe=33&e4e=234"));
		System.out.println(getRootDomain("http://www.baidu.com/fef/fefe.do?fe=33&e4e=234"));
		System.out.println(getRootDomain("https://www.baidu.com/fef/fefe.do?fe=33&e4e=234"));
		System.out.println(getRootDomain("ftp://abc.baidu.com/fef/fefe.do?fe=33&e4e=234"));
		System.out.println(getRootDomain("www.baidu.com/fef/fefe.do?fe=33&e4e=234"));
	}

}
