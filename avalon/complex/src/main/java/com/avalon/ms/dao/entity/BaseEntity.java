package com.avalon.ms.dao.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BaseEntity {

	private int logicalDelete;
	private Date insertedTime;
	private int curPageNo;
	private int pageSize;
	private int totalCount;
	private List listObject;
	
}
