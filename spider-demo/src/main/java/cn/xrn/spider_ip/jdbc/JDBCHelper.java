package cn.xrn.spider_ip.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import cn.xrn.spider_ip.conf.ConfigurationManager;
import cn.xrn.spider_ip.constant.Constants;

public class JDBCHelper {

	static {
		try {
			String driver = ConfigurationManager.get(Constants.JDBC_DRIVER);
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static JDBCHelper instance = null;

	public static JDBCHelper getInstance() {
		if (instance == null) {
			synchronized (JDBCHelper.class) {
				if (instance == null) {
					instance = new JDBCHelper();
				}
			}
		}
		return instance;
	}

	private LinkedList<Connection> datasource = null;

	private JDBCHelper() {
		datasource = new LinkedList<>();
		Integer dataSourceSize = ConfigurationManager.getInteger(Constants.JDBC_DATASOURCE_SIZE);
		for (int i = 0; i < dataSourceSize; i++) {
			String url = ConfigurationManager.get(Constants.JDBC_URL);
			String user = ConfigurationManager.get(Constants.JDBC_USER);
			String password = ConfigurationManager.get(Constants.JDBC_PASSWORD);
			try {
				Connection conn = DriverManager.getConnection(url, user, password);
				datasource.push(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized Connection getConnection() {
		while (datasource.size() == 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return datasource.poll();
	}

	/**
	 * 执行增删改SQL语句
	 * 
	 * @param sql
	 * @param params
	 * @return 影响的行数
	 */
	public int executeUpdate(String sql, Object[] params) {
		int rtn = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);

			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}

			rtn = pstmt.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				datasource.push(conn);
			}
		}

		return rtn;
	}

	/**
	 * 执行查询SQL语句
	 * 
	 * @param sql
	 * @param params
	 * @param callback
	 */
	public void executeQuery(String sql, Object[] params, QueryCallback callback) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}

			rs = pstmt.executeQuery();

			callback.process(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				datasource.push(conn);
			}
		}
	}

	/**
	 * 批量执行SQL语句
	 * @param sql
	 * @param paramsList
	 * @return 每条SQL语句影响的行数
	 */
	public int[] executeBatch(String sql, List<Object[]> paramsList) {
		int[] rtn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 第一步：使用Connection对象，取消自动提交
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);

			// 第二步：使用PreparedStatement.addBatch()方法加入批量的SQL参数
			if (paramsList != null && paramsList.size() > 0) {
				for (Object[] params : paramsList) {
					for (int i = 0; i < params.length; i++) {
						pstmt.setObject(i + 1, params[i]);
					}
					pstmt.addBatch();
				}
			}

			// 第三步：使用PreparedStatement.executeBatch()方法，执行批量的SQL语句
			rtn = pstmt.executeBatch();

			// 最后一步：使用Connection对象，提交批量的SQL语句
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				datasource.push(conn);
			}
		}

		return rtn;
	}

	/**
	 * 静态内部类：查询回调接口
	 * 
	 * @author Administrator
	 *
	 */
	public static interface QueryCallback {

		/**
		 * 处理查询结果
		 * 
		 * @param rs
		 * @throws Exception
		 */
		void process(ResultSet rs) throws Exception;

	}
}
