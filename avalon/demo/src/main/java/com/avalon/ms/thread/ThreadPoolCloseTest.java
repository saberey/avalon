package com.avalon.ms.thread;

import com.avalon.ms.common.util.ThreadPoolUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description
 * @author saberey
 * @date 2020/10/26 10:45
 * @version 1.0
 */
public class ThreadPoolCloseTest {

    private static ThreadPoolExecutor pool = ThreadPoolUtil.getNewThreadPool(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024));
    //创建任务总数
    private static int tasks = 0;
    //完成任务总数
    private static int completed_Tasks = 0;
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void test(){
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            lock.lock();
            try{
                tasks += 1;
            }finally {
                lock.unlock();
            }
            pool.execute(() ->{
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "" + finalI);
                    completed_Tasks += 1;

                }finally {
                    lock.unlock();
                }
            });
        }
    }

    public static void isfinshWork() throws InterruptedException {
        System.out.println(pool.isTerminated());
        printWorkDetail();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(pool.isTerminated());
        printWorkDetail();
    }

    public static void printWorkDetail(){
        System.out.println("tasks:" + tasks);
        System.out.println("complete_tasks:" + completed_Tasks);
    }

    public static void shutdown(){
        pool.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolCloseTest.test();
        ThreadPoolCloseTest.isfinshWork();
        ThreadPoolCloseTest.shutdown();
        ThreadPoolCloseTest.isfinshWork();
    }
}
