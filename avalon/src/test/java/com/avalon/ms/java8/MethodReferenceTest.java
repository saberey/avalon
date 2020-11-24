package com.avalon.ms.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年9月25日 上午11:25:04
 *@version
 */
public class MethodReferenceTest {

	public static MethodReferenceTest create(Supplier<MethodReferenceTest> supplier){
		return supplier.get();
	}
	
	public static void collide(MethodReferenceTest methodReferenceTest){
		System.out.println("Collided "+methodReferenceTest.toString());
	}
	
	public void follow(MethodReferenceTest methodReferenceTest){
		System.out.println("Following the "+methodReferenceTest.toString());
	}
	
	public void repair(){
		System.out.println("Repaired "+this.toString());
	}
	
	public static void main(String[] args) {
		//第一种方法引用的类型是构造器引用，语法是Class::new，或者更一般的形式：Class<T>::new。注意：这个构造器没有参数。
		MethodReferenceTest mReferenceTest = MethodReferenceTest.create(MethodReferenceTest :: new );
		List<MethodReferenceTest> mReferenceTestList = Arrays.asList(mReferenceTest);
		//第二种方法引用的类型是静态方法引用，语法是Class::static_method。注意：这个方法接受一个Car类型的参数。
		mReferenceTestList.forEach(MethodReferenceTest :: collide);
		//第三种方法引用的类型是某个类的成员方法的引用，语法是Class::method，注意，这个方法没有定义入参：
		mReferenceTestList.forEach(MethodReferenceTest :: repair);
		//第四种方法引用的类型是某个实例对象的成员方法的引用，语法是instance::method。注意：这个方法接受一个Car类型的参数：
		mReferenceTestList.forEach(mReferenceTest :: follow);
	}
}
