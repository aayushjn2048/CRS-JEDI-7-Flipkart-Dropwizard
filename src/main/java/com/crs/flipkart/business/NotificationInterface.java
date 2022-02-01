/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Notification;

/**
 * @author HP
 *
 */
public interface NotificationInterface {

	public void generateNotification(Notification notification);
	public ArrayList<Notification> viewNotifications(int userId);
}
