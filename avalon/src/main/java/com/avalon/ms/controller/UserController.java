package com.avalon.ms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.avalon.ms.common.support.MySqlTableName;
import com.avalon.ms.dao.entity.MsOperator;
import com.avalon.ms.dao.service.CommonSqlService;
import com.avalon.ms.dao.service.MsOperatorService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private MsOperatorService msOperatorService;
	
	@Autowired
	private CommonSqlService commonSqlService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/onquery")
	public void query(@ModelAttribute("user") MsOperator msOperator,HttpServletRequest request
			,HttpServletResponse response){
		int pageNo = Integer.valueOf(request.getParameter("curPageNo"));
		System.out.println("pageno:"+pageNo);
		int pageSize = 10;
		pageNo = (pageNo-1)*pageSize;
		msOperator.setCurPageNo(pageNo);
		msOperator.setPageSize(pageSize);
		List<MsOperator> userList = msOperatorService.getAllUser(msOperator);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSON.toJSONString(userList));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/delete")
	public void  delete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		PrintWriter out = null;
		try{
			msOperatorService.deleteUser(Integer.parseInt(id));
			logger.info("User controller delete id : {} success",id);
			out = response.getWriter();
			out.write(JSON.toJSONString("success"));
		}catch(Exception e){
			e.printStackTrace();
			logger.info("User controller delete id : {} error",id);
		}
	}	
	
	@RequestMapping("/total")
	public void getTotal(HttpServletRequest request
			,HttpServletResponse response){
		int totalCount = commonSqlService.getTotalCount(MySqlTableName.MSOPERATOR);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(JSON.toJSONString(totalCount));
	}
	
	@RequestMapping("/query")
	public void queryUser(@ModelAttribute("user") MsOperator msOperator,HttpServletRequest request
			,HttpServletResponse response){
		System.out.println("query:"+msOperator);
		List<MsOperator> userList = new ArrayList<MsOperator>();
		MsOperator msoper= msOperatorService.getUserInfo(msOperator);
		userList.add(msoper);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSON.toJSONString(userList));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/queryById")
	public void queryById(@RequestParam("id") String id,HttpServletRequest request
			,HttpServletResponse response){
		MsOperator msOperator = new MsOperator();
		msOperator.setId(Integer.parseInt(id));
		msOperator = msOperatorService.getUserInfo(msOperator);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(JSON.toJSONString(msOperator));
	}
	
	@RequestMapping("/addUser")
	public void addUser(@ModelAttribute("user") MsOperator msOperator,HttpServletRequest request
			,HttpServletResponse response){
		System.out.println(msOperator);
		msOperatorService.addUser(msOperator);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = "{\"success\":true}";
		System.out.println(result);
		writer.write(result);
	}
	
	@RequestMapping("/editUser")
	public void editUser(@ModelAttribute("user") MsOperator msOperator,HttpServletRequest request
			,HttpServletResponse response){
		int result = msOperatorService.Update(msOperator);
		String resultStr = result >0 ? "success" :"error";
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resultStr);
		writer.write(JSON.toJSONString(resultStr));
	}
}
