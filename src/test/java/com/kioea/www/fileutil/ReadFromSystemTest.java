package com.kioea.www.fileutil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2014-8-14 上午09:43:35
 * @version:
 */
public class ReadFromSystemTest {

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
	 * Test method for {@link com.sekift.fileutil.ReadFromSystem#systemIn()}.
	 */
	@Test
	public void testSystemIn() {
		System.out.println(ReadFromSystem.systemIn());
	}

}
