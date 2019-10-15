package com.kioea.www.urlutil;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author:Administrator
 * @time:2015-9-7 上午10:31:05
 * @version:
 */
public class SMSSender {

	protected Logger logger = Logger.getLogger(SMSSender.class);

	private static String spCode = "221816";
	private static String username = "admin2";
	private static String password = "hrt150605";

	protected static Properties pro = new Properties();
	static {
		Properties pro = new Properties();
		pro.put("log4j.rootLogger", "DEBUG,C,R,A");

		pro.put("log4j.appender.C", "org.apache.log4j.ConsoleAppender");
		pro.put("log4j.appender.C.Threshold", "INFO");
		pro.put("log4j.appender.C.layout", "org.apache.log4j.PatternLayout");
		pro.put("log4j.appender.C.layout.ConversionPattern", "%n %m");

		pro.put("log4j.appender.R", "org.apache.log4j.RollingFileAppender");
		pro.put("log4j.appender.R.File", "d:\\logs\\fileLog.log");
		pro.put("log4j.appender.R.MaxFileSize", "10000KB");
		pro.put("log4j.appender.R.MaxBackupIndex", "20");
		pro.put("log4j.appender.R.Threshold", "INFO");
		pro.put("log4j.appender.R.layout", "org.apache.log4j.PatternLayout");
		pro.put("log4j.appender.R.layout.ConversionPattern", "%n[%d{HH:mm:ss}] [%p] %m");

		pro.put("log4j.appender.A", "org.apache.log4j.RollingFileAppender");
		pro.put("log4j.appender.A.File", "d:\\logs\\fileLog2.log");
		pro.put("log4j.appender.A.MaxFileSize", "10000KB");
		pro.put("log4j.appender.A.MaxBackupIndex", "20");
		pro.put("log4j.appender.A.Threshold", "DEBUG");
		pro.put("log4j.appender.A.layout", "org.apache.log4j.PatternLayout");
		pro.put("log4j.appender.A.layout.ConversionPattern", "%n[%d{HH:mm:ss}] [%p] %m");
		PropertyConfigurator.configure(pro);
	}

	public static void main(String[] args) {

		String content = "你的注册手机为{13660708603}恩人{13660708603}你好,你已经成功注册为何人堂创意产品网站会员.";
		String phone = "13660708603";

		String result = querySMSLeaveNum();
		String sendResult = sendSMS(content, phone);
		String reportResult = queryCallbackMsg();

	}

	/**
	 * 发短信
	 * 
	 * @param content
	 * @return
	 */
	public static String sendSMS(String content, String target) {

		String info = null;
		try {
			HttpClient httpclient = new HttpClient();
			PostMethod post = new PostMethod("http://gd.ums86.com:8899/sms/Api/Send.do");//
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "gbk");
			post.addParameter("SpCode", spCode);
			post.addParameter("LoginName", username);
			post.addParameter("Password", password);
			post.addParameter("MessageContent", content);
			post.addParameter("UserNumber", target);
			post.addParameter("SerialNumber", "");
			post.addParameter("ScheduleTime", "");
			post.addParameter("ExtendAccessNum", "");
			post.addParameter("f", "1");
			httpclient.executeMethod(post);
			info = new String(post.getResponseBody(), "gbk");
			System.out.println(info);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询剩余短信数量
	 * 
	 * @return
	 */
	public static String querySMSLeaveNum() {
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod("http://gd.ums86.com:8899/sms/Api/SearchNumber.do");//
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "gbk");
		post.addParameter("SpCode", spCode);
		post.addParameter("LoginName", username);
		post.addParameter("Password", password);

		try {
			httpclient.executeMethod(post);
			String info = new String(post.getResponseBody(), "gbk");
			System.out.println(info);
			return info;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
     *
     */

	public static String queryCallbackMsg() {
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod("http://gd.ums86.com:8899/sms/Api/report.do");//
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "gbk");
		post.addParameter("SpCode", spCode);
		post.addParameter("LoginName", username);
		post.addParameter("Password", password);

		try {
			httpclient.executeMethod(post);
			String info = new String(post.getResponseBody(), "gbk");
			System.out.println(info);
			return info;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

}
