/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Date;

import com.crs.flipkart.application.CRSApplication;
import com.crs.flipkart.bean.Notification;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.Role;
import com.crs.flipkart.dao.AdminDaoInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.UserDaoInterface;
import com.crs.flipkart.dao.UserDaoOperation;
import com.crs.flipkart.exceptions.PasswordIsWeakException;

/**
 * @author HP
  * Implementations of User Operations
 * 
 */
public class UserImplementation implements UserInterface {

	/**
	 * Method to add credentials of Users
	 * @param user contains the student details
	 */
	@Override
	public void addUserdata(Student user) throws PasswordIsWeakException{
		// TODO Auto-generated method stub
		
		UserDaoInterface userDaoOperation = new UserDaoOperation();
		if(user.getPasswordHash().length()<4)
			throw new PasswordIsWeakException();
		if(userDaoOperation.addUsertData(user))
		{
			Notification notification = new Notification();
			notification.setUserId(CRSApplication.getUserId());
			notification.setMessage("Welcome to our CRS Application portal!!!");
			Date date = new Date();
			notification.setDateTime(date);
			notification.setUserType(Role.STUDENT);
			AdminDaoInterface adminDao = new AdminDaoOperation();
			adminDao.generateNotification(notification);
		}
	}
	

}
