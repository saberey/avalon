package com.avalon.ms.common.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketException;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月25日 下午4:49:52
 *@version
 */
public class FtpUtil {

	private final static Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	
	public static FTPClient login(String url,int port,String userName,String passWord) throws SocketException, IOException{
		FTPClient ftp = new FTPClient();
		//连接
		ftp.connect(passWord, port);
		//登陆
		ftp.login(userName, passWord);
		int reply = ftp.getReplyCode();
		if(!FTPReply.isPositiveCompletion(reply)){
			throw new IOException("FTP服务器登陆失败");
		}
		//被动方式 或主动方式
		//ftp.enterLocalActiveMode();
		//ftp.enterLocalPassiveMode();
		return ftp;
	}
	
	public static boolean uploadFile(String url,int port,String userName,String passWord,
			String remotePath,String remoteFileName,InputStream inputStream,String encodeType){
		boolean success = false;
		FTPClient ftp = null;
		try{
			ftp = login(url, port, userName, passWord);
			remotePath = new String(remotePath.getBytes(encodeType),"iso-8859-1");
			remoteFileName = new String(remoteFileName.getBytes(encodeType),"iso-8859-1");
			ftp.setControlEncoding(encodeType);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory(remotePath);
			success = ftp.storeFile(remoteFileName, inputStream);
		} catch(IOException e){
			logger.error("FTP上传文件失败！",e);
		} finally{
			close(inputStream);
			close(ftp);
		}
		
		if(success){
			logger.info("FTP上传文件成功！");
		}else{
			logger.error("FTP上传文件失败");
		}
		return success;
	}
	
	public static boolean downloadFile(String url,int port,String userName,String passWord,
			String remotePath,String remoteFileName,String localPath,String encodeType){
		boolean success = false;
		FTPClient ftp = null;
		OutputStream os = null;
		try{
			ftp = login(url, port, userName, passWord);
			remotePath =  new String(remotePath.getBytes(encodeType),"iso-8859-1");
			ftp.setControlEncoding(encodeType);
			ftp.changeWorkingDirectory(remotePath);
			
			File localFile = new File(localPath+"/"+remoteFileName);
			os = new FileOutputStream(localFile);
			success = ftp.retrieveFile(new String(remoteFileName.getBytes(),"iso-8859-1"), os);
		} catch (IOException e){
			logger.error("FTP下载文件失败！");
		} finally{
			close(os);
			close(ftp);
		}
		if(success){
			logger.info("FTP下载文件成功！");
		}else{
			logger.error("FTP下载文件失败！");
		}
		return success;
	}
	
	public static void close(FTPClient ftp){
		if(ftp!=null){
			try{
				ftp.logout();
				if(ftp.isConnected())
					ftp.disconnect();
			} catch(IOException e){
				
			}
		}
	}
	
	public static void close(InputStream inputStream){
		if(inputStream!=null){
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void close(OutputStream outputStream){
		if(outputStream!=null){
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
