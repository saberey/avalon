package com.avalon.ms.common.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description
 * @author saberey
 * @date 2020/10/26 10:49
 * @version 1.0
 */
public class ThreadPoolUtil {


    private static ThreadFactory defaultFactory = new ThreadFactory() {
        private AtomicLong counter = new AtomicLong(1);
        private String prefix = "default-thread-";
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, prefix+counter.getAndIncrement());
        }
    };

    private static RejectedExecutionHandler defaultRejectedHandler = new ThreadPoolExecutor.AbortPolicy();

    public static ThreadPoolExecutor getNewThreadPool(int corePoolSize,
                                                       int maximumPoolSize,
                                                       long keepAliveTime,
                                                       TimeUnit unit,
                                                       BlockingQueue<Runnable> workQueue,
                                                       ThreadFactory threadFactory,
                                                       RejectedExecutionHandler handler){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler);
        return pool;
    }

    public static ThreadPoolExecutor getNewThreadPool(int corePoolSize,
                                                       int maximumPoolSize,
                                                       long keepAliveTime,
                                                       TimeUnit unit,
                                                       BlockingQueue<Runnable> workQueue){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                defaultFactory,
                defaultRejectedHandler);
        return pool;
    }
}
