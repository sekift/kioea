package com.kioea.www.timerutil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * java自带的定时器
 * 
 * @author:sekift
 * @time:2015-5-12 下午02:58:20
 * @version:
 */
public class RingTask extends TimerTask {
	int second = 1;
	int delay = 1;

	public RingTask() {

	}

	public RingTask(int s, int d) {
		this.second = s;
		this.delay = d;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	@Override
    public void run() {
		System.out.println("我是打铃程序!" + "我第一次打铃延迟了" + delay + "秒！");
	}

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new RingTask(3, 3), 3000, 3000);
	}
}
