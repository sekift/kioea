package com.kioea.www.urlutil;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.Constant;

/**
 * 
 * @author:sekift
 * @time:2014-7-8 下午05:19:23
 */
public class GetDetailURLTest {

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
	 */
	@Test
	public void testGetAll() {
//		String url = "http://www.ganji.com";
//		System.out.println(URLAPITool.get(url.replace("http://", "")));
//		System.out.println(GetURLContent.getPageTitle(url));
//		String type = Constant.regex_ID.IPHONE;
//
//		Set<String> set = new TreeSet<String>(GetDetailURL.getRegex(url, type));
//
//		Iterator<String> ite = set.iterator();
//		while (ite.hasNext()) {
//			System.out.println(ite.next());
//		}
//		System.out.println(set.size());
		
		String url = "http://tieba.baidu.com/p/3992089517?pn=3";
		String type = Constant.regex_ID.EMAIL;

		Set<String> set = new TreeSet<String>(GetDetailFromPage.getDetailFromPage(url, type));

		Iterator<String> ite = set.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
		System.out.println(set.size());
	}
	
	@Test
	public void testGetEmailFromPage(){
		String url = "http://tieba.baidu.com/p/3992089517?pn=3";
		Set<String> set = new TreeSet<String>(GetDetailFromPage.getEmailFromPage(url));
		Iterator<String> ite = set.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
	}

	@Test
	public void testGetIpFromPage(){
		String url = "http://1.163.com/record/getDuobaoRecord.do?pageNum=1&pageSize=50&totalCnt=0&gid=1919&period=309211585";
		Set<String> set = new TreeSet<String>(GetDetailFromPage.getEmailFromPage(url));
		Iterator<String> ite = set.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
	}
}
