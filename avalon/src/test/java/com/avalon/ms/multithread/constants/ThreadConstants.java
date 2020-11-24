package com.avalon.ms.multithread.constants;
/**
 *@descriptionTODO
 *@author saber
 *@date 2017年12月21日 下午3:43:49
 *@version
 */
public enum ThreadConstants {

	SLEEP_GRANULARITY(10000);
	
	public int value;
	private ThreadConstants(int value){
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
