package com.kioea.www.iputil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kioea.www.stringutil.StringUtil;

/**
 * 
 * @author:sekift
 * @time:2015-8-18 上午11:34:12
 * @version:
 */
public class URLIPUtil {

	private static final Logger logger=LoggerFactory.getLogger(URLIPUtil.class);
	 /**
     * 根据IP和端口串返回IP地址
     * 
     * @param iPPort
     *            -- IP和端口串,例如127.0.0.1:3308
     * @return  返回字符串形式的IP
     */
    private static String getNodeIp(String jdbcUrl) {
        if(StringUtil.isNullOrBlank(jdbcUrl)){
            return "";
        }
        String iPPort = getIpPortString(jdbcUrl);
        if (StringUtil.isNullOrBlank(iPPort)) {
            return "";
        }
        String[] iPPorts = iPPort.split(":");
        if(iPPorts!=null&&iPPorts.length > 0){
            return iPPorts[0];            
        }else{
            return "";
        }

    }
    
    /**
     * 根据IP和端口串返回端口
     * 
     * @param iPPort
     *            -- IP和端口串,例如127.0.0.1:3308
     * @return 返回字符串形式的port
     */
    private static String getNodePort(String jdbcUrl) {
        String defaultPort="";
        if(StringUtil.isNullOrBlank(jdbcUrl)){
            return "0";
        }
        if(jdbcUrl.contains("mysql")){
            defaultPort = "3306";
        }else if(jdbcUrl.contains("sqlserver")){
            defaultPort = "1433";
        }
        String ipPortString = getIpPortString(jdbcUrl);

        if (StringUtil.isNullOrBlank(ipPortString)) {
            return "0";
        }
        String[] iPPorts = ipPortString.split(":");
        if (2 != iPPorts.length){
            logger.warn("getNodePort()提示:jdbc url没有正确配置数据库服务端口,使用默认端口:"+defaultPort+". 请检查配置是否配置了服务端口!");
            return defaultPort;            
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(iPPorts[1]);
        if( !isNum.matches() ){
            logger.warn("getNodePort()提示:端口:"+iPPorts[1]+"包含非数字字符,请检查配置是否正确配置了服务端口!");
        }
        return iPPorts[1];
    }
	
	/**
     * 用于从jdbc url 获取ip和端口串
     * 
     * @param jdbcDriverUrl
     *            -- 数据库的jdbc连接url串
     * @return IP和port串,例如127.0.0.1:3308
     */
    private static String getIpPortString(String jdbcDriverUrl) {

        if (StringUtil.isNullOrBlank(jdbcDriverUrl)) {
            return "";
        }
        String[] tmp2 = null;
        String[] tmp1 = jdbcDriverUrl.split("//");
        if(null==tmp1||tmp1.length==0){
            return "";
        }
        if(jdbcDriverUrl.contains("mysql")){
            tmp2 = tmp1[1].split("/");
        }else if(jdbcDriverUrl.contains("sqlserver")){
            tmp2 = tmp1[1].split(";");
        }
        if(null==tmp2||tmp2.length==0){
            return "";
        }
        String ipPortString = tmp2[0];
        return ipPortString;
    }
    
    
    
	public static void main(String[] args) {
		String jdbcDriverUrl="jdbc:mysql://localhost:3306/test";
		System.out.println(getIpPortString(jdbcDriverUrl));
		System.out.println(getNodeIp(jdbcDriverUrl));
		System.out.println(getNodePort(jdbcDriverUrl));
	}

}
