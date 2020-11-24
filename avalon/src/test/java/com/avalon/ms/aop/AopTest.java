package com.avalon.ms.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年2月5日 下午5:54:48
 *@version
 */
public class AopTest {

	public static void main(String[] args) {
		
		ApplicationContext context = new FileSystemXmlApplicationContext("E:/wangjw/学习/Ms/Ms/ms/src/test/resources/ApplicationContext.xml");
		//AopTarget at = context.getBean(AopTarget.class);
		//at.test();
		GlobalAPI gla = context.getBean(GlobalAPI.class);
		gla.init();
	}
}
