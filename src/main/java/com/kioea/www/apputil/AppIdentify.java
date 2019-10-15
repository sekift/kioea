package com.kioea.www.apputil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kioea.www.encryptutil.EncryptMD5;
import com.kioea.www.jsonutil.JsonUtil;
import com.kioea.www.stringutil.StringUtil;

public class AppIdentify {
	
	Logger logger = LoggerFactory.getLogger(AppIdentify.class);
	
	//拆开各个产品的加密串	
	private static Map<String, String> product_keys = new HashMap<String, String>();
	//注册参数加密串
	private static Map<String, String> register_keys = new HashMap<String, String>();
	
	static{
		product_keys.put("product1", "lkjhgfd");	//产品1
		product_keys.put("product2", "lkjhgfd");	//产品2
		
		register_keys.put("product1_iPhone", "qwerwer");	//IOS
		register_keys.put("product1_android", "adfsdgd");	//安卓
		register_keys.put("product2_iPhone", "mnbbcxdf");	//IOS
		register_keys.put("product2_android", "zxcvncv");	//安卓
	}
	
	/** 
	* https验证
	* 
	* @author: sekift
	* @date: 2017-11-7 下午05:20:57
	*/
	private boolean validHttps(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String protocol = request.getHeader("Protocol");
		if(!"https".equals(protocol)){
			JsonUtil.getFailedResponse("0", "请使用https协议");
			return true;
		}
		return false;
	}
	
	/** 
	* 客户端验证:
	* 1、加密校验
	* 2、请求时间校验, 30秒有效
	* (lldb) po [request requestHeaders]
	*/
	private AppHeaderVo getHeader(HttpServletRequest request, HttpServletResponse response, boolean addSid) throws IOException{
		
		String c_uuid = request.getHeader("uuid");
		String c_channel = request.getHeader("channel");
		String c_mobile = request.getHeader("mobile");
		String c_vcode = request.getHeader("vcode");
		String c_vname = request.getHeader("vname");
		String c_os = request.getHeader("os");
		String c_product = request.getHeader("product");
		String c_hw = request.getHeader("hw");
		String c_ahw = request.getHeader("ahw");
		
		if(!product_keys.containsKey(c_product)){
			JsonUtil.getFailedResponse("0", "未知客户端请求");
			return null;
		}
		
		String validString = c_uuid + c_channel + c_mobile + c_vcode + c_vname + c_os + c_product + product_keys.get(c_product);
		
		if(!StringUtil.isNullOrBlank(c_hw)){
			validString += c_hw;
		}
		if(!StringUtil.isNullOrBlank(c_ahw)){
			validString += c_ahw;
		}
		
		String __sid = request.getParameter("uuid");
		if(!StringUtil.isNullOrBlank(__sid) && addSid){
			validString += __sid;
		}
		
		String headCstr = EncryptMD5.encryptMD5(validString, "utf-8");
		String c_headCstr = request.getHeader("headCstr");
		if(!headCstr.equals(c_headCstr)){
			JsonUtil.getFailedResponse("0", "客户端验证失败");
			return null;
		}
		AppHeaderVo app = new AppHeaderVo();
		app.setChannel(c_channel);
		app.setHeadCstr(c_headCstr);
		app.setMobile(c_mobile);
		app.setOs(c_os);
		app.setProduct(c_product);
		app.setUuid(c_uuid);
		app.setVcode(c_vcode);
		app.setVname(c_vname);
		app.setHw(c_hw);
		app.setAhw(c_ahw);
		return app;
	}
	
	/** 
	* 客户端接口参数的签名
	* md5（params+key+uuid）
	*/
	private boolean verifyParams(HttpServletResponse response, AppHeaderVo app, String signature, Object... params){
		
		if(StringUtil.isNullOrBlank(signature)){
			JsonUtil.getFailedResponse("0", "参数未签名");
			return false;
		}
		
		String md5Str = app.getProduct()+"_"+app.getOs();
		if(!register_keys.containsKey(md5Str)){
			JsonUtil.getFailedResponse("0", "未知客户端请求");
			return false;
		}
		
		StringBuffer strParams = new StringBuffer();
		for(Object parma: params){
			strParams.append(parma);
		}
		
		strParams.append(register_keys.get(md5Str));
		strParams.append(app.getUuid());
		
		String md5str = EncryptMD5.encryptMD5(strParams.toString(), "utf-8");
		if(md5str.equals(signature)){
			return true;
		}
		JsonUtil.getFailedResponse("0", "参数签名不正确");
		return false;
	}
}
