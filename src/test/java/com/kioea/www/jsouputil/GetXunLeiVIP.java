package com.kioea.www.jsouputil;

import java.io.IOException;

import org.jsoup.nodes.Document;

import com.kioea.www.jsouputil.JsoupUtil;

public class GetXunLeiVIP {

	public static void main(String[] args) throws IOException {
		String url = "http://1.163.com/record/getDuobaoRecord.do?pageNum=1&pageSize=50&totalCnt=0&gid=1919&period=309211585&token=395e5e34-620f-4789-b02e-960c29bf17a9&t=1474439453618";
		Document doc = JsoupUtil.getDocByConnect(url);
		System.out.println(doc);
	}
}
