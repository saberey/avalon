package test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;



public class ResourcesTest {

	public static void main(String[] args) throws Exception {
		String file = "E:\\wangjw\\学习\\Ms\\Ms\\ms\\src\\test\\java\\test\\test.txt";
		//ClassLoader cl = ResourcesTest.class.getClassLoader();
		//InputStream ips = cl.getResourceAsStream(file);
		InputStream ips  = new FileInputStream(file);
		byte[] bytes = new byte[1024];
		while(ips.available()!=0){
			ips.read(bytes);
			System.out.println(new String(bytes));
		}
		ips.close();
	}
}
