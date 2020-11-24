package com.avalon.ms.common.auth;

import com.avalon.ms.common.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月4日 上午10:34:06
 *@version
 */
public class AuthCodeCheck {

	private static class Helper{
		private final static AuthCodeCheck authCodeCheck  = new AuthCodeCheck();
	}
	
	public static final AuthCodeCheck getInstance(){
		return Helper.authCodeCheck;
	}

	private AuthCodeCheck(){
		
	}
	private final String CHECK_SUFFIX = "_login";
	
	public String getCode(String accountId){
		Jedis jedis = JedisUtil.getJedis(1);
		
		boolean  isExist = jedis.exists(accountId+CHECK_SUFFIX);
		String code;
		if(isExist){
			code = jedis.get(accountId+CHECK_SUFFIX);
		}else{
			code = UUID.randomUUID().toString();
			jedis.setex(accountId+CHECK_SUFFIX, 60, code);
		}
		//jedis.set(key, value);
		//JedisUtil.broken(jedis);
		jedis.close();
		return code;
	}
	
	public boolean check(String accountId,String code){
		
		Jedis jedis = JedisUtil.getJedis(1);
		boolean checkFlag = jedis.exists(accountId+CHECK_SUFFIX);
		if(checkFlag){
			if(code.equals(jedis.get(accountId+CHECK_SUFFIX))){
				checkFlag = true;
			}else{
				checkFlag = false;
			}
		}
		//JedisUtil.broken(jedis);
		jedis.close();
		return checkFlag;
	}
	
	public static void main(String[] args) throws InterruptedException {
		String accountId = "wjw";
		String code = AuthCodeCheck.getInstance().getCode(accountId);
		System.out.println("get code "+LocalDateTime.now());
		System.out.println("sleep");
		for (int i = 0; i < 100; i++) {
			TimeUnit.SECONDS.sleep(1);
			System.out.printf("accountid %s after %s seconds check result %s",
					accountId,String.valueOf(i),String.valueOf(AuthCodeCheck.getInstance().check(accountId, code)));
			System.out.println();
		}
	}
}
