package com.avalon.ms.dao.service;

import java.util.List;

import com.avalon.ms.common.bean.MsMenuObject;
import com.avalon.ms.dao.entity.MsMenu;

public interface MsMenuService {

	int insert(MsMenu record);

	List<MsMenuObject> selectAll();

	void updateMenu(MsMenu menu);
}
