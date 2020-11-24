package com.avalon.ms.nio;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年4月18日 下午4:56:28
 *@version
 */
public class DatagramChannelServerDemo {

	private int port = 8801;
	private DatagramChannel channel;
	private Selector selector;
	private Charset charset;
	
	private static final Logger logger = LoggerFactory.getLogger(DatagramChannelServerDemo.class);
	
	private void init() throws Exception {
		channel = DatagramChannel.open();
		selector = Selector.open();
		charset = Charset.forName("utf-8");
	}
	
	public void service() throws Exception{
		//init
		init();
		channel.configureBlocking(false);
		channel.socket().bind(new InetSocketAddress("127.0.0.1", port));
		channel.register(selector, SelectionKey.OP_READ);
		
		while(true){
			selector.select();
			//System.out.println("接收到新的channel");
			logger.info("接收到新的channel");
			Iterator<SelectionKey> selectKeys = selector.selectedKeys().iterator();
			while(selectKeys.hasNext()){
				SelectionKey key = selectKeys.next();
				selectKeys.remove();
				if(key.isReadable()){
					receive(key);
				}
				if(key.isWritable()){
					send(key);
				}
			}
		}
		
	}
	
	 /* 
     * 接收 用receive()读IO 
     * 作为服务端一般不需要调用connect()，如果未调用<span style="font-family: 
     * Arial, Helvetica, sans-serif;">connect()时调</span><span style="font-family: 
     * Arial, Helvetica, sans-serif;">用read()\write()读写，会报java.nio.channels</span> 
     * .NotYetConnectedException 只有调用connect()之后,才能使用read和write. 
     */  
	private void receive(SelectionKey key) throws Exception{
		if(key == null){
			return;
		}
		DatagramChannel dc = (DatagramChannel) key.channel();
		String content = "";
		ByteBuffer buffer = ByteBuffer.allocate(1024);
	/*	dc.read(buffer);//报错NotYetConnectedException只有调用connect()之后,才能使用read和write. 
		buffer.flip();*/
		SocketAddress sa = dc.receive(buffer);
		String reqAddress = sa.toString().replaceAll("/", "").split(":")[0];
		String reqPort = sa.toString().replaceAll("/", "").split(":")[1];
		logger.info(" 接收channel address:{}:port:{}",reqAddress,reqPort);
		buffer.flip();
		while(buffer.hasRemaining()){
			buffer.get(new byte[buffer.limit()]);
			content +=new String(buffer.array());
		}
		buffer.clear();
		logger.info(" 接受数据:{}",content);
		
		ByteBuffer bufBack = ByteBuffer.allocate(1024);
		bufBack.clear();
		bufBack.put("udp是一个不可靠的连接协议，服务端与客户端之间不建立连接".getBytes());
		bufBack.flip();
		channel.send(bufBack, new InetSocketAddress(reqAddress, Integer.valueOf(reqPort)));
		
		ByteBuffer bufBack2 = ByteBuffer.allocate(1024);
		bufBack2.clear();
		bufBack2.put("发送结束".getBytes());
		bufBack2.flip();
		channel.send(bufBack2, new InetSocketAddress(reqAddress, Integer.valueOf(reqPort)));
		
	}
	
	private void send(SelectionKey key) throws Exception{
		DatagramChannel dc = (DatagramChannel) key.channel();
		dc.write(ByteBuffer.wrap("123456test".getBytes()));
		logger.info(" 发送数据 ");
	}
	
	/**传输文件**/
	private void sendFile(SelectionKey key) throws Exception{
		if(key == null){
			return ;
		}
		ByteBuffer buffer = (ByteBuffer) key.attachment();
		SocketChannel sc = (SocketChannel) key.channel();
		//String data = decode(buff);
		FileInputStream fileInput = new FileInputStream(new File(""));
		FileChannel fileChannel = fileInput.getChannel();
		fileChannel.transferTo(0, fileChannel.size(), sc);
		fileChannel.close();
		
	}
	
	public static void main(String[] args) {
		DatagramChannelServerDemo dcsd = new DatagramChannelServerDemo();
		try {
			dcsd.service();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
