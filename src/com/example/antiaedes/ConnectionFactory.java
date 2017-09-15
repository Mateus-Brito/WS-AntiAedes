package com.example.antiaedes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
 
	private static final String URL = "jdbc:mysql://127.12.73.130:3306/antiaedes";
	private static final String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
	private static final String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
	//private static final String URL = "jdbc:mysql://localhost:3306/antiaedes";
	//private static final String username = "root";
	//private static final String password = "";
	
	//private final static String name = "antiaedes";
	//private final static String url = "jdbc:mysql://127.12.73.130:3306/" + name;
	
	public static Connection getConnection(){
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		     e.printStackTrace();
		}
		try {
			System.out.println(username);
			return DriverManager.getConnection(URL ,username,password);
		}  catch (SQLException e) {
            System.out.println("Error.");
            return null;
        }
	}
}