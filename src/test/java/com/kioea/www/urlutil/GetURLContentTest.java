package com.kioea.www.urlutil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.encodeutil.TransformEncode;
import com.kioea.www.jsonutil.JsonUtils;

public class GetURLContentTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUrlConnectionPost() {
		GetURLContent.urlConnectionPost("http://www.baidu.com");
	}

	@Test
	public void testGetPageStatus() {
		System.out.println(TransformEncode.getEncoding(GetURLContent.getPageTitle("http://www.baidu.com")));
	}

	@Test
	public void testGetPageTitle() {
		System.out.println(GetURLContent
				.getPageTitle("http://220.181.65.103/masmanage/mas_page/system/login.do?action=input"));
		System.out.println(TransformEncode.getEncoding(GetURLContent.getPageTitle("http://220.181.65.101/")));
		System.out.println(TransformEncode.getEncoding(GetURLContent.getPageTitle("http://www.baidu.com")));
		System.out.println(TransformEncode.iso2utf8(GetURLContent.getPageTitle("http://220.181.65.101/")));
		// fail("Not yet implemented");
		
	}

	@Test
	public void testGetPageContent() {
//		System.out
//				.println(TransformEncode.unicode2utf8(GetURLContent
//						.getPageContent("http://api.map.baidu.com/geosearch/v3/local?region=%E5%B9%BF%E5%B7%9E&ak=g2SLWePtuLXkNo3rmGTxKs7W&geotable_id=70337&tags=%E4%B8%AD%E5%A4%A7&sortby=distance:1")));
//		System.out
//		.println(TransformEncode.unicode2utf8(GetURLContent
//				.getPageContent("http://ip.taobao.com/service/getIpInfo.php?ip=188.100.220.10")));
		System.out.println(GetURLContent.getPageContent("http://1.163.com/record/getDuobaoRecord.do?pageNum=2&pageSize=50&totalCnt=0&gid=1919&period=309211585"));
	}
	
	@Test
	public void testGetPageContentByHeader(){
		Map<String, String> header = new HashMap<String, String>();
		header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		header.put("Accept-Encoding", "gzip, deflate");
		header.put("Accept-Language", "zh-CN,zh;q=0.8");
		header.put("Cache-Control", "max-age=0");
		header.put("Connection", "keep-alive");
		header.put("Cookie", "uuid=\"w:25d38e9d089d4375936d17fefd15c001\"; UM_distinctid=15aeeae0d21287-0c0a2fad7339d8-6a11157a-13c680-15aeeae0d223d2; csrftoken=997d3747fdb18f190e8a53b85a0faf49; WEATHER_CITY=%E5%8C%97%E4%BA%AC; _ga=GA1.2.56366142.1489482755; _gid=GA1.2.1383472461.1499306669; tt_webid=6439473051209188877; CNZZDATA1259612802=366632942-1489477837-%7C1499304569; __tasessionId=o0tqcdnt61499306668305; utm_source=toutiao");
		header.put("Host", "www.toutiao.com");
		header.put("Upgrade-Insecure-Requests", "1");
		header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
		
		String url = "http://www.toutiao.com/api/article/feed/?category=essay_joke&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&tadrequire=true&as=A19539159D19AD9&cp=595D497AAD994E1";
		//String url = "http://www.toutiao.com/api/pc/feed/?category=news_hot&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&tadrequire=true&as=A19539159D19AD9&cp=595D497AAD994E1";
		String result = GetURLContent.getPageContentByHeader(url, header);
        String trans = TransformEncode.unicode2utf8(result);
        Map<String, List<Map<String, Map<String, Object>>>> map = JsonUtils.toBean(trans, Map.class);
        List<Map<String,Map<String, Object>>> list = (List<Map<String, Map<String, Object>>>)map.get("data");
        for(Map<String,Map<String, Object>> content: list){
			int repin_count = (Integer) content.get("group").get("repin_count");
			int digg_count = (Integer) content.get("group").get("digg_count");
			int bury_count = (Integer) content.get("group").get("bury_count");
			int share_count = (Integer) content.get("group").get("share_count");
			int favorite_count = (Integer) content.get("group").get("favorite_count");
			int go_detail_count = (Integer) content.get("group").get("go_detail_count");
			int comment_count = (Integer) content.get("group").get("comment_count");
			int status = digg_count - bury_count + repin_count + share_count + favorite_count + go_detail_count / 5
					+ comment_count;
			System.out.println("内涵指数：" + status + " : " + content.get("group").get("content"));//
        }
	}
	
	@Test
	public void testGetPageContentBai(){
		Map<String, String> header = new HashMap<String, String>();
		header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		header.put("Accept-Encoding", "gzip, deflate, br");
		header.put("Accept-Language", "zh-CN,zh;q=0.9");
		header.put("Cache-Control", "max-age=0");
		header.put("Connection", "keep-alive");
		header.put("Cookie", "_xsrf=2|ab5e5fc0|ad417b772917abe2615fea71726d0487|1553071278; Hm_lvt_2670efbdd59c7e3ed3749b458cafaa37=1553071282; Hm_lpvt_2670efbdd59c7e3ed3749b458cafaa37=1553071282; _ga=GA1.2.1070617368.1553071283; _gid=GA1.2.754822015.1553071284; _gat=1");
		header.put("Host", "www.qiushibaike.com");
		header.put("If-None-Match", "f34899792ddb49e9c0357bbe2339760dc0b61a96");
		header.put("Upgrade-Insecure-Requests", "1");
		header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
		
		String url = "http://www.qiushibaike.com/8hr/page/1/";
		String result = GetURLContent.getPageContentByHeader(url, header);
		System.out.println(result);
	}

	@Test
	public void testDownPageToFile() {
		// fail("Not yet implemented");
	}

}
