package com.kioea.www.jsouputil;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kioea.www.jsouputil.JsoupUtil;

/**
 * 下载圣经内容
 * 
 * @author 作者:sekift
 * @author E-mail:sekiftlyz@gmail.com
 * @version 创建时间：2014-8-9 下午07:19:50 类说明:[]
 */
public class GetDockerPages {

	private static final String PRE_PAGES = "http://udn.yyuap.com/doc/docker_practice";

	public static void main(String[] args) throws Exception {
		String url = PRE_PAGES + "/index.html";
		Document document = JsoupUtil.getDocByConnect(url);
		// System.out.println(doc);
		// Elements eles=doc.getElementsByClass("chapter");
		Elements eles = document.select("ul li a");
		String link = null;
		File file = new File("D://Docker_html.txt");
		for (Element ele : eles) {
			link = ele.attr("href").substring(1);

			document = JsoupUtil.getDocByConnect(PRE_PAGES + link);
			FileUtils.writeStringToFile(file, ele.text().toString() + "\n", "utf-8", true);
			FileUtils.writeStringToFile(file, document.getElementsByClass("page-wrapper").toString() + "\n",
					"utf-8", true);
			FileUtils.writeStringToFile(file, "\n \n", "utf-8", true);
		}

	}
}
