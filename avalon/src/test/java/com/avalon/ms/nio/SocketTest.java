package com.avalon.ms.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年4月12日 下午1:58:20
 *@version
 */
public class SocketTest {

	private static final Logger logger = LoggerFactory.getLogger(SocketTest.class);
	
	public static void main(String[] args) throws Exception {
		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress("127.0.0.1", 9001));
		ByteBuffer buffer = ByteBuffer.allocate(128);
		String requestConent = UUID.randomUUID().toString()+"|"+Thread.currentThread().getName();
		buffer.put(requestConent.getBytes());
		buffer.flip();
		sc.write(buffer);
		logger.info(" {} send message {}",Thread.currentThread().getName(),requestConent);
		sc.close();
	}
}
