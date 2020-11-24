package com.avalon.ms.common.runtime;

import java.io.IOException;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年2月12日 下午5:05:35
 *@version
 */
public class ExterM {

	public static void main(String[] args) {
		
		try {
			Runtime.getRuntime().exec("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
