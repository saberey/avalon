package com.avalon.ms.datasource.database;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avalon.ms.common.constant.DataBaseCommonProperties;
import com.avalon.ms.common.util.DBParamsUtil;
import com.avalon.ms.common.util.MapBeanUtil;
import com.avalon.ms.datasource.database.pool.AbstactDataBasePool;

public abstract class AbstactDataBase extends AbstactDataBasePool implements
		DataBaseInterface {

	private Logger logger = LoggerFactory.getLogger(getClass());
	public AbstactDataBase(String ip, Integer port, String dataBaseName,
			String userName, String password, Integer linkPoolSize) throws SQLException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		super(ip, port, dataBaseName, userName, password, linkPoolSize);
	}
	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private <T>List<T> resultSetToListMap(ResultSet rs,Class<T> clazz) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException{
		List<T> list = new ArrayList<T>();
		ResultSetMetaData resultMetaData = rs.getMetaData();
		int cols = resultMetaData.getColumnCount(); // get the count of all the
													// coulums ,this will be 5
		while (rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int j = 1; j <= cols; j++) {
				map.put(resultMetaData.getColumnName(j),
						rs.getObject(resultMetaData.getColumnName(j)));
			}
			if(clazz!=Map.class){
				list.add(MapBeanUtil.mapToObject(map, clazz));
			}else{
				list.add((T)map);
			}
		}
		return list;
	}
	/**
	 */
	@Override
	public <T> List<T> query(String sql, Class<T> clazz,List<Object> param) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		Connection conn=this.getConnection();
		logger.info("query sql:{}",sql);
		logger.info("query param:{}",param);
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setQueryTimeout(DataBaseCommonProperties.timeOut);
		DBParamsUtil dBParams =new DBParamsUtil();
		if(param!=null){
			for(Object obj:param){
				dBParams.addParam(obj);
			}
		}
		dBParams.prepareStatement(ps);
		ResultSet rs=ps.executeQuery();
		//锟斤拷锟斤拷锟捷凤拷锟斤拷map锟斤拷
		List<T> list=resultSetToListMap(rs,clazz);
		rs.close();
		ps.close();
		logger.info("query releaseConnection....");
		this.releaseConnection(conn);
		return list;
	}
	/**
	 */
	@Override
	public List<Map> query(String sql,List<Object> param) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		return query(sql,Map.class,param);
	}
	/**
	 */
	@Override
	public Integer executeUpdate(String sql,List<Object> param) throws SQLException{
		Connection conn=this.getConnection();
		logger.info("executeUpdate sql:{}",sql);
		logger.info("executeUpdate param:{}",param);
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setQueryTimeout(DataBaseCommonProperties.timeOut);
		DBParamsUtil dBParams =new DBParamsUtil();
		if(param!=null){
			for(Object obj:param){
				dBParams.addParam(obj);
			}
		}
		dBParams.prepareStatement(ps);
		int count=ps.executeUpdate();
		this.releaseConnection(conn);
		return count;
	}
	/**
	 */
	@Override
	public boolean executeInsert(String sql,List<Object> param) throws SQLException{
		Connection conn=this.getConnection();
		logger.info("executeInsert sql:{}",sql);
		logger.info("executeInsert param:{}",param);
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setQueryTimeout(DataBaseCommonProperties.timeOut);
		DBParamsUtil dBParams =new DBParamsUtil();
		if(param!=null){
			for(Object obj:param){
				dBParams.addParam(obj);
			}
		}
		dBParams.prepareStatement(ps);
		boolean isOk=ps.execute();
		this.releaseConnection(conn);
		return isOk;
	}
	/**
	 */
	@Override
	public Long queryCount(String sql,List<Object> param) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException{
		List<Map> list=query(sql,param);
		return Long.valueOf(list.get(0).values().iterator().next().toString());
	}
}
