package com.avalon.ms.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月11日 下午4:59:40
 *@version
 */
public class RateLimitUtil {

	private static final Logger logger = LoggerFactory.getLogger(RateLimitUtil.class);
	
	private static final Object lock = new Object();
	
	public static boolean incr(String limitName,String limitKeyWord,int timeOut,int max){
		boolean result = false;
		Jedis jedis  = null;
		String keyWords =limitName+":"+limitKeyWord;
		try{
			jedis = JedisUtil.getJedis(1);
			long leftTime = jedis.pttl(keyWords);
			long next = 0;
			if(leftTime>0){
				next = jedis.incr(keyWords);
				if(next > max){
					logger.info("key:{},超出{}秒内{}次访问限制，这是第{}次访问",keyWords,timeOut,max,next);
				}else{
					result = true;
				}
			}else{
				synchronized (lock) {
					if(jedis.pttl(keyWords)<=0){
						jedis.expire(keyWords, timeOut);
					}
					next = jedis.incr(keyWords);
					if(next >max){
						logger.info("key:{},超出{}秒内{}次访问限制，这是第{}次访问",keyWords,timeOut,max,next);
					}else{
						result = true;
					}	
				}
			}
			
			if(result){
				logger.info("key:{},第{}次访问",keyWords,next);
			}
			
		}catch(Exception e){
			logger.error(keyWords+" limit  error",e);
			e.printStackTrace();
		}finally{
			if(jedis!=null){
				jedis.disconnect();
			}
		}
		
		return result;
	}
}
