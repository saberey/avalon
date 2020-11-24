package com.avalon.ms.copy;

import java.util.List;

import com.avalon.ms.bean.People;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月28日 下午2:14:33
 *@version
 */
public class CopyObject {
	
	public static void main(String[] args) {
		
		List<Object> list = null;
		
		if(list == null||list.isEmpty()){
			System.out.println(1);
		}
		/*if(list.isEmpty()){
			System.out.println(2);
		}*/
		/*People  p1 = new People();
		p1.setName("test");
		p1.setAge(18);
		//People p2 = (People) copy(p1);
		People p2 = p1;
		p1.setName("uc");
		p1.setAge(15);
		System.out.println(p1.toString());
		System.out.println(p2.toString());*/
	}
	
	public static Object copy(People p1){
		People p2 = new People();
		p2.setAge(p1.getAge());
		p2.setName(p1.getName());
		return p2;
	}
}
