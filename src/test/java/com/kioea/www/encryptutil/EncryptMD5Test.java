package com.kioea.www.encryptutil;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2015-3-10 下午03:56:38
 * @version:
 */
public class EncryptMD5Test {

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
	 * Test method for {@link com.sekift.encryptutil.EncryptMD5#encryptMD5(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testEncryptMD5() {
		String content="1123581321";
		String charset="utf-8";
		String result=EncryptMD5.encryptMD5(content, charset);
		System.out.println(result);
	}

}
