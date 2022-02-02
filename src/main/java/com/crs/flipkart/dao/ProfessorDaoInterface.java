/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;
import java.util.Map;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.exceptions.CourseNotFoundException;

/**
 * @author HP
 *
 */
public interface ProfessorDaoInterface {
	public Map<Integer,ArrayList<Student>> viewEnrolledStudents(int professorId);
	
	public boolean selectCourse(int professorId, int courseId);
	public ArrayList<Course> viewAvailableCourses();
	public Boolean assignGrade(int studentId, int courseId,float grade) throws CourseNotFoundException;
}
