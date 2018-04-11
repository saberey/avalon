package com.avalon.ms.aop;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *@description:TODO
 *@author saber
 *@date 2018年2月7日 上午11:14:39
 *@version
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AopService {

	private int index;
	private String serviceName;
	
	public AopService(){
		
	}
	
	public void init(int index,String serviceName){
		this.index = index;
		this.serviceName = serviceName;
	}
	
	public void service(){
		System.out.println(this+"["+index+"|"+serviceName+"]"+" is serving");
	}
}
