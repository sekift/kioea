package com.kioea.www.async;

import java.util.concurrent.ExecutorService;

/**
 * 
 * @author:sekift
 * @time:2015-10-19 上午10:48:01
 * @version:
 */
public class ThreadPoolsUtil {

	// 线程池
	private static ExecutorService threadpool = ThreadPools.newExecutorService(50, 300, 20, "异步线程池");

	/**
	 * 异步缓存VIPTYPE
	 * 
	 * @param vo
	 */
	public void setString(final String str) {
		threadpool.execute(new Runnable() {
			@Override
			public void run() {
				getString(str);
			}
		});
	}

	public void getString(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		ThreadPoolsUtil tpu = new ThreadPoolsUtil();
		for (int i = 1; i < 500; i++) {
			tpu.setString(String.valueOf(i));
			if (i % 100 == 0) {
				try {
					Thread.sleep(2 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
