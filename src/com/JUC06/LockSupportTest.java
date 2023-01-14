package com.JUC06;

/**
 * @author: Richerlv
 * @date: 2023/1/6 19:17
 * @description:
 *
 *LockSupport: 工具类、作用为挂起和唤醒线程、是创建锁和其他同步类的基础、是调用Unsafe类实现的。
 * LockSupport会与调用他的线程绑定一个许可，默认情况下调用LockSupport的方法不存在许可。
 *
 * 主要方法:
 * void park();
 * void unpark(Thread thread);
 * void parkNanos(long nanos);
 * void park(Object blocker);
 *      Thread类里面有一个volatile Object parkBlocker, 用来存放park方法传递的blocker,
 *      诊断工具可以通过调用getBlocker(Thread thread)观察线程被阻塞的原因。
 * void parkNanos(Object blocke long nanos);
 * void parkUntil(Object block町， long deadline);
 *
 * 特点:
 * 1.可以指定唤醒哪个线程
 * 2.unpark可以在park之前
 * 3.unpark、park不可以累计, 即多次park只需要一次unpark释放
 *
 */

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 设计一个先进先出的锁, 只有队首线程才能获取锁, 忽略中断
 */
public class LockSupportTest {

    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public void lock() {
        boolean isInterrupted = false;
        //获取当前线程
        Thread thread = Thread.currentThread();

        //尝试获取锁(队首 + CAS)
        while(!waiters.peek().equals(thread) || !locked.compareAndExchange(false, true)) {
            LockSupport.park();
            //如果是返回原因是中断，那么重新阻塞
            if(Thread.interrupted()) {
                isInterrupted = true;
            }
        }

        //获取到锁:若其他线程中断过该线程, 恢复标志(虽然本锁不感兴趣不代表其他线程不感兴趣)
        waiters.remove();
        if(isInterrupted) {
            thread.interrupt();
        }
    }

    public void unLock() {
        locked.set(false);
        //给队首阻塞的线程一个许可
        LockSupport.unpark(waiters.peek());
    }

}
