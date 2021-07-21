package com.kioea.www.async.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author:sekift
 * @time:2017-7-24 下午05:07:08
 * @version:
 */
public class Master {
    // 任务队列
    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<>();
    // worker进程队列
    protected Map<String, Thread> threadMap = new HashMap<>();
    // 子任务处理结果集
    protected Map<String, Object> resultMap = new ConcurrentHashMap<>();

    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public Master(Worker worker, int countWorker) {
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for (int i = 0; i < countWorker; i++) {
            threadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
        }
    }

    public void submit(Object job) {
        workQueue.add(job);
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void execute() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            entry.getValue().start();
        }
    }
}
