package com.avalon.ms.dubbo.demoService;

import com.alibaba.fastjson.JSON;
import com.avalon.ms.dao.entity.Student;
import com.avalon.ms.dao.mybatis.enums.SexEnums;
import com.avalon.ms.dubbo.demo.DemoService;

/**
 *@description:TODO
 *@author saber
 *@date 2017年12月4日 下午4:23:09
 *@version
 */
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		Student student = new Student();
		student.setName(name);
		student.setId(1);
		student.setSex(SexEnums.MALE);
		student.setStudentNo("1");
		return JSON.toJSONString(student);
	}

}
