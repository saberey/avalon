package com.avalon.ms.agent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 *@description:TODO
 *@author saber
 *@date 2018年2月6日 下午1:11:45
 *@version
 */
public class CustomAgent implements ClassFileTransformer {
	
	public static void premain(String agentArgs,Instrumentation inst){
		inst.addTransformer(new CustomAgent());
	}
	
	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		// TODO Auto-generated method stub
		if(!className.startsWith("java")&&!className.startsWith("sun")){
			int lastIndexOf = className.lastIndexOf("/")+1;
			String filename = className.substring(lastIndexOf)+".class";
			String dir = "E:/javaworkspace/bytecode/exported/";
			exportClass(dir,filename,classfileBuffer);
			System.out.println(className+" Exported Success!");
		}
		return classfileBuffer;
	}
	
	public void exportClass(String dir,String fileName,byte[] classfileBuffer){
		try {
			File file = new File(dir+fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(classfileBuffer);
			fos.close();
		} catch (IOException e) {
			System.err.println("exception occured while doing some operation!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
