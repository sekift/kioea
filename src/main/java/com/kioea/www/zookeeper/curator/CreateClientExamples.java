package com.kioea.www.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class CreateClientExamples {
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
		String path = "/zk-book/nodecache";
		CuratorFramework client = createWithOptions("localhost:2181", new ExponentialBackoffRetry(1000, 3), 5000, 3000);
		client.start();

		try {
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
			System.out.println(client.getChildren().forPath("/zk-book"));

			final NodeCache cache = new NodeCache(client, path, false);
			cache.start(true);
			cache.getListenable().addListener(new NodeCacheListener() {
				@Override
				public void nodeChanged() throws Exception {
					System.out.println("Node data update, new data: " + new String(cache.getCurrentData().getData()));
				}
			});

			client.setData().forPath(path, "u".getBytes());
			Thread.sleep(1000);
			client.delete().deletingChildrenIfNeeded().forPath(path);
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
