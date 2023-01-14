package com.JUC06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Richerlv
 * @date: 2023/1/6 22:08
 * @description: ReentrantLock: 可重入独占锁, 既可以实现公平也可以实现非公平
 *
 * 公平 or 非公平
 * 1.依赖Sync, 有两个子类{
 *     NofairSync : tryAcquire直接重试获取锁
 *     FairSync : tryAcquire先判断当前线程结点是否有前驱结点
 * }
 *
 * 2.void lock()
 * 3.void unLock()
 * 4.void locklnterruptibly() 响应中断
 * 5.boolean tryLock()  失败不阻塞
 *
 */

//实现一个简单的线程安全的list
public class ReentrantLockList<T> {

    //线程不安全的List
    List<T> list = new ArrayList<>();
    //独占锁
    ReentrantLock lock = new ReentrantLock();

    public void add(T t) {
        lock.lock();
        try {
            list.add(t);
        } finally {
            lock.unlock();
        }
    }

    public void remove(T t) {
        lock.lock();
        try {
            list.remove(t);
        } finally {
            lock.unlock();
        }
    }

    public T get(int index) {
        lock.lock();
        try {
            return list.get(index);
        } finally {
            lock.unlock();
        }
    }
}
