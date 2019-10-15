package com.kioea.www.urlutil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class HostHttpUtil {

	public static String getResponseText(String queryUrl, String ip) {
		// queryUrl，完整的url，host和ip需要绑定的host和ip
		HttpURLConnection conn = null;
		try {
			URL url = new URL(queryUrl);
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ip,80));
			conn = (HttpURLConnection) url.openConnection(proxy);
			
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setConnectTimeout(2000);
			conn.setReadTimeout(2000);
			conn.setDefaultUseCaches(false);
			conn.setUseCaches(false);

			// 接收返回结果
			StringBuilder result = new StringBuilder();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			if (in != null) {
				String line = "";
				while ((line = in.readLine()) != null) {
					result.append(line);
				}
				in.close();
			}
			return ip + " : " + result.toString();
		} catch (Exception e) {
			try {
				byte[] buf = new byte[100];
				InputStream es = conn.getErrorStream();
				if(es != null){
					while (es.read(buf) > 0) {;}
					es.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			//关闭连接
			if (conn != null){
				conn.disconnect();
			}	
		}
		return null;
	}
	
	public static void main(String[] args){
		System.out.println(getResponseText("http://userlogo.tianya.cn/adsp/user/getVIipUser.jsp?jsonpcallback=jsonpcallback&userid=87407354", "124.225.135.82"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/adsp/user/getVIipUser.jsp?jsonpcallback=jsonpcallback&userid=115935125", "124.225.135.101"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/adsp/user/getVIipUser.jsp?jsonpcallback=jsonpcallback&userid=87407354", "124.225.135.103"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/adsp/user/getVIipUser.jsp?jsonpcallback=jsonpcallback&userid=87407354", "124.225.135.105"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/adsp/user/getVIipUser.jsp?jsonpcallback=jsonpcallback&userid=115935125", "124.225.135.106"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/adsp/user/getVIipUser.jsp?jsonpcallback=jsonpcallback&userid=87407354", "124.225.135.109"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/adsp/user/getVIipUser.jsp?jsonpcallback=jsonpcallback&userid=115935125", "124.225.214.213"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/webcheck/heavycheck", "124.225.214.213"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/webcheck/heavycheck", "124.225.135.82"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/webcheck/heavycheck", "124.225.135.101"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/webcheck/heavycheck", "124.225.135.103"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/webcheck/heavycheck", "124.225.135.105"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/webcheck/heavycheck", "124.225.135.106"));
		System.out.println(getResponseText("http://userlogo.tianya.cn/webcheck/heavycheck", "124.225.135.109"));
	}
}
