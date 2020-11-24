package com.avalon.ms.aop;

import org.springframework.stereotype.Component;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年2月5日 下午5:58:28
 *@version
 */
@Component
public class StackApT {

	public void doBefore(){
		System.out.println("before");
	}
	
	public void doAfter(){
		System.out.println("after");
	}
}
