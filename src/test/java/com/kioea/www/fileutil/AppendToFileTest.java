package com.kioea.www.fileutil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2014-7-14 下午05:36:06
 * @version:
 */
public class AppendToFileTest {

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
	 * {@link com.sekift.fileutil.AppendToFile#appendWithAccess(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testAppendWithAccess() {
		String fileName = "D:\\temp\\teswfet.txt";
		String content = "new append!";
		AppendToFile.appendWithAccess(fileName, content);
		System.out.println(AppendToFile.appendWithAccess(fileName, "fawfew"));

	}

	/**
	 * Test method for
	 * {@link com.sekift.fileutil.AppendToFile#appendWithWriter(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testAppendWithWriter() {
		String fileName = "D:\\temp\\teswfet.txt";
		String content = "new append!";
		System.out.println(AppendToFile.appendWithWriter(fileName, content));
		AppendToFile.appendWithWriter(fileName, "append end. \n");
	}

}
