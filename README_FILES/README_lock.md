JUC 并发

/**

* 创建多线程方式
*
    1. 继承 Thread
*
*
    2. 实现Runable接口
*
*
    3. 实现 callable 接口
*
*
    4. 线程池方式
* */

# 公平锁与非公平锁

默认非公平锁

```
private final Lock lock=new ReentrantLock(true);
```

特点 非公平锁: 其他线程饿死,效率高 公平锁: 雨露均沾,效率低

# 可重入锁,也就是递归锁

synchronized(隐式) 和 Lock(显式,需要手动开关) 都是 可重入锁

# 死锁

多个进程在执行过程中,因为争夺资源而造成一种互相等待的现象, 如果没有外力干涉,无法继续执行下去

## 死锁原因

1.资源不足 2.进程推荐顺序不合适 3.资源分配不当

## 检查死锁

1. jps -l ( 类似linux ps -ef)
2. jstack (jvm 自带堆栈跟踪工具)

# 3种辅助类

```text
CountDownLatch
CyclicBarrier
Semaphore
```

# 读写锁

```text
读锁:共享锁,发生死锁,
写锁:独占锁,发生死锁
读写互斥,读读共享

1线程修改时候,需要等待2线程读之后,
2线程修改时候,需要等待1线程读之后
```

## 悲观锁,乐观锁

```text
悲观锁:不支持并发,只能一个人加锁,开锁,完成
乐观锁:支持并发,通过版本号控制,相同才提交
```

## 表锁,行锁

```text
表锁:整个表锁定,不会死锁
行锁:表中行锁定,会死锁
```

## 锁降级

```text
将写入锁降级为读锁,
读锁不能升级写锁
```
