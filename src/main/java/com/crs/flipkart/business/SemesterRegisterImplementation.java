/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Catalogue;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.exceptions.CourseAlreadyExists;
import com.crs.flipkart.exceptions.CourseNotDeletedException;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author TEST
 * SemesterRegisterImplementation class
 * 
 */
public class SemesterRegisterImplementation implements SemesterRegistration {

	/**
	 * Method for getting the register courses
	 * @return List of registered courses
	 */
	@Override
	public ArrayList<Course> registerCourses() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private CourseInterface courseImplementation=CourseImplementation.getInstance();
	
	/**
	 * Method for Adding the course
	 * @param course object
	 * @return StandardResponse object
	 * @throws CourseAlreadyExists 
	 */
	@Override
	public StandardResponse addCourse(Course course) throws CourseAlreadyExists {
		// TODO Auto-generated method stub
		courseImplementation.addCourse(course);
		return new StandardResponse();
	}

	/**
	 * Method for Removing the course
	 * @param courseId
	 * @throws CourseNotFoundException
	 * @return StandardResponse object
	 */
	@Override
	public StandardResponse removeCourse(int courseId) throws CourseNotDeletedException{
		// TODO Auto-generated method stub
		try {
			courseImplementation.removeCourse(courseId);
		} catch (CourseNotDeletedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new StandardResponse();
	}

	/**
	 * Method for Total amount of fee for courses
	 * @param courses available in the catalog
	 * @return final sum
	 */
	public int payFee(ArrayList<Course> courses) {
		// TODO Auto-generated method stub
		Catalogue catalogue=new Catalogue();
		int finalSum=0;
		for(Course course:courses) {
			finalSum+=catalogue.getCourseAmount(course.getCourseId());
		}
		return finalSum;
	}

}
