package com.avalon.ms.common.bean;

import lombok.Data;

/**
 *@descriptionTODO
 *@author saber
 *@date 2017年11月10日 上午10:18:52
 *@version
 */
@Data
public class MailInfo {

	private String title;
	private String subject;
	private String content;
	private String boot;
	private int attach;
}
