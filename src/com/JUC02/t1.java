package com.JUC02;

/**
 * @author: Richerlv
 * @date: 2023/1/5 10:05
 * @description:
 */

public class t1 implements Runnable{

    private TestClass tc = new TestClass();

    @Override
    public void run() {
        tc.printLockInstance();
    }
}
