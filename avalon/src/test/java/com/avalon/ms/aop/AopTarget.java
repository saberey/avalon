package com.avalon.ms.aop;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年2月5日 下午5:52:20
 *@version
 */
@Component
public class AopTarget {

	private int i;
	private String content;
	
	@PostConstruct
	private void init(){
		i = 1;
		content = "hello world";
		System.out.println("init");
	}
	
	public void test(){
		System.out.println(this.getClass().getName());
		System.out.println(111111111);
	}
}
