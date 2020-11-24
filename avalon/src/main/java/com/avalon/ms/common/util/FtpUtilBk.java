package com.avalon.ms.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月25日 下午6:42:39
 *@version
 */
public class FtpUtilBk {

	private FTPClient ftp;
	private boolean is_connected;
	
	public FtpUtilBk(){
		ftp = new FTPClient();
		is_connected = false;
	}
	
	public FtpUtilBk(int defaultTimeoutSecond,int connectTimeoutSecond,int dataTimeoutSecond){
		ftp = new FTPClient();
		is_connected = false;
		ftp.setDefaultTimeout(defaultTimeoutSecond);
		ftp.setConnectTimeout(connectTimeoutSecond);
		ftp.setDataTimeout(dataTimeoutSecond);
	}
	
	public void connect(String host,int port,String user,String password,boolean isTextMode) throws IOException{
		
		try {
			ftp.connect(host, port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new IOException("Can't find FTP server '"+host+"'");
		} 
		
		int reply = ftp.getReplyCode();
		if(!FTPReply.isPositiveCompletion(reply)){
			disconnect();
			throw new IOException("Can't connect to server '"+host+"'");
		}
		
		if(!ftp.login(user, password)){
			is_connected = false;
			disconnect();
			throw new IOException("Can't login to server '"+host+"'");
		}else{
			is_connected = true;
		}
		
		if(isTextMode){
			ftp.setFileType(FTP.ASCII_FILE_TYPE);
		}else{
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
		}
		
	}
	
	public void upload(String ftpFileName,File localFile) throws IOException{
		if(!localFile.exists()){
			throw new IOException("Can't upload '"+localFile.getAbsolutePath()+"'.This file doesn't exist.");
		}
		InputStream in = null;
		try{
			ftp.enterLocalPassiveMode();
			
			in = new BufferedInputStream(new FileInputStream(localFile));
			if(!ftp.storeFile(ftpFileName, in)){
				throw new IOException("Can't upload '"+localFile.getAbsolutePath()+"' to ftp server. please check FTP permissions and path.");
			}
		} finally{
				in.close();
		}
	}
	
	public void download(String ftpFileName,File localFile) throws IOException{
		OutputStream out = null;
		
		try {
			ftp.enterLocalPassiveMode();
			
			FTPFile[] fileInfoArray = ftp.listFiles(ftpFileName);
			if(fileInfoArray==null){
				throw new FileNotFoundException("File "+ftpFileName+" was not found on FTP server.");
			}
			
			FTPFile fileInfo = fileInfoArray[0];
			long size = fileInfo.getSize();
			if(size>Integer.MAX_VALUE){
				throw new IOException("File "+ftpFileName+" is too large.");
			}
			out = new BufferedOutputStream(new FileOutputStream(localFile));
			if(!ftp.retrieveFile(ftpFileName, out)){
				throw new IOException("Error loading file "+ftpFileName+" from FTP server. Check FTP permissions and path.");
			}
			out.flush();
		}  finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	
	public void remove(String ftpFileName) throws IOException {
		        if (!ftp.deleteFile(ftpFileName)) {
		             throw new IOException("Can't remove file '" + ftpFileName + "' from FTP server.");
		         }
     }
	
	public List<String> list(String filePath) throws IOException {
		         List<String> fileList = new ArrayList<String>();
		         
		         // Use passive mode to pass firewalls.
		         ftp.enterLocalPassiveMode();
		         
		         FTPFile[] ftpFiles = ftp.listFiles(filePath);
		         int size = (ftpFiles == null) ? 0 : ftpFiles.length;
		         for (int i = 0; i < size; i++) {
		             FTPFile ftpFile = ftpFiles[i];
		             if (ftpFile.isFile()) {
		                 fileList.add(ftpFile.getName());
		             }
		         }
		         
		         return fileList;
    }
	
	public void sendSiteCommand(String args) throws IOException {
		         if (ftp.isConnected()) {
		             try {
		                 ftp.sendSiteCommand(args);
		             } catch (IOException ex) {
		             }
		         }
	}

	
	public void disconnect() throws IOException{
		if(ftp.isConnected()){
			ftp.logout();
			ftp.disconnect();
			is_connected = false;
		}
	}
	
	
	 public String makeFTPFileName(String ftpPath, File localFile) {                                                                                   
	      if (ftpPath == "") {                                                                                                                         
	          return localFile.getName();                                                                                                              
	      } else {                                                                                                                                     
	          String path = ftpPath.trim();                                                                                                            
	          if (path.charAt(path.length() - 1) != '/') {                                                                                             
	              path = path + "/";                                                                                                                   
	          }                                                                                                                                        
	                                                                                                                                                   
	          return path + localFile.getName();                                                                                                       
	      }                                                                                                                                            
	  }                                                                                                                                                
	                                                                                                                                                   
	  /**                                                                                                                                              
	   * Test coonection to ftp server                                                                                                                 
	   *                                                                                                                                               
	   * @return true, if connected                                                                                                                    
	   */                                                                                                                                              
	  public boolean isConnected() {                                                                                                                   
	      return is_connected;                                                                                                                         
	  }                                                                                                                                                
	                                                                                                                                                   
	  /**                                                                                                                                              
	   * Get current directory on ftp server                                                                                                           
	   *                                                                                                                                               
	   * @return current directory                                                                                                                     
	   */                                                                                                                                              
	  public String getWorkingDirectory() {                                                                                                            
	      if (!is_connected) {                                                                                                                         
	          return "";                                                                                                                               
	      }                                                                                                                                            
	                                                                                                                                                   
	      try {                                                                                                                                        
	          return ftp.printWorkingDirectory();                                                                                                      
	      } catch (IOException e) {                                                                                                                    
	      }                                                                                                                                            
	                                                                                                                                                   
	      return "";                                                                                                                                   
	  }                                                                                                                                                
	                                                                                                                                                   
	  /**                                                                                                                                              
	   * Set working directory on ftp server                                                                                                           
	   *                                                                                                                                               
	   * @param dir                                                                                                                                    
	   *            new working directory                                                                                                              
	   * @return true, if working directory changed                                                                                                    
	   */                                                                                                                                              
	  public boolean setWorkingDirectory(String dir) {                                                                                                 
	      if (!is_connected) {                                                                                                                         
	          return false;                                                                                                                            
	      }                                                                                                                                            
	                                                                                                                                                   
	      try {                                                                                                                                        
	          return ftp.changeWorkingDirectory(dir);                                                                                                  
	      } catch (IOException e) {                                                                                                                    
	      }                                                                                                                                            
	                                                                                                                                                   
	      return false;                                                                                                                                
	  }                                                                                                                                                
	                                                                                                                                                   
	  /**                                                                                                                                              
	   * Change working directory on ftp server to parent directory                                                                                    
	   *                                                                                                                                               
	   * @return true, if working directory changed                                                                                                    
	   */                                                                                                                                              
	  public boolean setParentDirectory() {                                                                                                            
	      if (!is_connected) {                                                                                                                         
	          return false;                                                                                                                            
	      }                                                                                                                                            
	                                                                                                                                                   
	      try {                                                                                                                                        
	          return ftp.changeToParentDirectory();                                                                                                    
	      } catch (IOException e) {                                                                                                                    
	      }                                                                                                                                            
	                                                                                                                                                   
	      return false;                                                                                                                                
	  }                                                                                                                                                
	                                                                                                                                                   
	  /**                                                                                                                                              
	   * Get parent directory name on ftp server                                                                                                       
	   *                                                                                                                                               
	   * @return parent directory                                                                                                                      
	   */                                                                                                                                              
	  public String getParentDirectory() {                                                                                                             
	      if (!is_connected) {                                                                                                                         
	          return "";                                                                                                                               
	      }                                                                                                                                            
	                                                                                                                                                   
	      String w = getWorkingDirectory();                                                                                                            
	      setParentDirectory();                                                                                                                        
	      String p = getWorkingDirectory();                                                                                                            
	      setWorkingDirectory(w);                                                                                                                      
	                                                                                                                                                   
	      return p;                                                                                                                                    
	  }                                                                                                                                                
	                                                                                                                                                   
	  /**                                                                                                                                              
	   * Get directory contents on ftp server                                                                                                          
	   *                                                                                                                                               
	   * @param filePath                                                                                                                               
	   *            directory                                                                                                                          
	   * @return list of FTPFileInfo structures                                                                                                        
	   * @throws IOException                                                                                                                           
	   */                                                                                                                                              
	  public List<FfpFileInfo> listFiles(String filePath) throws IOException {                                                                         
	      List<FfpFileInfo> fileList = new ArrayList<FfpFileInfo>();                                                                                   
	                                                                                                                                                   
	      // Use passive mode to pass firewalls.                                                                                                       
	      ftp.enterLocalPassiveMode();                                                                                                                 
	      FTPFile[] ftpFiles = ftp.listFiles(filePath);                                                                                                
	      int size = (ftpFiles == null) ? 0 : ftpFiles.length;                                                                                         
	      for (int i = 0; i < size; i++) {                                                                                                             
	          FTPFile ftpFile = ftpFiles[i];                                                                                                           
	          FfpFileInfo fi = new FfpFileInfo();                                                                                                      
	          fi.setName(ftpFile.getName());                                                                                                           
	          fi.setSize(ftpFile.getSize());                                                                                                           
	          fi.setTimestamp(ftpFile.getTimestamp());                                                                                                 
	          fi.setType(ftpFile.isDirectory());                                                                                                       
	          fileList.add(fi);                                                                                                                        
	      }                                                                                                                                            
	                                                                                                                                                   
	      return fileList;                                                                                                                             
	  }                                                                                                                                                
	  
	  class FfpFileInfo{
		   
		   private  String name;
		   private long size;
		   private boolean type;
		   private Calendar timestamp;
		   
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public long getSize() {
				return size;
			}
			public void setSize(long size) {
				this.size = size;
			}
			public boolean isType() {
				return type;
			}
			public void setType(boolean type) {
				this.type = type;
			}
			public Calendar getTimestamp() {
				return timestamp;
			}
			public void setTimestamp(Calendar timestamp) {
				this.timestamp = timestamp;
			}
	  }
	  
	  /**                                                                                                                                              
	   * Get file from ftp server into given output stream                                                                                             
	   *                                                                                                                                               
	   * @param ftpFileName                                                                                                                            
	   *            file name on ftp server                                                                                                            
	   * @param out                                                                                                                                    
	   *            OutputStream                                                                                                                       
	   * @throws IOException                                                                                                                           
	   */                                                                                                                                              
	  public void getFile(String ftpFileName, OutputStream out) throws IOException {                                                                   
	      try {                                                                                                                                        
	          // Use passive mode to pass firewalls.                                                                                                   
	          ftp.enterLocalPassiveMode();                                                                                                             
	                                                                                                                                                   
	          // Get file info.                                                                                                                        
	          FTPFile[] fileInfoArray = ftp.listFiles(ftpFileName);                                                                                    
	          if (fileInfoArray == null) {                                                                                                             
	              throw new FileNotFoundException("File '" + ftpFileName + "' was not found on FTP server.");                                          
	          }                                                                                                                                        
	                                                                                                                                                   
	          // Check file size.                                                                                                                      
	          FTPFile fileInfo = fileInfoArray[0];                                                                                                     
	          long size = fileInfo.getSize();                                                                                                          
	          if (size > Integer.MAX_VALUE) {                                                                                                          
	              throw new IOException("File '" + ftpFileName + "' is too large.");                                                                   
	          }                                                                                                                                        
	                                                                                                                                                   
	          // Download file.                                                                                                                        
	          if (!ftp.retrieveFile(ftpFileName, out)) {                                                                                               
	              throw new IOException("Error loading file '" + ftpFileName + "' from FTP server. Check FTP permissions and path.");                  
	          }                                                                                                                                        
	                                                                                                                                                   
	          out.flush();                                                                                                                             
	                                                                                                                                                   
	      } finally {                                                                                                                                  
	          if (out != null) {                                                                                                                       
	              try {                                                                                                                                
	                  out.close();                                                                                                                     
	              } catch (IOException ex) {                                                                                                           
	              }                                                                                                                                    
	          }                                                                                                                                        
	      }                                                                                                                                            
	  }                                                                                                                                                
	                                                                                                                                                   
	  /**                                                                                                                                              
	   * Put file on ftp server from given input stream                                                                                                
	   *                                                                                                                                               
	   * @param ftpFileName                                                                                                                            
	   *            file name on ftp server                                                                                                            
	   * @param in                                                                                                                                     
	   *            InputStream                                                                                                                        
	   * @throws IOException                                                                                                                           
	   */                                                                                                                                              
	  public void putFile(String ftpFileName, InputStream in) throws IOException {                                                                     
	      try {                                                                                                                                        
	          // Use passive mode to pass firewalls.                                                                                                   
	          ftp.enterLocalPassiveMode();                                                                                                             
	                                                                                                                                                   
	          if (!ftp.storeFile(ftpFileName, in)) {                                                                                                   
	              throw new IOException("Can't upload file '" + ftpFileName + "' to FTP server. Check FTP permissions and path.");                     
	          }                                                                                                                                        
	      } finally {                                                                                                                                  
	          try {                                                                                                                                    
	              in.close();                                                                                                                          
	          } catch (IOException ex) {                                                                                                               
	          }                                                                                                                                        
	      }                                                                                                                                            
	  }                                                                                                                                                
}
