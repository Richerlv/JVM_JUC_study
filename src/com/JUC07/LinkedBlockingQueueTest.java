package com.JUC07;

/**
 * @author: Richerlv
 * @date: 2023/1/9 13:44
 * @description: LinkedBlockingQueue - 阻塞队列
 * 单项链表
 * 包含{
 *     Node head : 链表头结点
 *     Node tail: 链表尾结点
 *     ReentrantLock takeLock : 控制只有一个线程进行入队操作的锁
 *     ReentrantLock putLock : 控制只有一个线程进行出队操作的锁
 *     AtomicInteger count : 记录队列中的元素个数
 * }
 *
 * 为什么ConcurrentLinkedQueue不用原子变量count记录个数？
 * 答：需要保持入队操作、出队操作、原子变量操作的原子性，ConcurrentLinkedQueue底层是CAS，
 * 无法保证入队操作、出队操作的原子性。
 */

public class LinkedBlockingQueueTest {
}
