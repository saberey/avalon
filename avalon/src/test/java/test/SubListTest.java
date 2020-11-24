package test;

import java.util.ArrayList;
import java.util.List;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年10月31日 上午10:11:36
 *@version
 */
public class SubListTest {

	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList();
		for(int i=0;i<100;i++){
			list1.add(i);
		}
		int tmpListLength = list1.size();
		System.out.println("list size:"+tmpListLength);
		int split_size = 10;
		int arraySize = tmpListLength%split_size>0 ? tmpListLength/split_size +1 : tmpListLength/split_size;
		System.out.println("arraySize:"+arraySize);
		List<Integer> tmpList = new ArrayList<Integer>();
		for(int j=0;j<arraySize;j++){
			tmpList = list1.subList(j*arraySize,tmpListLength<(j+1)*arraySize?tmpListLength:(j+1)*arraySize);
			System.out.println("list "+j+" size: "+tmpList.size());
			System.out.println("list content: "+tmpList.toString());
		}
	}
}
