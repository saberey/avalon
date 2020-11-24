package com.avalon.ms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月28日 上午10:51:49
 *@version
 */
public class ArrayCopyTest {

	public static void main(String[] args) {
		List<Integer> orgList = new ArrayList<>();
		for (int i = 0; i < 500; i++) {
			orgList.add(i);
		}
		
		int arraySize = orgList.size()%50 > 0 ? orgList.size()/50 + 1 : orgList.size()/50;
		
		List<List<Integer>> outer = new ArrayList<List<Integer>>();
		List<Integer> tmpList = null;
		for (int i=0;i<arraySize;i++){
			tmpList = new ArrayList<Integer>();
			tmpList = orgList.subList(i*50, orgList.size()<(i+1)*50?orgList.size():(i+1)*50);
			//tmpList = deepCopy(orgList.subList(i*50, orgList.size()<(i+1)*50?orgList.size():(i+1)*50), tmpList);
			outer.add(tmpList);
		}
		/*try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		orgList.clear();
		
		
		for(List curList : outer){
			System.out.println(curList.toString());
			curList.clear();
			/*try {
				//TimeUnit.SECONDS.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		
	}
	
	public static List deepCopy(List srcList,List destList){
		destList =  new ArrayList<>(Arrays.asList(new Object[srcList.size()]));
		Collections.copy(destList, srcList);
		return destList;
	}
}
