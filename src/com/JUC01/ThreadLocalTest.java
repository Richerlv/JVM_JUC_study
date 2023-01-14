package com.JUC01;

/**
 * @author: Richerlv
 * @date: 2023/1/2 14:39
 * @description:
 * ThreadLocal：线程本地变量
 *
 * 数据并不被保存在ThreadLocal实例中，他只是一个工具人，实际上数据被保存在线程的threadLocals或inheritableThreadLocals变量中。
 * threadLocals: 定制化Map、不可继承
 * inheritableThreadLocals: 定制化Map、可继承、创建子线程时会把父线程的~复制一份到子线程中
 */

public class ThreadLocalTest {

    /**
     * 演示线程本地变量
     */
    //创建变量
    static ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();
    static int i = 0;

    //打印方法
    public static void print(String str) {
        //打印线程本地变量值
        System.out.println(str + " : " + threadLocal.get());
        //清除线程本地变量值
//        threadLocal.remove();

//        System.out.println(str + " : i = " + i);
//        i = 0;
    }

//    public static void main(String[] args) {
//        Thread threadA = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                threadLocal.set("resourceA");
//                print(Thread.currentThread().getName());
//                //打印线程本地变量值
//                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
//
//
////                i = 1;
////                print(Thread.currentThread().getName());
////                //打印线程本地变量值
////                System.out.println(Thread.currentThread().getName() + " : i = " + i);
//            }
//        });
//
//        Thread threadB = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                threadLocal.set("resourceB");
//                print(Thread.currentThread().getName());
//                //打印线程本地变量值
//                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
//
////                i = 2;
////                print(Thread.currentThread().getName());
////                //打印线程本地变量值
////                System.out.println(Thread.currentThread().getName() + " : i = " + i);
//            }
//        });
//
//        threadA.start();
//        threadB.start();
//    }


    /**
     * 演示是否可以继承 不可以
     */
//    public static void main(String[] args) {
//
//        Thread child = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                print(Thread.currentThread().getName());
//            }
//        });
//
//        threadLocal.set("this is set in parent");
//
//        print(Thread.currentThread().getName());
//        child.start();
//    }


    /**
     * 演示是否可以继承 可以
     */
    public static void main(String[] args) {

        Thread child = new Thread(new Runnable() {
            @Override
            public void run() {
                print(Thread.currentThread().getName());
            }
        });

        threadLocal.set("this is set in parent");

        print(Thread.currentThread().getName());
        child.start();
    }
}
