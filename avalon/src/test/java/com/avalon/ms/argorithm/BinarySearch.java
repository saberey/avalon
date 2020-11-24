package com.avalon.ms.argorithm;

import java.lang.instrument.Instrumentation;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月21日 上午10:13:55
 *@version
 */
public class BinarySearch {
	
	
	private final static Logger logger = Logger.getLogger(BinarySearch.class);
	
	public static int binarySearch(int[] num,int key){
		int low = 0;
		int high = num.length-1;
		while(low<high){
			int mild = (low+high)/2;
			int mildValue = num[mild];
			if(mildValue<key){
				low = mild+1;
			}else if(mildValue>key){
				high = mild-1;
			}else{
				return mild;
			}
		}
		return -(low-1);
	}
	
	public static void main(String[] args) {
		int[] tmpInt = new int[Integer.MAX_VALUE];
		for (int i = 0; i < tmpInt.length; i++) {
			tmpInt[i]=i;
		}
		int key = new Random().nextInt(Integer.MAX_VALUE);
		logger.info(" key values is :["+key+"]");
		int index = BinarySearch.binarySearch(tmpInt, key);
		logger.info("target index is :["+index+"]");
		logger.info("int array["+tmpInt+"]");
	}
}
