package com.kioea.www.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * @author:Administrator
 * @time:2015-4-30 下午04:30:00
 * @version:
 */
public class MySemaphore extends Thread {

	Semaphore position;
	private int id;

	public MySemaphore(int i, Semaphore s) {
		this.id = i;
		this.position = s;
	}

	public void run() {
		try {
			if (position.availablePermits() > 0) {
				System.out.println(this.id + "进入厕所，有空位");
			} else {
				System.out.println(this.id + "进入厕所，无空位，需排队");
			}
			position.acquire();
			System.out.println(this.id + "获取位置");
			Thread.sleep(10 * 1000);
			System.out.println(this.id + "使用完毕");
			position.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExecutorService list = Executors.newCachedThreadPool();
		Semaphore position = new Semaphore(2);
		for (int i = 0; i < 10; i++) {
			list.submit(new MySemaphore(i + 1, position));
		}
		list.shutdown();
		position.acquireUninterruptibly(2);
		System.out.println("over and clear");
		position.release(2);
	}

}
