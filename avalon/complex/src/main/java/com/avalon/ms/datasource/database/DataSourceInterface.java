package com.avalon.ms.datasource.database;

public interface DataSourceInterface {
	/**
	 * ip地址或者域名
	 * @return
	 */
	public String getIp();
	/**
	 * ip地址或者域名
	 */
	public void setIp(String ip);
	/**
	 * 端口号
	 * @return
	 */
	public Integer getPort();
	/**
	 * 端口号
	 */
	public void setPort(Integer port);
	
	/**
	 * 数据库的驱动类
	 */
	public String getJdbcDriver();
	/**
	 * 
	 * @return
	 */
	public String getDbUrl();
}
