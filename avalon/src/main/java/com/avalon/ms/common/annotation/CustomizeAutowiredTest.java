package com.avalon.ms.common.annotation;


import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.avalon.ms.common.annotation.spring.MyInject;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月9日 上午11:12:50
 *@version
 */
@Configuration
public class CustomizeAutowiredTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new 
				AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.register(CustomizeAutowiredTest.class);
		annotationConfigApplicationContext.refresh();
		BeanClass beanClass = annotationConfigApplicationContext.getBean(BeanClass.class);
		beanClass.print();
	}
	
	@Component
	public static class BeanClass{
		
		@MyInject
		private FieldClass fieldClass;
		
		public void print(){
			//Assert.assertNotNull("fieldClass == null",fieldClass);
			//assert fieldClass==null:"fieldClass == null";
			fieldClass.print();
		}
	}
	
	@Component
	public static class FieldClass{
		public void print(){
			System.out.println("Hello World!");
		}
	}
	
	@Bean
	public AutowiredAnnotationBeanPostProcessor getAutowiredAnnotationBeanPostProcessor(){
		AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
		autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(MyInject.class);
		return autowiredAnnotationBeanPostProcessor;
	}
}
