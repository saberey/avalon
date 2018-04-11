package com.avalon.ms.common.util;

import java.io.File;

/**
 *@description:TODO
 *@author saber
 *@date 2017年12月14日 下午5:12:13
 *@version
 */
public class VirtualMachineShow {

	private static Class<?> findVirtualMachineClass(){
	    String virtualMachineClassName = "com.sun.tools.attach.VirtualMachine";
		try {
			return Class.forName(virtualMachineClassName);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			File file = new File(System.getProperty("java.home"));
			if("jre".equalsIgnoreCase(file.getName())){
				file = file.getParentFile();
			}
			
			String[] defaultToolLocation = {"lib","tools.jar"};
			
			for(String name : defaultToolLocation){
				
			}
		}
		return null;
	}
}
