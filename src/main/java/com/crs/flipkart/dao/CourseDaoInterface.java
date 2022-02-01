/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.StudentRegisteredCourses;

/**
 * @author HP
 * Interface for Course Dao Operations
 *
 */
public interface CourseDaoInterface {
	
	 /**
	 * Method for Adding Course to Catalog DataBase
	 * @param course the course object whose details need to be added
 */
	public void addCourse(Course course);

	/**
	 * Method for Deleting Course to DataBase
	 * @param courseid Of subject
	 */
    public void deleteCourse(int courseId);

    /**
   	 * Method for Updating Course to DataBase
   	 * @param course the course object which contains course updated details
   	 */
    public void updateCourse(Course course);
    
    /**
	 * Method for Getting Student Registered Courses
	 * @param studentId of student
	 * returns List of courses registered by student
	 */
    public StudentRegisteredCourses getStudentRegisteredCourses(int studentId);
    
    /**
   	 * Method for Getting Course From CourseId
   	 * @param courseId of course
   	 * returns Course object consisting course details
   	 */
    public Course getCourseFromCourseId(int courseId);
    
    /**
	 * Method for Getting All Courses
	 * returns list of courses
	 */
    public ArrayList<Course> getAllCourse();
}
