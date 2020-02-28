package com.himu.rest.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class DbUtil {
	private static String driver,url,username,password;
	static
	{
		ResourceBundle rb = ResourceBundle.getBundle("jdbc");
		driver = rb.getString("jdbc.driver");
		url = rb.getString("jdbc.url");
		username = rb.getString("jdbc.user");
		password = rb.getString("jdbc.password");
	
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		try{
			return DriverManager.getConnection(url,username,password);
		}
		catch(Exception ex)
		{
			System.out.println("Failed to start DB");
		}
		return null;
		
		
   }
}
