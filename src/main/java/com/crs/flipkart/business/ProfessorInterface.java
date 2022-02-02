/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.exceptions.CourseNotFoundException;

/**
 * @author HP
 *
 */
public interface ProfessorInterface {
	
	public  ArrayList<Professor> viewProfessorData();
	public  void addProfessordata(Professor professor);
	public  boolean removeProfessordata(int professorId);
	public  void updateProfessordata(Professor professor);
	public ArrayList<Course> viewAvailableCourses() throws CourseNotFoundException;
	
	public ArrayList<Student> viewEnrolledStudents(int professorid, int courseid);
	
	public void selectCourse(int professorId, int courseId) throws CourseNotFoundException ;
	
	public void assignGrade(int studentId, int courseId, float grade) throws CourseNotFoundException;
}
