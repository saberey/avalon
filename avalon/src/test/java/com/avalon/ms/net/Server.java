package com.avalon.ms.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.avalon.ms.bean.People;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月24日 下午1:17:52
 *@version
 */
public class Server {

	private static ServerSocket serverSocket = null;
	
	public Server(){
	}
	
	public void init(int port){
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("创建ServerSocket异常。"+e.getMessage());
		}
	}
	
	public void service(){
		while(true){
			System.out.println("服务端已启动！");
			Socket socket = null;
			InputStream is = null;
			ObjectOutputStream os = null;
			try {
				socket = serverSocket.accept();
				is = socket.getInputStream();
				byte[] tmpByte = new byte[1024];
				while(is.read(tmpByte)!=-1){
					System.out.println("ip:"+socket.getInetAddress().getHostAddress()+"port:"+socket.getPort()+new String(tmpByte));
				}
				System.out.println("out");
				//socket.shutdownInput();
				os = new ObjectOutputStream(socket.getOutputStream());
				People people = new People();
				people.setName("test");
				people.setAge(18);
				os.writeObject(people);
				os.flush();
				
				socket.shutdownOutput();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try {
					is.close();
					os.close();
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.init(2345);
		server.service();
	}
}
