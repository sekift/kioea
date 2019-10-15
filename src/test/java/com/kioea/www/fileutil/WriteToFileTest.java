package com.kioea.www.fileutil;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.fileutil.WriteToFile;

/**
 * 
 * @author:Administrator
 * @time:2015-9-16 上午11:42:46
 * @version:
 */
public class WriteToFileTest {

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
	public void testWriteToFileByByte(){
		WriteToFile.writeFileByBytes("D:\\temp\\test1\\tetate.txt", "访问鹅防");
	}
}
