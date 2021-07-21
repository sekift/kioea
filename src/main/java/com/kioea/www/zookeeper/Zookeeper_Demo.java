package com.kioea.www.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import com.kioea.www.async.SleepUtil;

/**
 * 
 * @author:Administrator
 * @time:2016-3-15 下午02:59:09
 * @version:
 */
public class Zookeeper_Demo implements Watcher {
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper("localhost:2181", 5000, new Zookeeper_Demo());

		connectedSemaphore.await();
		// 创建一个目录节点
//		zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// 创建一个子目录节点
//		zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(), Ids.OPEN_ACL_UNSAFE,
//				CreateMode.PERSISTENT);
		System.out.println("========"+new String(zk.getData("/testRootPath", false, null)));
		// 取出子目录节点列表
		System.out.println(zk.getChildren("/testRootPath", true));
		// 修改子目录节点数据
//		zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
		System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");
		// 创建另外一个子目录节点
//		zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), Ids.OPEN_ACL_UNSAFE,
//				CreateMode.PERSISTENT);
		System.out.println("two节点==="+new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));
		System.out.println("所有节点==="+new String(zk.getChildren("/", true).toString()));
		System.out.println("所有子节点==="+new String(zk.getChildren("/mycat", true).toString()));
		System.out.println("所有子节点==="+new String(zk.getChildren("/mycat/mycat-cluster-1", true).toString()));
		System.out.println("所有子节点==="+new String(zk.getChildren("/mycat/mycat-cluster-1/ruledata", true).toString()));
//		zk.delete("/testRootPath/testChildPathTwo", -1);
		SleepUtil.sleepBySecond(20,40);
		zk.delete("/testRootPath/testChildPathOne", -1);
		// 删除父目录节点
//		zk.delete("/testRootPath", -1);
		// 关闭连接
		zk.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
	 */
	@Override
	public void process(WatchedEvent event) {
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
			System.out.println("countDown: [ "+connectedSemaphore.getCount()+" ]");
		}
	}
}
