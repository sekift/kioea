package com.kioea.www.urlutil;

import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Cookie操作类，封装cookie操作，支持key-value格式的cookie。
 */
public class CookieUtil {

	private static Logger logger = Logger.getLogger(CookieUtil.class);

	public static String getCookieValue(Cookie[] cookies, String cookieName) {
		if (cookies == null) {
			return "";
		}

		String value = null;
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName())) {
				value = cookie.getValue();
				value = unescape(value);
				break;
			}
		}

		if (value == null) {
			return "";
		}

		return value;
	}

	/**
	 * 根据cookie name 获得其值 cookie格式为:cookieName=w=xxx&id=xxx
	 * 
	 * @param cookies
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(Cookie[] cookies, String cookieName, String key) {
		if (cookies == null) {
			return "";
		}

		String cookieStr = null;
		String value = "";
		try {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					cookieStr = cookie.getValue();
					cookieStr = unescape(cookieStr);
					break;
				}
			}

			if (cookieStr == null || "".equals(cookieStr)) {
				return "";
			}

			String validKey = key + "=";

			// 对cookieStr进行解析
			StringTokenizer st = new StringTokenizer(cookieStr, "&");
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				if (token.indexOf(validKey) != -1) {
					value = token.substring(token.indexOf("=") + 1, token.length());

					value = unescape(value);
					break;
				}

			}
		} catch (Exception ex) {
			logger.error("解析cookie出错:cookieStr=" + cookieStr, ex);
			value = "";
		}

		return value;
	}

	/**
	 * 实现对应的JavaScript escape函数．
	 * 
	 * @param src
	 *            要编码的字符串。
	 * @return
	 */
	public static String escape(String src) {
		if (src == null) {
			return null;
		}

		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (j < 256) { // 1字节
				// 字母或者数字
				if (Character.isLetterOrDigit(j)) {
					tmp.append(j);
				} else {
					tmp.append("%u00");

					// 0.5个字节
					if (j < 16) {
						tmp.append("0");
					}

					tmp.append(Integer.toString(j, 16));
				}
			} else {
				tmp.append("%u");

				// 1.5个字节
				if (j < 0x1000) {
					tmp.append("0");
				}

				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 实现对应的JavaScript unescape函数．
	 * 
	 * @param src
	 *            要解码的字符串。
	 * @return
	 */
	public static String unescape(String src) {
		if (src == null) {
			return null;
		}

		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}

		return tmp.toString();
	}

	/**
	 * 读取所有cookie 注意二、从客户端读取Cookie时，包括maxAge在内的其他属性都是不可读的，也不会被提交。
	 * 浏览器提交Cookie时只会提交name与value属性。maxAge属性只被浏览器用来判断Cookie是否过期
	 * 
	 * @param request
	 * @param response
	 */
	public void showCookies(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (null == cookies) {
			System.out.println("没有cookie=========");
		} else {
			for (Cookie cookie : cookies) {
				System.out.println("name:" + cookie.getName() + ",value:" + cookie.getValue());
			}
		}

	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	public void addCookie(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name.trim(), value.trim());
		cookie.setMaxAge(30 * 60);// 设置为30min
		cookie.setPath("/");
		System.out.println("已添加===============");
		response.addCookie(cookie);
	}

	/**
	 * 修改cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 *            注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等
	 *            ，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
	 */
	public void editCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			System.out.println("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					System.out.println("原值为:" + cookie.getValue());
					cookie.setValue(value);
					cookie.setPath("/");
					cookie.setMaxAge(30 * 60);// 设置为30min
					System.out.println("被修改的cookie名字为:" + cookie.getName() + ",新值为:" + cookie.getValue());
					response.addCookie(cookie);
					break;
				}
			}
		}

	}

	/**
	 * 删除cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	public void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			System.out.println("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath("/");
					System.out.println("被删除的cookie名字为:" + cookie.getName());
					response.addCookie(cookie);
					break;
				}
			}
		}
	}
}