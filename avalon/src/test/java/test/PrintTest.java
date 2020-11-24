package test;

import java.util.function.BooleanSupplier;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月15日 下午7:14:19
 *@version
 */
public class PrintTest {

	public void print(){
		if(new Object(){
			public boolean print(){
				System.out.print("a");
				return false;
			}
		}.print()){
			System.out.println("a");
		}else{
			System.out.println("b");
		}
	}
	
	public void print2(){
		if(System.out.printf("a")==null){
			System.out.println("a");
		}else{
			System.out.println("b");
		}
	}
	
	public void print3(){
		if(((BooleanSupplier)(()->{System.out.print("a");return false;})).getAsBoolean()){
			System.out.println("a");
		}else{
			System.out.println("b");
		}
	}
	public void print4(){
		if(((PHelper)(()->{System.out.print("a");return false;})).getBoolean()){
			System.out.println("a");
		}else{
			System.out.println("b");
		}
	}
	
	public static void main(String[] args) {
		PrintTest printTest = new PrintTest();
		printTest.print();
		printTest.print2();
		printTest.print3();
		printTest.print4();
	}
}

interface PHelper{
	boolean getBoolean();
}
