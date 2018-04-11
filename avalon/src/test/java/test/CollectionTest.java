package test;

import java.util.Date;
import java.util.HashMap;

/**
 *@description:TODO
 *@author saber
 *@date 2018年1月5日 上午11:35:10
 *@version
 */
public class CollectionTest {

	public static void main(String[] args) {
		//Hashtable<Object, Object> ht = new Hashtable<Object, Object>();
		User user1 = new User(1, 2, 3);
		User user2 = new User("1","2","3");
		CollectionTest collectionTest = new CollectionTest();
		collectionTest.setUser(user2);
		HashMap<Date, String> hm = new HashMap<>();
		Date date = new Date();
		System.out.println("orginal hashcode:"+date.hashCode());
		hm.put(date, "hello");
		date.setTime(12345l);
		System.out.println("new hashcode:"+date.hashCode());
		System.out.println(hm.containsKey(date));
		
		String str ="12345";
		String str2 = new String("12345");
		String str3 = new String("12345");
		int i = 123;
		int j = 123;
		int k = 128;
		int h = 128;
		
		System.out.println(str.equals(str2));
		System.out.println(str2.equals(str3));
		System.out.println(str.hashCode());
		System.out.println(str2.hashCode());
		System.out.println(str3.hashCode());
		
		System.out.println(i==j);
		System.out.println(k==h);
		Integer q = new Integer(123);
		Integer p = new Integer(128);
		System.out.println(q.equals(i));
		System.out.println(p.equals(k));
	}
	
	public void setUser(User<? extends Integer, ? extends String, ? extends String> user){
		System.out.println("right");
	}
	
	public <T> void getB(T x){
		System.out.println(x);
	}
}

class User<T1,T2,T3>{
	private T1 t1;
	private T2 t2;
	private T3 t3;
	public User(T1 t1,T2 t2,T3 t3){
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}
}
