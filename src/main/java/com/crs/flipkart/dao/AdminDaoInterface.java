/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;
import java.util.Map;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Notification;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.exceptions.DatabaseException;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author HP
 * Interface for Admin Dao Operations
 *
 */
public interface AdminDaoInterface {
	
	/**
	 * Method for Adding Course to DataBase
	 * @param course the course object whose details to be added
	 * returns whether course is added successfully or not
	 */
	public Boolean addCourse(Course course) throws DatabaseException;;
	
	/**
	 * Method for Deleting Course to DataBase
	 * @param courseId the id of course which is to be deleted
	 * returns whether course is deleted successfully or not
	 */
	public Boolean deleteCourse(int courseId) throws DatabaseException;;
	
	/**
	 * Method for Updating Course to DataBase
	 * @param course contains the details of updated course
	 * returns whether course is updated or not
	 */
	public Boolean updateCourse(Course course) throws DatabaseException;;
	
	/**
	 * Method for Adding Professor to DataBase
	 * @param professor the professor object whose details to be added
	 * returns whether AddingProfessor is successfully or not
	 */
	public Boolean addProfessor(Professor professor) throws DatabaseException;;
	
	/**
	 * Method for Removing Professor to DataBase
	 * @param professorId the id of professor whose details need to be removed
	 * returns whether removal of Professor is successful or not
	 */
	public Boolean removeProfessor(int professorId) throws DatabaseException;;
	
	/**
	 * Method for Updating Professor to DataBase
	 * @param professorOld object, professorNew object
	 * returns whether updating professor is succesfully or not
	 */
	public Boolean updateProfessor(Professor professorOld,Professor professorNew) throws DatabaseException;;
	
	/**
	 * Method for AddStudentCourseChoices to DataBase
	 * @param studentId and arrayList of courseList 
	 * returns whether addStudentCourseChoices is succesfully or not
	 */
	public Boolean addStudentCourseChoices(int studentId,ArrayList<Integer> courseList);
	
	/**
	 * Method for updateAllocatedStudentCoursesto DataBase
	 * @param list contains student id and its choices of courses 
	 * returns whether updateAllocatedStudentCourses is successfully or not
	 */
	public Boolean updateAllocatedStudentCourses(Map<Integer,ArrayList<Integer>> list);
	
	/**
	 * Method for Getting All Course Details from database
	 * returns Arraylists of course
	 */
	public ArrayList<Course> getAllCourseDetails();
	
	/**
	 * Method for Get All Course Choices 
	 * returns Map of studentid with his choices of coursesid
	 */
	public Map<Integer,ArrayList<Course>> getAllCourseChoices();
	
	/**
	 * Method for Getting all the professor details
	 * returns list of professor details
	 */
	public ArrayList<Professor> getAllProfessorDetails();
	
	/**
	 * Method for Getting all the Course details
	 * returns list of Course details
	 */
	public ArrayList<Course> viewAllCourses();
	
	/**
	 * Method for studentAlreadyRegistered 
	 * @param studentId of student
	 * returns whether studentAlreadyRegistered is succesfully or not
	 */
	public boolean studentSelfRegistration(int studentId);
	
	/**
	 * Method for studentAlreadyRegistered 
	 * @param studentId of student
	 * returns Boolean object
	 */
	public Boolean studentAlreadyRegistered(int studentId);
	
	/**
	 * Method for updatePaymentStatus to DataBase
	 * @param studentList the list of student Ids
	 * returns whether updatePaymentStatus is succesfully or not
	 */
	public Boolean updatePaymentStatus(ArrayList<Integer> studentList);
	public void generateNotification(Notification notification);
	public ArrayList<Notification> viewNotifications(int userId);
}
