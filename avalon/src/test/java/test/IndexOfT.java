package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月13日 下午2:49:52
 *@version
 */
public class IndexOfT {

	
	public static void main(String[] args) {
		String test = "Connection timed out";
		
		if(test.trim().indexOf("No route to host")!=-1
				||test.trim().indexOf("Connection refused")!=-1
				||test.trim().indexOf("Connection timed out")!=-1){
			System.out.println(test);
		}else{
			System.out.println("no error");
		}
			List<Integer> original = Arrays.asList(new Integer[]{1,7,9,4,5,6,7,8});
			Collections.sort(original);
			System.out.println(original.toString());
	}
}
