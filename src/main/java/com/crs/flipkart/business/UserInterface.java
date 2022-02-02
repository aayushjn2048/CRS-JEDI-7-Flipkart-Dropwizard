/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.exceptions.PasswordIsWeakException;

/**
 * @author HP
 * Interface for User Operations
 * 
 */
public interface UserInterface {
	
	/**
	 * Method to add credentials of Users
	 * @param user contains the student details
	 * @throws PasswordIsWeakException 
	 */
	public void addUserdata(Student student) throws PasswordIsWeakException;
}
