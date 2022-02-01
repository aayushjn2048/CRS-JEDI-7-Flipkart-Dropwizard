/**
 * 
 */
package com.crs.flipkart.bean;

import java.util.ArrayList;

/**
 * @author HP
 *
 */
public class GradeCard {
	private int studentId;
	private int semester;
	private float sgpa;
	private boolean published;
	private ArrayList<Course> registeredCourse;
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
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}
	/**
	 * @return the sgpa
	 */
	public float getSgpa() {
		return sgpa;
	}
	/**
	 * @param sgpa the sgpa to set
	 */
	public void setSgpa(float sgpa) {
		this.sgpa = sgpa;
	}
	/**
	 * @return the published
	 */
	public boolean isPublished() {
		return published;
	}
	/**
	 * @param published the published to set
	 */
	public void setPublished(boolean published) {
		this.published = published;
	}
	/**
	 * @return the registeredCourse
	 */
	public ArrayList<Course> getRegisteredCourse() {
		return registeredCourse;
	}
	/**
	 * @param registeredCourse the registeredCourse to set
	 */
	public void setRegisteredCourse(ArrayList<Course> registeredCourse) {
		this.registeredCourse = registeredCourse;
	}
}
