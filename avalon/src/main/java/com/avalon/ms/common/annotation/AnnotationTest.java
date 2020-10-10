package com.avalon.ms.common.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.avalon.ms.common.annotation.spring.InjectTest;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月18日 上午10:06:10
 *@version
 */
public class AnnotationTest {

	
	@InjectTest("This is just a test!")
	public void hi(){
		System.out.println("hello World");
	}
	
	
	public static void main(String[] args) {
		Class targetClass = AnnotationTest.class;
		Method[] methods = targetClass.getMethods();
		for(Method m : methods){
			if(m.isAnnotationPresent(InjectTest.class)){
				InjectTest ijt = m.getAnnotation(InjectTest.class);
				System.out.println(ijt.value());
				try {
					m.invoke(targetClass.newInstance(), null);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
