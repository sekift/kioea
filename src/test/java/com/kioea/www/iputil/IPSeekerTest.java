package com.kioea.www.iputil;


import org.junit.After;
import org.junit.Before;

/**
 * 
 * @author:sekift
 * @time:2014-8-13 下午04:51:24
 * @version:
 */
public class IPSeekerTest {

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
	
	public static void main(String[] args) {
		IPSeeker ip1 = IPSeeker.getInstance();
		String ips = "22.33.20.70";
		String country;
		String area;
		area = ip1.getArea(ips);
		country = ip1.getCountry(ips);
		System.out.println("国家名： " + country + " 地区名：" + area);
	}

}
