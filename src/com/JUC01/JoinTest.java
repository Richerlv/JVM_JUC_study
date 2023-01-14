package com.JUC01;

/**
 * @author: Richerlv
 * @date: 2023/1/2 14:42
 * @description:
 * join(): 等待线程执行终止
 *
 * 线程实例的join()方法可以使得一个线程在另一个线程结束后再执行，即也就是说使得当前线程可以阻塞其他线程执行；
 * thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
 */

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("threadA begin");
                    Thread.sleep(1000);
                    System.out.println("threadA over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("threadB begin");
                    Thread.sleep(2000);
                    System.out.println("threadB over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();
        System.out.println("main begin");

        //等待A、B线程先执行完
        threadA.join();
        threadB.join();

        System.out.println("main over");
    }
}
