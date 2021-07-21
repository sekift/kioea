package com.kioea.www.urlutil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 * 
 * 
 * @author:Administrator
 * @time:2015-9-6 上午09:49:39
 * @version:
 */
public class SendMessageTool {
	//baidu apikey
	private static final String apikey="7a54976c51e9ebca1cf76045a15d2e01";
	/**
	 * http://apistore.baidu.com/apiworks/servicedetail/832.html 
	 * 天下畅通 秒到验证码服务
	 * 需要用户名和密码，要钱的
	 * @param mobileNumber
	 * @param content
	 * @return
	 */
	public static String sendMessageByTxct(String moblie, String content) {
		// 接口地址
		String url = "http://apis.baidu.com/txct/txct/tianxiachangtong";
		Map<String, String> headers = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<String, Object>();
		headers.put("apikey", apikey);
		params.put("mobile", moblie);
		params.put("content", content);

		return HttpUtil.get(url, params, headers, 5*1000, 3 * 60 * 1000, "UTF-8");
	}
	
	/**
	 * http://apistore.baidu.com/apiworks/servicedetail/1018.html
	 * 凯途传媒 106短信服务
	 * 无需账号密码，但是短信要有【xxx】签名。不稳定
	 *  短信内容采用：您本次的验证码为：1234(数字要每次变化),有效期为5分钟，收到请告知，谢谢！
	 * @param mobileNumber
	 * @param content
	 * @return
	 */
	public static String sendMessageByKingtto(String moblie,String title,String content){
		// 接口地址
		String url = "http://apis.baidu.com/kingtto_media/106sms/106sms";
		Map<String, String> headers = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<String, Object>();
		headers.put("apikey", apikey);
		params.put("mobile", moblie);
		params.put("content", title+content);

		return HttpUtil.get(url, params, headers, 5*1000, 3 * 60 * 1000, "UTF-8");
	}
	
	/**
	 * http://apistore.baidu.com/apiworks/servicedetail/715.html
	 * 安徽创瑞 100次/天
	 * 调试400错误
	 * @param moblie
	 * @param content
	 * @return
	 */
	public static String sendMessageByChonry(String moblie,String content){
		// 接口地址
		String url = "http://apis.baidu.com/chonry/chonrysms/chonryapi";
		Map<String, String> headers = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<String, Object>();
		headers.put("apikey", apikey);
		params.put("mobile", moblie);
		params.put("content", content);

		return HttpUtil.get(url, params, headers, 5*1000, 3 * 60 * 1000, "UTF-8");
	}
	
	/**
	 * http://apistore.baidu.com/apiworks/servicedetail/774.html
	 * 湖南乐云 100次/天 无需账号密码，但是有【政务短信】【验证宝】签名
	 * 最好测试验证短信，内容包含"验证码"几个字
	 * 短信内容采用：您本次的验证码为：1234(数字要每次变化),有效期为5分钟，收到请告知，谢谢！
	 * 调试通过
	 * @param moblie
	 * @param content
	 * @return
	 */
	public static String sendMessageByLeyun(String moblie,String content){
		// 接口地址
		String url = "http://apis.baidu.com/hunanlehuotechnologyco/sms/api";
		Map<String, String> headers = new HashMap<String, String>();
		Map<String, Object> params = new HashMap<String, Object>();
		headers.put("apikey", apikey);
		params.put("mobile", moblie);
		params.put("content", content);
		
		return HttpUtil.get(url, params, headers, 5*1000, 3 * 60 * 1000, "UTF-8");
	}
	
	/**
	 * 短信猫RemoteSMS发送短信
	 * 返回1代表成功，其他：可能是0代表不成功
	 * @param numbers
	 * @param text
	 * @return
	 */
	public static String sendMessageByRemoteSMS(String numbers,String text){
		// 接口地址
		String url = "http://localhost:8080/";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("numbers", numbers);
		try {
			params.put("text", URLEncoder.encode(text, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String response = HttpUtil.get(url, params, null, 5*1000, 3 * 60 * 1000, "UTF-8");
		if(response==null){
			return "0";
		}
		return response;
	}
	
	/**
	 * 短信猫RemoteSMS接收新短信，分几步：
	 * 1.check 监控接口：http://localhost:8080/?checkHistory=1&_=1480572282335；返回0表示没有新信息，返回0|xxx表示有xxx div的新信息
	 * 2.获取信息：http://localhost:8080/?showConversation=308&_=1480572297973 ；返回的最后一条就是新信息。
	 * 以上方法仅是当没有获取过的可用，即需要定时器轮询接口。
	 * 还可以获取全部，然后取第一条数据。
	 * @param numbers
	 * @param text
	 * @return
	 */
	public static String getMessageByRemoteSMS() {
		// 接口地址
		String url = "http://localhost:8080/?showConversation=308";
		String response = HttpUtil.get(url, null, null, 5 * 1000, 3 * 60 * 1000, "UTF-8");
		String docment = "<html><head></head><body>" + response + "</body></html";
		Elements eles = null;
		String ele = null;
		String result = "";
		try {
			eles = Jsoup.parse(docment).getElementsByClass("one");
			ele = eles.last().text();
			String[] str = ele.split("，");
			result = str[0].substring(str[0].length() - 5, str[0].length()).trim();
		} catch (Exception e) {
			System.out.println("页面错误");
		}
		return result;
	}
	
	public static void main(String[] args){
		System.out.println(getMessageByRemoteSMS());
	}
}
