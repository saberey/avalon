package com.avalon.ms.common.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 工具类
 * 
 * @author saber
 * @date 2017年7月18日
 * @version
 */
public class JedisUtil {

	 private static JedisPool jedisPool = null;
	 private static String jedisAddr = ConfigUtil.getProperty("config/datasource.properties", "redis.address");
	 private static int jedisPort = Integer.parseInt(ConfigUtil.getProperty("config/datasource.properties", "redis.port"));
	 private static int jedisTimeout =Integer.parseInt(ConfigUtil.getProperty("config/datasource.properties", "redis.timeout"));
	 private static String jedisPwd = ConfigUtil.getProperty("config/datasource.properties", "redis.password");
	 
	 static {
         JedisPoolConfig config = new JedisPoolConfig();  
         //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
         //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
         config.setMaxTotal(50);  
         //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
         config.setMaxIdle(10);
         //设置最小空闲数
         config.setMinIdle(5);
         //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
         config.setMaxWaitMillis(5000);  
         //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
         config.setTestOnBorrow(true);
         config.setTestOnReturn(true);            
         //Idle时进行连接扫描
         config.setTestWhileIdle(true);
         //表示idle object evitor两次扫描之间要sleep的毫秒数
         config.setTimeBetweenEvictionRunsMillis(500);
         //表示idle object evitor每次扫描的最多的对象数
         config.setNumTestsPerEvictionRun(5);
         //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
         config.setMinEvictableIdleTimeMillis(1000);
         //redis如果设置了密码：
         //jedisPool = new JedisPool(config, jedisAddr, jedisPort, jedisTimeout, jedisPwd);
         jedisPool = new JedisPool(config, jedisAddr,  6379);
	   }
	 /**
	  * @description获取指定的redis 连接对象
	  * @param index
	  * @return Jedis
	  * @exception:
	  * @authorsaber
	  * @time:2017年7月18日下午2:03:57
	  */
	 public static Jedis getJedis(int index) {
    	 Jedis jedis = jedisPool.getResource();
    	 if(index>0){
    	 	jedis.select(index);
		 }
    	 return jedis;
     }
	 
	 public static Jedis getJedis(){
		 return jedisPool.getResource();
	 }
	 
	 @SuppressWarnings("deprecation")
	 public static void broken(Jedis jedis){
		 if(null!=jedis && null!=jedisPool){
			 //jedisPool.returnBrokenResource(jedis);
		 }
	 }
	 
	 public static void clear(int index){
		 Jedis jedis = getJedis(index);
		 jedis.flushDB();
		 broken(jedis);
	 }
	 
	 public static void clearAll(){
		 Jedis jedis = getJedis();
		 jedis.flushAll();
		 broken(jedis);
	 }
	 
	 public static void main(String[] args) throws Exception {
		//Jedis jedis = getJedis(1);\
		//Socket socket = new Socket("192.168.137.128", 6379);
		/* Jedis jedis = new Jedis("192.168.137.128", 6379, 30000);
		 jedis.auth("foobared");
		 jedis.select(1);
		 jedis.set("1", "1");
		 System.out.println(jedis.get("1"));*/
		 Jedis jedis = JedisUtil.getJedis(1);
		 System.out.println(jedis.keys("*"));
	}
}
