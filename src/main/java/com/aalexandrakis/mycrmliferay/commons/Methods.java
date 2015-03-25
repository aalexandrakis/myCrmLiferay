package com.aalexandrakis.mycrmliferay.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Methods {

	public static Connection getConnection(String userName, String password){
		try {
			Class.forName(getDbDriver());
			return DriverManager.getConnection(getConnectionString(),userName ,password);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static String getConnectionString(){
		return Constants.MYSQL_CONNECTION_STRING;
	}
	
	public static String getDbDriver(){
		return Constants.MYSQL_DRIVER;	
	}
}