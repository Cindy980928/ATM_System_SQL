package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 用于建立数据库连接的类
 * @author lubbe
 *
 */
public class DBUtils {
	
	private static final String URL = "jdbc:mysql://localhost:3306/atm?useSSL=false" ;
	private static final String USER = "root" ;
	private static final String PASSWORD = "123456" ;
/**
 * 获得一个连接,连接本地数据库3306的atm数据库
 * @return 连接atm数据的connection类对象
 */
	public static Connection getConnection()  {
		Connection conn = null ;
		try {
			//1. 加载驱动
			Class.forName("com.mysql.jdbc.Driver") ;
			//2. 生成连接
			conn = DriverManager.getConnection(URL, USER, PASSWORD) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn ;
	}
}
