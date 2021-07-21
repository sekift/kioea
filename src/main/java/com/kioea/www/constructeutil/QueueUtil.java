package com.kioea.www.constructeutil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author:sekift
 * @time:2015-2-12 下午02:25:50
 * @version:
 */
public class QueueUtil {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        System.out.println("1 size:" + queue.size());
        int a = queue.poll();
        int b = queue.peek();
        System.out.println(a);
        System.out.println(b);
        System.out.println("2 size:" + queue.size());
        queue.offer(3);
        System.out.println("3 size:" + queue.size());
        a = queue.poll();
        b = queue.peek();
        System.out.println(a);
        System.out.println(b);
        System.out.println("4 size:" + queue.size());

    }
}
