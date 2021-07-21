package com.kioea.www.async.futuredata;

/**
 * 
 * @author:sekift
 * @time:2017-7-24 下午04:43:25
 * @version:
 */
public class FutureData implements Data {
	protected RealData realData = null;
	protected boolean isReady = false;

	public synchronized void setRealData(RealData realData) {
		if (isReady) {
			return;
		}
		this.realData = realData;
		isReady = true;
		notifyAll();
	}

	@Override
    public synchronized String getResult() {
		while (!isReady) {
			try {
				wait();
			} catch (Exception e) {

			}
		}
		return realData.result;
	}

}
