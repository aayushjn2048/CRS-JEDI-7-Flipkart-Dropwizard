/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.exceptions.CourseAlreadyExists;
import com.crs.flipkart.exceptions.CourseNotDeletedException;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author TEST
 * Interface for SemesterRegistration Implementation
 * 
 */
public interface SemesterRegistration {
	
	/**
	 * Method for getting the register courses
	 * @return List of registered courses
	 */
	public ArrayList<Course> registerCourses();
	
	/**
	 * Method for Adding the course
	 * @param course object
	 * @return StandardResponse object
	 * @throws CourseAlreadyExists 
	 */
	public StandardResponse addCourse(Course course) throws CourseAlreadyExists; 
	
	/**
	 * Method for Removing the course
	 * @param courseId
	 * @throws CourseNotFoundException
	 * @return StandardResponse object
	 */
	public StandardResponse removeCourse(int courseId) throws CourseNotDeletedException;
	
	/**
	 * Method for Total amount of fee for courses
	 * @param courseList : Courses available in the catalog
	 * @return finalsum
	 */
	public int payFee(ArrayList<Course> courses);
}
