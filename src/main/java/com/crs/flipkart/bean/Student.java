/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author HP
 *
 */
public class Student extends User {
	private int studentId;
	private Boolean gradeCardVisibility;
	/**
	 * @return the gradeCardVisibility
	 */
	public Boolean getGradeCardVisibility() {
		return gradeCardVisibility;
	}

	/**
	 * @param gradeCardVisibility the gradeCardVisibility to set
	 */
	public void setGradeCardVisibility(Boolean gradeCardVisibility) {
		this.gradeCardVisibility = gradeCardVisibility;
	}

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
}
