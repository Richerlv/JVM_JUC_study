package com.JVM01;

/**
 * @author: Richerlv
 * @date: 2023/1/9 15:48
 * @description:
 */

public class DeadThread {
    static {
        System.out.println(Thread.currentThread().getName() + "正在初始化当前类");

    }
}
