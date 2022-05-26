package com.kioea.www.timeutil;

import java.util.concurrent.TimeUnit;

public class TimeDiffTest {
    public static void main(String[] args) throws InterruptedException {
        final long startMs = TimeUtils.nowMs();
        // 模拟业务代码
        TimeUnit.SECONDS.sleep(3);
        System.out.println("timeCost: " + TimeUtils.diffMs(startMs));
    }
}
