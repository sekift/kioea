package com.kioea.www.async.masterworker;

import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author:sekift
 * @time:2017-7-24 下午05:16:25
 * @version:
 */
public class Worker implements Runnable {
	protected Queue<Object> workQueue;
	protected Map<String, Object> resultMap;

	public void setWorkQueue(Queue<Object> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public Object handle(Object input) {
		return input;
	}

	@Override
	public void run() {
		while (true) {
			Object input = workQueue.poll();
			if (input == null) {
				break;
			}
			Object re = handle(input);
			resultMap.put(Integer.toString(input.hashCode()), re);
		}
	}
}
