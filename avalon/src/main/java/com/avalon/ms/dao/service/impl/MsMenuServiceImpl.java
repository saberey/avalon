package com.avalon.ms.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avalon.ms.common.bean.MsMenuObject;
import com.avalon.ms.dao.entity.MsMenu;
import com.avalon.ms.dao.service.MsMenuService;
import com.google.common.base.Strings;

@Service
public class MsMenuServiceImpl implements MsMenuService {
	
	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public int insert(MsMenu record) {
		// TODO Auto-generated method stub
		return sqlsession.insert("com.avalon.ms.dao.mybatis.mapper.MsMenuMapper.insert", record);
	}

	@Override
	public List<MsMenuObject> selectAll() {
		// TODO Auto-generated method stub
		
		List<MsMenuObject> menuObjectList = new ArrayList<>();
		List<MsMenu> munuList = sqlsession.selectList("com.avalon.ms.dao.mybatis.mapper.MsMenuMapper.selectAll");
		for(MsMenu cur : munuList){
			if(Strings.isNullOrEmpty(cur.getFatherid())){
				MsMenuObject msMenuObject = new MsMenuObject();
				msMenuObject.setMsMenu(cur);
				menuObjectList.add(msMenuObject);
			}
		}
		System.out.println(menuObjectList.size());
		for(MsMenuObject curObject : menuObjectList){
			for(MsMenu cur : munuList){
				if((curObject.getMsMenu().getMenuid()).equals(cur.getFatherid())){
					curObject.getSubMenuList().add(cur);
				}
			}
		}
		return menuObjectList;
	}

	@Override
	public void updateMenu(MsMenu menu) {
		// TODO Auto-generated method stub
		sqlsession.update("com.avalon.ms.dao.mybatis.mapper.MsMenuMapper.updateMenu", menu);
	}

}
