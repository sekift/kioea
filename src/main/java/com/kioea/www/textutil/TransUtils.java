package com.kioea.www.textutil;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.kioea.www.jsonutil.JsonUtils;

/**
 * 调用微软翻译api
 * 
 * @author crazy_000
 * 
 */
public class TransUtils {

	private String client_id;
	private String client_secret;

	public TransUtils() {
		// TODO Auto-generated constructor stub
	}

	public TransUtils(String client_id, String client_secret) {
		this.client_id = client_id;
		this.client_secret = client_secret;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	/**
	 * 通过微软翻译api获取标题的英文翻译
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public String translate(String str, String fromLanguage, String toLanguage)
			throws ClientProtocolException, IOException {
		String ak = getAcessKey();
		String uri = "http://api.microsofttranslator.com/v2/Http.svc/Translate?text=" + URLEncoder.encode(str, "utf-8")
				+ "&from=" + fromLanguage + "&to=" + toLanguage;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet method = new HttpGet(uri);
		String token = "Bearer " + ak;
		method.setHeader("Authorization", token);
		HttpResponse response = httpClient.execute(method);
		int statusCode = response.getStatusLine().getStatusCode();
		// System.out.println("code:" + statusCode);
		String body = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		body = body.substring(
				body.indexOf("<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">")
						+ "<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">".length(),
				body.indexOf("</string>"));
		// body = body.toLowerCase();
		return body;
	}

	private String getAcessKey() throws ClientProtocolException, IOException {
		String uri = "https://datamarket.accesscontrol.windows.net/v2/OAuth2-13";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost method = new HttpPost(uri);
		// 创建参数list
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("client_id", client_id));
		params.add(new BasicNameValuePair("client_secret", client_secret));
		params.add(new BasicNameValuePair("scope", "http://api.microsofttranslator.com"));
		params.add(new BasicNameValuePair("grant_type", "client_credentials"));
		// 放入参数
		method.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

		HttpResponse response = httpClient.execute(method);
		int statusCode = response.getStatusLine().getStatusCode();
		String body = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		TransEntity entity = JsonUtils.toBean(body, TransEntity.class);

		return entity.getAccess_token();
	}

	public static void main(String[] args) {
		TransUtils mtu = new TransUtils("sekift-microsoft-azure", "br2uPvmKwSHroLntOB3zkTS0cuKysjzzKGkamaSUOeY=");
		long startTime = System.currentTimeMillis();
		try {
			System.out.println(mtu.translate("你好", Language.ChineseSimplified, Language.Japanese));
			// System.out.println(mtu.getAcessKey());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long time = System.currentTimeMillis() - startTime;
		System.out.println((double) time / 1000);
	}
}
