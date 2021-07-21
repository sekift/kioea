package com.kioea.www.urlutil;

import java.net.HttpURLConnection;
import java.net.URL;

import com.kioea.www.stringutil.StringUtil;

/**
 * 检测当前URL是否可连接或是否有效, 最多连接网络 5 次, 如果 5 次都不成功说明该地址不存在或视为无效地址.
 * 
 * @author 作者:sekift
 * @author E-mail:sekiftlyz@gmail.com
 * @version 创建时间：2014-8-10 上午02:16:03
 */
public class URLConnectUtil {
	public static URL urlStr;
	public static HttpURLConnection connection;
	public static int state = -1;
	public static String succ;
	public static long length = 0L;

	/**
	 * 返回
	 * 
	 * @param url
	 * @return boolean
	 */
	public static synchronized String isConnectByUrl(String url) {
		int counts = 0;
		succ = null;
		if (StringUtil.isNullOrBlank(url)) {
			return succ;
		}
		while (counts < 5) {
			try {
				urlStr = new URL(url);
				connection = (HttpURLConnection) urlStr.openConnection();
				state = connection.getResponseCode();
				length = connection.getContentLength();
				if (state == 200) {
					succ = connection.getURL().toString();
				}
				break;
			} catch (Exception ex) {
				counts++;
				continue;
			}
		}
		return succ;
	}

	/**
	 * boolean值的判断
	 * 
	 * @param url
	 * @return boolean
	 */
	public static synchronized boolean isConnect(String url) {
		int counts = 0;
		boolean result = false;
		if (StringUtil.isNullOrBlank(url)) {
			return result;
		}
		while (counts < 5) {
			try {
				urlStr = new URL(url);
				connection = (HttpURLConnection) urlStr.openConnection();
				state = connection.getResponseCode();
				length = connection.getContentLength();
				if (state == 200) {
					result = true;
				}
				break;
			} catch (Exception ex) {
				counts++;
				continue;
			}
		}
		return result;
	}
	
