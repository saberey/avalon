package com.avalon.ms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sign")
public class SignController {
	
	@RequestMapping("/signup")
	@ResponseBody
	public String signup(HttpServletRequest request,HttpServletResponse response){
		String signupusername = request.getParameter("signupusername");
		String signuppassword = request.getParameter("signuppassword");
		String signupemail = request.getParameter("signupemail");
		
		String text = "{ signupusername : "+signupusername+",signuppassword : "+signuppassword+",signupemail : "+signupemail+" }";
		String rs =  JSON.toJSONString(text);
		System.out.println("rs:"+rs);
		return rs;
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		String text = "{ text : success }";
		String rs =  JSON.toJSONString(text);
		return rs;
	}
}
