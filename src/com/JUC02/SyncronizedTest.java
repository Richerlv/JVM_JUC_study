package com.JUC02;

/**
 * @author: Richerlv
 * @date: 2023/1/4 15:28
 * @description:syncronized
 * 用法：
 * 1.修饰静态方法 -> 锁类
 * 2.修饰实例方法 -> 锁实例
 * 3.修饰代码块  -> {
 *     1. this:当前实例
 *     2. object：实例, 括号里是谁就锁谁，不是所在类的实例
 *     3.XXX.class:锁类
 * }
 */

public class SyncronizedTest {

    public static void main(String[] args) {
        new Thread(new t1()).start();
        new Thread(new t2()).start();
    }
}
