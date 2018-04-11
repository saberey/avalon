package test;
/**
 *@description:TODO
 *@author saber
 *@date 2017年11月21日 上午11:02:54
 *@version
 */
public class NumBoundTest {

	public static void main(String[] args) {
		int num = Integer.MAX_VALUE+10;
		if(num>Integer.MAX_VALUE||num<Integer.MIN_VALUE){
			System.out.println(1);
		}else{
			System.out.println(String.format("num min  content is %s", Integer.MIN_VALUE));
			System.out.println(String.format("num content is %s", num));
			System.out.println(2);
		}
	}
}
