package com.kioea.www.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.KeeperException.BadVersionException;
import org.apache.zookeeper.KeeperException.ConnectionLossException;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

/**
 * 删除节点
 * 
 * @author:Administrator
 * @time:2016-3-15 下午02:35:04
 * @version:
 */
public class Zookeeper_Delete_API_Sync_Usage implements Watcher {
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 5000, new Zookeeper_Delete_API_Sync_Usage());

		String path1 = zookeeper.create("/zk-test-test-", "test".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println(path1);
		try {
			zookeeper.delete("/zk-test-test-", -1);
		} catch (NoNodeException e) {
			e.printStackTrace();
		} catch (ConnectionLossException el) {
			el.printStackTrace();
		} catch (BadVersionException eb) {
			eb.printStackTrace();
		} catch (KeeperException ek) {
			ek.printStackTrace();
		}
	}

	@Override
	public void process(WatchedEvent event) {
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}

	}

}
