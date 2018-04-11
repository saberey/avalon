package com.avalon.ms.common.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.avalon.ms.dao.entity.MsMenu;

@Data
public class MsMenuObject {

	private MsMenu msMenu;
	
	private List<MsMenu> subMenuList = new ArrayList<>();
}
