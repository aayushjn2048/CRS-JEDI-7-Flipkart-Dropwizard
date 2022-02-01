/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;
import java.util.Map;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;

/**
 * @author HP
 * Interface for Professor Dao Operations
 *
 */
public interface ProfessorDaoInterface {
	
	/**
	 * Method to view list of enrolled Students using SQL Commands
	 * @param: professorId the id of professor
	 * @return: return the enrolled students for the corresponding professor and course code.
	 */
	public Map<Integer,ArrayList<Student>> viewEnrolledStudents(int professorId);
	
	/**
	 * Method to get Courses by Professor Id using SQL Commands
	 * @param professorId the id of professor
	 * @param courseId the id of course
	 * @return get the courses offered by the professor.
	 */
	public boolean selectCourse(int professorId, int courseId);
	
	/**
	 * Method to view all the available courses using SQL Commands
	 * @return: return the list of all the available courses
	 */
	public ArrayList<Course> viewAvailableCourses();
	
	/**
	 * Assign grades to student using SQL commands
	 * @param studentId the student id
	 * @param courseId the course id
     * @param grade the grade to be assigned
	 * @return Grades to student
	 */
	public Boolean assignGrade(int studentId, int courseId,float grade);
}
