package com.kioea.www.async.futuredata;

/**
 * 
 * @author:sekift
 * @time:2017-7-24 下午04:45:30
 * @version:
 */
public class RealData implements Data {
	protected final String result;

	public RealData(String para) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(para);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
		result = sb.toString();
	}

	@Override
	public String getResult() {
		return result;
	}

}
