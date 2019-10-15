package com.kioea.www.jsouputil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created with IntelliJ IDEA. User: lujianing Date: 13-11-21 Time: 下午3:42 To
 * change this template use File | Settings | File Templates.
 */
public class GitbookDownloadImpl implements Runnable {

	private static String tempDir = "D://gitbook//";

	private String origin_url;
	private String book_type;

	public String getOrigin_url() {
		return origin_url;
	}

	public String getBook_type() {
		return book_type;
	}

	// 下载什么版本：pdf、mobi、epub
	private static final String PDF = "pdf";
	private static final String MOBI = "mobi";
	private static final String EPUB = "epub";

	public GitbookDownloadImpl(String url, String type) {
		this.origin_url = url;
		this.book_type = type;
	}

	public static void main(String[] args) throws IOException {
		String type = PDF;
		String str = "https://www.gitbook.com/explore?lang=zh&page=";
		for (int i = 0; i <= 0; i++) {
			str = str + i;
			GitbookDownloadImpl w = new GitbookDownloadImpl(str, type);
			Thread thread = new Thread(w);
			thread.start();
		}
	}

	@Override
	public void run() {
		getListPage(this.getOrigin_url(), this.getBook_type());
	}

	public static void getListPage(String url, String type) {
		try {
			Document document = Jsoup.connect(url).userAgent("Mozilla").get(); // 处理首页
			Elements htmls = document.select("div.Book .book-infos a");
			String html = "";
			for (Element element : htmls) {
				html = element.attr("href");
				if (html != null && !html.equals("")) {
					getPage(html, type);
				}
			}
		} catch (IOException e) {
		}
	}

	public static void getPage(String url, String type) {
		try {
			Document document = Jsoup.connect(url).userAgent("Mozilla").get(); // 处理首页
			String file_name = "";
			String book_path = "";

			Elements titleLinkElements = document.select("title");
			for (Element element : titleLinkElements) {
				file_name = element.text();
				file_name = file_name.substring(0, file_name.length() - 10);
			}

			book_path = url.replace("https://www.gitbook.com/", "https://www.gitbook.com/download/" + type + "/");
			downloadFile(file_name, book_path, type);
		} catch (IOException e) {
		}
	}

	public static void downloadFile(String dir, String file, String type) {
		File firdir = new File(tempDir);
		if (!firdir.exists()) {
			firdir.mkdirs();
		}
		String filePath = tempDir + dir + "." + type;
		File fileName = new File(filePath);
		if(fileName.isFile() && fileName.exists()){
			return;
		}
		int bytesum = 0;
		int byteread = 0;
		URL url = null;
		FileOutputStream fs = null;
		try {
			url = new URL(file);
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			fs = new FileOutputStream(filePath);

			byte[] buffer = new byte[1024];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
			}
			System.out.println(file + "下载完成");
		} catch (Exception e) {
		} finally {
			try {
				fs.flush();
				fs.close();
			} catch (Exception e) {
			}
		}
	}
}
