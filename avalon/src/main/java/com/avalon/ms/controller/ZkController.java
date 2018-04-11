package com.avalon.ms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.avalon.ms.service.ZkService;

/**
 *@description:TODO
 *@author saber
 *@date 2018年3月14日 下午2:09:10
 *@version
 */
@Controller
@RequestMapping("/zk")
public class ZkController {
	
	@Autowired
	private ZkService zkService;
	
	@RequestMapping("/list")
	@ResponseBody
	public String list(@RequestParam("path") String path,HttpServletRequest request,HttpServletResponse response){
		List<String> children = zkService.getChildren(path);
		String jsonStr = JSON.toJSONString(children);
		return jsonStr;
	}
}
