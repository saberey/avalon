package com.avalon.ms.common.bean;

import lombok.Data;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月10日 上午10:14:50
 *@version
 */
@Data
public class MailContact {

	private String sender;
	private String senderPwd;
	private String senderAlias;
	private int senderType;
	private String to;
	private String copyTo;
}
