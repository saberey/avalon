package com.avalon.ms.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@description:TODO
 *@author saber
 *@date 2018年4月17日 上午10:04:20
 *@version
 */
public class RandomAccessFileTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RandomAccessFileTest.class);
	private String file ;
	
    public RandomAccessFileTest(){
    }
    
    
    
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}



	public enum PointEnum{
		BEGIN("begin"),
		MIDDLE("middle"),
		RANDOM("random"),
		END("end");
		
		String value;
		private PointEnum(String value){
			this.value = value;
		}
		public String getValue(){
			return value;
		}
	}
	
	public enum AccessPolicy{
		RANCOMACCESSPOLICY("randomAccess"),
		FILEINPUTSTREAMPOLICY("fileInputStream");
		String value;
		private AccessPolicy(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	public void appendContent(PointEnum pointEnum,AccessPolicy accessPolicy,String content) {
		try {
				Method method = RandomAccessFileTest.class.getMethod(accessPolicy.getValue(), new Class[]{PointEnum.class,String.class});
				System.out.println(method.getName());
				method.invoke(this, pointEnum,content);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void randomAccess(PointEnum pointEnum,String content) throws Exception{
		logger.info("random access! {} {}",pointEnum.getValue(),content);
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			long point;
			switch(pointEnum.getValue()){
			case "end" : 
				    point = raf.length();
				    addContent(raf,point,content);
					break;
			case "begin" :
					point = 0;
					insert(raf, point, content);
				    break;
			case "middle" :
					point = raf.length()/2;
					insert(raf, point, content);
					break;
			case "random":
					point = new Random(raf.length()).nextInt();
					insert(raf, point, content);
					break;
			default :
				break;
			}
			
			logger.info("{} append content {}",pointEnum.getValue(),content);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 指定位置插入内容
	 * @description:TODO
	 * @param raf
	 * @param point
	 * @param content
	 * @throws Exception
	 * void
	 * @exception:
	 * @author:saber
	 * @time:2018年4月17日下午3:07:59
	 */
	public void insert(RandomAccessFile raf,long point,String content) throws Exception{
		//会把原始文件的内容覆盖，所以需要把原始文件内容保存到临时文件中，写完插入内容，再把原始文件的内容写回
	    File tmp = File.createTempFile("tmp", null);
	    FileOutputStream fos = new FileOutputStream(tmp);
	    FileInputStream fis = new FileInputStream(tmp);
	    exportToTmp(file,tmp,raf,point,fos);
	    addContent(raf,point,content);
		importFromTmp(tmp,raf,fis);
	}
	
	/**
	 * 从临时文件中读取内从写入目标文件中
	 * @description:TODO
	 * @param tmp
	 * @param raf
	 * @param fis
	 * void
	 * @exception:
	 * @author:saber
	 * @throws IOException 
	 * @time:2018年4月17日下午2:50:48
	 */
	private void importFromTmp(File tmp, RandomAccessFile raf,
			FileInputStream fis) throws IOException {
		// TODO Auto-generated method stub
		byte[] buffer = new byte[1024];
		int reads;
		while((reads=fis.read(buffer))!=-1){
			raf.write(buffer, 0, reads);
		}
		fis.close();
		logger.info("导入原始文件数据结束！");
	}
	
	/**
	 * point的内容保存到临时文件中
	 * @description:TODO
	 * @param file2
	 * @param tmp
	 * @param raf
	 * @param point
	 * @param fos
	 * void
	 * @exception:
	 * @author:saber
	 * @throws IOException 
	 * @time:2018年4月17日下午2:50:15
	 */
	private void exportToTmp(String file2, File tmp, RandomAccessFile raf,
			long point,FileOutputStream fos) throws IOException {
		// TODO Auto-generated method stub
		
		raf.seek(point);
		byte[] buffer = new byte[1024];
		int reads;
		while((reads=raf.read(buffer))!=-1){
			fos.write(buffer,0,reads);
		}
		fos.close();
		logger.info("导出文件内容到临时文件结束！");
	}

	public void addContent(RandomAccessFile raf,long point,String content) throws Exception{
		raf.seek(point);
		raf.write(content.getBytes());
		logger.info("插入内容{}结束",content);
	}
	
	public void fileInputStream(PointEnum pointEnum,String content){
		logger.info("fileInputStream! {} {}",pointEnum.getValue(),content);
	}
	
	public static void main(String[] args) throws Exception {
		
		RandomAccessFileTest raft = new RandomAccessFileTest();
		raft.setFile("E:\\duizhang\\xwFile\\20180415\\20180415_6000003151_COMMISSION.txt");
		raft.appendContent(PointEnum.RANDOM, AccessPolicy.RANCOMACCESSPOLICY, "hello world!");
	}
}
