package com.avalon.ms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.avalon.ms.common.util.JsonUtil;
import com.avalon.ms.dao.entity.City;
import com.avalon.ms.dao.mybatis.enums.CityAliasEnums;
import com.avalon.ms.dao.service.CityService;

/**
 * 
 * @description:TODO
 * @author: saber
 * @time: 2017年9月18日 下午6:30:07
 * @version
 */
@Controller
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	private Logger logger = LoggerFactory.getLogger(CityController.class);
	
	@RequestMapping("/query")
	@ResponseBody
	public Object query(@RequestParam("code") String str){
		System.out.println(str);
		City city = cityService.selectByCode(str);
		return JSON.toJSON(city);
	}
	
	
	@RequestMapping("/queryById")
	public void queryById(@RequestParam("id") int id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		City city = cityService.selectById(id);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");//设置页面返回编码，直接通过浏览器调用接口时返回中文乱码（此种方式未生效）
		response.setContentType("application/json;charset=utf-8");//直接通过浏览器调用接口时返回中文正常
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(city));
		//return 
	}
	
	@RequestMapping("/insertSelect")
	public void insertSelect(HttpServletRequest request,HttpServletResponse response){
		int id = Integer.valueOf(request.getParameter("id"));
		String city = request.getParameter("city");
		String code = request.getParameter("code");
		String province = request.getParameter("province");
		City newC = new City();
		newC.setCity(city);
		newC.setId(id);
		newC.setCode2(findEnum(code));
		newC.setProvince(province);
		cityService.insertSelect(newC);
	}
	public CityAliasEnums findEnum(String code){
		return CityAliasEnums.getCityAlias(code);
	}
	
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public Object queryForAll(@RequestParam(value="formdata",required=false) String formData){
	//	System.out.println("1"+city);
		System.out.println("formData:"+formData);
		City city = JsonUtil.json2bean(formData, City.class);
		System.out.println("jsontobean"+city);
		//City city = null;
		List<City> list = new ArrayList<>();
		City cityb = null;
		list = cityService.selectByAll(null);
		//}else{
		//	cityb = cityService.selectByCode(city.getCode());
	//	}list.size() == 0 ? JSON.toJSON(list) : JSON.toJSON(cityb);
		Object rs = JSON.toJSON(list);
		System.out.println("rs"+rs);
		return rs;
	}
	
	@RequestMapping("/code")
	@ResponseBody
	public Object code(@RequestParam(value="city") String city){
		try {
			logger.info(Thread.currentThread().getName()+"in");
			logger.info(Thread.currentThread().getName()+"city:"+city);
			//TimeUnit.MINUTES.sleep(60);
			if(city.equals("4")){
				TimeUnit.MINUTES.sleep(60);
			}else{
				TimeUnit.SECONDS.sleep(5);
			}
			logger.info(Thread.currentThread().getName()+"out");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON.toJSON("{city:"+city+"}");
	}
	
	@RequestMapping("/path")
	public ModelAndView pathTest(){
		return new ModelAndView("redirect:../../Path/path.txt");
	}
}
