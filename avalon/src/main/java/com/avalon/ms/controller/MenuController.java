package com.avalon.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.avalon.ms.common.bean.MsMenuObject;
import com.avalon.ms.dao.service.MsMenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MsMenuService msMenuService;
	
	@RequestMapping("/init")
	@ResponseBody
	public Object initMenu(){
		
		List<MsMenuObject> menuList = msMenuService.selectAll();
		System.out.println(menuList);
		return JSON.toJSON(menuList);
	}
	
	@RequestMapping("/menu")
	public String menu(){
		return "/paper/menu/menumanage";
	}
	
	@RequestMapping("/user")
	public String user(){
		return "/paper/usermg/usermanage";
	}
	
	@RequestMapping("/role")
	public String role(){
		return "/paper/rolemg/rolemanage";
	}
}
