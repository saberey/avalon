package com.avalon.ms.common.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月5日 下午4:12:30
 *@version
 */
public class Transfer {

	
	public byte[] transferCLassToBytes(String classPath){
		File file = new File(classPath);
		if(!file.isFile()){
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] tmpByte = new byte[1024];
			int total = 0;
			int length = 0;
			int limit = 0;
			while((length = fis.read(tmpByte))!=-1){
				total +=length;
				byte[] content = new byte[total];
				System.arraycopy(tmpByte, 0, content, limit, length);
				limit+=length;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally{
			return null;
		}
	}
	
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Map<String,String> tM = new HashMap<>();
		LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
		TreeMap<String, String> tm = new TreeMap<String, String>();
	}
}
