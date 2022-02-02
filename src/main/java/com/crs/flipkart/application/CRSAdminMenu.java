/**
 * 
 */
package com.crs.flipkart.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Challan;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.SemesterRegistration;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.business.AdminImplementation;
import com.crs.flipkart.business.CourseImplementation;
import com.crs.flipkart.business.CourseInterface;
import com.crs.flipkart.business.ProfessorImplementation;
import com.crs.flipkart.business.StudentImplementation;
import com.crs.flipkart.constants.Designation;
import com.crs.flipkart.constants.Gender;
import com.crs.flipkart.constants.Role;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.exceptions.CourseAlreadyExists;
import com.crs.flipkart.exceptions.CourseNotDeletedException;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.GradeCardNotPublishedException;
import com.crs.flipkart.exceptions.PasswordIsWeakException;
import com.crs.flipkart.exceptions.ProfessorNotFoundException;
import com.crs.flipkart.utils.DateAndTimeUtil;

/**
 * @author HP
 *
 */
public class CRSAdminMenu {

	/**
	 * @param args
	 */
	CourseInterface courseImplementation = CourseImplementation.getInstance();
	StudentImplementation studentImplementation = StudentImplementation.getInstance();
	private static Logger logger = Logger.getLogger(CRSAdminMenu.class);
	
