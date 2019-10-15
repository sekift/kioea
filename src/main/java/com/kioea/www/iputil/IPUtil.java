package com.kioea.www.iputil;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kioea.www.stringutil.StringUtil;

/**
 * IP相关类
 * 
 * @author:sekift
 * @time:2015-3-10 下午04:13:06
 * @version:
 */
public class IPUtil {

	private static final Logger logger = LoggerFactory.getLogger(IPUtil.class);

	private static List<InetAddress> localAddressList;

	static {
		// 初始化本地网络接口列表
		localAddressList = getLocalAddresses();
	}

	/**
	 * 把IP地址转换成整形
	 * 
	 * @param ip
	 * @return
	 */
	public static long convertTpToInt(String ip) {
		// 创建ip数组
		String[] ipArray = StringUtils.split(ip, ".");
		// 创建ip整形
		long ipInt = 0;
		// 对数组进行循环
		try {
			for (int i = 0; i < ipArray.length; i++) {
				if (ipArray[i] == null || ipArray[i].trim().equals("")) {
					ipArray[i] = "0";
				}
				if (new Integer(ipArray[i].toString()).intValue() < 0) {
					Double j = new Double(Math.abs(new Integer(ipArray[i].toString()).intValue()));
					ipArray[i] = j.toString();
				}
				if (new Integer(ipArray[i].toString()).intValue() > 255) {
					ipArray[i] = "255";
				}
			}
			ipInt = new Double(ipArray[0]).longValue() * 256 * 256 * 256 + new Double(ipArray[1]).longValue() * 256
					* 256 + new Double(ipArray[2]).longValue() * 256 + new Double(ipArray[3]).longValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回整形
		return ipInt;
	}

	/**
	 * 得到客户端的ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIntAddress(HttpServletRequest request) {
		return getUserIP(request);
	}

	/**
	 * 取得本机IP（多网卡主机，默认取非内网IP，如果非内网IP也有多个，取获取到的第一个）
	 * 
	 * @return
	 */
	public static String getLocalIP() {
		return getLocalIP(false);
	}

    /**
     * 取得本机IP地址 （多网卡主机返回其中一张网卡的IP）
     * 
     * @param isInter
     *            是否返回内网IP（默认网段为19.2.）
     * @return 本机ip地址
     */
	public static String getLocalIP(boolean isInter) {
		String localIP = "";
		for (InetAddress ia : localAddressList) {
			String ip = ia.getHostAddress();
			// 忽略ipv6和loopback地址
			if (ia instanceof Inet6Address || ip.startsWith("127")) {
				continue;
			}
			// 记录第一个找到的ipv4地址
			if (StringUtil.isNullOrBlank(localIP)) {
				localIP = ip;
			}
			if (isInter && ip.startsWith("19.")) {
				return ip;
			}
			if (!isInter && !ip.startsWith("19.")) {
				return ip;
			}
		}
		return localIP;
	}
	
	/**
	 * 判断是否为ip地址
	 * @param address
	 * @return
	 */
	public static boolean isIP(String address){
		Pattern ipPattern=Pattern.compile("\\b(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\\b");
		return ipPattern.matcher(address).matches();
	}

    /**
     * 取得本机网络地址列表
     * @return
     */
	public static List<InetAddress> getLocalAddresses(){
		if(localAddressList==null){
			localAddressList=new ArrayList<InetAddress>();
			try{
				//获取可用的网络接口
				Enumeration<NetworkInterface> interfaces=NetworkInterface.getNetworkInterfaces();
				while(interfaces!=null&&interfaces.hasMoreElements()){
					NetworkInterface interfaceN=interfaces.nextElement();
					//获取网络接口上的网络地址
					Enumeration<InetAddress> ienum=interfaceN.getInetAddresses();
					while(ienum.hasMoreElements()){
						InetAddress ia=ienum.nextElement();
						//添加网络地址到本机网络地址列表
						localAddressList.add(ia);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			logger.info("已完成本地网络接口列表初始化[{}]", localAddressList);
		}
		return localAddressList;
	}
	
	/**
	 * 获取用户终端的IP地址
	 * @param request
	 * @return
	 */
	public static String getUserIP(HttpServletRequest request){
		return getClientIP(request,0);
	}
	
    /**
     * 获取Wap调用的用户IP
     * @param request
     * @return
     */
    public static String getWapUserIP(HttpServletRequest request) {
        return getClientIP(request, 1);
    }

    /**
     * 获取最接近的客户端IP
     * @param request
     * @return
     */
    public static String getNearestClientIP(HttpServletRequest request) {
        return getClientIP(request, 2);
    }
    
    /**
     * 根据指定模式获取客户端IP
     * 
     * @param request
     *            http请求
     * @param mode
     *            0:user ip, 1:wap user ip, 2: nearest client ip
     * @return
     */
    public static String getClientIP(HttpServletRequest request,int mode){
    	String ip=request.getHeader("x-forwarded-for");
    	if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
    		ip=request.getHeader("Proxy-Client-IP");
    	}else{
    		String[] ips=ip.split(",");
    		for(int i=0;i<ips.length;i++){
    			ip=ips[i].trim();
    			switch(mode){
    			case 0:
    				//user ip
    				if (!(ip.startsWith("10.") || ip.startsWith("192.168") || ip.startsWith("172.16.")
                            || ip.startsWith("19.2.168") || ip.startsWith("19.2.169") || ip.startsWith("19.2.170")
                            || ip.startsWith("19.2.171") || ip.startsWith("19.2.172") || ip.startsWith("19.2.173")
                            || ip.startsWith("19.2.174") || "".equals(ip) || ip.equalsIgnoreCase("unknown"))) {
                        return ip.trim();
                    }
    				break;
    			case 1:
    				// wap user ip
                    if (!(ip.startsWith("192.168") || ip.startsWith("172.16.") || ip.startsWith("19.2.168")
                            || ip.startsWith("19.2.169") || ip.startsWith("19.2.170") || ip.startsWith("19.2.171")
                            || ip.startsWith("19.2.172") || ip.startsWith("19.2.173") || ip.startsWith("19.2.174")
                            || "".equals(ip) || ip.equalsIgnoreCase("unknown"))) {
                        return ip.trim();
                    }
                    break;
    			case 2:
    				//nearest client ip
    				if(!(ip.startsWith("10.") || ip.startsWith("192.168") || ip.startsWith("172.16.") || "".equals(ip) || ip
                            .equalsIgnoreCase("unknown"))){
    					return ip.trim();
    				}
    				break;
    			}
    			ip=null;
    		}
    	}
    	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        } else {
            return ip.trim();
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip.trim();
    }
    
    /**
     * 
     * 获取远程客户端端口
     * 
     * @param request
     *            http请求
     * @return int
     */
    public static int getUserPort(HttpServletRequest request) {
        String portStr = request.getHeader("Ty_Remote_Port");
        if (StringUtil.isNullOrBlank(portStr))
            return request.getRemotePort();
        try {
            return Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            return request.getRemotePort();
        }
    }
}
