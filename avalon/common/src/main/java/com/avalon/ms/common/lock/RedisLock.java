package com.avalon.ms.common.lock;

import com.avalon.ms.common.util.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @description
 * @author saberey
 * @date 2020/11/2 17:34
 * @version 1.0
 */
public class RedisLock {

    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);
    /**锁前缀*/
    private String lock_key_prefix = "redis_lock_";

    public boolean lock(String key, String id, long lockTime, long timeout){
        SetParams params = SetParams.setParams().nx().px(lockTime);
        Jedis jedis = JedisUtil.getJedis();
        Long start = System.currentTimeMillis();
        try{
            for(;;){
                //SET命令返回OK ，则证明获取锁成功
                String lock = jedis.set(lock_key_prefix+key, id, params);
                if("OK".equals(lock)){
                    return true;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= timeout) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            jedis.close();
        }
    }

    /**
     * 解锁
     * @param id
     * @return
     */
    public boolean unlock(String key, String id){
        Jedis jedis = JedisUtil.getJedis();
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";
        try {
            Object result = jedis.eval(script, Collections.singletonList(lock_key_prefix+key),
                    Collections.singletonList(id));
            if("1".equals(result.toString())){
                return true;
            }
            return false;
        }finally {
            jedis.close();
        }
    }
}
