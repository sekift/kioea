package com.kioea.www.jsouputil;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * 
 * @author:sekift
 * @time:2015-2-6 上午11:30:11
 * @version:
 */
public class MailUtil {
    private static final String hostName = "smtp.qq.com";
    private static final String userName ="3300587869@qq.com";
    private static final String password ="sekift123";
    
    private static final String from = "3300587869@qq.com";
    private static final String to = "sekift@163.com";
    private static final String charset = "utf-8";
     
    //simple
    public static void sendSimple(String subject,String msg){
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName(hostName);
            email.setFrom(from);
            email.addTo(to);
            email.setAuthentication(userName, password);
            email.setCharset(charset);
            email.setSubject(subject);
            email.setMsg(msg);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
    
    //带邮箱的html
    public static void sendHTML(String tomail,String subject,String msg){
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(hostName);
            email.setFrom(from);
            email.addTo(tomail);
            email.setAuthentication(userName, password);
            email.setCharset(charset);
            email.setSubject(subject);
            email.setMsg(msg);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
    
    //html
    public static  void sendHTML(String subject,String msg){
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(hostName);
            email.setFrom(from);
            email.addTo(to);
            email.setAuthentication(userName, password);
            email.setCharset(charset);
            email.setSubject(subject);
            email.setMsg(msg);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
     
    //attach
    public static void sendAttach(String subject,String msg){
        try {
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName(hostName);
            email.setFrom(from);
            email.addTo(to);
            email.setAuthentication(userName, password);
            email.setCharset(charset);
            email.setSubject(subject);
            email.setMsg(msg);
            
            EmailAttachment attach = new EmailAttachment();
            attach.setPath("");
            attach.setDisposition(EmailAttachment.ATTACHMENT);
            attach.setName("a");
            email.attach(attach);
             
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args) {
    	MailUtil mail = new MailUtil();
        String site = "http://searchex.yixun.com/html?path=706019t706023&attr=55e3506&area=1&sort=0&show=0&size=40&pf=0&as=0&charset=utf-8&YTAG=3.706023241101&sf=1#list";
        mail.sendHTML("测试", "<p><a href='"+site+"'>易迅抢购?????</a></p>");
        System.out.println("over");
    }  

}