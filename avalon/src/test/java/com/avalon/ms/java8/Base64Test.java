package com.avalon.ms.java8;

import java.nio.charset.StandardCharsets;
import java.util.Base64;



/**
 *@description:TODO
 *@author saber
 *@date 2017年9月25日 下午5:35:08
 *@version
 */
public class Base64Test {

	public static void main(String[] args) {
		
		final String text = "Base64 final in java 8!";
		
		final String encoded = Base64.getEncoder()
				.encodeToString(text.getBytes(StandardCharsets.UTF_8));
		System.out.println(encoded);
		
		final String decoded = new String(Base64.getDecoder()
				.decode(encoded),StandardCharsets.UTF_8);
		System.out.println(decoded);
	}
}
