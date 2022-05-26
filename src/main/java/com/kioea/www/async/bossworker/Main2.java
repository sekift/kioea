package com.kioea.www.async.bossworker;

public class Main2 {
    public static void main(String[] args) {
        BossWorker bossWorker = new BossWorker();
        try {
            bossWorker.pushAll("开始了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
