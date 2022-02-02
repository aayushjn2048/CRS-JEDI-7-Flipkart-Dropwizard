/**
 * 
 */
package com.crs.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author HP
 *
 */
public class DBUtils {
	
	private static Connection connection = null;
	
	/**
	 * Method to establish the connection
	 * @return Connection object
	 * 
	 */
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://crs-database.ca10tiiqurow.ap-south-1.rds.amazonaws.com/CRS-Flipkart";

	   //  Database credentials
	   static final String USER = "ce";
	   static final String PASS = "Crs12345#";
	public static Connection getConnection() {
		
        if (connection != null)
            return connection;
        else {
            try {
//            	Properties prop = new Properties();
//                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
//                prop.load(inputStream);
                String driver = JDBC_DRIVER;
                String url = DB_URL;
                String user = USER;
                String password = PASS;
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
