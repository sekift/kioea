package com.kioea.www.jsouputil;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTest2 {

	/**
	 * 使用前
	 * 1 发送的邮件需要开启smtp功能。有设置的。默认关闭
	 * 2 当前电脑关闭杀毒软件，如qq卫士什么的
	 * 
	 */
	static int port = 25;// 这里是25而不是其他的
	static String server = "smtp.qq.com";// 邮件服务器mail.cpip.net.cn
	static String from = "【时间过客】";// 发送者,显示的发件人名字
	static String user = "3300587869@qq.com";// 发送者邮箱地址
	static String password = "sekift123";// 

	public static void sendEmail(String email, String subject, String body) throws UnsupportedEncodingException {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", server);
			props.put("mail.smtp.port", String.valueOf(port));
			props.put("mail.smtp.auth", "true");
			Transport transport = null;
			Session session = Session.getDefaultInstance(props, null);
			transport = session.getTransport("smtp");
			transport.connect(server, user, password);
			MimeMessage msg = new MimeMessage(session);
			msg.setSentDate(new Date());
			InternetAddress fromAddress = new InternetAddress(user, "OSF管理中心", "UTF-8");
			msg.setFrom(fromAddress);
			InternetAddress[] toAddress = new InternetAddress[1];
			toAddress[0] = new InternetAddress(email);
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.saveChanges();
			transport.sendMessage(msg, msg.getAllRecipients());
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws UnsupportedEncodingException {
		sendEmail("574919797@qq.com", "修改测试", "这是一封测试邮件");// 收件人，名称，内容
		System.out.println("ok");
	}
}
