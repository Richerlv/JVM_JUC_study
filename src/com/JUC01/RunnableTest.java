package com.JUC01;

/**
 * @author: Richerlv
 * @date: 2023/1/1 9:01
 * @description:
 */

public class RunnableTest implements Runnable{

    public int i = 0;

    @Override
    public void run() {
        i ++;
        System.out.println("这是实现Runnable接口创建线程");
        System.out.println("i = " + i);
        System.out.println(this);
    }

    public static void main(String[] args) {
        RunnableTest runnableTest = new RunnableTest();
        new Thread(runnableTest).start();
        new Thread(new RunnableTest()).start();
    }
}
