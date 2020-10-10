package com.avalon.ms.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;

/**
 *@description:TODO
 *@author saber
 *@date 2018年3月14日 下午2:18:52
 *@version
 */
@Service
public class ZkService {

	private ZkClient zkClient = null;
	
	@PostConstruct
	public void init(){
		zkClient = new ZkClient("127.0.0.1:2181", 30000, 30000, new ZkSerializer() {
			
			@Override
			public byte[] serialize(Object data) throws ZkMarshallingError {
				// TODO Auto-generated method stub
				return String.valueOf(data).getBytes(Charsets.UTF_8);
			}
			
			@Override
			public Object deserialize(byte[] bytes) throws ZkMarshallingError {
				// TODO Auto-generated method stub
				return new String(bytes,Charsets.UTF_8);
			}
		});
	}
	
	public List<String> getChildren(String path){
		return zkClient.countChildren(path)>0 ?zkClient.getChildren(path):null;
	}
	
	public String delete(String path,boolean isRecursion){
		String result = "";
		if(!isRecursion){
			if(zkClient.countChildren(path)>0){
				return path+"有子节点不能删除";
			}else{
				return zkClient.delete(path)?"删除节点"+path+"success!":"删除节点"+path+"失败";
			}
		}else{
			return zkClient.deleteRecursive(path)?"递归删除节点"+path+"success!":"递归删除节点"+path+"失败";
		}
	}
}
