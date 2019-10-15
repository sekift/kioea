package com.kioea.www.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author:Administrator
 * @time:2015-4-30 下午04:43:58
 * @version:
 */
public class TestCountDownLatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final CountDownLatch begin = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(10);

		final ExecutorService exe = Executors.newFixedThreadPool(10);

		for (int i = 0; i < 10; i++) {
			final int NO = i + 1;
			Runnable run = new Runnable() {
				public void run() {
					try {
						begin.await();// 阻塞
						System.out.println(begin.toString());
						Thread.sleep(10 * 1000);
						System.out.println("NO " + NO + " arrived");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						end.countDown();
					}
				}
			};
			exe.submit(run);
		}

		System.out.println("start");
		begin.countDown();
		end.await();
		System.out.println("over");
		exe.shutdown();
	}
}
