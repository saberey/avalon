package com.avalon.ms.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 *@description:TODO
 *@author saber
 *@date 2018年4月16日 上午11:22:23
 *@version
 */
public class UDPServer {

	public static void main(String[] args) throws Exception {
		DatagramChannel datagramChannel = DatagramChannel.open();
		datagramChannel.socket().bind(new InetSocketAddress("127.0.0.1", 801));
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Selector selector = Selector.open();
		datagramChannel.configureBlocking(false);
		datagramChannel.register(selector,SelectionKey.OP_READ);
		while(true){
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				if(key.isReadable()){
					DatagramChannel dc = (DatagramChannel)key.channel();
					/*int reads = dc.read(buffer);
					buffer.flip();
					byte[] bytes = new byte[reads];
					buffer.get(bytes);
					System.out.println("receive: "+new String(bytes));
					buffer.clear();*/
					SocketAddress sa = dc.receive(buffer);
					System.out.println(sa.toString());
					buffer.flip();
					String tmpContent = "";
					while(buffer.hasRemaining()){
						buffer.get(new byte[buffer.limit()]);
						tmpContent += new String(buffer.array());
					}
					buffer.clear();
					System.out.println("接收到数据："+tmpContent);
				}
			}
		}
	}
}
