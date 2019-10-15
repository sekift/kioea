package com.kioea.www.urlutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class ConnectUtil {
	private static Logger logger =Logger.getLogger(ConnectUtil.class);

	public static String doGet(String domain,String urlStr, String recvEncoding) {
		HttpURLConnection url_con =null;
		String responseContent = null;
		try {
			URL url = new URL(urlStr);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestProperty("Referer", "www.baidu.com");
			if(domain!=null)
				url_con.setRequestProperty("Host", domain);

			// connection = (HttpURLConnection) url.openConnection();
			// 模拟成ie
			url_con.setRequestProperty("user-agent", "mozilla/4.0 (compatible; msie 6.0; windows 2000)");
			url_con.connect();
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			logger.error(domain+"出错:"+urlStr,e);
			responseContent = responseContent + e;
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}
	
	public static void main(String args[]){
		System.out.println(doGet("baidu.com","http://www.baidu.com","utf-8"));
	}
	
	@SuppressWarnings("unchecked")
	public static String doPost(String domain,String reqUrl, Map parameters, String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
				Entry<String, String> element = (Entry<String, String>)iter.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(),"UTF-8"));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestProperty("Referer", "www.baidu.com");
			if(domain!=null)
				url_con.setRequestProperty("Host", domain);
			url_con.setRequestMethod("POST");
			url_con.setConnectTimeout(5000);// （单位：毫秒）连接超时
			url_con.setReadTimeout(5000);// （单位：毫秒）读操作超时
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			responseContent = responseContent + e;
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}

}
