package com.avalon.ms.common.util;

import org.junit.Test;
import org.redisson.api.listener.MessageListener;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @description
 * @author saberey
 * @date 2020/10/30 18:09
 * @version 1.0
 */
public class JedisUtilTest {

    @Test
    public void test(){
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("test".getBytes(),"helloworld".getBytes());
        jedis.close();
    }


    @Test
    public void testSubscribe(){
        Jedis jedis = JedisUtil.getJedis();
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                super.onMessage(channel, message);
            }
        });
    }


    private static class RedisListener implements MessageListener {

        @Override
        public void onMessage(CharSequence charSequence, Object o) {

        }
    }
}
