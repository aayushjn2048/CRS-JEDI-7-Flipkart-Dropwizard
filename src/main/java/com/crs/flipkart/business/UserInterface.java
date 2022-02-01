/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;

/**
 * @author HP
 * Interface for User Operations
 * 
 */
public interface UserInterface {
	
	/**
	 * Method to add credentials of Users
	 * @param user contains the student details
	 */
	public void addUserdata(Student student);
}
