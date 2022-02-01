/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Student;

/**
 * @author JEDI-03
 * Interface for user Dao Operations
 * 
 */
public interface UserDaoInterface {
	
	/**
	 * Method to add credentials of Users to DataBase
	 * @param user contains the student details
	 * @return boolean
	 */
	public boolean addUsertData(Student student);
}
