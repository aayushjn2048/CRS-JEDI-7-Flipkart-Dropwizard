/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Date;
import java.util.Scanner;

import com.crs.flipkart.bean.Notification;
import com.crs.flipkart.constants.Role;
import com.crs.flipkart.dao.AdminDaoInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.utils.ServiceUtils;

/**
 * @author HP
 *
 */
public class CRSPaymentMenu {
	Scanner scn;
	private AdminDaoInterface adminDao = new AdminDaoOperation();
	public String displayPaymentMethods() {
		// TODO Auto-generated method stub
		scn = new Scanner(System.in);
		System.out.println("Choose payment mode\n\t1)Offline\n\t2)Online");
		System.out.print("Enter Choice: ");
		int paymentMethod = scn.nextInt();
		switch (paymentMethod) {
		case 1:
			return displayOffline();
		case 2:
			return displayOnline();
		}
		return null;
	}

	private String displayOnline() {
		// TODO Auto-generated method stub
		System.out.println("\nEnter Debit/Credit Card Details:-");
		System.out.print("\n\tCard number: ");
		scn.next();
		System.out.print("\n\tExpiry Date: ");
		scn.next();
		System.out.print("\n\tCVV: ");
		scn.next();
		String refNo = "R" + ServiceUtils.createUserId();
		Notification notification = new Notification();
		notification.setUserId(CRSApplication.getUserId());
		notification.setMessage("Payment successful!!! Your semester registration is completed.");
		Date date = new Date();
		notification.setDateTime(date);
		adminDao.generateNotification(notification);
		return refNo;
	}

	private String displayOffline() {
		// TODO Auto-generated method stub
		System.out.print("Enter Reference Details for Offline payment: ");
		Notification notification = new Notification();
		notification.setUserId(CRSApplication.getUserId());
		notification.setMessage("Payment successful!!! Your semester registration is completed.");
		Date date = new Date();
		notification.setDateTime(date);
		notification.setUserType(Role.STUDENT);
		adminDao.generateNotification(notification);
		return scn.next();
	}
}