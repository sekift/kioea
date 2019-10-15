package com.kioea.www.stringutil;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2015-3-25 下午02:09:38
 * @version:
 */
public class StringUtilTest {

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
	 * Test method for {@link com.sekift.stringutil.StringUtil#getDJBHash(java.lang.String)}.
	 */
	@Test
	public void testGetDJBHash() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#encodeStr(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testEncodeStrStringString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#encodeStr(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testEncodeStrStringStringString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#isBlank(java.lang.String)}.
	 */
	@Test
	public void testIsBlank() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#isNull(java.lang.String)}.
	 */
	@Test
	public void testIsNull() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#isNullOrBlank(java.lang.String)}.
	 */
	@Test
	public void testIsNullOrBlank() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#trimWords(java.lang.String, int)}.
	 */
	@Test
	public void testTrimWords() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#encodeUrl(java.lang.String)}.
	 */
	@Test
	public void testEncodeUrlString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#encodeUrl(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testEncodeUrlStringString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#joinArray(java.lang.Object[], java.lang.String)}.
	 */
	@Test
	public void testJoinArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#filterPunctuation(java.lang.String)}.
	 */
	@Test
	public void testFilterPunctuation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#getCamelCaseString(java.lang.String)}.
	 */
	@Test
	public void testGetCamelCaseString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#getExceptionAsStr(java.lang.Throwable)}.
	 */
	@Test
	public void testGetExceptionAsStr() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.stringutil.StringUtil#subStringBylen(java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public void testSubStringBylen() {
		String str="中国俄方哇额外发而为啊发文";
		String subStr=StringUtil.subStringBylen(str, 10);
		System.out.println(subStr);
	}

}
