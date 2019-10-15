package com.kioea.www.async.futuredata;

/**
 * 
 * @author:sekift
 * @time:2017-7-24 下午04:39:56
 * @version:
 */
public class Client {
	public Data request(final String queryStr) {
		final FutureData future = new FutureData();
		new Thread() {
			public void run() {
				RealData realdata = new RealData(queryStr);
				future.setRealData(realdata);
			}
		}.start();
		return future;
	}
}
