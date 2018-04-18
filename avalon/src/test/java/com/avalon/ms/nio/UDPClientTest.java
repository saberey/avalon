package com.avalon.ms.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 *@description:TODO
 *@author saber
 *@date 2018年4月16日 上午11:47:59
 *@version
 */
public class UDPClientTest {

	public static void main(String[] args) throws Exception {
		DatagramChannel datagramChannel = DatagramChannel.open();
		datagramChannel.connect(new InetSocketAddress("127.0.0.1",801));
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put("Hello world".getBytes());
		buffer.flip();
		datagramChannel.write(buffer);
		//datagramChannel.close();
		//datagramChannel.send(buffer, new InetSocketAddress("127.0.0.1",801));
		//datagramChannel.close();
		
	}
}
