package com.kioea.www.jsouputil;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kioea.www.async.SleepUtil;
import com.kioea.www.stringutil.StringUtil;

/**
 * 
 * @author:sekift
 * @time:2015-2-6 上午11:29:58
 * @version:
 */
public class TianyaBuluo implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(TianyaBuluo.class);
	private static final String URL = "http://groups.tianya.cn/group_home.jsp?itemId=";
	private static final String PARAM = "&tabNum=4";
	private static Set<String> userIdSet = new HashSet<String>();

	@Override
	public void run() {
		Document doc = null;
		Elements eles = null;
		int buluoid = 165059;
		try {
			while (true) {
				int size1 = userIdSet.size();
				for (int i = buluoid; i < buluoid + 1; i++) {
					doc = JsoupUtil.getDocByConnectQuietly(URL + i);
					if(null == doc ){
						continue;
					}
					System.out.println(URL + i);
					Elements name = doc.getElementsByClass("qiuzhang-name");
					String zhuxi = name.select("a").attr("href").replace("http://www.tianya.cn/", "");
					System.out.println("主席\t" + name.text() + "\t"
							+ zhuxi);
					eles = doc.getElementsByTag("td").select("a");
					
//					if(null != eles && eles.size() >0){
//						userIdSet.add(zhuxi);
//					}
					
					int a = 1;
					for (Element ele : eles) {
						if (a % 2 == 1) {
							System.out.print(ele.text() + "\t");
						} else {
							String userId = ele.attr("href").replace("http://www.tianya.cn/", "");
							userIdSet.add(userId);
							System.out.println(ele.text() + "\t" + userId);
						}
						a++;
					}

					doc = JsoupUtil.getDocByConnectQuietly(URL + i + PARAM);					
					eles = JsoupUtil.getByTag(URL + i + PARAM, "li");
					for (Element ele : eles) {
						if (!StringUtil.isNullOrBlank(ele.attr("_id"))) {
//							System.out.println("内容\t" + ele.attr("_name") + "\t" + ele.attr("_id"));
							userIdSet.add(ele.attr("_id"));
						}
					}
				}
				int size2 = userIdSet.size();
				System.out.println("总数：" + size1 + "===>" + size2);
				for (String id : userIdSet) {
					System.out.print(id + " ");
				}
				// 睡眠时间5分钟
				SleepUtil.sleepByMinute(2, 10);
			}
		} catch (Exception e) {
			logger.error("出错了: ", e);
			// 出错后重新开始,睡眠时间1分钟
			SleepUtil.sleepByMinute(1, 1);
			Thread t = new Thread(new TianyaBuluo());
			t.start();
			e.printStackTrace();
			System.out.println("线程已重新开始");

		}
	}

	public static void main(String[] args) {
		Thread t = new Thread(new TianyaBuluo());
		t.start();
		System.out.println("线程开始");
	}
}