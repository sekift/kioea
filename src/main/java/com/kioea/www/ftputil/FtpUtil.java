package com.kioea.www.ftputil;

import org.apache.log4j.Logger;

import com.enterprisedt.net.ftp.FTPClient;
import com.enterprisedt.net.ftp.FTPConnectMode;
import com.enterprisedt.net.ftp.FTPMessageCollector;
import com.enterprisedt.net.ftp.FTPTransferType;

/**
 * ftl工具类
 * 
 * @author:sekift
 * @time:2015-3-5 下午03:03:02
 * @vertion 1.0.0
 */

public class FtpUtil {

	private static Logger log = Logger.getLogger(FtpUtil.class);
	
	/**
	 * 连接ftp servier
	 * @param host ftp ip地址
	 * @param port ftp端口
	 * @param username
	 * @param password
	 */
	public static FTPClient connetToServer(String host,Integer port,String username,String password){       
		FTPClient ftp=new FTPClient();        
        try {        	
        	ftp.setRemoteHost(host);
        	if(!port.equals(21)){
        		ftp.setRemotePort(port);
        	}else{
        		ftp.setRemotePort(21);
        	}
        	FTPMessageCollector listener = new FTPMessageCollector(); 
        	ftp.setMessageListener(listener);        	
        	ftp.connect();
        	// login     
        	log.warn("Logging in");     
        	ftp.login(username, password);            
        	// set up passive ASCII transfers        
        	log.warn("Setting up passive");       
        	ftp.setConnectMode(FTPConnectMode.PASV);
        } catch (Exception e) {
           log.error("",e);
        }
        return ftp;
	}
	/**
	 * 关闭连接
	 * @param ftp
	 */
	public static void shoutdownFtp(FTPClient ftp){
		// Shut down client
        log.warn("Quitting client");
        try {			
        	ftp.quit();
		} catch (Exception e) {
			log.error("",e);
		} 
	}
	/**
	 * 上传文件
	 * @param localFile 本地绝对路径及文件名
	 * @param remoteFile ftp登陆目录开始的相对路径及文件名,以/打头
	 * @param ftp
	 */
	public static boolean upload(String localFile,String remoteFile,FTPClient ftp){	
		boolean flag=false;
		try {
			ftp.setType(FTPTransferType.BINARY);
			String [] temp=ftp.dir();
			StringBuilder sb=new StringBuilder();
			if(temp!=null){
				for(String t:temp){
					sb.append(t+":");
				}
			}
			log.warn("ftpdir "+sb.toString());
			log.warn("transfer file begin "+localFile+" to "+remoteFile);
			ftp.put(localFile, remoteFile);			
			log.warn("transfer file end ");
			flag=true;
		} catch (Exception e) {
			log.error("",e);
			shoutdownFtp(ftp);
		}
		return flag;
	}
}
