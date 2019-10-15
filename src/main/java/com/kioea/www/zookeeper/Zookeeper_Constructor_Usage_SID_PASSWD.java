package com.kioea.www.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * 复用seesionId和session passwd
 * @author:Administrator
 * @time:2016-3-15 上午11:29:35
 * @version:
 */
public class Zookeeper_Constructor_Usage_SID_PASSWD implements Watcher{
	private static CountDownLatch connectedSemaphore=new CountDownLatch(1);
	
	public static void main(String args[])throws Exception {
		ZooKeeper zookeeper = new ZooKeeper(
				"localhost:2181",5000,new Zookeeper_Constructor_Usage_SID_PASSWD()
		); 
		
		connectedSemaphore.await();
		long sessionId=zookeeper.getSessionId();
		byte[] passwd=zookeeper.getSessionPasswd();
		
		zookeeper = new ZooKeeper("localhost:2181", 5000, 
				new Zookeeper_Constructor_Usage_SID_PASSWD(), 
				sessionId, passwd);
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
	 */
	@Override
	public void process(WatchedEvent event) {
		System.out.println("Receive watched event: " + event);
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}
	}

}
