package com.avalon.ms.common.zk;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@description:TODO
 *@author saber
 *@date 2018年2月7日 下午2:30:14
 *@version
 */
public class ZkclientUtil {
	
	private ZkClient zkClient;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public ZkclientUtil(String server,int sessionTimeout,int connectTimeout,ZkSerializer zks){
		zkClient = new ZkClient(server, sessionTimeout, connectTimeout, zks);
		logger.info("创建zkclient成功，服务器|{}",server);
	}
	
	public boolean exists(String path){
		return zkClient.exists(path);
	}
	
	public void createPersist(String path,Object data){
		zkClient.createPersistent(path, data);
	}
	
	public void createEphemeral(String path,Object data){
		zkClient.createEphemeral(path, data);
	}
	
	public String createSqueuePersist(String path,Object data){
		return zkClient.createPersistentSequential(path, data);
	}
	
	public String createQueueEphemeral(String path,Object data){
		return zkClient.createEphemeralSequential(path, data);
	}
	
	
}
