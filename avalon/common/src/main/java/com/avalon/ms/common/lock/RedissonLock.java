package com.avalon.ms.common.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 *
 * @description
 * @author saberey
 * @date 2020/11/2 17:49
 * @version 1.0
 */
public class RedissonLock {



    public static void lock(String key){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        //config.useSingleServer().setPassword("");
        final RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock(key);
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" get lock");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+" unlock");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock("test");
            }).start();
        }

        TimeUnit.SECONDS.sleep(60);
    }
}
