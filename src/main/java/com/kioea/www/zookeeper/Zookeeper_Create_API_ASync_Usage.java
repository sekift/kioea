package com.kioea.www.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

/**
 * 创建节点
 * @author:Administrator
 * @time:2016-3-15 下午02:05:57
 * @version:
 */
public class Zookeeper_Create_API_ASync_Usage implements Watcher{
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	
	public static void main(String args[])throws Exception{
		ZooKeeper zookeeper=new ZooKeeper("localhost:2181",
				5000,new Zookeeper_Create_API_ASync_Usage());
		connectedSemaphore.await();
		
		zookeeper.create("/zk-test-ephemeral-", 
				"".getBytes(), 
				Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL,
				new IStringCallback(),
				"I am context");
		
		zookeeper.create("/zk-test-ephemeral-", 
				"".getBytes(), 
				Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL,
				new IStringCallback(),
				"I am context");
		
		zookeeper.create("/zk-test-ephemeral-", 
				"".getBytes(), 
				Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL_SEQUENTIAL,
				new IStringCallback(),
				"I am context");
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	@Override
	public void process(WatchedEvent event){
		if(KeeperState.SyncConnected == event.getState()){
			connectedSemaphore.countDown();
		}
	}
}

class IStringCallback implements AsyncCallback.StringCallback{
	@Override
	public void processResult(int rc, String path, Object ctx, String name) {
		System.out.println("Create path result:["+rc+", "+path+", "+ctx
				+", real path name: "+name);
	}
}
