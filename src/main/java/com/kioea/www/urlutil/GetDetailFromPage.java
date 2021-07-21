package com.kioea.www.urlutil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kioea.www.CloseUtil;
import com.kioea.www.Constant;

/**
 * 提供获取页面邮箱、号码或网址的类
 * 
 * @author:sekift
 * @time:2014-7-8 下午03:38:14
 */
public class GetDetailFromPage {
	public static final Logger logger = LoggerFactory.getLogger(GetDetailFromPage.class);

	/**
	 * 获取邮箱
	 */
	public static List<String> getEmailFromPage(String urlName) {
		return getDetailFromPage(urlName, Constant.regex_ID.EMAIL);
	}

	/**
	 * 获取手机
	 */
	public static List<String> getPhoneFromPage(String urlName) {
		return getDetailFromPage(urlName, Constant.regex_ID.PHONE);
	}

	/**
	 * 获取网页
	 */
	public static List<String> getUrlFromPage(String urlName) {
		return getDetailFromPage(urlName, Constant.regex_ID.URL);
	}

	/**
	 * 分析页面（邮箱、号码、网址）
	 * 
	 * @param urlName
	 * @param type
	 * @return
	 */
	public static List<String> getDetailFromPage(String urlName, String type) {
		InputStreamReader isr = null;
		BufferedReader br = null;
		List<String> list = null;
		try {
			URL url = new URL(urlName);
			isr = new InputStreamReader(url.openStream());
			br = new BufferedReader(isr);
			String st = null;
			list = new ArrayList<String>();
			while ((st = br.readLine()) != null) {
				Matcher m = Pattern.compile(type).matcher(st);
				while (m.find()) {
					list.add(m.group());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[sekiftutil]分析页面出错：", e);
		} finally {
			CloseUtil.closeSilently(br);
			CloseUtil.closeSilently(isr);
		}
		System.out.println(list);
		return list;
	}
}
