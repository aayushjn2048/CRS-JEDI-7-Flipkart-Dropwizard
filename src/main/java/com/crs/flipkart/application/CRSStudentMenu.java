/**
 * 
 */
package com.crs.flipkart.application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.crs.flipkart.bean.Notification;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.NotificationImplementation;
import com.crs.flipkart.business.NotificationInterface;
import com.crs.flipkart.business.PaymentImplementation;
import com.crs.flipkart.business.PaymentInterface;
import com.crs.flipkart.business.ProfessorImplementation;
import com.crs.flipkart.business.StudentImplementation;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.GradeCardNotPublishedException;
import com.crs.flipkart.exceptions.PasswordIsWeakException;
import com.crs.flipkart.exceptions.ProfessorNotFoundException;
import com.crs.flipkart.utils.DateAndTimeUtil;

/**
 * @author HP
 *
 */
public class CRSStudentMenu {
	public void studentMenuMain() throws CourseNotFoundException, ProfessorNotFoundException, GradeCardNotPublishedException, PasswordIsWeakException{
		DateAndTimeUtil.loginDisplayDateAndTime();
		System.out.println("\033[0;1m" +"\n------------------!!Welcome "+CRSApplication.getUserName()+"!!-------------------\n"+"\033[0m" );
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t1 : View Course List\n\t2 : Semester Registration\n\t3 : Payment\n\t4 : View Grade Card\n\t5 : View Profile\n\t6 : View Notifications\n\t7 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		int studentId = CRSApplication.getUserId();
		StudentInterface studentImplementation = new StudentImplementation();
		PaymentInterface paymentImplementation = new PaymentImplementation();
		while(true) {
			System.out.print("Enter your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 7)
			{
				System.out.println("\nBye!!!");
				DateAndTimeUtil.logoutDisplayDateAndTime();
				System.out.println("");
				CRSApplication.startApplication();
				break;
			}
			switch(choice) {
				case 1:{
					studentImplementation.displayCourseCatalog();
					break;
				}
				case 2: {
					//Semester Registration
					if(studentImplementation.studentAlreadyRegistered(CRSApplication.getUserId()))
					{
						System.out.println("You already submitted the registration form");
						break;
					}
					System.out.print("Enter email: ");
					String email = scanner.next();
					System.out.print("Enter contact number: ");
					String contaccNo = scanner.next();

					studentImplementation.selectCourses(CRSApplication.getUserId());
					break;
				}
				case 3:{
					//Payment
					String paymentStatus=studentImplementation.getPaymentStatus(studentId);
					CRSPaymentMenu crsPaymentMenu=new CRSPaymentMenu();
					if(paymentStatus==null || !paymentStatus.equals("SUCCESS")) {
						System.out.println("Fees to be paid for current semester is: " + paymentImplementation.calculateFees(studentId));
						String referenceNo = crsPaymentMenu.displayPaymentMethods();
						studentImplementation.makePaymentSuccessful(CRSApplication.getUserId(),referenceNo);
					}
					System.out.println("Payment done successfully!");
					break;
				}
				case 4:{
					//System.out.println("Grade card for current semester is: ");
					try {
						if(studentImplementation.isStudentRegistered(CRSApplication.getUserId()))
							studentImplementation.displayGradeCard(studentId);
						else
							System.out.println("Student not registered");
					}
					catch(GradeCardNotPublishedException g)
					{}
					break;
				}
				case 5: {
					//view profile
					System.out.println("\n===============STUDENT PROFILE===============\n");
					Student student = studentImplementation.viewStudentDetails(studentId);
					System.out.println("\tStudentId:\t" + student.getStudentId());
					System.out.println("\tName:\t\t" + student.getName());
					System.out.println("\tContact:\t" + student.getContactNo());
					System.out.println("\tAddress:\t" + student.getAddress());
					System.out.println("\n=============================================\n");
					break;
				}
				case 6: {
					NotificationInterface notificationImplementation = new NotificationImplementation();
					ArrayList<Notification> notifications = notificationImplementation.viewNotifications(CRSApplication.getUserId());
					System.out.println("Serial No\tDate and Time\t\tMessage");
					int count = 1;
					for(Notification notification: notifications)
					{
						Date date = notification.getDateTime();
						SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
						System.out.println(count+"\t\t"+dt.format(date)+"\t"+notification.getMessage());
						count++;
					}
					break;
				}
			}
		}
	}
}
