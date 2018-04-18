package com.avalon.ms.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *@description:TODO
 *@author saber
 *@date 2018年4月9日 下午4:20:14
 *@version
 */
public class ChannelTest {

	public static void main(String[] args) throws Exception {
		
		RandomAccessFile file = new RandomAccessFile("E:\\duizhang\\duizhang\\xwFile\\20180408_6000003151_ALLBALANCE.txt", "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(48);
		int byteread = channel.read(byteBuffer);
		while(byteread !=-1){
			System.out.println("read:"+byteread);
			byteBuffer.flip();
			while(byteBuffer.hasRemaining()){
				System.out.println((char)byteBuffer.get());
			}
			byteBuffer.clear();
			byteread = channel.read(byteBuffer);
		}
		file.close();
	}
}
