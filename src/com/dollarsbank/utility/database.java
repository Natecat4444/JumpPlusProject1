package com.dollarsbank.utility;

import java.sql.Connection;

public class database {
	private String uri = null;
    private String driver = null;
    private String user = null;
    private String password = null;
    private Connection conn = null;

  public database(){
    uri = "jdbc:mysql://localhost/travel?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    driver = "com.mysql.cj.jdbc.Driver";
    user = "root";
    password = "nikki4";
    conn = null;

  }
}
