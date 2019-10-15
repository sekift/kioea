package com.kioea.www.async.futuredata;

/**
 * 
 * @author:sekift
 * @time:2017-7-24 下午04:37:21
 * @version:
 */
public class Main {
	public static void main(String args[]) {
		Client client = new Client();
		Data data = client.request("name");
		System.out.println("请求完毕");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
		System.out.println("数据=" + data.getResult());
	}
}
