package com.kioea.www.urlutil;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.urlutil.URLAPITool;

/**
 * 
 * @author:Administrator
 * @time:2015-9-2 下午03:45:24
 * @version:
 */
public class URLAPIToolTest {
	URLAPITool ult=new URLAPITool();
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
	
	@Test
	public void testGetExpress(){
		System.out.println(ult.getExpress("huitongkuaidi","211008145142"));
	}
	
	@Test
	public void testGetAddressByPhoneFromIP138(){
		System.out.println(ult.getAddressByPhoneFromIP138("1358031"));
	}
	
	@Test
	public void testGetAddressByPhoneFromTB(){
		System.out.println(ult.getAddressByPhoneFromTB("1358031"));
	}
	
	@Test
	public void testGetQQInfo(){
		System.out.println(ult.getQQInfo("574919797"));
	}

	@Test
	public void testGetAddressByIP(){
		System.out.println(ult.getAddressByIP("121.33.201.170"));
	}
	
	@Test
	public void testGetShortURLFromDwz(){
		System.out.println(ult.getShortURLFromDwz("http://help.baidu.com/question?prod_en=webmaster"));
	}
	
	@Test
	public void testGetLongURLFromDwz(){
		System.out.println(ult.getLongURLFromDwz("http://www.dwz.cn/dH14W"));
	}
	
	@Test
	public void testGetJokeFromSinaapp(){
		System.out.println(ult.getJokeFromSinaapp());
	}
}
