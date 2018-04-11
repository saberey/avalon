package com.avalon.ms.datasource.database.pool;

import java.sql.Connection;
import java.sql.SQLException;

import com.avalon.ms.datasource.database.DataSourceInterface;

public interface DataBasePoolInterface extends DataSourceInterface {

	public void initPool() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException ;
	public Connection getConnection() throws SQLException;
	public boolean releaseConnection(Connection conn) throws SQLException;
	public String getDbUrl();
	public String getJdbcDriver();
	public String getDataBaseName();
	public void setDataBaseName(String dataBaseName);
	public Integer getLinkPoolSize();
	public void setLinkPoolSize(Integer linkPoolSize);
	public String getUserName();
	public void setUserName(String userName);
	public String getPassword();
	public void setPassword(String password);
}
