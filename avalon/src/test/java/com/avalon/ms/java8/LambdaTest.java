package com.avalon.ms.java8;

import java.util.Arrays;

/**
 *@description:TODO
 *@author saber
 *@date 2017年9月25日 上午9:49:13
 *@version
 */
public class LambdaTest {

	public static void main(String[] args) {
		
		//Arrays.asList("a","b","c").forEach(e -> { System.out.println(e);});
		//Arrays.asList("a","b","c").forEach(e -> System.out.println(e)); 
		String separator = "|";
		//Arrays.asList("a","b","c").forEach(e -> System.out.print(e+separator));
		Arrays.asList("a","b","c").sort((e1,e2)-> {int result = e1.compareTo(e2);return result;});
	}
}
