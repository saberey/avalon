package com.avalon.ms.dao.entity;

import lombok.Data;

/**
 *@description:TODO
 *@author saber
 *@date 2017年10月31日 下午6:43:18
 *@version
 */
@Data
public class PageParams {

	private Integer page;
	private Integer pageSize;
	private Boolean useFlag;
	private Boolean checkFlag;
	private Integer total;
	private Integer totalPage;
}
