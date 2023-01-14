package com.JUC06;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Richerlv
 * @date: 2023/1/6 20:32
 * @description: AQS --> AbstractQueuedSyncronizer
 * 内部主要包括: state、一个双向队列、 ObjectCondition
 *
 * 双向队列(阻塞队列):
 * 节点：{
 *      SHARED: 一个竞争共享资源被阻塞的线程  Node
 *      EXCLUSIVE: 一个竞争独占资源被阻塞的线程  Node
 *      CONDITION: 此状态表示该线程处于条件队列中  int
 *      ...
 * }
 * state: volatile修饰
 * AQS中维护了一个state变量, 不同的实现类有不同的含义: {
 *     ReentrantLock:代表锁的可重入次数
 *     ReentrantReadWriteLock:高16位代表获取读锁的次数,低16位代表写锁的可重入次数
 *     Semaphore:可用的信号量个数
 *     CountDownLatch:计数器的值
 * }
 * ObejctCondition:
 * 可以直接访问AQS内部的变量,比如state值和AQS队列,ConditionObject是条件变量,1个条件变量对应1个条件队列(单向链表队列),其用来存放调用条件变量await方法后被阻塞的线程
 *
 * 方法:
 * acquire(int arg) {
 *     1.调用tryAcquire()设置state的值
 *     2.成功返回
 *     3.失败将当前线程封装为Node.EXCLUSIVE的结点插入AQS阻塞队列的尾部,调用LockSupport.park()阻塞自己
 * }
 *
 * release(int arg) {
 *      1.tryRelease()设置state的值
 *      2.调用LockSupport.unpark(thread)激活队首线程
 * }
 *
 * acquireShared(int arg) {
 *     1.调用tryAcquireShared()设置state的值
 *     2.成功返回
 *     3.失败将当前线程封装为Node.SHARED的结点插入AQS阻塞队列的尾部,调用LockSupport.park()阻塞自己
 * }
 *
 * releaseSharedShared(int arg) {
 *      1.tryRelease()设置state的值
 *      2.调用LockSupport.unpark(thread)激活队首线程
 * }
 *
 * lock()    -->   sync.lock   -->   AQS.acqurie    相当于进入syncronized同步代码块, 被阻塞的线程进入AQS阻塞队列
 * await():线程释放锁, 进入ObjectCondition的条件队列, 在await处阻塞
 * signal/signalALL(): 会把条件队列中的队首线程移除放入阻塞队列并激活该线程, signal之后被唤醒的线程不在持有锁，需要重新获取然后从await处开始执行
 * unLock()  同lock
 *
 */

public class AQSTest {

    //测试signal之后还会不会持有锁
    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("begin await");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end await");
                lock.unlock();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                System.out.println("begin signal");
                condition.signal();
                System.out.println("end signal");
                lock.unlock();
            }
        });

        t1.start();
        t2.start();

    }

}
