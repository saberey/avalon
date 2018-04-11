package com.avalon.ms.redis;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import com.avalon.ms.common.util.JedisUtil;

public class JedisUtilTest {

	@Test
	public void testGetJedis() {
		//fail("Not yet implemented");
		Jedis jedis = JedisUtil.getJedis(1);
		Pipeline pipeline = jedis.pipelined();
		Response<Set<String>> res1 = pipeline.smembers("citic:redis:key:FS");
		Response<Set<String>> res2 = pipeline.smembers("citic:redis:key:HK");
		pipeline.sync();
		//System.out.println(res1.get);		
		//System.out.println(res1.);
		for(String each:res1.get()){
			System.out.println(each);
		}
		System.out.println("");
		for(String each:res2.get()){
			System.out.println(each);
		}
	}
}
