/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Notification;
import com.crs.flipkart.dao.AdminDaoInterface;
import com.crs.flipkart.dao.AdminDaoOperation;

/**
 * @author HP
 *
 */
public class NotificationImplementation implements NotificationInterface{

	AdminDaoInterface admin = new AdminDaoOperation();
	@Override
	public void generateNotification(Notification notification) {
		admin.generateNotification(notification);
	}

	@Override
	public ArrayList<Notification> viewNotifications(int userId) {
		return admin.viewNotifications(userId);
	}

}
