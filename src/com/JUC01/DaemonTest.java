package com.JUC01;

/**
 * @author: Richerlv
 * @date: 2023/1/2 14:29
 * @description:用户线程VS守护线程
 * ps:当最后一个用户线程结束，JVM退出；换言之，若系统中只有守护线程还没执行完，JVM也会退出   （JVM是否退出看右上角正方形）
 *
 * 总结用法：
 * 1.如果希望主线程结束后终止JVM进程，可以在创建线程时将其设置为守护线程
 * 2.如果希望主线程结束后子线程继续工作，可以在创建线程时将其设置为用户线程
 */

public class DaemonTest {

    /**
     * 这也说明父线程结束后，子线程还是可以继续存在的
     * @param args
     */
//    //用户线程
//    public static void main(String[] args) {
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true) {
//
//                }
//            }
//        });
//
//        thread.start();
//        System.out.println("main thread is over!");
//    }

    //守护线程
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {

                }
            }
        });

        //设置为守护线程
        thread.setDaemon(true);
        thread.start();
        System.out.println("main thread is over!");
    }
}
