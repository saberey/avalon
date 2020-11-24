package com.avalon.ms.common.mail.enums;
/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月10日 下午1:39:04
 *@version
 */
public enum EmailSenderTypeEnums {

	NETEASE(1),QQ(2),GMAIL(3);
	
	private EmailSenderTypeEnums(int type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public static EmailSenderTypeEnums getEmailSenderTypeEnums(int type){
		switch (type) {
		case 1:
			return NETEASE;
		case 2:
			return QQ;
		case 3:
			return GMAIL;
		default:
			return null;
		}
	}
}
