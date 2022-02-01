/**
 * 
 */
package com.crs.flipkart.bean;

import java.util.ArrayList;

/**
 * @author HP
 *
 */
public class StudentCourseChoice {
	private int studentId;
	private ArrayList<Course> courses;
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the courses
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
}
