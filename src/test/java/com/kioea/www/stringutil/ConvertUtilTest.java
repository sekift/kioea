package com.kioea.www.stringutil;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.stringutil.ConvertUtil;

/**
 * 
 * @author:sekift
 * @time:2015-3-25 上午11:07:56
 * @version:
 */
public class ConvertUtilTest {

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
	public void testToDouble(){
		System.out.println(ConvertUtil.toDouble("zhfwe3.123"));
	}
	
	@Test
	public void testToLong(){
		System.out.println(ConvertUtil.toLong("3"));
	}
}
