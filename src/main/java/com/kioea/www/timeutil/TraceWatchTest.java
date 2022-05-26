package com.kioea.www.timeutil;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.TimeUnit;

public class TraceWatchTest {

    /**
     * output: {"function2":[{"data":1000,"taskName":"function2"}],"function1":[{"data":1000,"taskName":"function1"},{"data":1,"taskName":"function1"}]}
     */

    public static void main(String[] args) throws InterruptedException {
        TraceWatch traceWatch = new TraceWatch();
        traceWatch.start("第一步");
        // 模拟业务代码
        TimeUnit.SECONDS.sleep(1);
        traceWatch.stop();
        traceWatch.start("第二步");
        // 模拟业务代码
        TimeUnit.SECONDS.sleep(2);
        traceWatch.stop();
        System.out.println(JSONObject.toJSONString(traceWatch.getTaskMap()));
    }
}