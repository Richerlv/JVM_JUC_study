package com.JUC01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: Richerlv
 * @date: 2023/1/1 9:01
 * @description: 有返回值
 */

public class CallableTest implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("这是实现Callable创建线程");
        return "Callable-res";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建异步任务
        FutureTask futureTask = new FutureTask(new CallableTest());

        //启动线程
        new Thread(futureTask).start();
        String res = (String) futureTask.get();
        System.out.println(res);
    }
}
