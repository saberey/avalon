package com.avalon.ms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/ipswap")
public class IpSwapController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private int times = Runtime.getRuntime().availableProcessors();
	private int times2 = Runtime.getRuntime().availableProcessors();
	private int times3= Runtime.getRuntime().availableProcessors();
	private boolean changeFlag = false;
	private boolean changeFlag2 = false;
	private boolean changeFlag3 = false;
	
	@RequestMapping("/query")
	public void query(HttpServletResponse response){
		logger.info("receive query req time {}",System.nanoTime());
		String result = "query resp time "+System.nanoTime();
		PrintWriter writer= null;
		try {
			if(!changeFlag){
				times--;
				if(times<0){
					times = Runtime.getRuntime().availableProcessors();
					changeFlag = true;
					logger.info("change result:");
				}
				response.setStatus(HttpStatus.BAD_GATEWAY.value(),"error");
			}else{
				times--;
				if(times<0){
					times = Runtime.getRuntime().availableProcessors();
					changeFlag = false;
					logger.info("change result:");
				}
			}
			
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write(JSON.toJSONString(result));
	}
	
	@RequestMapping("/reg")
	public void reg(HttpServletResponse response){
		logger.info("receive reg req time {}",System.nanoTime());
		String result = "reg resp time "+System.nanoTime();
		PrintWriter writer= null;
		try {
			if(!changeFlag2){
				times2--;
				if(times2<0){
					times2 = Runtime.getRuntime().availableProcessors();
					changeFlag2 = true;
					logger.info("change result:");
				}
				response.setStatus(HttpStatus.BAD_GATEWAY.value(),"error");
			}else{
				times2--;
				if(times2<0){
					times2 = Runtime.getRuntime().availableProcessors();
					changeFlag2 = false;
					logger.info("change result:");
				}
			}
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write(JSON.toJSONString(result));
	}
	
	@RequestMapping("/transfer")
	public void transfer(HttpServletResponse response){
		logger.info("receive query req time {}",System.nanoTime());
		String result = "transfer resp time "+System.nanoTime();
		PrintWriter writer= null;
		try {
			if(!changeFlag3){
				times3--;
				if(times3<0){
					times3 = Runtime.getRuntime().availableProcessors();
					changeFlag3 = true;
					logger.info("change result:");
				}
				response.setStatus(HttpStatus.BAD_GATEWAY.value(),"error");
			}else{
				times3--;
				if(times3<0){
					times3 = Runtime.getRuntime().availableProcessors();
					changeFlag3 = false;
					logger.info("change result:");
				}
			}
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write(JSON.toJSONString(result));
	}
}
