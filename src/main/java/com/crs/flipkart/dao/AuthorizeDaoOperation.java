/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.crs.flipkart.application.CRSApplication;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.constants.Role;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author HP
 * Dao Class Operations for Authorization
 * 
 */
public class AuthorizeDaoOperation implements AuthorizeDaoInterface{

	private static Logger logger = Logger.getLogger(AuthorizeDaoOperation.class);
	private Connection conn = DBConnection.connectDB();
	
	/**
	 * Method for authorizeUser
	 * @param username of user , password of user
	 * returns The role of user
	 */
	@Override
	public Role authorizeUser(String username, String password) {
		try {
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM user join role ON (user.userId = role.userId) WHERE username = ? AND password = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()){
		            return Role.stringToName(rs.getString("roleName"));
			 }
			}
			catch(Exception e){
				logger.error("Exception raised"+e.getMessage());
			}
		return null;
	}

}
