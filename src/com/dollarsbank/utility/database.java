package com.dollarsbank.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
	private static final String uri = "jdbc:mysql://localhost/bank?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "nikki4";
    
    private static Connection conn = null;

  public database(){

  }
  
  public static void makeConnection() {
	  try {
		Class.forName(driver);
		conn = DriverManager.getConnection(uri, user, password);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
  }

  public static Connection getConn() {
	  return conn;
  }
  
  public static void closeConn() {
	  try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