	/**
	 * 返回码
	 * 
	 * @param url
	 * @return boolean
	 */
	public static synchronized int getStatus(String url) {
		int counts = 0;
		int result = 0;
		if (StringUtil.isNullOrBlank(url)) {
			return result;
		}
		while (counts < 3) {
			try {
				urlStr = new URL(url);
				connection = (HttpURLConnection) urlStr.openConnection();
				result = connection.getResponseCode();
				break;
			} catch (Exception ex) {
				counts++;
				continue;
			}
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public static void getResponseReason(String url) {

		try {
			urlStr = new URL(url);
			connection = (HttpURLConnection) urlStr.openConnection();
			state = connection.getResponseCode();
			length = connection.getContentLength();
		} catch (Exception e) {
			e.printStackTrace();
		}

		succ=isConnectByUrl(url);
		if (state == HttpURLConnection.HTTP_OK) {
			if (succ.equals(url)) {
				System.out.println(succ + " 连接成功！");
				if (length == -2) {
					System.out.println("该链接虽然可以连接，但是内容可能已被删除！");
				}
			} else {
				System.out.println("链接跳转到 " + succ + " 连接成功！");
			}
		} else if (state == HttpURLConnection.HTTP_CREATED) {
			System.out.println(succ + " 请求已经被实现，而且有一个新的资源已经依据请求的需要而建立!");
		} else if (state == HttpURLConnection.HTTP_ACCEPTED) {
			System.out.println(succ + " 已接受请求，但尚未处理!");
		} else if (state == HttpURLConnection.HTTP_NOT_AUTHORITATIVE) {
			System.out.println(succ + " 已成功处理了请求，但返回的实体头部元信息不是在原始服务器上有效的确定集合，而是来自本地或者第三方的拷贝。");
		} else if (state == HttpURLConnection.HTTP_NO_CONTENT) {
			System.out.println(succ + " 已成功处理了请求，但不需要返回任何实体内容，并且希望返回更新了的元信息。");
		} else if (state == HttpURLConnection.HTTP_RESET) {
			System.out.println(succ + " 已成功处理了请求，且没有返回任何内容。");
		} else if (state == HttpURLConnection.HTTP_PARTIAL) {
			System.out.println(succ + " 已经成功处理了部分 GET 请求。");
		} else if (state == 207) {
			System.out.println(succ + " 已经成功处理请求,信息将是一个XML消息。");
		} else if (state == HttpURLConnection.HTTP_MULT_CHOICE) {
			System.out.println(succ + " 存在多个可用的被请求资源。");
		} else if (state == HttpURLConnection.HTTP_MOVED_PERM) {
			System.out.println(succ + " 请求到的资源会分配一个永久的URL。");
		} else if (state == HttpURLConnection.HTTP_MOVED_TEMP) {
			System.out.println(succ + " 请求到的资源在一个不同的URL处临时保存。");
		} else if (state == HttpURLConnection.HTTP_SEE_OTHER) {
			System.out.println(succ + " 请求的响应可以在另一个 URI 上被找到，应当采用 GET 的方式访问那个资源。");
		} else if (state == HttpURLConnection.HTTP_NOT_MODIFIED) {
			System.out.println(succ + " 请求到的资源没有更新。");
		} else if (state == HttpURLConnection.HTTP_USE_PROXY) {
			System.out.println(succ + " 被请求的资源必须通过指定的代理才能被访问。");
		} else if (state == 307) {
			System.out.println(succ + " 请求的资源现在临时从不同的URI 响应请求。");
		} else if (state == HttpURLConnection.HTTP_BAD_REQUEST) {
			System.out.println(succ + " 语言有误或请求参数有误，请检查你的链接。");
		} else if (state == HttpURLConnection.HTTP_UNAUTHORIZED) {
			System.out.println(succ + " 未授权。");
		} else if (state == HttpURLConnection.HTTP_PAYMENT_REQUIRED) {
			System.out.println(succ + " 预留的状态码。");
		} else if (state == HttpURLConnection.HTTP_FORBIDDEN) {
			System.out.println(succ + " 已经理解请求，但是拒绝执行或该网站已被禁止！");
		} else if (state == HttpURLConnection.HTTP_NOT_FOUND) {
			System.out.println(succ + " 连接失败,NO FOUND！");
		} else if (state == HttpURLConnection.HTTP_BAD_METHOD) {
			System.out.println(succ + " 请求行中指定的请求方法不能被用于请求相应的资源。");
		} else if (state == HttpURLConnection.HTTP_NOT_ACCEPTABLE) {
			System.out.println(succ + " 请求的资源的内容特性无法满足请求头中的条件，因而无法生成响应实体。");
		} else if (state == HttpURLConnection.HTTP_PROXY_AUTH) {
			System.out.println(succ + " 请进行身份认证……");
		} else if (state == HttpURLConnection.HTTP_CLIENT_TIMEOUT) {
			System.out.println(succ + " 请求超时,你可再次提交请求！");
		} else if (state == HttpURLConnection.HTTP_CONFLICT) {
			System.out.println(succ + " 由于和被请求的资源的当前状态之间存在冲突，请求无法完成。");
		} else if (state == HttpURLConnection.HTTP_GONE) {
			System.out.println(succ + " 被请求的资源已经不再可用，而且没有任何已知的转发地址。");
		} else if (state == HttpURLConnection.HTTP_LENGTH_REQUIRED) {
			System.out.println(succ + " 拒绝在没有定义 Content-Length 头的情况下接受请求。");
		} else if (state == HttpURLConnection.HTTP_PRECON_FAILED) {
			System.out.println(succ + " 验证在请求的头字段中给出先决条件时，没能满足其中的一个或多个。");
		} else if (state == HttpURLConnection.HTTP_ENTITY_TOO_LARGE) {
			System.out.println(succ + " 服务器拒绝处理当前请求，因为该请求提交的实体数据大小超过了服务器愿意或者能够处理的范围。");
		} else if (state == HttpURLConnection.HTTP_REQ_TOO_LONG) {
			System.out.println(succ + " 请求的URI 长度超过了服务器能够解释的长度，因此服务器拒绝对该请求提供服务。");
		} else if (state == HttpURLConnection.HTTP_UNSUPPORTED_TYPE) {
			System.out.println(succ + " 对于当前请求的方法和所请求的资源，请求中提交的实体并不是服务器中所支持的格式，因此请求被拒绝。");
		} else if (state == 416) {
			System.out.println(succ + " 响应被禁止使用。");
		} else if (state == 417) {
			System.out.println(succ + " 请求的内容无法被满足，不可显示。");
		} else if (state == 421) {
			System.out.println(succ + " 当前的IP地址连接数超过了许可的最大范围。");
		} else if (state == 422) {
			System.out.println(succ + " 请求格式正确，但是由于含有语义错误，无法响应或当期资源被锁定。");
		} else if (state == 424) {
			System.out.println(succ + " 由于之前的某个请求发生的错误，导致当前请求失败。");
		} else if (state == 426) {
			System.out.println(succ + " 应当切换到TLS/1.0……");
		} else if (state == 449) {
			System.out.println(succ + " 代表请求应当在执行完适当的操作后进行重试。");
		} else if (state == HttpURLConnection.HTTP_SERVER_ERROR) {
			System.out.println(succ + " 无法完成对请求的处理。");
		} else if (state == HttpURLConnection.HTTP_NOT_IMPLEMENTED) {
			System.out.println(succ + " 不支持当前请求所需要的某个功能。");
		} else if (state == HttpURLConnection.HTTP_BAD_GATEWAY) {
			System.out.println(succ + " 作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应。");
		} else if (succ == null) {
			if (state == HttpURLConnection.HTTP_UNAVAILABLE) {
				System.out.println(succ + " 由于临时的服务器维护或者过载，服务器当前无法处理请求!");
			} else if (state != HttpURLConnection.HTTP_UNAVAILABLE) {
				System.out.println(succ + " 请检查你的网络……");
			}
		} else if (state == HttpURLConnection.HTTP_GATEWAY_TIMEOUT) {
			System.out.println(succ
					+ " 作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS）收到响应。");
		} else if (state == HttpURLConnection.HTTP_VERSION) {
			System.out.println(succ + " 服务器不支持，或者拒绝支持在请求中使用的 HTTP 版本。");
		} else if (state == 506) {
			System.out.println(succ + " 被请求的协商变元资源被配置为在透明内容协商中使用自己，因此在一个协商处理中不是一个合适的重点。");
		} else if (state == 507) {
			System.out.println(succ + " 服务器无法存储完成请求所必须的内容。这个状况被认为是临时的。");
		} else if (state == 509) {
			System.out.println(succ + " 服务器达到带宽限制。");
		} else if (state == 510) {
			System.out.println(succ + " 获取资源所需要的策略并没有没满足。");
		} else {
			System.out.println(succ + " 其他情况！");
		}

	}

	public static void main(String[] args) {
		System.out.println(isConnect("http://www.baidu.com"));
		getResponseReason("http://www.google.cn");
	}
}
