/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentCourseChoice;

/**
 * @author HP
 * Interface for Student Dao Operations
 *
 */
public interface StudentDaoInterface {
	
	 /**
	 * Method to view all students from database
	 * @return List of student 
 */
	public ArrayList<Student> viewAllStudents();

	/**
	 * Method to view activate grade card for students
	 * @return true if grade card is activated for student, else false
 */
    public Boolean activateGradeCard();

    /**
	 * Method to view student from database
	 * @param studentId: studentId
	 * @return student: student object containing all the fields
	 */
    public Student viewStudentDetails(int studentId);
    
    /**
   	 * Method to view all courses from database
   	 * @return List of course 
   	 */
    public ArrayList<Course> viewAllCourses();
    
    /**
   	 * Method to add student to database
   	 * @param student: student object containing all the fields
   	 * @return true if student is added, else false
   	 */
    public boolean addStudentData(Student student);
    
    /**
	 * Method to Store student course choice to database
	 * @param studentCourseChoice StudentCourseChoice object containing all the fields
	 */
    public void storeStudentCourseChoice(StudentCourseChoice studentCourseChoice);
    
    /**
	 * Method to view student is registered or not.
	 * @param studentId: studentId
	 * @return true if student is already registered, else false
	 */
    public Boolean studentAlreadyRegistered(int studentId);
    
    /**
	 * Method to make payment succesfull.
	 * @param studentId: studentId
	 */
    public void makePaymentSuccessful(int studentId, String referenceNo);
    
    /**
	 * Method to Getting Payment status
	 * @param studentId: studentId
	 * @return payment status
	 */
    public String getPaymentStatus(int studentId);

    /**
	 * Method to update student's password.
	 * @param username the username of user
	 * @param oldPassword the old password of user
	 * @param newPassword the new password of user
	 * */
    public Boolean update(String username,String oldPassword,String newPassword);
    
    /**
	 * Method to check student Registered or not
	 * @param studentId
	 * @return Boolean object
	 * */
    public Boolean isStudentRegistered(int studentId);


}
