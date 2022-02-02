/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.AdminImplementation;
import com.crs.flipkart.business.AdminInterface;
import com.crs.flipkart.business.StudentImplementation;
import com.crs.flipkart.business.UserImplementation;
import com.crs.flipkart.constants.Gender;
import com.crs.flipkart.constants.Role;
import com.crs.flipkart.exceptions.PasswordIsWeakException;

/**
 * @author HP
 *
 */
public class StudentSelfRegistration {

	public void selfRegistration()  {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("\n=====================STUDENT REGISTRATION=====================\n");
		
		System.out.print("Enter scholar no: ");
		int studentId = scanner.nextInt();
		
		System.out.print("Enter username: ");
		String username = scanner.next();
		
		System.out.print("Enter password: ");
		String passwordHash = scanner.next();
		
		System.out.print("Enter name: ");
		String name = scanner.next();
		
		System.out.print("Enter address: ");
		String address = scanner.next();
		
		System.out.print("Enter contact number: ");
		String contactNo = scanner.next();
		
		System.out.print("Enter Gender('MALE','FEMALE','OTHERS'): ");
		String gen=scanner.next();
		Gender gender = Gender.stringToGender(gen);

		
		AdminInterface adminimplementation = new AdminImplementation();
		if(adminimplementation.approveStudentRegistration(studentId)) {
			StudentImplementation studentImplementation = new StudentImplementation();
			
			Student user= new Student();
			user.setUserId(studentId);
			user.setUsername(username);
			user.setPasswordHash(passwordHash);
			user.setName(name);
			user.setAddress(address);
			user.setContactNo(contactNo);
			user.setGender(gender);
			user.setRole(Role.STUDENT);
			UserImplementation userImplementation = new UserImplementation();
			try {
				userImplementation.addUserdata(user);
			} catch (PasswordIsWeakException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Student is registerd succesfully");
		}
		else {
			System.out.println("Error occured while registering");
		}
		
	}
}
