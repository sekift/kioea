package com.kioea.www.csvutil;

import java.util.List;

import org.junit.Test;

import com.kioea.www.fileutil.AppendToFile;

/**
 * @author 作者:sekift
 * @version 创建时间：2014-8-8 下午10:06:49 类说明:[]
 */
public class CsvParserTest {

	@Test
	public void testCsvParser() {
		String file = "E:/ip/112.124.30.csv";
        String fileName="E:/ip/112.124.30.html";
        
		CsvParser p = new CsvParser(file, "GBK");
		while (p.hasNext()) {
			List<String> row = p.next();
			if (row.get(4).contains("200")||row.get(4).contains("403")) {
				System.out.println("<a href="+row.get(1)+">"+row.get(1)+"</a>");
				AppendToFile.appendWithWriter(fileName, "<a href="+row.get(1)+">"+row.get(1)+"</a>");
				AppendToFile.appendWithAccess(fileName, "\r\n");
			}
		}
		p.close();
	}

}
