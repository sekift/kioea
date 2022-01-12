package com.kioea.www;

import java.util.concurrent.ThreadLocalRandom;

public class Test {

    static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        System.out.println(~0>>>1);
        System.out.println(random.nextInt(1, 10));
    }
}
