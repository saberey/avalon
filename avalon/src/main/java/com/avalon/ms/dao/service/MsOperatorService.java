package com.avalon.ms.dao.service;

import java.util.List;

import com.avalon.ms.dao.entity.MsOperator;

public interface MsOperatorService {

	public List<MsOperator> getAllUser(MsOperator msOperator);
	
	public void addUser(MsOperator msOperator);
	
	public MsOperator getUserInfo(MsOperator msOperator);
	
	public void deleteUser(int id);
	
	public int Update(MsOperator msOperator);
}
