package com.kioea.www.jsouputil;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class BeeperControl {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public void beepForAnHour() {
		/*final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("beep");
			}
		};*/
		WebCatchImpl wcl = new WebCatchImpl("aaa");
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(wcl, 0, 10, SECONDS);
		/*scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 60 * 60, SECONDS);*/
	}
	
	public static void main(String args[]){
		BeeperControl bc = new BeeperControl();
		bc.beepForAnHour();
	}
}
