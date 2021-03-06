package com.kioea.www.jsouputil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.kioea.www.regexutil.RegexUtil;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kioea.www.async.SleepUtil;
import com.kioea.www.dateutil.DateUtil;
import com.kioea.www.stringutil.StringUtil;
import com.kioea.www.urlutil.HtmlUtil;
import com.kioea.www.urlutil.SendMessageTool;

/**
 * 
 * @author:sekift
 * @time:2015-2-6 上午11:29:58
 * @version:
 */
public class TianyaHongbao implements Runnable {
	private static final Logger logger=LoggerFactory.getLogger(TianyaHongbao.class);
	
	private static List<String> siteList = new ArrayList<String>();
	private static List<Queue<String>> queueList = new ArrayList<Queue<String>>();
	private static List<String> fileList = new ArrayList<String>();
	//长日期
	private static final String DATE_LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";

	static {
		getSiteList();
		getQueueList();
		getFileList();
	}
	
	private static List<String> getSiteList() {
		siteList.add("http://bbs.otianya.cn/post-stocks-1266823-10000.shtml");// 一把火          1
		siteList.add("http://bbs.otianya.cn/post-stocks-1312311-10000.shtml");// andy1976 2
		/*
		siteList.add("http://bbs.otianya.cn/cgi-bin/bbs.pl?url=http://bbs.tianya.cn/post-stocks-1394413-10000.shtml");// 犀利哥          3
		siteList.add("http://bbs.otianya.cn/cgi-bin/bbs.pl?url=http://bbs.tianya.cn/post-stocks-1468898-10000.shtml");// 一点钟          4
		siteList.add("http://bbs.otianya.cn/cgi-bin/bbs.pl?url=http://bbs.tianya.cn/post-stocks-1403333-10000.shtml");// 航海              5
		siteList.add("http://bbs.otianya.cn/cgi-bin/bbs.pl?url=http://bbs.tianya.cn/post-stocks-1557984-10000.shtml");// 超短              6
		siteList.add("http://bbs.otianya.cn/cgi-bin/bbs.pl?url=http://bbs.tianya.cn/post-no100-73073-10000.shtml");// 龙              7
		*/
		//TODO 在这里添加适当合法的网址
		return siteList;
	} 

	private static List<Queue<String>> getQueueList() {
		for (int i = 0; i < siteList.size(); i++) {
			queueList.add(new LinkedList<String>());
		}
		return queueList;
	}

	private static List<String> getFileList() {
		String preFileName = System.getProperty("user.dir") + "\\TianyaHongbao";
		String proFileName = ".txt";
		File file = null;
		String medFileName=null;
		for (int i = 0; i < siteList.size(); i++) {
			medFileName = RegexUtil.fetchStr("[-\\d-]+", siteList.get(i));
			String fileName = preFileName + medFileName + proFileName;
			file = new File(fileName);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fileList.add(fileName);
		}
		return fileList;
	}

