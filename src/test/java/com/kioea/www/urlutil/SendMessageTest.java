package com.kioea.www.urlutil;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.urlutil.SendMessageTool;

/**
 * 
 * @author:Administrator
 * @time:2015-9-6 上午10:01:31
 * @version:
 */
public class SendMessageTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSendMessageUseTxct(){
		System.out.println(SendMessageTool.sendMessageByTxct("13660708603", "你的验证码是458716，请尽快完成验证，有效期为3分钟。"));
	}
	
	@Test
	public void testSendMessageByKingtto(){
		System.out.println(SendMessageTool.sendMessageByKingtto("13660708603", "【嘿嘿网】","你的验证码是123456，有效期为3分钟。今晚吃什么菜啊？"));
	}
	
	@Test
	public void testSendMessageByChonry(){
		System.out.println(SendMessageTool.sendMessageByChonry("13660708603", "你的验证码是123456，有效期为3分钟。"));
	}
	
	@Test
	public void testSendMessageByLeyun(){
		System.out.println(SendMessageTool.sendMessageByLeyun("13902409246",  "嘿嘿，今晚吃什么菜啊？"));
	}
	
	@Test
	public void testSendMessageByRemoteSMS(){
		System.out.println(SendMessageTool.sendMessageByRemoteSMS("13660708603", "接口端测试"));
	}

}
