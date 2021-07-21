package com.kioea.www.urlutil;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author:Administrator
 * @time:2015-9-6 下午05:12:55
 * @version:
 */
public class FetionSend10086cn {
	/**
	 * 下文中所有HTTP请求所指的Host都是f.10086.cn
	 * 
	 * @return
	 */
	public String FetionSendMessage() {
		/*
		 * 1.打开登录页面：GET
		 * /huc/user/space/login.do?m=submit&fr=space，获取两个cookie值：JSESSIONID和UUID
		 */
		String JSESSIONID = "F8E4B005EBC47B59FF4A04029E9E7810.2301";
		String UUID = "7271863a-d1e2-478b-804b-e3c143aff699";

		/*
		 * 2、登录：POST
		 * /huc/user/space/login.do，数据为手机号码和密码：mobilenum=your_phone_number
		 * &password
		 * =your_fetioon_password&m=submit&backurl=http%3A%2F%2Ff.10086.
		 * cn%2F&fr=space，获取cookie值：Set-Cookie:
		 * cell_cookie，注意：登录时，需要携带header：Content-Type:
		 * application/x-www-form-urlencoded，否则会触发验证码检查
		 */
		String loginUrl = "http://f.10086.cn/huc/user/space/login.do";
		Map<String, String> headers = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Cookie",
				"JSESSIONID=F8E4B005EBC47B59FF4A04029E9E7810.2301; UUID=7271863a-d1e2-478b-804b-e3c143aff699");

		params.put("mobilenum", "13660708603");
		params.put("password", "sekift123");
		params.put("m", "submit");
		params.put("backurl", "http%3A%2F%2Ff.10086.cn%2F");
		params.put("fr", "space");

		String response=HttpUtil.post(loginUrl, params, headers, 5000, 15 * 1000, "UTF-8");
		System.out.println(response);
		/*
		 * 2.1、上述步骤2会重定向到其他路径：Location:
		 * http://f.10086.cn/?nuc_id=5cb9e9eca65e2bc2875a6fe55869daa1
		 * ,4e8cbe602e50b189ddc33e51de704474,e017a7e72b081563f1fbf1263d2fd8f8,1
		 * 2.2、http://f.10086.cn/?nuc_id=5cb9e9eca65e2bc2875a6fe55869daa1,4e8
		 * cbe602e50b189ddc33e51de704474
		 * ,e017a7e72b081563f1fbf1263d2fd8f8,1<----这个会继续重定向到Location:
		 * http://f.10086.cn/wap2.jsp，同时会重置cookie值：JSESSIONID。wap2.jsp是一个新闻综合网页。
		 */

		/*
		 * 3、发送消息：POST /im/user/sendMsgToMyselfs.action HTTP/1.1，数据：msg=A hello
		 * word short message 3.1、上述步骤可能会被重定向到Location:
		 * http://f.10086.cn/im/login/cklogin.action?t=1420875966727，此时可以重新POST
		 * /im/user/sendMsgToMyselfs.action HTTP/1.1，即可发送成功。
		 */
		String sendUrl="http://f.10086.cn/im/user/sendMsgToMyselfs.action";
		Map<String, String> paramsSend = new HashMap<String, String>();
		
		paramsSend.put("msg", "给我自己发一条测试短信。");
		response=HttpUtil.post(sendUrl, params, headers, 5000, 15 * 1000, "UTF-8");
		System.out.println(response);
		
		response=HttpUtil.post(sendUrl, params, headers, 5000, 15 * 1000, "UTF-8");
		System.out.println(response);
		return null;
	}
	
	public static void main(String[] args){
		FetionSend10086cn fs=new FetionSend10086cn();
		fs.FetionSendMessage();
	}
}
