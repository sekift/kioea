package com.kioea.www.fileutil;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2014-7-15 下午05:14:48
 * @version:
 */
public class ReadFromFileTest {

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
	 * {@link com.sekift.fileutil.ReadFromFile#readFileByBytes(java.lang.String)}
	 * .
	 */
	@Test
	public void testReadFileByBytes() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.sekift.fileutil.ReadFromFile#readFileByChars(java.lang.String)}
	 * .
	 */
	@Test
	public void testReadFileByChars() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.sekift.fileutil.ReadFromFile#readFileByLines(java.lang.String)}
	 * .
	 */
	@Test
	public void testReadFileByLines() {
		String fileName = "D:/temp/ceshi.txta";
		ReadFromFile.readFileByLines(fileName);
	}

	/**
	 * Test method for
	 * {@link com.sekift.fileutil.ReadFromFile#readFileByRandomAcccess(java.lang.String)}
	 * .
	 */
	@Test
	public void testReadFileByRandomAcccess() {
		fail("Not yet implemented");
	}

}
