package com.kioea.www.jsouputil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kioea.www.dateutil.DateUtilHelper;
import com.kioea.www.fileutil.ReadFromFile;
import com.kioea.www.fileutil.WriteToFile;
import com.kioea.www.randomutil.RandomTool;
import com.kioea.www.stringutil.StringUtil;

/**
 * 
 * @author:sekift
 * @time:2015-2-6 上午11:29:58
 * @version:
 */
public class TianyaTool implements Runnable {

	private static Queue<String> queuea = new LinkedList<String>();// 创建一个队列
	// 文件路径
	private static String fileName = "D:\\TianyaTool.txt";

	// 从文件获取上次保存的信息
	private static Queue<String> getQueue() {
		String str = ReadFromFile.readFileByLines(fileName);
		if (!StringUtil.isNullOrBlank(str)) {
			str = str.replace("[", "").replace("]", "");
			queuea.add(str.substring(0, str.indexOf(",")).trim());
			queuea.add(str.substring(str.indexOf(",") + 1, str.length()).trim());
		}
		return queuea;
	}

	public static void main(String[] args) {
		Thread t = new Thread(new TianyaTool());
		// 初始化上次的信息
		queuea = getQueue();
		queuea.poll();
		t.start();
		System.out.println("线程开始");
	}

	// 网站网址
	String site1 = "http://bbs.otianya.cn/cgi-bin/bbs.pl?url=bbs.tianya.cn/post-stocks-1333219-176.shtml";

	@Override
	public void run() {
		Date dateA1 = null, dateA2 = null;
		Date date = null;
		// sort_page
		String[] site = new String[] { site1 };
		String time = null;
		Element content = null;
		int j = 0;
		try {
			while (true) {
				date = new Date(System.currentTimeMillis());
				for (int i = 0; i < site.length; i++) {
					URI uri = new URI(site[i]);
					URL url = uri.toURL();
					// 获取网站内容，包括易挂的形式
					Document doc = Jsoup.parse(url, 5 * 60 * 1000);
					// System.out.println(doc);
					// 获取标签
					Elements eles = doc.getElementsByClass("atl-item");
					// System.out.println(eles);
					for (Element ele : eles) {
						time = ele.select("span").get(1).text();
						content = ele.getElementsByClass("bbs-content").get(0);
					}
					// System.out.println(time);
					// System.out.println(content);

					// 转换
					// String timestampA = eleA.select("td").get(9).text();
					if (time != null) {
						String timestampA = time.replace("时间：", "");
						queuea.offer(timestampA);

						if (queuea.size() > 1) {
							System.out.println(queuea);
							// 写入到文件，方便线程挂后重新获取（替换方式）
							WriteToFile.writeFileByLines(fileName, queuea.toString());

							String timestampA1 = queuea.poll();
							String timestampA2 = queuea.peek();

							try {
								dateA1 = DateUtilHelper.convertStringToDate("yyyy-MM-dd HH:mm:ss", timestampA1);
								dateA2 = DateUtilHelper.convertStringToDate("yyyy-MM-dd HH:mm:ss", timestampA2);
							} catch (ParseException e) {
								e.printStackTrace();
							}

							if (dateA2.getTime() > dateA1.getTime()) {
								System.out.println("yes,立刻邮件通知");
								String title = "阿剑推荐";
								String msg = time + "<br />" + "<a href=\"" + site1 + "\">" + site1 + "</a><br />"
										+ content;
								MailUtil.sendHTML("sekift@163.com", title, msg);
								j++;
								if (j > 10) {
									break;
								}
							}
						}
					}
				}
				System.out.println(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds()
						+ " =====================");
				Thread.currentThread().sleep(RandomTool.randomInt(5, 8) * 60 * 1000); // 5-10分钟随机时间
			}

		} catch (SocketException e) {
			System.out.println("[服务器出错]：SocketException.");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Thread t = new Thread(new TianyaTool());
			// 初始化上次的信息
			/**
			 * 无需初始化
			 */
			// queuea = getQueue();
			// queuea.poll();
			t.start();
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}