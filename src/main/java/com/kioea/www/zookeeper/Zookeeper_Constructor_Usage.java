package com.kioea.www.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * Java API 创建连接 创建一个最基本的Zookeeper会话实例
 * 
 * @author:Administrator
 * @time:2016-3-15 上午11:01:33
 * @version:
 */
public class Zookeeper_Constructor_Usage implements Watcher {
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

	public static void main(String args[]) throws Exception {
		ZooKeeper zookeeper = new ZooKeeper("localhost:2182", 5000, new Zookeeper_Constructor_Usage());

		System.out.println(zookeeper.getState());
		try {
			connectedSemaphore.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Zookeeper session established.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
	 */
	@Override
	public void process(WatchedEvent event) {
		System.out.println("Receive watched event: " + event);
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}
	}
}
