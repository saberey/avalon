package com.avalon.ms.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *@description:TODO
 *@author saber
 *@date 2018年4月12日 上午11:43:16
 *@version
 */
public class SocketServerTest {
	
	private static  final	Logger logger = LoggerFactory.getLogger(SocketServerTest.class);

	public static void main(String[] args) throws Exception {
		ServerSocketChannel  ssc = ServerSocketChannel.open();
		ssc.bind(new InetSocketAddress(9001));
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		while(true){
			SocketChannel sc = ssc.accept();
			logger.info("receive new request! {}",sc.getRemoteAddress());
			pool.execute(new ReceiveTask(sc));
		}
	}
	
	private static class ReceiveTask implements Runnable{
		
		private SocketChannel sc;
		
		public ReceiveTask(SocketChannel sc){
			this.sc = sc;
		}
		@Override
		public void run() {
			logger.info("thread {} read start!",Thread.currentThread().getName());
			// TODO Auto-generated method stub
			ByteBuffer buffer = ByteBuffer.allocate(64);
			try {
				int length ;
				while((length= sc.read(buffer))!=-1){
					buffer.flip();
					byte[] bytes = new byte[length];
					buffer.get(bytes);
					buffer.clear();
					logger.info("thread {} receive message {}!",Thread.currentThread().getName(),new String(bytes));
				}
				sc.close();
				logger.info("thread {} read end!",Thread.currentThread().getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


