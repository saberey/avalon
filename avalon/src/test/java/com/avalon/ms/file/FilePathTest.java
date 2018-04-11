package com.avalon.ms.file;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class FilePathTest {
	
	//@Test
	public void test() {
		//fail("Not yet implemented");
		String path = "pom.xml";
		File file = new File(path);
		if(file.isFile()){
			System.out.println(file.getAbsolutePath());
		}
	}
	
	private static String path;
	
	@Before
	public void before(){
		path= "pom.xml";
	}
	
	
	//@Test
	public void test2(){
		File file = new File(path);
		if(file.isFile()){
			System.out.println(file.getAbsolutePath());
		}
	}
	
	@Test
	public void test3(){
		File file = new File("e:/th.txt");
	}
}
