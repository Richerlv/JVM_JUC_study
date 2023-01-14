package com.JUC01;

/**
 * @author: Richerlv
 * @date: 2023/1/2 14:17
 * @description:
 * 模拟死锁
 * 1.互斥
 * 2.请求与保持
 * 3.不剥夺
 * 4.环路等待
 *
 * 避免死锁: 破坏以上一个条件
 * eg:以下代码可以同时竞争A,得到A才去拿B
 */

public class DeadLockTest {

    static Object resourceA = new Object();
    static Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //先申请资源A,再申请资源B
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resourceB) {
                        System.out.println("threadA get resourceB");
                        System.out.println("threadA get All!");
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                //先申请资源B,再申请资源A
                synchronized (resourceB) {
                    System.out.println("threadB get resourceB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resourceA) {
                        System.out.println("threadB get resourceA");
                        System.out.println("threadB get All!");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
