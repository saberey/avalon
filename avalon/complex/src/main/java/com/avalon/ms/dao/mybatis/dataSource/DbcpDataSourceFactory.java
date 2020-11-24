package com.avalon.ms.dao.mybatis.dataSource;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;

/**
 *@descriptionMybatis自定义dataSource
 *@author saber
 *@date 2017年10月19日 上午11:10:24
 *@version
 */
public class DbcpDataSourceFactory  implements DataSourceFactory {
	
	private Properties props = null;
	
	@Override
	public void setProperties(Properties props) {
		// TODO Auto-generated method stub
		this.props = props;
	}

	@Override
	public DataSource getDataSource() {
		// TODO Auto-generated method stub
		//创建DataSource 需自行实现
		//create dataSource;
		return null;
	}
}
