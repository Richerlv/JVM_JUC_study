package com.JVM01;

/**
 * @author: Richerlv
 * @date: 2023/1/9 15:43
 * @description:
 */

public class DeadThreadTest {

    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "begin");
            DeadThread deadThread = new DeadThread();
            System.out.println(Thread.currentThread().getName() + "end");
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

    }

}


