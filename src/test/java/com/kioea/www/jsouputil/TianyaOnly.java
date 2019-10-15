package com.kioea.www.jsouputil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 天涯只看楼主
 * 
 * @author:luyz
 * @time:2015-5-4 上午11:36:56
 * @version:1.2.1
 */
public class TianyaOnly {

	public static void main(String[] args) throws IOException {
		int page = 173;

		while (page <= 174) {
			StringBuffer urlBase = new StringBuffer();
			urlBase.append("http://bbs.otianya.cn/cgi-bin/bbs.pl?url=bbs.tianya.cn/post-stocks-1394413-");
			StringBuffer url = new StringBuffer();
			// http://jandan.net/ooxx/page-1139#comments
			url.append(urlBase);
			url.append(page);
			url.append(".shtml");

			// 解析url
			String threadUrl = url.toString();
			Document doc = (Document) Jsoup.connect(threadUrl).get();
//			System.out.println(doc);
			// 解析html文档
			// File input = new File("D:\\text.txt");
			// Document doc = Jsoup.parse(input, "UTF-8");
			Elements eles = doc.getElementsByClass("atl-item");

//			writeToFile(url.toString());
			for (Element ele : eles) {
				String time = ele.select("span").get(1).text();
				Element content = ele.getElementsByClass("bbs-content").get(0);
				System.out.println(time);
				System.out.println(content);

//				writeToFile(time);
//				writeToFile(content.toString());
//				writeToFile("<br><br><br>");
			}
			page = page + 1;
		}
	}

	public static void writeToFile(String txt) throws IOException {
		File file = new File("d:\\tianyaonly.html");// 指定文件路径
		FileOutputStream fos = new FileOutputStream(file, true);
		fos.write(txt.getBytes());
		fos.close();
	}
}
