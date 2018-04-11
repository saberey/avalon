package com.avalon.ms.service;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.avalon.ms.common.util.HttpClientUtil;

public class IPSwapService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//private AtomicInteger atomicTryTimes = new AtomicInteger(tryTimes);
	private ThreadLocal<Boolean> threadLocal = new ThreadLocal<Boolean>();
	private int ipIndex = 0;
	private static int tryTimes = 3;
	private static boolean changeFlag = false;
	
	private String url = "http://localhost:8080/ms/ipswap";
	
	public IPSwapService(){
		tryTimes = Runtime.getRuntime().availableProcessors();
		logger.info("IPSwapService init tryTimes {}",tryTimes);
		logger.info("IPSwapService instance object hashcode {}",this.hashCode());
	}
	
	// /query
	public void query(){
		try{
			threadLocal.set(changeFlag);
			logger.info(" method {} index {} tryTimes {} flag {}","query",ipIndex,tryTimes,changeFlag);
			int result = getPostReturn("query");
			TimeUnit.SECONDS.sleep(10);
			if(result>200){
				throw new Exception("query return code:"+result);
			}
		}catch(Exception e){
			machineChange();
		}
	}
	
	//  action  in ( query reg transfer)
	private int getPostReturn(String action) {
		Object[] results = HttpClientUtil.httpPost(url+"/"+action, null);
		logger.info(results[0]+new String((byte[])results[1]));
		return (int) results[0];
	}
	
	// /transfer
	public void transfer(){
		try{
			threadLocal.set(changeFlag);
			logger.info(" method {} index {} tryTimes {} flag {}","transfer",ipIndex,tryTimes,changeFlag);
			int result = getPostReturn("transfer");
			if(result>200){
				throw new Exception("transfer return code:"+result);
			}
		}catch(Exception e){
			machineChange();
		}
	}
	
	// /reg
	public void reg(){
		try{
			//logger.info(" method {} index {} tryTimes {}","reg",ipIndex,tryTimes);
			threadLocal.set(changeFlag);
			logger.info(" method {} index {} tryTimes {} flag {}","reg",ipIndex,tryTimes,changeFlag);
			int result = getPostReturn("reg");
			if(result>200){
				throw new Exception("reg return code:"+result);
			}
		}catch(Exception e){
			machineChange();
		}
		logger.info("reg end try times {}",tryTimes);
	}
	
	private synchronized void machineChange() {
		logger.info(" try times {}",tryTimes);
		if(threadLocal.get()!=changeFlag){
			logger.info("flag {} return ",changeFlag);
			return;
		}
		
		if(!changeFlag){
			tryTimes--;
			if(tryTimes<0){
				tryTimes =  Runtime.getRuntime().availableProcessors();
				ipIndex = (ipIndex+1)%2;
				changeFlag = true;
				logger.info("machine change index {}",ipIndex);
			}
		}else{
			tryTimes--;
			if(tryTimes<0){
				tryTimes =  Runtime.getRuntime().availableProcessors();
				ipIndex = (ipIndex+1)%2;
				changeFlag = false;
				logger.info("machine change index {}",ipIndex);
			}
		}
		logger.info("try times {} ipIndex {} changeFlag {}",tryTimes,ipIndex,changeFlag);
	}
}
