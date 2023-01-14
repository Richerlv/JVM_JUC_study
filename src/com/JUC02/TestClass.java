package com.JUC02;

/**
 * @author: Richerlv
 * @date: 2023/1/5 9:45
 * @description: 测试类 ：在这里声明相关变量
 */

public class TestClass {

    private static Integer i = 0;

    public void printLockInstance() {
        synchronized (i) {
            while(i < 100) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
                i++;
            }
        }
    }

    public static synchronized void printLockClass() {
        for(int i = 0; i < 100; i ++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}
