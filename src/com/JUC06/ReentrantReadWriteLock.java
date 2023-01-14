package com.JUC06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Richerlv
 * @date: 2023/1/6 22:26
 * @description: 读写锁 ReentrantReadWriteLock
 * 出现原因 ： ReentrantLock就可以解决线程安全的问题，但他是独占锁，只有一个线程可以获取锁，现实中读多写少，ReentrantLock效率比较低
 *
 * 读锁加锁：获取读锁，如果当前没有其他线程持有写锁，则当前线程获取读锁，state高16位值+1，否则阻塞
 * 写锁加锁：获取写锁，如果当前没有其他线程持有写锁或读锁，则执行tryAquire(参考ReentrantLock)
 * 写锁的公平/非公平：参考ReentrantLock
 */

//优化ReentrantLockList
public class ReentrantReadWriteLock <T> {

    //线程不安全的List
    private List<T> list = new ArrayList<>();
    //读写锁
    private final ReentrantReadWriteLock<T> lock = new ReentrantReadWriteLock<>();
    private final Lock readLock = lock.readLock;
    private final Lock writeLock = lock.writeLock;

    public void add(T t) {
        writeLock.lock();
        try {
            list.add(t);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(T t) {
        writeLock.lock();
        try {
            list.remove(t);
        } finally {
            writeLock.unlock();
        }
    }

    public T get(int index) {
        readLock.lock();
        try {
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }

}
