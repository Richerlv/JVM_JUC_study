package com.JUC01;

/**
 * @author: Richerlv
 * @date: 2023/1/1 9:01
 * @description:
 */

public class ThreadTest extends Thread{

    public int i = 0;

    @Override
    public void run() {
        i ++;
        System.out.println("这是继承Thread类创建新线程");
        System.out.println("i = " + i);
        System.out.println(this);
    }

    public static void main(String[] args) {
        //创建线程
        ThreadTest t1 = new ThreadTest();
        ThreadTest t2 = new ThreadTest();

        //启动线程
        t1.start();
        t2.start();
//        System.out.println(Thread.currentThread());
    }
}
