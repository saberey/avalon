package com.avalon.ms.dao.mybatis.plugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import com.avalon.ms.dao.entity.PageParams;


/**
 *@description:TODO
 *@author saber
 *@date 2017年10月31日 下午7:32:33
 *@version
 */
@Intercepts({
	@Signature(type=StatementHandler.class,
method="prepare",
args={Connection.class})})
public class PagingPlugin  implements Interceptor{

	private Integer defaultPage;	//默认页码
	private Integer defaultPageSize;//默认每页条数
	private Boolean defaultUseFlag;//默认是否启动插件
	private Boolean defaultCheckFlag;//默认是否检测当前页码的正确性
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		//获取原始未代理的对象
		StatementHandler stmtHandler = getUnProxyObject(invocation);
		MetaObject metaStatementHandler = SystemMetaObject.forObject(stmtHandler);
		String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		//不是sql
		if(!checkSelect(sql)){
			return invocation.proceed();
		}
		BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
		Object parameterObject = boundSql.getParameterObject();
		PageParams pageParams = getPageParams(parameterObject);
		if(pageParams == null){//没有分页参数，不起用插件
			return invocation.proceed();
		}
		//获取分页参数，获取不到时候使用默认值
		Integer pageNum = pageParams.getPage() == null? this.defaultPage:pageParams.getPage();
		Integer pageSize = pageParams.getPageSize() == null?this.defaultPageSize :pageParams.getPageSize();
		Boolean useFlag = pageParams.getUseFlag() == null? this.defaultUseFlag : pageParams.getUseFlag();
		Boolean checkFlag = pageParams.getCheckFlag() == null?this.defaultCheckFlag:pageParams.getCheckFlag();
		if(!useFlag){//不适用分页插件
			return invocation.proceed();
		}
		int total = getTotal(invocation,metaStatementHandler,boundSql);
		//回填总数到分页参数里
		setTotalToPageParams(pageParams,total,pageSize);
		//检查当前页码的有效性
		checkPage(checkFlag,pageNum,pageParams.getTotalPage());
		//修改SQL
		return changeSql(invocation,metaStatementHandler,boundSql,pageNum,pageSize);
	}
	
	/**
	 * 从代理对象中分离真实对象
	 * @description:TODO
	 * @param ivt
	 * @return
	 * StatementHandler
	 * @exception:
	 * @author: saber
	 * @time:2017年11月1日上午11:06:59
	 */
	private StatementHandler getUnProxyObject(Invocation ivt){
		StatementHandler statementHandler = (StatementHandler) ivt.getTarget();
		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
		//分离代理对象链（由于目标类可能被多个拦截器拦截，从而形成多次代理，通过循环可以分离出最原始的目标类）
		Object object = null;
		while(metaStatementHandler.hasGetter("h")){
			object = metaStatementHandler.getValue("h");
		}
		
		if(object == null){
			return statementHandler;
		}
		return (StatementHandler) object;
	}
	
	/**
	 * 判断是否select语句
	 * @description:TODO
	 * @param sql
	 * @return
	 * boolean
	 * @exception:
	 * 
	 * @time:2017年11月1日上午11:13:57
	 */
	private boolean checkSelect(String sql){
		String trimSql = sql.trim();
		int idx = trimSql.toLowerCase().indexOf("select");
		return idx == 0;
	}
	
	/**
	 * 分解分页参数，这里支持使用Map和@Param注解传递参数，或者pojo集成pageParams
	 * @description:TODO
	 * @param parameterObject
	 * @return
	 * PageParams
	 * @exception:
	 * @author: saber
	 * @time:2017年11月1日下午2:02:47
	 */
	private PageParams getPageParams(Object parameterObject){
		if(parameterObject == null){
			return null;
		}
			PageParams pageParams = null;
			//支持map参数和MyBatis的@param注解参数
			if(parameterObject instanceof Map){
				Map<String,Object> paramMap = (Map<String, Object>) parameterObject;
				Set<String> keySet = paramMap.keySet();
				Iterator<String> iterator = keySet.iterator();
				while(iterator.hasNext()){
					String key = iterator.next();
					Object value = paramMap.get(key);
					if(value instanceof PageParams){
						return (PageParams) value;
					}
				}
			} else if(parameterObject instanceof PageParams){//继承方式
				pageParams = (PageParams) parameterObject;
			}
		return pageParams;
	}
	
	private  int getTotal(Invocation ivt, MetaObject metaStatementHandler,
			BoundSql boundSql) throws Throwable{
		//获取当前的mappedStatement
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		//配置对象
		Configuration cfg = mappedStatement.getConfiguration();
		//当前需要执行的sql
		String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		//改写为统计总数的sql,这里是mysql数据库，如果是其他数据需按数据库的sql规范改写
		String countSql = "select count(*) as total from ("+sql+")$_paging";
		//获取拦截方法参数，我们知道是Connection对象
		Connection connection = (Connection) ivt.getArgs()[0];
		PreparedStatement ps = null;
		int total = 0;
		try{
			//预编译统计总数sql
			ps = connection.prepareStatement(countSql);
			//构建统计总数Boundsql
			BoundSql countBoundSql = new BoundSql(cfg, countSql, boundSql.getParameterMappings(),
					boundSql.getParameterObject());
			//构建MyBatis的ParameterHandler用来设置总数SQL的参数
			ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
			//设置总数sql参数
			handler.setParameters(ps);
			//执行查询
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				total = rs.getInt("total");
			}
		}finally{
			//这里不能关闭Connection 否则后续的sql就没法继续了
			if(ps != null){
				ps.close();
			}
		}
		System.err.println("总条数:"+total);
		return total;
	}
	
	private void setTotalToPageParams(PageParams pageParams,int total, int pageSize){
		pageParams.setTotal(total);
		//计算总页数
		int totalPage = total%pageSize == 0 ? total/pageSize:total/pageSize+1;
		pageParams.setTotalPage(totalPage);
	}
	
	/**
	 * 检查当前页码的有效性
	 * @description:TODO
	 * @param checkFlag
	 * @param pageNum
	 * @param pageTotal
	 * @throws Throwable
	 * void
	 * @exception:
	 * @author: saber
	 * @time:2017年11月1日下午2:45:24
	 */
	private void checkPage(Boolean checkFlag,Integer pageNum,Integer pageTotal) throws Throwable{
		if(checkFlag){
			//检查页码page是否合法
			if(pageNum>pageTotal){
				throw new Exception("查询失败，查询页码【"+pageNum+"】大于页数【"+pageTotal+"】!!");
			}
		}
	}
	
	/**
	 * 修改当前查询的sql
	 * @description:TODO
	 * @param invocation
	 * @param metaStatementHandler
	 * @param boundSql
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * Object
	 * @exception:
	 * @author: saber
	 * @time:2017年11月1日下午3:04:22
	 */
	private Object changeSql(Invocation invocation,MetaObject metaStatementHandler,
			BoundSql boundSql,int page,int pageSize) throws Exception{
		//获取当前需要执行的sql
		String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		//修改sql,这里使用的是MySql,如果是其他数据库则需要修改
		String newSql = "select * from("+sql+")$_paging_table limit ?,?";
		//修改当前需要执行的sql
		metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
		//相当于调用StatementHandler的prepare方法，预编译了当前SQL并设置原有的参数，但是少了两个分页参数，它返回的是一个PreparedStatement对象
		PreparedStatement ps = (PreparedStatement) invocation.proceed();
	    //计算sql总参数个数
		int count = ps.getParameterMetaData().getParameterCount();
		//设置两个分页参数
		ps.setInt(count-1, (page-1)*pageSize);
		ps.setInt(count, pageSize);
		return ps;
	}
	
	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);//产生动态代理对象
	}

	@Override
	public void setProperties(Properties properties) {
		//设置配置的参数得到默认值
		// TODO Auto-generated method stub
		String steDefaultPage = properties.getProperty("default.page","1");
		String strDefaultPageSize = properties.getProperty("default.pageSize","50");
		String strDefaultUseFlag = properties.getProperty("default.useFlag","false");
		String strDefaultCheckFlag = properties.getProperty("default.checkFlag","false");
		
		this.defaultPage = Integer.parseInt(steDefaultPage);
		this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
		this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
		this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);
	}
}
