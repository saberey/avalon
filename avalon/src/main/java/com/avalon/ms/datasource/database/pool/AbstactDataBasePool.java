package com.avalon.ms.datasource.database.pool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avalon.ms.common.constant.DataBaseCommonProperties;
import com.avalon.ms.datasource.database.PooledConnection;



public  abstract class AbstactDataBasePool implements DataBasePoolInterface {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private String ip;
	private Integer port;
	private String dataBaseName;
	private String userName;
	private String password;
	private String jdbcDriver;
	private Integer maxConnections;
	private volatile  Vector<PooledConnection> connections = null; // 存放连接池中数据库连接的向量 , 初始时为 null
	
	/**
	 * 数据库连接池大小
	 */
	private Integer linkPoolSize;
	
	public  AbstactDataBasePool(String ip,Integer port,String dataBaseName,String userName,String password,Integer linkPoolSize) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		this.setIp(ip);
		this.setPort(port);
		this.setDataBaseName(dataBaseName);
		this.setUserName(userName);
		this.setPassword(password);
		this.setLinkPoolSize(linkPoolSize);
		this.maxConnections=DataBaseCommonProperties.maxConnections;
		initPool();
	}
	
	/**
	* 创建一个新的数据库连接并返回它
	*
	* @return 返回一个新创建的数据库连接
	*/

	private Connection newConnection() throws SQLException {
	         // 创建一个数据库连接
	         Connection conn = DriverManager.getConnection(getDbUrl(), this.getUserName(), this.getPassword());
	         // 如果这是第一次创建数据库连接，即检查数据库，获得此数据库允许支持的
	         // 最大客户连接数目
	         //connections.size()==0 表示目前没有连接己被创建
	         if (connections.size() == 0) {
	        	  DatabaseMetaData metaData = conn.getMetaData();
		          int driverMaxConnections = metaData.getMaxConnections();
		          // 数据库返回的 driverMaxConnections 若为 0 ，表示此数据库没有最大
		          // 连接限制，或数据库的最大连接限制不知道
		          //driverMaxConnections 为返回的一个整数，表示此数据库允许客户连接的数目
		          // 如果连接池中设置的最大连接数量大于数据库允许的连接数目 , 则置连接池的最大
		          // 连接数目为数据库允许的最大数目
		          if (driverMaxConnections > 0 && this.maxConnections > driverMaxConnections) {
		        	  this.maxConnections = driverMaxConnections;
		          }
	         }
	         return conn; // 返回创建的新的数据库连接
	}
	
	private synchronized boolean createConnections() throws SQLException{
		 // 循环创建指定数目的数据库连接
        for (int x = 0; x < this.getLinkPoolSize(); x++) {
	         // 是否连接池中的数据库连接的数量己经达到最大？最大值由类成员 maxConnections
	         // 指出，如果 maxConnections 为 0 或负数，表示连接数量没有限制。
	         // 如果连接数己经达到最大，即退出。
	         if (this.maxConnections > 0 && this.connections.size() >= this.maxConnections) {
	                break;
	         }
	         //add a new PooledConnection object to connections vector
	         // 增加一个连接到连接池中（向量 connections 中）
	         try{
	          connections.addElement(new PooledConnection(newConnection()));
	         }catch(SQLException e){
	          logger.error(" 创建数据库连接失败！ "+e.getMessage());
	          throw new SQLException();
	         }
	         logger.debug(" 数据库连接己创建 ......");
        }
        return true;
	}
	/**
	*
	* 创建一个数据库连接池，连接池中的可用连接的数量采用类成员
	* initialConnections 中设置的值
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	*/
	@Override
	public void initPool() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.jdbcDriver=this.getJdbcDriver();
		// 确保连接池没有创建
		// 如果连接池己经创建了，保存连接的向量 connections 不会为空
		if (connections != null) {
			return; // 如果己经创建，则返回
		}
		// 实例化 JDBC Driver 中指定的驱动类实例
		Driver driver = (Driver) (Class.forName(this.jdbcDriver).newInstance());
		DriverManager.registerDriver(driver); // 注册 JDBC 驱动程序
		// 创建保存连接的向量 , 初始时有 0 个元素
		connections = new Vector<PooledConnection>();
		// 根据 initialConnections 中设置的值，创建连接。
		createConnections();
		logger.debug(" 数据库连接池创建成功！ ");

	}
	
	/**
	* 测试一个连接是否可用，如果不可用，关掉它并返回 false
	* 否则可用返回 true
	*
	* @param conn 需要测试的数据库连接
	* @return 返回 true 表示此连接可用， false 表示不可用
	*/

	private boolean testConnection(Connection conn) {
		try {
			// 判断测试表是否存在
			Statement stmt = conn.createStatement();
			logger.debug("testsql:{}",this.getTestSQL());
			stmt.execute(this.getTestSQL());
		} catch (SQLException e) {
			// 上面抛出异常，此连接己不可用，关闭它，并返回 false;
			logger.error("testConnection  conn is exception:{}",e.getMessage());
			closeConnection(conn);
			return false;
		}
		// 连接可用，返回 true
		return true;

	}
	
	//测试语句
	public String getTestSQL(){
		return "";
	}
	
	
	@Override
	public  boolean releaseConnection(Connection conn) throws SQLException {
		logger.debug("releaseConnection beginning...");
		// 确保连接池存在，如果连接没有创建（不存在），直接返回
		if (connections == null) {
			logger.warn(" 连接池不存在，无法返回此连接到连接池中 !");
			return false;
		}
		logger.debug("releaseConnection before:{}",connections.size());
		PooledConnection pConn = null;
		Enumeration<PooledConnection> enumerate = connections.elements();
		// 遍历连接池中的所有连接，找到这个要返回的连接对象
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection) enumerate.nextElement();
			// 先找到连接池中的要返回的连接对象
			if (conn == pConn.getConnection()) {
				//如果连接池里有超过最小空闲的连接则删除多余的空闲连接
				// 找到了 , 设置此连接为空闲状态
				pConn.setBusy(false);
				logger.debug("releaseConnection ....");
				if (removeMoreConnection(pConn)){
					break;
				}
				break;
			}
		}
		logger.debug("releaseConnection after:{}",connections.size());
		return true;
	}
	/**
	 * 如果连接池里有超过最小空闲的连接则删除多余的空闲连接
	 * @throws SQLException 
	 */
	private  boolean removeMoreConnection(PooledConnection pConn) throws SQLException{
		//查询当前空闲连接数
		int count=findFreeConnectionCount();
		logger.debug("findFreeConnectionCount is {}",count);
		//如果数量超过连接池默认大小则清除刚释放的连接
		if(count>this.getLinkPoolSize()){
			synchronized(this){
				count=findFreeConnectionCount();
				if(count>this.getLinkPoolSize()){
					logger.debug("removeMoreConnection before:{}",connections.size());
					closeConnection(pConn.getConnection());
					connections.remove(pConn);
					logger.debug("removeMoreConnection after:{}",connections.size());
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	* 关闭一个数据库连接
	*
	* @param 需要关闭的数据库连接
	*/

	private void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error(" 关闭数据库连接出错： " + e.getMessage());
		}
	}
	
	/**
	* 使程序等待给定的毫秒数
	* @param 给定的毫秒数
	*/
	private void wait(int mSeconds) {
		try {
			Thread.sleep(mSeconds);
		} catch (InterruptedException e) {
		}

	}
	
	/**
	* 关闭连接池中所有的连接，并清空连接池。
	*/
	public synchronized void closeConnectionPool() throws SQLException {
		// 确保连接池存在，如果不存在，返回
		if (connections == null) {
			logger.warn(" 连接池不存在，无法关闭 !");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> enumerate = connections.elements();
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection) enumerate.nextElement();
			// 如果忙，等 5 秒
			if (pConn.isBusy()) {
				wait(5000); // 等 5 秒
			}
			// 5 秒后直接关闭它
			closeConnection(pConn.getConnection());
			// 从连接池向量中删除它
			connections.removeElement(pConn);
		}
		// 置连接池为空
		connections = null;
	}
	
	/**
	* 刷新连接池中所有的连接对象
	*
	*/
	public synchronized void refreshConnections() throws SQLException {
         // 确保连接池己创新存在
         if (connections == null) {
	          logger.warn(" 连接池不存在，无法刷新 !");
	          return;
         }
         PooledConnection pConn = null;
         Enumeration<PooledConnection> enumerate = connections.elements();
         while (enumerate.hasMoreElements()) {
	          // 获得一个连接对象
	          pConn = (PooledConnection) enumerate.nextElement();
	          // 如果对象忙则等 5 秒 ,5 秒后直接刷新
	          if (pConn.isBusy()) {
	        	  wait(5000); // 等 5 秒
	          }
	          // 关闭此连接，用一个新的连接代替它。
	          closeConnection(pConn.getConnection());
	          pConn.setConnection(newConnection());
	          pConn.setBusy(false);
         }
	}
	
	/**
	* 查找连接池中所有的连接，查找一个可用的数据库连接，
	* 如果没有可用的连接，返回 null
	* @return 返回一个可用的数据库连接
	*/

	private Connection findFreeConnection() throws SQLException {
		Connection conn = null;
		PooledConnection pConn = null;
		// 获得连接池向量中所有的对象
		Enumeration<PooledConnection> enumerate = connections.elements();
		// 遍历所有的对象，看是否有可用的连接
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection) enumerate.nextElement();
			if (!pConn.isBusy()) {
				// 如果此对象不忙，则获得它的数据库连接并把它设为忙
				conn = pConn.getConnection();
				pConn.setBusy(true);
				// 测试此连接是否可用
				if (!testConnection(conn)) {
					// 如果此连接不可再用了，则创建一个新的连接，
					// 并替换此不可用的连接对象，如果创建失败，返回 null
					try {
						conn = newConnection();
					} catch (SQLException e) {
						logger.warn(" 创建数据库连接失败！ " + e.getMessage());
						return null;
					}
					pConn.setConnection(conn);
				}
				break; // 己经找到一个可用的连接，退出
			}
		}
		return conn;// 返回找到到的可用连接
	}
	/**
	 * 查找连接池中所有的连接，查找一个可用的数据库连接，
	 * 如果没有可用的连接，返回 null
	 * @return 返回一个可用的数据库连接
	 */
	
	private Integer findFreeConnectionCount() throws SQLException {
		int count=0;
		PooledConnection pConn = null;
		// 获得连接池向量中所有的对象
		Enumeration<PooledConnection> enumerate = connections.elements();
		// 遍历所有的对象，看是否有可用的连接
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection) enumerate.nextElement();
			if (!pConn.isBusy()) {
				// 如果此对象不忙，则获得它的数据库连接并把它设为忙
				count++;
			}
		}
		return count;// 返回找到到的可用连接
	}
	
	/**
	* 本函数从连接池向量 connections 中返回一个可用的的数据库连接，如果
	* 当前没有可用的数据库连接，本函数则根据 incrementalConnections 设置
	* 的值创建几个数据库连接，并放入连接池中。
	* 如果创建后，所有的连接仍都在使用中，则返回 null
	* @return 返回一个可用的数据库连接
	*/
	private Connection getFreeConnection() throws SQLException {
		// 从连接池中获得一个可用的数据库连接
		Connection conn = findFreeConnection();
		if (conn == null) {
			// 如果目前连接池中没有可用的连接
			// 创建一些连接
			logger.warn("如果目前连接池中没有可用的连接,需要创建连接，连接池数量:{}",this.connections.size());
			createConnections();
			logger.warn("创建之后，连接池数量:{}",this.connections.size());
			// 重新从池中查找是否有可用连接
			conn = findFreeConnection();
			if (conn == null) {
				// 如果创建连接后仍获得不到可用的连接，则返回 null
				return null;
			}
		}
		return conn;
	}
	
	/**
	* 通过调用 getFreeConnection() 函数返回一个可用的数据库连接 ,
	* 如果当前没有可用的数据库连接，并且更多的数据库连接不能创
	* 建（如连接池大小的限制），此函数等待一会再尝试获取。
	*
	* @return 返回一个可用的数据库连接对象
	*/

	public synchronized Connection getConnection() throws SQLException {
		// 确保连接池己被创建
		if (connections == null) {
			return null; // 连接池还没创建，则返回 null
		}
		Connection conn = getFreeConnection(); // 获得一个可用的数据库连接
		// 如果目前没有可以使用的连接，即所有的连接都在使用中
		while (conn == null) {
			// 等一会再试
			wait(250);
			conn = getFreeConnection(); // 重新再试，直到获得可用的连接，如果
			// getFreeConnection() 返回的为 null
			// 则表明创建一批连接后也不可获得可用连接
		}
		return conn;// 返回获得的可用的连接
	}
	
	@Override
	public String getIp() {
		// TODO Auto-generated method stub
		return ip;
	}

	@Override
	public void setIp(String ip) {
		this.ip=ip;
	}

	@Override
	public Integer getPort() {
		// TODO Auto-generated method stub
		return port;
	}

	@Override
	public void setPort(Integer port) {
		// TODO Auto-generated method stub
		this.port=port;
	}
	@Override
	public String getDataBaseName() {
		return dataBaseName;
	}
	@Override
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName=dataBaseName;
	}

	@Override
	public String getUserName() {
		return userName;
	}
	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public Integer getLinkPoolSize() {
		return linkPoolSize;
	}
	@Override
	public void setLinkPoolSize(Integer linkPoolSize) {
		this.linkPoolSize=linkPoolSize;
	}
}
