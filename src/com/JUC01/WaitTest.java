package com.JUC01;

/**
 * @author: Richerlv
 * @date: 2023/1/2 13:50
 * @description:
 * wait()
 * wait(long timeout)
 * wait(long timeout, int nanos)  nanos参数表示额外时间
 */

public class WaitTest {

    static Object obj = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("===================threadA begin===================");
                    //阻塞当前线程
                    synchronized (obj) {
                        obj.wait();
                    }
                    System.out.println("===================threadA end===================");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        System.out.println("main start");
//        threadA.interrupt();
        System.out.println("main end");
    }
}
