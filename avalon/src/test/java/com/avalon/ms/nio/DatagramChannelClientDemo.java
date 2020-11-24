package com.avalon.ms.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年4月18日 下午5:31:07
 *@version
 */
public class DatagramChannelClientDemo {

	private int port = 8801;
	private DatagramChannel channel;
	private Selector selector;
	private static final Logger logger = LoggerFactory.getLogger(DatagramChannelClientDemo.class);
	
	public void service() throws Exception{
		
		channel = DatagramChannel.open();
		selector = selector.open();
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress("127.0.0.1", port));
		channel.write(ByteBuffer.wrap("客户端请求数据".getBytes()));
		channel.register(selector, SelectionKey.OP_READ);
		while(true){
			selector.select();
			Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
			while(selectionKeys.hasNext()){
				SelectionKey key = selectionKeys.next();
				selectionKeys.remove();
				if(key.isReadable()){
					receive(key);
				}
			}
		}
	}
	
	private void receive(SelectionKey key) throws IOException{
		DatagramChannel dc = (DatagramChannel) key.channel();
		String content = "";
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear();
		SocketAddress sa = dc.receive(buffer);
		logger.info(" 接收数据地址 {}",sa.toString());
		buffer.flip();
		while(buffer.hasRemaining()){
			buffer.get(new byte[buffer.limit()]);
			content +=new String(buffer.array());
		}
		buffer.clear();
		logger.info("接收到数据：{}",content);
		
		content ="";
		ByteBuffer buffer2 = ByteBuffer.allocate(1024);
		buffer2.clear();
		SocketAddress sa2 = dc.receive(buffer2);
		logger.info(" 接收数据地址 {}",sa.toString());
		buffer2.flip();
		while(buffer2.hasRemaining()){
			buffer2.get(new byte[buffer2.limit()]);
			content +=new String(buffer2.array());
		}
		buffer2.clear();
		logger.info("接收到数据：{}",content);
	}
	public static void main(String[] args) {
		DatagramChannelClientDemo dccd = new DatagramChannelClientDemo();
		try {
			dccd.service();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
