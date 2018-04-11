package com.avalon.ms.bean;

import java.io.Serializable;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月24日 下午2:43:00
 *@version
 */
public class People implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 119899434325800848L;
	
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString(){
		return "people["+age+"|"+name+"]";
	}
}
