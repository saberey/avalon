package com.avalon.ms.java8;

import java.util.Optional;

/**
 *@description:TODO
 *@author saber
 *@date 2017年9月25日 下午1:45:30
 *@version
 */
public class OptionalTest {

	public static void main(String[] args) {
		Optional<String> fullName = Optional.ofNullable(null);
		System.out.println("full name is set ?"+fullName.isPresent());
		System.out.println("full name: "+fullName.orElseGet(()->"[none]"));
		System.out.println(fullName.map(s ->"hey "+s+"!").orElse("hey stranger!"));
	}
}
