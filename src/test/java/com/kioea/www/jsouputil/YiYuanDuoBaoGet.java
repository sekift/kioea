package com.kioea.www.jsouputil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.kioea.www.async.SleepUtil;
import com.kioea.www.jsonutil.JsonUtils;
import com.kioea.www.urlutil.GetURLContent;

/**
 * wangyi一元夺宝的所有ip获取
 * 
 * @author:Administrator
 * @time:2015-9-30 下午03:05:06
 * @version:
 */
public class YiYuanDuoBaoGet {
	// 网址base
	private static final String baseUrl = "http://1.163.com";

	// 文件夹base
	private static final String baseFilePath = "D:\\yiyuanduobao";

	// 1 首先获取所有的gid
	public void getGid(String url) {
		// url = http://1.163.com/list/0-0-1-" + i + ".html
		String result = null;
		String html = GetURLContent.getPageContent(url);
		Document doc = Jsoup.parse(html);
		Elements eles = doc.getElementsByClass("w-goods-pic");
		String gid = null;
		for (int i = 0; i < eles.size(); i++) {
			gid = eles.get(i).select("a").attr("href");
			result = gid.replace("/detail/", "").replace(".html", "");
			// 写入文件
			try {
				FileUtils.writeStringToFile(new File(baseFilePath + "\\gid.txt"), result + "\n", "utf-8", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// all gid
	private static final String[] gidList = { "54", "70", "117", "119", "128", "133", "137", "140", "141", "145",
			"148", "160", "164", "194", "201", "206", "212", "215", "216", "218", "219", "224", "330", "336", "340",
			"342", "347", "348", "351", "352", "354", "359", "360", "375", "385", "419", "422", "432", "437", "438",
			"441", "442", "443", "444", "446", "451", "455", "458", "459", "483", "485", "502", "510", "516", "517",
			"519", "520", "526", "535", "544", "545", "546", "547", "550", "552", "560", "564", "569", "582", "590",
			"597", "610", "611", "612", "651", "654", "702", "703", "736", "741", "748", "754", "758", "768", "770",
			"771", "774", "775", "813", "830", "831", "895", "896", "897", "898", "931", "933", "935", "940", "961",
			"964", "970", "975", "976", "978", "979", "980", "992", "1061", "1093", "1096", "1100", "1115", "1116",
			"1142", "1164", "1197", "1212", "1217", "1231", "1232", "1233", "1234", "1235", "1295", "1300", "1310",
			"1311", "1312", "1318", "1319", "1323", "1324", "1357", "1378", "1380", "1409", "1440", "1441", "1443",
			"1445", "1450", "1536", "1538", "1539", "1540", "1541", "1542", "1543", "1544", "1545", "1546", "1548",
			"1551", "1552", "1561", "1565", "1566", "1572", "1585", "1587", "1588", "1710", "1711", "1715", "1717",
			"1720", "1722", "1727", "1728", "1731", "1732", "1740", "1744", "1746", "1747", "1749", "1751", "1753",
			"1799", "1800", "1801", "1816", "1817", "1818", "1819", "1820", "1822", "1824", "1825", "1826", "1830",
			"1832", "1833", "1834", "1840", "1846", "1848", "1850", "1851", "1852", "1855", "1856", "1857", "1858",
			"1859", "1860", "1861", "1862", "1872", "1874", "1896", "1897", "1899", "1900", "1901", "1902", "1919",
			"1920", "1923", "1924", "1926", "1927", "1928", "1929", "1930", "1931", "1939", "1940", "1941", "1943",
			"1945", "1951", "1959", "1960", "1961", "1962", "1964", "1966", "1968", "1969", "1971", "1973", "1986",
			"1987", "1988", "1997", "1999", "2002", "2003", "2004", "2005", "2006", "2009", "2010", "2011", "2012",
			"2013", "2019", "2027", "2031", "2033", "2069", "2071", "2072", "2073", "2074", "2103", "2109", "2113",
			"2114", "2115", "2119", "2121", "2122", "2128", "2130", "2150", "2151", "2152", "2153", "2155", "2160",
			"2161", "2162", "2165", "2167", "2168", "2169", "2170", "2171", "2184", "2185", "2186", "2188", "2291",
			"2293", "2294", "2298", "2301", "2302", "2305", "2306", "2307", "2308", "2311", "2312", "2313", "2318",
			"2319", "2320", "2321", "2322", "2323", "2325", "2329", "2331", "2332", "2334", "2335", "2342", "2347",
			"2349", "2352", "2353", "2354", "2355", "2356", "2357", "2359", "2361", "2363", "2364", "2366", "2367",
			"2371", "2372", "2373", "2374", "2375", "2376", "2377", "2378", "2379", "2380", "2381", "2384", "2385",
			"2388", "2389", "2391", "2392", "2393", "2394", "2396", "2397", "2400", "2401", "2402", "2403", "2404",
			"2405", "2407", "2408", "2409", "2415", "2416", "2417", "2418", "2419", "2422", "2425", "2427", "2429",
			"2431", "2433", "2434", "2435", "2439", "2440", "2444", "2449", "2451", "2452", "2461", "2463", "2464",
			"2465", "2466", "2470", "2471", "2472", "2473", "2474", "2475", "2479", "2482", "2483", "2484", "2485",
			"2486", "2487", "2489", "2490", "2491", "2492", "2493", "2496", "2497", "2498", "2499", "2500", "2501",
			"2502", "2506", "2507", "2508", "2513", "2514", "2862", "2885", "2887", "2888", "2889", "2891", "2894",
			"2895" };

	// 2 get all period 期数
	public void getPeriod(String url) {
		// http://1.163.com/win/getList.do?pageNum=6&pageSize=10&gid=437
		String html = GetURLContent.getPageContent(url);
		// 写入文件
		try {
			FileUtils.writeStringToFile(new File(baseFilePath + "\\period.txt"), url + "\t" + html + "\r\n", "utf-8",
					true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 3 释出gid对应的period
	public void getPeriodFromGid() {
		String path = baseFilePath + "\\整理后的period.txt";
		try {
			LineIterator it = FileUtils.lineIterator(new File(path), "gbk");
			while (it.hasNext()) {
				String userIdS = it.nextLine();
				String[] strArray = userIdS.split("\t");
				String gid = strArray[0];
				Map responseMap = (Map) JsonUtils.toBean(strArray[1], Map.class);
				Map resultMap = (Map) responseMap.get("result");
				List resultList = (List) resultMap.get("list");
				Object period = null;
				for (Object o : resultList) {
					Map map = (Map) o;
					period = map.get("period");
					FileUtils.writeStringToFile(new File(baseFilePath + "\\gidAndPeriod.txt"), gid + "\t" + period
							+ "\r\n", "utf-8", true);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 4 换取详细记录
	public void getRecordFromGidAndPeriod() {
		String path = baseFilePath + "\\gidAndPeriod.txt";
		String url = "http://1.163.com/record/getDuobaoRecord.do?pageSize=50&totalCnt=0";// &pageNum=2&gid=1919&period=309211583";
		String urlBefore = null;
		String urlAfter = null;
		try {
			LineIterator it = FileUtils.lineIterator(new File(path), "utf-8");
			int k=0;
			while (it.hasNext()) {
				String userIdS = it.nextLine();
				String[] strArray = userIdS.split("\t");

				// url:http://1.163.com/record/getDuobaoRecord.do?pageNum=2&pageSize=50&totalCnt=0&gid=1919&period=309211583
				urlBefore = url + "&gid=" + strArray[0] + "&period=" + strArray[1];
				int j = 0;
				for (int i = 1; i < 500; i++) {
					if ("309240287".equals(strArray[1])) {
						j = i + 8;
					} else 
						j = i;
					urlAfter = urlBefore + "&pageNum=" + j;
					String html = GetURLContent.getPageContent(urlAfter);
					
					FileUtils.writeStringToFile(new File(baseFilePath + "\\record2482.txt"), urlAfter + "\t" + html + "\r\n",
							"utf-8", true);
					if(html == null){
						System.out.println("urlAfter="+urlAfter+";html=null");
						continue;
					}
					Map responseMap = (Map) JsonUtils.toBean(html, Map.class);
					Map resultMap = (Map) responseMap.get("result");
					int totalCnt = (Integer) resultMap.get("totalCnt");
					System.out.println("urlAfter====" + urlAfter+" ;total=" + totalCnt);
					SleepUtil.sleepBySecond(5, 9);
					if (totalCnt / 50.0 < i) {
						break;
					}
				}
				k++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		YiYuanDuoBaoGet db = new YiYuanDuoBaoGet();
		db.getRecordFromGidAndPeriod();
		
		// 1.get gid
		// for (int i = 1; i < 12; i++) {
		// db.getGidFromUrl("http://1.163.com/list/0-0-1-" + i + ".html");
		// }

		// 2
		/*
		 * String url = null; for (String listEle : gidList) { for (int i = 1; i
		 * <= 5; i++) { url = "http://1.163.com/win/getList.do?pageNum=" + i +
		 * "&pageSize=10&gid=" + listEle; db.getPeriod(url);
		 * SleepUtil.sleepBySecond(5, 15); } SleepUtil.sleepBySecond(2, 8); }
		 */

		// String url =
		// "http://1.163.com/record/getDuobaoRecord.do?pageNum=2&pageSize=50&totalCnt=0&gid=1919&period=309211583";
		// String url =
		// "http://1.163.com/win/getList.do?pageNum=2&pageSize=10&totalCnt=29&gid=2325&period=309202149";
		// String result = GetURLContent.getPageContent(url);
		// System.out.println(result);
	}
}
