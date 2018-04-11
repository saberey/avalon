package com.avalon.ms.zk;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.codehaus.groovy.transform.tailrec.GotoRecurHereException;

import com.google.common.base.Charsets;

/**
 *@description:TODO
 *@author saber
 *@date 2018年2月7日 下午2:30:43
 *@version
 */
public class ZkClientTest {
	
	
	public static void main(String[] args) throws Exception {
		
		ZkClient zkClient  = new ZkClient("192.168.212.121:2181", 30000,30000,new ZkSerializer() {
			
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
		String path = "/";
		zkClient.subscribeChildChanges(path, new IZkChildListener() {
			
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds)
					throws Exception {
				// TODO Auto-generated method stub
				System.out.println("path "+parentPath+" child changed. children is :"+currentChilds);
			}
		});
		
		/*if(!zkClient.exists(path)){
			zkClient.createPersistent(path);
			System.out.println("创建持久节点"+path);
		}else{
			System.out.println(path+" 已存在");
		}
		String subPath = path+"test";
		if(!zkClient.exists(subPath)){
			zkClient.createPersistent(subPath);
			System.out.println("创建持久节点"+subPath);
		}else{
			System.out.println(subPath+"已存在");
		}*/
		String root = "/";
		listChildren(zkClient,root);
		//List<String> children = zkClient.getChildren(root);
		//String DELETE_PREFIX = "6fdb7d4djob";
	//	String DELETE_PREFIX2 = "6fdb7d4djob";
		/*if(children.size()>0){
			for (String each : children) {
				System.out.println(root+each);
				System.out.println(zkClient.readData(root+each));
				listChildren(zkClient, root+each);
				if(each.startsWith(DELETE_PREFIX)||each.startsWith(DELETE_PREFIX2)){
					delete(zkClient,root+each);
					//delete(zkClient,root+each);
				}
			}
		}*/
	}
	
	
	public static void listChildren(ZkClient zkClient,String path){
		System.out.println("path: "+path);
		if(zkClient.countChildren(path)>0){
			for (String each : zkClient.getChildren(path)) {
				listChildren(zkClient, path+PREFIX+each);
			}
		}
	}
	
	public static void deletePath(ZkClient zkClient,String path){
		zkClient.deleteRecursive(path);
	}
	
	public static void delete(ZkClient zkClient,String path){
		if(hasChild(zkClient, path)){
			for (String each : zkClient.getChildren(path)) {
				delete(zkClient,path+PREFIX+each);
			}
		}
		//delete(zkClient,path);
		zkClient.delete(path);
	}
	
	private static final String PREFIX = "/";
	
	public void subscribeChildChange(ZkClient zkClient,String path){
		zkClient.subscribeChildChanges(path, new IZkChildListener() {
			
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds)
					throws Exception {
				// TODO Auto-generated method stub
				System.out.println("path "+parentPath+" child changed. children is :"+currentChilds);
			}
		});
	}
	
	public static boolean hasChild(ZkClient zkClient,String path){
		return zkClient.countChildren(path)>0?true:false;		
	}
}
