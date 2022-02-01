/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author HP
 *
 */
public class Catalogue {
	private int courseId;
	private String courseName;
	private int courseAmount;
	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @param courseId the courseId for which amount to be returned
	 * @return the courseAmount
	 */
	public int getCourseAmount(int courseId) {
		return courseAmount;
	}
	/**
	 * @param courseAmount the courseAmount to set
	 */
	public void setCourseAmount(int courseAmount) {
		this.courseAmount = courseAmount;
	}
}
