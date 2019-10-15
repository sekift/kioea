package com.kioea.www.fileutil;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2014-7-15 下午03:25:58
 * @version:
 */
public class ListFileTest {

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
	 * {@link com.sekift.fileutil.ListFile#listAllFiles(java.lang.String)}.
	 */
	@Test
	public void testListAllFiles() {
		String dir = "D:\\temp";
		System.out.println(dir + "目录下的内容：");
		System.out.println(ListFile.listAllFiles(dir));

		Iterator<String> it = ListFile.listAllFiles(dir).iterator();
		while (it.hasNext()) {
			String str = (String) it.next();
			System.out.println(str);
			File file = new File(str);
			//System.out.println(file.getName());

		}
	}

	/**
	 * Test method for
	 * {@link com.sekift.fileutil.ListFile#listFiles(java.lang.String, int)}.
	 */
	@Test
	public void testListFiles() {
		String dir = "D:\\temp";
		System.out.println(dir + "目录下的内容：");
		System.out.println(ListFile.listFiles(dir, 2));

		Iterator<String> it = ListFile.listFiles(dir, 2).iterator();
		while (it.hasNext()) {
			String str = (String) it.next();
			if (str.endsWith(".log"))
				System.out.println(str);
		}
	}

	/**
	 * Test method for
	 * {@link com.sekift.fileutil.ListFile#listFilesByFilenameFilter(java.io.FilenameFilter, java.lang.String)}
	 * .
	 */
	@Test
	public void testListFilesByFilenameFilter() {
		String dir = "D:\\temp";
		System.out.println("经过过滤后的内容：");
		FilenameFilter myFilenameFilter = new ListFile.MyFilenameFilter(".log");
		ListFile.listFilesByFilenameFilter(myFilenameFilter, dir);

	}

}
