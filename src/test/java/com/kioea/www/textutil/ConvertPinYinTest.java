package com.kioea.www.textutil;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.textutil.ConvertPinYin;

/**
 * 
 * @author:sekift
 * @time:2015-3-25 上午10:57:04
 * @version:
 */
public class ConvertPinYinTest {

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
	public void testGetFullSpell(){
		String str="中国";
		System.out.println(ConvertPinYin.getFullSpell(str));
		
	}

}
