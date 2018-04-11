package com.avalon.ms.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 *@description:TODO
 *@author saber
 *@date 2018年4月10日 下午3:26:15
 *@version
 */
public class NioServer {

	public static void main(String[] args) throws Exception{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		Selector selector = Selector.open();
		
		serverSocketChannel.socket().bind(new InetSocketAddress(8080));
		serverSocketChannel.configureBlocking(false);
		
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while(true){
				
			selector.select();
			Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
			while(keyIterator.hasNext()){
				SelectionKey key = keyIterator.next();
				if(key.isAcceptable()){
					SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
					System.out.println("accept"+clientChannel.getRemoteAddress());
					clientChannel.configureBlocking(false);
					clientChannel.register(key.selector(), SelectionKey.OP_READ,ByteBuffer.allocate(1024));
				}
				if(key.isReadable()){
					SocketChannel clientChannel = (SocketChannel) key.channel();
					System.out.println("read"+clientChannel.getRemoteAddress());
					ByteBuffer buf = (ByteBuffer) key.attachment();
					long bytesRead = clientChannel.read(buf);
					if(bytesRead ==-1){
						clientChannel.close();
					}else{
						key.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
						System.out.println("Get data length:"+bytesRead);
					}
				}
				
				if(key.isValid()&&key.isWritable()){
					ByteBuffer buf = (ByteBuffer) key.attachment();
					buf.flip();
					SocketChannel clientChannel = (SocketChannel) key.channel();
					System.out.println("write"+clientChannel.getRemoteAddress());
					clientChannel.write(buf);
					if(!buf.hasRemaining()){
						key.interestOps(SelectionKey.OP_READ);
					}
					buf.compact();
				}
				
				keyIterator.remove();
				System.out.println("remove key "+key.toString());
			}
		}
			
	}
}
