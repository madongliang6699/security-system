package com.security.study.并发编程._13_JUC中的Condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueDemo<E> {

    int size;//阻塞队列最大容量
    ReentrantLock lock = new ReentrantLock();
    LinkedList<E> list = new LinkedList<>();//队列底层实现
    Condition notFull = lock.newCondition();//队列满时的等待条件
    Condition notEmpty = lock.newCondition();//队列空时的等待条件
    public BlockingQueueDemo(int size) {
        this.size = size;
    }
    public void enqueue(E e) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == size){
                notFull.await();//队列已满,在notFull条件上等待
            }
            list.add(e);//入队:加入链表末尾
            System.out.println("入队：" + e);
            notEmpty.signal(); //通知在notEmpty条件上等待的线程
        } finally {
            lock.unlock();
        }
    }
    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();
        try {
            while (list.isEmpty()){
                notEmpty.await();//队列为空,在notEmpty条件上等待
            }
            e = list.removeFirst();//出队:移除链表首元素
            System.out.println("出队：" + e);
            notFull.signal();//通知在notFull条件上等待的线程
            return e;
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        BlockingQueueDemo<Integer> queue = new BlockingQueueDemo<>(2);
        for (int i = 0; i < 100; i++) {
            int data = i;
            new Thread(() -> {
                try {
                    queue.enqueue(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Integer data = queue.dequeue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        /**
         * 代码非常容易理解，创建了一个阻塞队列，大小为3，队列满的时候，会被阻塞，等待其他线程去消费，
         * 队列中的元素被消费之后，会唤醒生产者，生产数据进入队列。
         * 上面代码将队列大小置为1，可以实现同步阻塞队列，
         * 生产1个元素之后，生产者会被阻塞，待消费者消费队列中的元素之后，生产者才能继续工作。
         */
    }



}
