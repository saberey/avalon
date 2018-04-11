package com.avalon.ms.algorithm;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 *@description:TODO
 *@author saber
 *@date 2017年12月13日 下午3:17:01
 *@version
 */
public class QuickSort {

	public static void quickSort(int[] array,int left,int right) throws InterruptedException{
		System.out.println("数组array:"+Arrays.toString(array)+" left:"+left+" right:"+right);
   		if(left>=right){
			return;
		}
		int i = left;
		int j = right;
		int key = array[left];
		System.out.println("key:array["+left+"]:"+key);
		while(i<j){
			while(i<j&&array[j]>key){
				j--;
			}
			TimeUnit.SECONDS.sleep(1);
			if(i<j){
				array[i] = array[j];//右边比小于key的元素同i进行交换
				System.out.println("array["+j+"]:"+array[j]+"赋值给array["+i+"]");
				i++;
			}
			while(i<j&&array[i]<key){
				i++;
			}
			TimeUnit.SECONDS.sleep(1);
			if(i<j){
				array[j] = array[i]; //左边大于key的元素同j进行交换
				System.out.println("array["+i+"]:"+array[i]+"赋值给array["+j+"]");
				j--;
			}
		}
		array[i] = key;
		quickSort(array,left,i-1);
		quickSort(array, i+1, right);
	}
	
	public static void main(String[] args) throws InterruptedException {
		int array[] = {21,5,4,20,33,24,3,24,24};
		quickSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
	}
}