	public void adminMenuMain() throws CourseNotFoundException, ProfessorNotFoundException, GradeCardNotPublishedException, PasswordIsWeakException{
		DateAndTimeUtil.loginDisplayDateAndTime();
		System.out.println("\033[0;1m" +"\n-----------------------!!Welcome Admin!!----------------------\n"+"\033[0m" );
		System.out.println("Choose an option:-");
		System.out.println("--------------------------------------------------------------");
		System.out.println("\t1 : Add Course\n\t2 : Delete Course\n\t3 : Update Course\n\t4 : Activate Grade Card");
		System.out.println("\t5 : Add Professor Details\n\t6 : Delete Professor Details\n\t7 : Update Professor Details\n\t8 : Approve Student Registration");
		System.out.println("\t9 : View Student Details\n\t10: View Professor Details\n\t11: View All Courses\n\t12: Student Course Allocation\n\t13: Logout");
		System.out.println("==============================================================");
		AdminImplementation admin = new AdminImplementation();
		while(true)
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nEnter Choice Number: ");
			int choice = scanner.nextInt();
			if(choice == 13)
			{
				System.out.println("\nBye!!! ");
				DateAndTimeUtil.logoutDisplayDateAndTime();
				System.out.println("");
				CRSApplication.startApplication();
				break;
			}
			switch(choice)
			{
				case 1: {
							Course newCourse = new Course();
							System.out.print("Enter CourseId: ");
							newCourse.setCourseId(scanner.nextInt());
		
							System.out.print("Enter course name: ");
							newCourse.setName(scanner.next());
		
							System.out.print("Enter Course Fee: ");
							newCourse.setCourseFee(scanner.nextInt());
		
							System.out.print("Enter CatalogId");
							newCourse.setCatalogId(scanner.nextInt());
		
							try {
								courseImplementation.addCourse(newCourse);
							} catch (CourseAlreadyExists e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						}
				case 2: {
							System.out.print("Enter CourseId: ");
							try {
								courseImplementation.removeCourse(scanner.nextInt());
							} catch (CourseNotDeletedException  ce) {
								// TODO: handle exception
								logger.error("Exception raised" + ce.getMessage());
							}

							break;
						}
				case 3: {
							
							Course newCourse1 = new Course();
							System.out.print("Enter Course Id: ");
							newCourse1.setCourseId(scanner.nextInt());
							System.out.println("Note: Enter NA if you want to keep the details same.");
							String s;
							System.out.print("Enter new course name: ");
							s = scanner.next();
							if(!s.equals("NA"))
								newCourse1.setName(s);
							
							System.out.print("Enter new Professor Id: ");
							s = scanner.next();
							if(!s.equals("NA"))
								newCourse1.setProfessorId(Integer.parseInt(s));
							
							System.out.print("Enter new Course Fee: ");
							s = scanner.next();
							if(!s.equals("NA"))
								newCourse1.setCourseFee(Integer.parseInt(s));
							
							try {
								courseImplementation.updateCourse(newCourse1);
							} catch (CourseNotFoundException ce) {
								logger.error("Exception raised" + ce.getMessage());
							}
							
							break;
						}
				case 4: {
							AdminImplementation adminImplementation = new AdminImplementation();
							adminImplementation.activateGradeCard();
							break;
					    }
				case 5: {
							Professor professor = new Professor();
							System.out.print("Enter username: ");
							String username = scanner.next();
							professor.setUsername(username);
							System.out.print("Enter password: ");
							String passwordHash = scanner.next();
							professor.setPasswordHash(passwordHash);
							System.out.print("Enter name: ");
							String name = scanner.next();
							professor.setName(name);
							System.out.print("Enter address: ");
							String address = scanner.next();
							professor.setAddress(address);
							System.out.print("Enter gender('MALE'/'FEMALE'/'OTHERS'): ");
							String gender = scanner.next();	//Input MALE / FEMALE / OTHER all in capitals
							professor.setGender(Gender.valueOf(gender));
							System.out.print("Enter contact number: ");
							String contactNo = scanner.next();
							professor.setContactNo(contactNo);
							System.out.print("Enter designation('ASSOCIATE'/'ASSISTANT'/'PROFESSOR'): ");		//this is not role , role will always be professor , this is designation
							String designation = scanner.next();
							professor.setDesignation(Designation.valueOf(designation)); //Designation enum is not yet implemented , so uncomment it after imiplementation
							admin.addProfessor(professor);
							break;
						}
				//for deleting professor
				case 6: {
							System.out.print("Enter ProfessorId: ");
							int ProfessorId = scanner.nextInt();
							try {
								admin.removeProfessor(ProfessorId);
							} catch (ProfessorNotFoundException pe) {
								logger.error("Exception raised" + pe.getMessage());
							}
							break;
						}
				case 7: {
							Professor professor = new Professor();
							System.out.print("Enter username: ");
							String username = scanner.next();
							professor.setUsername(username);
							System.out.print("Enter password: ");
							String passwordHash = scanner.next();
							professor.setPasswordHash(passwordHash);
							System.out.print("Enter name: ");
							String name = scanner.next();
							professor.setName(name);
							System.out.print("Enter address: ");
							String address = scanner.next();
							professor.setAddress(address);
							System.out.print("Enter contact number: ");
							String contactNo = scanner.next();
							professor.setContactNo(contactNo);
							System.out.print("Enter role: ");
							String role = scanner.next();
							professor.setRole(Role.stringToName(role));
							System.out.print("Enter ProfessorId: ");
							int ProfessorId = scanner.nextInt();
							professor.setProfessorId(ProfessorId);
							try {
								admin.updateProfessor(professor);
							} catch (ProfessorNotFoundException pe) {
								logger.error("Exception raised" + pe.getMessage());
							}
							break;
						}
				case 8: {
							System.out.print("Enter StudentId: ");
							int studentId = scanner.nextInt();
							System.out.println(admin.approveStudentRegistration(studentImplementation.viewStudentDetails(studentId).getStudentId()));
							break;
						}
				case 9: {
							admin.viewAllStudents();
							break;
						}
				case 10: {
							admin.viewAllProfessors();
							break;
						}
				case 11: {
							admin.viewAllCourses();
							break;
						}
				case 12: {    
							admin.allocatePendingCourses();
							break;
						}
				case 14: {
							SemesterRegistration semesterRegistration = new SemesterRegistration();
							Challan challan = admin.generateChallan(semesterRegistration);
							break;
						}
				default: System.out.println("Invalid Input");
			}
		}
	}

}
