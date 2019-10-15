package com.kioea.www.jsouputil;

import java.io.IOException;
import java.net.MalformedURLException;
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
public class YN1999Jsoup implements Runnable {

	private static Queue<String> queuea = new LinkedList<String>();// 创建一个队列
	// 文件路径
	private static String fileName = "D:\\YN1999Jsoup.txt";
	
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
		Thread t = new Thread(new YN1999Jsoup());
		// 初始化上次的信息
		queuea = getQueue();
		queuea.poll();
		t.start();
		System.out.println("线程开始");
	}

	@Override
	public void run() {
		Date dateA1 = null, dateA2 = null;
		Date date = null;
		// 网站网址
		String site1 = "http://www.yn1999.com/Game_User.asp?id=godball";
		// sort_page
		String[] site = new String[] { site1 };
		int j = 0;
		try {
			while (true) {
				date = new Date(System.currentTimeMillis());
				for (int i = 0; i < site.length; i++) {
					URI uri = new URI(site[i]);
					URL url = uri.toURL();
					// 获取网站内容，包括易挂的形式
					Document doc = Jsoup.parse(url, 5 * 60 * 1000);

					// 获取标签
					Elements eles = doc.select("tbody>tr");
					Element eleA = eles.get(1);

					// 转换
					String timestampA = eleA.select("td").get(9).text();

					queuea.offer("2015-" + timestampA + ":00");

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
							String title = "《godball》擂台最新推荐";
							String msg = "<a href=\"http://www.yn1999.com/Game_User.asp?id=godball\">"
									+ eles.get(1).select("td").text() + "</a>";
							MailUtil.sendHTML("sekift@163.com", title, msg);
							j++;
							if (j > 10) {
								break;
							}
						}
					}
				}
				System.out.println(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds()
						+ " =====================");
				Thread.currentThread().sleep(RandomTool.randomInt(5, 10) * 60 * 1000); // 5-10分钟随机时间
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Thread t = new Thread(new YN1999Jsoup());
			// 初始化上次的信息
			queuea = getQueue();
			queuea.poll();
			t.start();
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}