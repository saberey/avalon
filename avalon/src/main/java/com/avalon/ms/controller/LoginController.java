package com.avalon.ms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.avalon.ms.dao.entity.MsOperator;
import com.avalon.ms.dao.service.MsOperatorService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private  MsOperatorService msOperatorService;
	
	@RequestMapping("/signin")
	@ResponseBody
	public void singup(HttpServletRequest request,HttpServletResponse response){
		logger.info("request base uri {}, second uri {}", "/login","/signin");
		String username = request.getParameter("signupusername");
		String password = request.getParameter("signuppassword");
		
		MsOperator msOperator = new MsOperator();
		msOperator.setOperatorname(username);
		msOperator.setOperatorpwd(password);
		System.out.println(JSON.toJSONString(msOperator));	
		msOperator = msOperatorService.getUserInfo(msOperator);
		
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter pwWriter = response.getWriter();
			String resultMsg ;
			if(null==msOperator){
				resultMsg = "error";
				pwWriter.write(JSON.toJSONString(resultMsg));
				logger.debug("1");
			}else{
				pwWriter.write(JSON.toJSONString(msOperator));
				logger.debug("2");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
