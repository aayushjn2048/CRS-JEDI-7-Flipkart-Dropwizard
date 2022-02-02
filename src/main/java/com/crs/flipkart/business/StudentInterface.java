/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentCourseChoice;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.GradeCardNotPublishedException;

/**
 * @author HP
  * Interface for student Operations
 * 
 */
public interface StudentInterface {
	public void addStudentdata(Student student);
	
	/**
	 * Method to view all student details
	 * @return list of student objects 
	 */
	 public ArrayList<Student> viewStudentData();
	 
	 /**
		 * Method to view  student details
		 * @param StudentId
		 * @return student object 
		 */
	 public Student viewStudentDetails(int studentId);
	 
	 /**
		 * Method for Selecting Courses
		 * @param StudentId
		 * @return StudentCourseChoice object
	 * @throws CourseNotFoundException 
		 */
	 public StudentCourseChoice selectCourses(int studentId,ArrayList<Integer> id) throws CourseNotFoundException;
	 
	 /**
		 * Method to display course catalog
	*/
	 public void displayCourseCatalog();
	 
	 /**
		 * Method to display grade card
		 * @param StudentId
	 * @return 
		 * @throws GradeCardNotPublishedException
	*/
	 public GradeCard displayGradeCard(int studentId) throws GradeCardNotPublishedException;
	 
	 /**
		 * Method for checking student registered or not
		 * @param StudentId
		 * @return boolean object
		 */
	 public Boolean studentAlreadyRegistered(int studentId);
	 
	 /**
		 * Method for making payment successful
		 * @param StudentId
		 * @param referenceNo
	*/
	 public void makePaymentSuccessful(int studentId, String referenceNo);
	 
	 /**
		 * Method to get the payment status
		 * @param StudentId
		 * @param payment status 
	 */
	 public String getPaymentStatus(int studentId);
	 
	 /**
		 * Method for checking student registered or not
		 * @param StudentId
		 * @return boolean object
		 */
	 public Boolean isStudentRegistered(int studentId);
}
