package com.kioea.www.timeutil;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.TimeUnit;

public class TraceHolderTest {

    /**
     * output: {"function2":[{"data":1004,"taskName":"function2"}],"function1":[{"data":1001,"taskName":"function1"},{"data":1002,"taskName":"function1"}]}
     */
    public static void main(String[] args) {
        TraceWatch traceWatch = new TraceWatch();
        TraceHolder.run(traceWatch, "function1", i -> {
            try {
                // 模拟业务代码
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        String result = TraceHolder.run(traceWatch, "function2", () -> {
            try {
                // 模拟业务代码
                TimeUnit.SECONDS.sleep(1);
                return "YES";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "NO";
            }
        });
        TraceHolder.run(traceWatch, "function1", i -> {
            try {
                // 模拟业务代码
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(JSONObject.toJSONString(traceWatch.getTaskMap()));
    }
}