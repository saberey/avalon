package com.avalon.ms.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年1月18日 下午12:36:40
 *@version
 */
public class FileUtil {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
		
	public static List readFile(String path){
		List list = new ArrayList();
		File file = new File(path);
		if(!file.exists()){
			logger.error("{} is not exist",path);
			return null;
		}
		if(!file.canRead()){
			logger.error("{} can't be read",path);
			return null;
		}
		BufferedReader bufferedReader = null;
		String lineContent;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			while((lineContent = bufferedReader.readLine())!=null){
				//System.out.println(lineContent);
				//System.out.println(new String(lineContent.getBytes("utf-8")));
				list.add(lineContent);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			close(bufferedReader);
		}
		return list;
	}
	
	public static void close(Reader reader){
		if(reader!=null){
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
