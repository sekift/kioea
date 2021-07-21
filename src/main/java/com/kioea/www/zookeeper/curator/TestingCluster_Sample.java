package com.kioea.www.zookeeper.curator;

/**
 * 
 * @author:Administrator
 * @time:2016-3-16 下午04:35:39
 * @version:
 */
public class TestingCluster_Sample {
	public static void main(String[] args) throws Exception {
		TestingCluster cluster = new TestingCluster(3);
		cluster.start();
		Thread.sleep(2000);
		TestingZooKeeperServer leader = null;

		for (TestingZooKeeperServer zs : cluster.getServers()) {
			System.out.print("啊啊啊啊啊==========="+zs.getInstanceSpec().getServerId() + "");
			System.out.print(zs.getQuorumPeer().getServerState() + "-");
			System.out.println(zs.getInstanceSpec().getDataDirectory().getAbsolutePath());

			if ("leading".equals(zs.getQuorumPeer().getServerState())) {
				leader = zs;
			}

		}

		leader.kill();
		System.out.println("啊啊啊啊啊啊啊啊啊啊--after leader kill:");
		for (TestingZooKeeperServer zs : cluster.getServers()) {
			System.out.print("啊啊啊啊啊啊啊zk=========="+zs.getInstanceSpec().getServerId() + "=");
			System.out.print(zs.getQuorumPeer().getServerState() + "-");
			System.out.println(zs.getInstanceSpec().getDataDirectory().getAbsolutePath());
		}

		cluster.stop();
	}
}
