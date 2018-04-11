package com.avalon.ms.datasource.database.mysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.avalon.ms.common.constant.DataBaseCommonProperties;
import com.avalon.ms.datasource.database.AbstactDataBase;

public class MysqlDataBase  extends AbstactDataBase{
	
	public MysqlDataBase(String ip, Integer port, String dataBaseName,
			String userName, String password, Integer linkPoolSize) throws SQLException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		super(ip, port, dataBaseName, userName, password, linkPoolSize);
	}
	
	@Override
	public String getJdbcDriver() {
		return DataBaseCommonProperties.mysqldriver_6;
	}
	
	@Override
	public String getTestSQL() {
		// TODO Auto-generated method stub
		return "select 1 from dual";
	}
	@Override
	public String getDbUrl() {
		// TODO Auto-generated method stub
		return "jdbc:mysql://"+this.getIp()+":"+this.getPort()+"/"+this.getDataBaseName()+"?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&connectTimeout=660000&socketTimeout=660000";
	}
	public static void main(String[] args) {
		try {
			AbstactDataBase mysqlDataBase=new MysqlDataBase("127.0.0.1", 3306, "tiger", "root", "tiger86", 3);
			List<Map> list=mysqlDataBase.query("select city,province from city ", null);
//			Long count=mysqlDataBase.queryCount("select count(*) from sys_user where user_id=?", new ArrayList<Object>(Arrays.asList(20)));
//			Integer uc=mysqlDataBase.executeUpdate("update sys_user set role_id=? where user_id=?", new ArrayList<Object>(Arrays.asList(99,1)));
			//Integer ui=mysqlDataBase.executeUpdate(" insert sys_user (login_name,user_name,email) values(?,?,?)", new ArrayList<Object>(Arrays.asList("aa","bb","cc")));
			System.out.println(list);
//			System.out.println(count);
//			System.out.println(uc);
			//System.out.println(ui);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
