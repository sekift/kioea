package com.kioea.www.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author:Administrator
 * @time:2015年4月29日 下午2:49:09
 * @version:
 */
public class MyExecutor extends Thread {
	private int index;

	public MyExecutor(int i) {
		this.index = i;

	}

	public void run() {
		try {
			System.out.println(this.index);
			Thread.sleep((int) (Math.random() * 1000));
			System.out.println(this.index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			service.execute(new MyExecutor(i));
		}

		service.shutdown();

	}

}
