package com.avalon.ms.java8;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 *@description:TODO
 *@author saber
 *@date 2017年9月25日 下午5:40:50
 *@version
 */
public class ParallelArraysTest {

	public static void main(String[] args) {
		
		long[] arrayOfLong = new long[ 20000];
		
		Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1000000));
		
		Arrays.stream(arrayOfLong).limit(10).forEach( i -> System.out.print(i + " "));
		
		System.out.println();
		Arrays.parallelSort(arrayOfLong);
		Arrays.stream(arrayOfLong).limit(10).forEach( i -> System.out.print(i + " "));
		
		
	}
}
