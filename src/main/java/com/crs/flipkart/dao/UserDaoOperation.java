/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Student;

/**
 * @author HP
 *
 */
public class UserDaoOperation implements UserDaoInterface {
	
	private static Logger logger = Logger.getLogger(UserDaoOperation.class);
	Connection conn = DBConnection.connectDB();
	
	/**
	 * Method to add credentials of Users to DataBase
	 * @param user contains the student details
	 * @return boolean
	 */
	@Override
	public boolean addUsertData(Student user) {
		
		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO user(userId,username,password) values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getUserId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPasswordHash());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			
			sql = "INSERT INTO student(studentId,name,address,gender,contactNo,gradeCardVisibility) values(?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getUserId());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getAddress());
			stmt.setString(4, user.getGender().toString());
			stmt.setString(5, user.getContactNo());
			stmt.setInt(6, 0);
			rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			
			sql = "INSERT INTO role(userId,roleName) values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getUserId());
			stmt.setString(2, user.getRole().toString());
			rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			logger.error("Exception raised" + se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
		
	}

}
