#为什么要用线程池
1.复用线程，减小创建/销毁线程的统一开销
2.统一管理线程
3.控制并发数

#层次结构
ThreadPoolExecutor extends AbstractExecutorService
AbstractExecutorService implements ExecutorService
ExecutorService extends Executor

#核心参数
int corePoolSize  最大核心线程数
int maximumPoolSize  最大线程数(核心 + 非核心)
long keepAliveTime   非核心线程闲置超时时常，如设置allowCoreTimeOut(true),则也会作用于核心线程
TimeUnit unit     keepAliveTime的单位
BlockingQueue workerQueue  阻塞队列，维护者等待执行的Runnable任务对象
ThreadFactory threadFactory   创建线程的工厂 ，用于批量创建线程，统一在创建线程时设置一些参数，如是否守护线程、线程的优先级等。如果不指定，会新建一个默认的线程工厂。
RejectExecutionHandler handler 拒绝处理策略，若线程数大于最大线程数就会采用{
ThreadPoolExecutor.AbortPolicy(默认) :丢弃任务并抛出RejectedExecutionException 
ThreadPoolExecutor.DiscardPolicy : 丢弃新来的任务但是不抛出异常
ThreadPoolExecutor.DiscardOldestPolicy :丢弃队头最旧的任务，然后重新尝试执行程序(如果再次失败，重复此过程)
ThreadPoolExecutor.CallerRunsPolicy : 由调用线程处理该任务
}

#线程池里用到的锁
https://www.cnblogs.com/thisiswhy/p/15493027.html

#线程池是怎么实现线程复用的？
·Worker类实现了Runnable接口，所以Worker也是一个线程任务。
·创建worker时，构造方法中创建了一个线程，线程的任务就是自己。
·再看runWorker()方法的逻辑，先执行创建worker时就有的任务，
执行完这个任务后，worker的生命周期并没有结束，在下面的while循环中会不断调用getTask()方法从阻塞队列中获取任务然后执行，
只要getTask()不返回null，此线程就不会退出，从而达到复用线程的目的。
