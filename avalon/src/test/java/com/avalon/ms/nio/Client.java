package com.avalon.ms.nio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年4月10日 下午4:01:33
 *@version
 */
public class Client {

	public static void main(String[] args) throws Exception {
		
		Socket socket = new Socket();
		socket.connect(new InetSocketAddress("127.0.0.1", 8080));
		System.out.println("connect!");
		OutputStream ops = socket.getOutputStream();
		ops.write("hello world".getBytes());
		//ops.close();
		System.out.println("write over!");
		InputStream is = socket.getInputStream();
		//ByteBuffer buffer = ByteBuffer.allocate(1024);
		byte[] bytes = new byte[1024];
		int count = 0;
		int offset = 0;
		while((count = is.read(bytes))!=-1){
			//buffer.put(bytes,0,count);
			System.out.println("read content!"+new String(bytes));
		}
		System.out.println("read over!");
		//buffer.flip();
		//System.out.println(buffer.toString());
		ops.close();
		is.close();
		socket.close();
		System.out.println("disconnect!");
		
	}
}
