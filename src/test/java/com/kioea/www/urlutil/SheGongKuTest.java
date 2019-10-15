package com.kioea.www.urlutil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.fileutil.AppendToFile;
import com.kioea.www.fileutil.CreateFile;
import com.kioea.www.fileutil.DeleteFile;

/**
 * 
 * @author:sekift
 * @time:2015-3-25 下午04:59:18
 * @version:
 */
public class SheGongKuTest {

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
	 * {@link com.sekift.urlutil.SheGongKu#getSheGongKu(java.lang.String, int, boolean)}
	 * .
	 */
	@Test
	public void testGetSheGongKu() {
		String key = "谢文峰";
		int page = 1;
		int pageMax = 7;
		boolean encode = true;
		boolean showPage = false;
		
		String fileName="D:\\weiqiang.txt";
		DeleteFile.deleteFile(fileName);
		CreateFile.createFile(fileName);
		for (page = 1; page < pageMax; page++) {
			String responce = SheGongKu.getSheGongKu(key, page, encode, showPage);
			//判断当返回小于40则退出
			if(responce.length()<40){
				break;
			}
			System.out.println(responce);
			AppendToFile.appendWithWriter(fileName, responce);
		}
	}
}
