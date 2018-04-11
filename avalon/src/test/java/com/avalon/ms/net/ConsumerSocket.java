package com.avalon.ms.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.avalon.ms.bean.People;


/**
 *@description:TODO
 *@author saber
 *@date 2017年11月24日 下午1:17:11
 *@version
 */
public class ConsumerSocket {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		
		Socket client = new Socket("127.0.0.1",2345);
		OutputStream os = client.getOutputStream();
		os.write("Hello World".getBytes());
		os.flush();
		client.shutdownOutput();
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		System.out.println("1");
	    People people =  (People) ois.readObject();
	    System.out.println(people.getName()+""+people.getAge());
	    ois.close();
	    client.close();
	}
}
