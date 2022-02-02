/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.business.ProfessorImplementation;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.GradeCardNotPublishedException;
import com.crs.flipkart.exceptions.PasswordIsWeakException;
import com.crs.flipkart.exceptions.ProfessorNotFoundException;
import com.crs.flipkart.utils.DateAndTimeUtil;

/**
 * @author HP
 *
 */
public class CRSProfessorMenu {
	
	public void professorMenuMain()  throws CourseNotFoundException, ProfessorNotFoundException, GradeCardNotPublishedException, PasswordIsWeakException{
		DateAndTimeUtil.loginDisplayDateAndTime();
		System.out.println("\033[0;1m" +"\n---------------------!!Welcome "+CRSApplication.getUserName()+"!!-----------------------\n"+"\033[0m");
		System.out.println("Choose an option:-");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t1 : View Available Courses\n\t2 : View enrolled students\n\t3 : Select Course\n\t4 : Assign Grade\n\t5 : Logout");
		System.out.println("==========================================================");
		Scanner scanner = new Scanner(System.in);
		int professorId = CRSApplication.getUserId();
		ProfessorImplementation professorImplementation = ProfessorImplementation.getInstance();
		while(true) {
			System.out.print("Enter your Choice: ");
			int choice = scanner.nextInt();
			if(choice == 5)
			{
				System.out.println("\nBye!!!");
				DateAndTimeUtil.logoutDisplayDateAndTime();
				System.out.println("");
				CRSApplication.startApplication();
				break;
			}
			switch(choice) {
				case 1:{
					professorImplementation.viewAvailableCourses();
					break;
				}
				case 2: {
					System.out.println("Enetr your course Choice");
					int courseId=scanner.nextInt();
					professorImplementation.viewEnrolledStudents(professorId, courseId);
					break;
				}
				case 3:{
					
						System.out.print("Please enter your choice of selection: ");
						int courseId=scanner.nextInt();
						professorImplementation.selectCourse(professorId,courseId);
							

				break;
				}
				case 4:{
					System.out.print("Enter StudentId: ");
					int studentId= scanner.nextInt();
					System.out.print("Enter CourseId: ");
					int courseId= scanner.nextInt();
					System.out.print("Enter Grade: ");
					float grade= scanner.nextFloat();
					professorImplementation.assignGrade(studentId,courseId,grade);
					break;
				}
			}
		}
	}
}
