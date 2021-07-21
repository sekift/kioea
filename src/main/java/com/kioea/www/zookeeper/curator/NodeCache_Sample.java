package com.kioea.www.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class NodeCache_Sample {
	public static CuratorFramework createSimple(String connectionString) {
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);

		return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
	}

	public static CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy,
			int connectionTimeoutMs, int sessionTimeoutMs) {
		return CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy)
				.connectionTimeoutMs(connectionTimeoutMs).sessionTimeoutMs(sessionTimeoutMs).build();
	}

	public static void main(String[] args) {
		String path = "/zk-book";
		CuratorFramework client = createWithOptions("localhost:2181", new ExponentialBackoffRetry(1000, 3), 5000, 3000);
		client.start();

		try {
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
			System.out.println(client.getChildren().forPath("/zk-book"));

			Stat stat = new Stat();
			client.getData().storingStatIn(stat).forPath(path);
			System.out.println("Success node for: " + path + ", new version is: "
					+ client.setData().withVersion(stat.getAversion()).forPath(path));

			try {
				client.setData().withVersion(stat.getAversion()).forPath(path);
			} catch (Exception e) {
				System.out.println("Fail node for: " + path + ", new version is: "
						+ e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
