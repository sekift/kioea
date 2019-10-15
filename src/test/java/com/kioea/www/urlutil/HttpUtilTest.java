package com.kioea.www.urlutil;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kioea.www.jsonutil.JsonUtils;

/**
 * 
 * @author:sekift
 * @time:2015-3-10 下午03:25:42
 * @version:
 */
public class HttpUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.sekift.urlutil.HttpUtil#post(java.lang.String, java.util.Map, int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testPostStringMapIntIntString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.sekift.urlutil.HttpUtil#post(java.lang.String, java.util.Map, java.util.Map, int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testPostStringMapMapOfStringStringIntIntString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.sekift.urlutil.HttpUtil#get(java.lang.String, java.util.Map, int, int, java.lang.String)}
	 * .
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetStringMapIntIntString() {
//		String url = "http://www.baidu.com";
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("productId", 1003);
//		params.put("userId", 10);
//		String str = HttpUtil.get(url, params, 5000, 5000, "gb2312");
//		System.out.println(str);
		
//		String url="http://open.tianya.cn/v2/opt/createToken.do";
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("source", "wefu");
//		params.put("var", "jsonback");
//		String str = HttpUtil.get(url, params, 5000, 5000, "utf-8");
//		System.out.println(str);
		
		String url = "http://www.meihuazhu.com/getQunZuLink?shareId=";
		for(int i=1;i<30;i++){
			String result = HttpUtil.get(url+i, null, 5000, 5000, "utf-8");
			Map<String,String> map = JsonUtils.toBean(result, Map.class);
			System.out.println(map.get("qunZuLink"));
		}
	}

	/**
	 * Test method for
	 * {@link com.sekift.urlutil.HttpUtil#get(java.lang.String, java.util.Map, java.util.Map, int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetStringMapMapOfStringStringIntIntString() {
		// http://shegongku.us/index.php/Index/search/key/%E6%96%87%E5%B3%B0/vcode/p/15
		String str = "卢宇";
		int page = 1;
		StringBuffer url = null;
		url = new StringBuffer("http://shegongku.us/index.php/Index/search/key/" + str);
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
		System.out.println(url.toString());
		String response = null;
		response = HttpUtil.get(url.toString(), null, headers, 10 * 3600, 10 * 3600, "utf-8");
		// System.out.println(response.replaceAll("</td></tr><tr><td>",
		// "\r\n"));

		Document doc = Jsoup.parse(response);
		Elements eles = doc.getElementsByTag("td");

		Elements links = doc.getElementsByTag("a");
		for (Element link : links) {
			// System.out.println(link.toString());
			System.out.println(link.attr("href"));
		}

		for (Element ele : eles) {
			if (!ele.text().equals("下一页")) {
				System.out.println(ele.text());
			}
		}
	}

	/**
	 * Test method for
	 * {@link com.sekift.urlutil.HttpUtil#put(java.lang.String, java.util.Map, int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testPutStringMapIntIntString() {
		String url="http://www.dwz.cn/create.php";
		Map<String, String> params = new HashMap<String, String>();
		params.put("url","http://help.baidu.com/question?prod_en=webmaster&class=%CD%F8%D2%B3%CB%D1%CB%F7%CC%D8%C9%AB%B9%A6%C4%DC&id=1000913");
		String response = HttpUtil.post(url.toString(), params, null, 10 * 3600, 10 * 3600, "utf-8");
		System.out.println(response);
	}

	/**
	 * Test method for
	 * {@link com.sekift.urlutil.HttpUtil#put(java.lang.String, java.util.Map, java.util.Map, int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testPutStringMapMapOfStringStringIntIntString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.sekift.urlutil.HttpUtil#delete(java.lang.String, java.util.Map, int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteStringMapIntIntString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.sekift.urlutil.HttpUtil#delete(java.lang.String, java.util.Map, java.util.Map, int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteStringMapMapOfStringStringIntIntString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.sekift.urlutil.HttpUtil#head(java.lang.String, java.util.Map, int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testHeadStringMapIntIntString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.sekift.urlutil.HttpUtil#head(java.lang.String, java.util.Map, java.util.Map, int, int, java.lang.String)}
	 * .
	 */
	@Test
	public void testHeadStringMapMapOfStringStringIntIntString() {
		fail("Not yet implemented");
	}

}
