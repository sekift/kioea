package com.kioea.www.urlutil;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kioea.www.jsonutil.JsonUtil;
import com.kioea.www.jsonutil.JsonUtils;
import com.kioea.www.jsouputil.JsoupUtil;

/**
 * 提供获取URL的IP的类
 * 
 * @author:sekift
 * @time:2014-7-8 下午03:38:14
 */
public class URLAPITool {

	public static final Logger logger = LoggerFactory.getLogger(URLAPITool.class);

	/**
	 * 获取方法
	 * 
	 * @param urlName
	 * @return
	 */
	public static List<String> getIPFromURL(String urlName) {
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			InetAddress[] address = InetAddress.getAllByName(urlName);
			for (int i = 0; i < address.length; i++) {
				list.add(address[i].getHostAddress());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.exit(1);
			logger.error("[sekiftutil]根据URL获取IP错误", e);
		}
		return list;
	}

	/**
	 * 根据IP地址获取地理位置
	 * 
	 * @param ip
	 */
	public String getAddressByIP(String ip) {
		String url = "http://www.ip138.com/ips138.asp?ip=" + ip;
		Elements eles = JsoupUtil.getByAttrClass(url, "ul1");
		return eles.text();
	}

	/**
	 * 获取手机号码归属地 IP138
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public String getAddressByPhoneFromIP138(String phoneNumber) {
		String url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=" + phoneNumber;
		Elements eles = JsoupUtil.getByAttrClass(url, "tdc2");
		StringBuffer sb = new StringBuffer();
		sb.append(phoneNumber + " ");
		int i = 0;
		for (Element ele : eles) {
			i++;
			if (i > 1) {
				sb.append(ele.text() + " ");
			}
		}
		String result=sb.toString().substring(0, sb.length() - 7);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", result);
		return JsonUtil.getSuccessResponse("1", "成功", map);
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		int[] mobile={1700,1701,1702,1705,1707,1708,1709};
		URLAPITool uat=new URLAPITool();
		String mobileP=null;
		for(int l=0;l<mobile.length;l++){
			for(int i=0;i<=9;i++){
				for(int j=0;j<=9;j++){
					for(int k=0;k<=9;k++){
						mobileP=mobile[l]+""+i+j+k;
						//System.out.println(uat.getAddressByPhoneFromIP138(mobileP));
						try {
							String result=uat.getAddressByPhoneFromIP138(mobileP);
							Map<String,Object> map = JsonUtils.toBean(result,Map.class);
							FileUtils.writeStringToFile(new File("D://170mobile.txt"), map.get("data")+"\r\n", "utf-8",true);
							//Thread.sleep(RandomTool.randomInt(10, 20));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	/**
	 * 获取手机号码归属地 淘宝
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public String getAddressByPhoneFromTB(String phoneNumber) {
		String url = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + phoneNumber;
		return JsoupUtil.getDocByConnectIgnoreContent(url).text();
	}

	/**
	 * 快递接口 ps:快递公司编码:申通="shentong" EMS="ems" 顺丰="shunfeng" 圆通="yuantong"
	 * 中通="zhongtong" 韵达="yunda" 天天="tiantian" 汇通="huitongkuaidi"
	 * 全峰="quanfengkuaidi" 德邦="debangwuliu" 宅急送="zhaijisong"
	 * 
	 */
	public String getExpress(String name, String number) {
		String url = "http://www.kuaidi100.com/query?type=" + name + "&postid=" + number;
		return JsoupUtil.getDocByConnect(url).text();
	}

	/**
	 * 从百度获取短网址服务-获取短网址
	 * @param urlName
	 * @return
	 */
	public String getShortURLFromDwz(String urlName) {
		String url = "http://www.dwz.cn/create.php";
		Map<String, String> params = new HashMap<String, String>();
		params.put("url", urlName);
		String response = HttpUtil.post(url, params, null, 10 * 3600, 10 * 3600, "utf-8");
		return response;
	}
	
	/**
	 * 从百度获取短网址服务-获取长网址
	 * @param urlName
	 * @return
	 */
	public String getLongURLFromDwz(String urlName) {
		String url = "http://www.dwz.cn/query.php";
		Map<String, String> params = new HashMap<String, String>();
		params.put("tinyurl", urlName);
		String response = HttpUtil.post(url, params, null, 10 * 3600, 10 * 3600, "utf-8");
		return response;
	}

	/**
	 * 获取qq信息 已失效
	 * 
	 */
	public String getQQInfo(String number) {
		String url = "http://r.qzone.qq.com/cgi-bin/user/cgi_personal_card?uin=" + number;
		String result = JsoupUtil.getDocByConnectIgnoreContent(url).body().text();
		return result;
	}
	
	/**
	 * 获取笑话
	 * @param number
	 * @return
	 */
	public String getJokeFromSinaapp() {
		String url = "http://apix.sinaapp.com/joke/?appkey=trialuser";
		String result = JsoupUtil.getDocByConnectIgnoreContent(url).body().text();
		return result;
	}
	
}
