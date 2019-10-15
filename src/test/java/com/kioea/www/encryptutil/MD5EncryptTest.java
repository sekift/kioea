package com.kioea.www.encryptutil;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2015-3-10 下午03:59:11
 * @version:
 */
public class MD5EncryptTest {

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
	 * Test method for {@link com.sekift.encryptutil.MD5Encrypt#MD5Encrypt()}.
	 */
	@Test
	public void testMD5Encrypt() {
		String str=MD5Encrypt.MD5Encode("1123581321");
		System.out.println(str);
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.MD5Encrypt#byteArrayToString(byte[])}.
	 */
	@Test
	public void testByteArrayToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.MD5Encrypt#MD5Encode(java.lang.String)}.
	 */
	@Test
	public void testMD5Encode() {
		fail("Not yet implemented");
	}

}