	// 从文件获取上次保存的信息
	private static Queue<String> getQueue(String fileName, Queue<String> queuea) {
		String str = null;
		try {
			str = FileUtils.readFileToString(new File(fileName), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!StringUtil.isNullOrBlank(str)) {
			str = str.replace("[", "").replace("]", "");
			queuea.add(str.substring(0, str.indexOf(",")).trim());
			queuea.add(str.substring(str.indexOf(",") + 1, str.length()).trim());
		} else {
			queuea.add("1970-01-01 00:00:00");
			queuea.add("1970-01-01 00:00:00");
		}
		return queuea;
	}

	@Override
	public void run() {
		Date date = null;
		String time = "";
		String content = "";
		Element contents= null;
		String louzhu = "";
		int j = 0;
		Elements eles = null;
		try {
			while (true) {
				date = new Date(System.currentTimeMillis());
				logger.error(DateUtil.convertDateToStr(date, DATE_LONG_FORMAT));
				for (int i = 0; i < siteList.size(); i++) {
					eles = JsoupUtil.getByAttrClass(siteList.get(i), "atl-item");
					//获取最后一条楼主发言
					for (Element ele : eles) {
						if (!ele.select("span").get(0).text().contains("作者")) {
							louzhu = ele.select("span").get(0).text();// 楼主标识
							time = ele.select("span").get(1).text();// 帖子时间
							contents = ele.getElementsByClass("bbs-content").get(0);// 帖子内容未处理
							content = contents.text();// 帖子内容
						}
					}
					//出现eles空的情况，以当前时间为准，内容为空
					if (eles.isEmpty() || eles.size() == 0) {
						louzhu = "本页无内容";// 楼主标识
						time = DateUtil.convertDateToStr(date, DATE_LONG_FORMAT);// 帖子时间
						content = "本页无更新";// 帖子内容
					}
					// 转换
					if (time != null && louzhu.contains("楼主")) {
						String timestampA = time.replace("时间：", "");
						queueList.get(i).offer(timestampA);
						if (queueList.get(i).size() > 1) {
							if (queueList.get(i).size() > 2) {
								// 有时候会出错，稳定后删除
								System.out.println(queueList.get(i));
								logger.error("文件的时间数出错了，请检查…… ");
								break;
							}
							
							// 写入到文件，方便线程挂后重新获取（替换方式）
							FileUtils.writeStringToFile(new File(fileList.get(i)), queueList.get(i).toString(),
									"utf-8", false);
							
							// 发邮件判断
							if (vatifyFlag(queueList.get(i).poll(),  queueList.get(i).peek(), contents)) {
								// 邮件通知
								sendMessageByEmail(time, content, siteList.get(i));
								// 发送短信
//								sendMessageByMobile(time,content,siteList.get(i));

								j++;
								// 防止出了问题疯狂地发邮件
								if (j > 50) {
									break;
								}
							}
							
						}
					}
					//重新赋值
					louzhu = null;
					time = null;
					content = null;
				}
				//睡眠时间1-3分钟随机
				SleepUtil.sleepByMinute(1, 3);
			}
		} catch (Exception e) {
			logger.error("出错了: ",e);
			// 出错后重新开始
			Thread t = new Thread(new TianyaHongbao());
			t.start();
			e.printStackTrace();
			System.out.println("线程已重新开始");
		}
	}

	/**
	 * 触发判断 内容的逻辑处理
	 */
	private static boolean vatifyFlag(String firstTimestamp, String secondTimestamp, Element contents) {
		Date firstDate = DateUtil.convertStrToDate(firstTimestamp, DATE_LONG_FORMAT);
		Date secondDate = DateUtil.convertStrToDate(secondTimestamp, DATE_LONG_FORMAT);
		boolean haveHongbao = contents.toString().contains("抢红包</a>】") && !contents.toString().contains("抢到了");
		boolean flag = false;
		if (secondDate.getTime() > firstDate.getTime()) {
			if (haveHongbao) {
				flag = true;
			}
		}
		return flag;
	}

	//邮箱通知 内容过滤
	private static void sendMessageByEmail(String time, String content, String site) {
		try {
			String title = "【抢红包】";
			site = site.replace("bbs.otianya.cn/cgi-bin/bbs.pl?url=http://", "");
			String msg = time + "<br />" + "<a href=\"" + site + "\">" + site + "</a><br />" + content;

			System.out.println(site);
			MailUtil.sendHTML("sekift@163.com", title, msg);
//			MailUtil.sendHTML("niaoer2014@163.com", title, msg);
			//TODO 添加接收邮件的邮件

			// 139会有短信通知,过长不行
			String contents = HtmlUtil.filterHtmlTag(content, "UTF-8").replace("\n", "");
			contents = contents.replace(" ", "").replace("\t", "");
			msg = time + "<br />" + "<a href=\"" + site + "\">" + site + "</a><br />" + content;
			// MailUtil.sendHTML("13660708603@139.com", title, msg);
			//TODO 添加接收邮件的139邮件
		} catch (Exception e) {
			System.out.println("可能是mail的jar包冲突，请检查……");
			e.printStackTrace();
		}
	}
	
	// 短信通知 内容过滤
	private static void sendMessageByMobile(String time, String content, String site) {
		// 短信控制在70字以内
		String msg = null;
		site = site.replace("bbs.otianya.cn/cgi-bin/bbs.pl?url=http://", "");
		String contents = HtmlUtil.filterHtmlTag(content, "UTF-8").replace("\n", "");
		contents = contents.replace(" ", "").replace("\t", "");
		if (contents.length() >= 30) {
			contents = contents.substring(0, 30);
		}
		msg = time + " " + site + " " + contents;

		// TODO 添加发短信的接口
		String response = SendMessageTool.sendMessageByRemoteSMS("13660708603", msg);
		if (response.equals("1")) {
			System.out.println("发送短信成功");
		} else if (response.equals("0")) {
			System.out.println("发送短信失败");
			logger.error("发送短信失败" + 0);
		}
	}
	
	public static void main(String[] args) {
		Thread t = new Thread(new TianyaHongbao());
		// 初始化上次的信息
		for (int i = 0; i < fileList.size(); i++) {
			queueList.set(i, getQueue(fileList.get(i), queueList.get(i)));
			queueList.get(i).poll();
		}
		t.start();
		System.out.println("线程开始");
	}
}