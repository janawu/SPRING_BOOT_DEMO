package com.jana.demo.util;


public class Constants {
	
	public static String SQL_QUERY_USER_BY_NAME = "select * from User where name=:name";
	public static String SQL_QUERY_USER = "select * from User";
	public static String SQL_INSERT_USER = "insert into User (name, phone, address) values (:name, :phone, :address)";
	
	public static String PARAM_NAME = "name";

}
