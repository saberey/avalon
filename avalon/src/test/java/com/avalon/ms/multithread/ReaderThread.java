package com.avalon.ms.multithread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *@description:TODO
 *@author saber
 *@date 2017年11月14日 下午5:51:26
 *@version
 */
public class ReaderThread extends Thread{

	private final Socket socket;
	private final InputStream in;
	private final int BUFSIZE = 1024;
	
	public ReaderThread(Socket socket) throws IOException {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.in = socket.getInputStream();
	}
	
	public void run(){
		try {
			byte[] buf = new byte[BUFSIZE];
			while(true){
				int count = in.read(buf);
				if(count<0)
					break;
				else if(count>0)
					System.out.println();//
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void interrupt(){
		try{
			socket.close();
		}catch(IOException e)
		{}
		finally{
			super.interrupt();
		}
	}
}
