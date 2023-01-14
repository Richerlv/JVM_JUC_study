package com.JVM01;

/**
 * @author: Richerlv
 * @date: 2023/1/11 15:04
 * @description:
 *
 * 默认 20473
 * -Xss256k 3367
 */

public class XssTest {

    static int i = 0;

    public static void main(String[] args) {
        System.out.println(i);
        i ++;
        main(args);
    }
}
