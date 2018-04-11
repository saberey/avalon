package com.avalon.ms.factory;
/**
 *@description:TODO
 *@author saber
 *@date 2017年12月27日 下午1:39:52
 *@version
 */
public class Factory {

	
	public void proceed(String person,String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class cl = Class.forName(className);
		BaseOperate bo = (BaseOperate) cl.newInstance();
		bo.operate(person);
	}
	
	//通过插入类名，动态创建对象并执行
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Factory factory = new Factory();
		factory.proceed("test","com.avalon.ms.factory.Night");
		factory.proceed("test","com.avalon.ms.factory.Daylight");
	}
}
