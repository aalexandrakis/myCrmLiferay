package com.aalexandrakis.mycrmliferay.commons;

public class Constants {
	public static final String DB_URL = System.getenv("MYSQL_DB_URL");
	public static final String DB_PORT = System.getenv("MYSQL_DB_PORT");
	public static final String DB_HOST =  DB_URL + ":" + DB_PORT;
	public static final String MYSQL_CONNECTION_STRING = "jdbc:mysql://" + DB_HOST + "/" + System.getenv("MYCRM_DB") + "?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8";
	
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String CURRENT_DB = "MYSQL";
}
