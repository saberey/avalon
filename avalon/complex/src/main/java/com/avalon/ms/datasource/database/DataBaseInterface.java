package com.avalon.ms.datasource.database;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DataBaseInterface {

	/**
	 * @param sql
	 * @param clazz
	 * @return
	 */
	public <T> List<T> query(String sql, Class<T> clazz, List<Object> param) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException;
	
	/**
	 * @param sql
	 * @return
	 */
	public  List<Map> query(String sql, List<Object> param) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException;
	
	/**
	 * @param sql
	 * @return
	 */
	public Integer executeUpdate(String sql, List<Object> param) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException;
	
	/**
	 * @param sql
	 * @return
	 */
	public boolean executeInsert(String sql, List<Object> param) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException;
	
	/**
	 * @param sql
	 * @return
	 */
	public Long queryCount(String sql, List<Object> param) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException;
}
