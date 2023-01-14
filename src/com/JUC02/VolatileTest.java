package com.JUC02;

/**
 * @author: Richerlv
 * @date: 2023/1/4 15:28
 * @description: volatile
 * 1.保证程序可见性
 * 2.禁止指令重排序
 */

public class VolatileTest {
    private static volatile int i = 1;

    //循环打印123
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    while(i % 3 != 1) {

                    }
                    System.out.println(1);
                    i ++;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    while(i % 3 != 2) {

                    }
                    System.out.println(2);
                    i ++;
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while(i % 3 != 0) {

                    }
                    System.out.println(3);
                    i ++;
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}

//public class VolatileTest {
//    private static int i = 0;
//
//    public static void main(String[] args) {
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(i < 100) {
//                    System.out.println(Thread.currentThread().getName() + "-->" + i);
//                    i++;
//                }
//            }
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(i < 100) {
//                    System.out.println(Thread.currentThread().getName() + "-->" + i);
//                    i++;
//                }
//            }
//        });
//
//        t1.start();
//        t2.start();
//    }
//}
