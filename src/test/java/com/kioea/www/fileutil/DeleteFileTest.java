package com.kioea.www.fileutil;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2014-7-15 下午05:16:46
 * @version:
 */
public class DeleteFileTest {

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
	 * {@link com.sekift.fileutil.DeleteFile#delete(java.lang.String)}.
	 */
	@Test
	public void testDelete() {
		String fileName = "D:/temp/Myclass.ser";
		System.out.println(DeleteFile.delete(fileName));
	}

	/**
	 * Test method for
	 * {@link com.sekift.fileutil.DeleteFile#deleteFile(java.lang.String)}.
	 */
	@Test
	public void testDeleteFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.sekift.fileutil.DeleteFile#deleteDirectory(java.lang.String)}.
	 */
	@Test
	public void testDeleteDirectory() {
		fail("Not yet implemented");
	}

}
