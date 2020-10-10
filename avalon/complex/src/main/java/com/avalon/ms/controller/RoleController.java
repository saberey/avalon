package com.avalon.ms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avalon.ms.dao.entity.MsOperator;
import com.avalon.ms.dao.entity.MsRole;

@Controller
@RequestMapping("/role")
public class RoleController {

	@RequestMapping("/onquery")
	public void onquery(@ModelAttribute("role") MsRole msRole,HttpServletRequest request
			,HttpServletResponse response){
		
	}
}
