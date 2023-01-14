package com.JUC01;

/**
 * @author: Richerlv
 * @date: 2023/1/2 14:52
 * @description:
 * yield() -> 让出CPU执行权
 *
 * 一般很少使用这个方法，在调试或者测试时这个方法或许可以帮助复现由于并发竞争条件导致的问题
 * 其在设计并发控制时或许会有用途，后面在 java.util.concurrent.locks 包里面的锁时会看到该方法的使用。
 *
 *
 * sleep执行后线程进入阻塞状态
 * yield执行后线程进入就绪状态
 * join执行后线程进入阻塞状态
 */

public class YieldTest {

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i ++) {
                    if(i % 5 == 0) {
                        System.out.println(Thread.currentThread() + "yield CPU...");
                        Thread.yield();
                    }
                }
                System.out.println(Thread.currentThread() + "is over");
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i ++) {
                    if(i % 5 == 0) {
                        System.out.println(Thread.currentThread() + "yield CPU...");
                        Thread.yield();
                    }
                }
                System.out.println(Thread.currentThread() + "is over");
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i ++) {
                    if(i % 5 == 0) {
                        System.out.println(Thread.currentThread() + "yield CPU...");
                        Thread.yield();
                    }
                }
                System.out.println(Thread.currentThread() + "is over");
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
