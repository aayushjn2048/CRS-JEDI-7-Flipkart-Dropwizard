/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author HP
 *
 */
public class Course {
	private int courseId;
	private String name = null;
	private int professorId = -1;
	private int courseFee = -1;
	private int catalogId = -1;

	/**
	 * @return the catalogId
	 */
	public int getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId the catalogId to set
	 */
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * @return the courseFee
	 */
	public int getCourseFee() {
		return courseFee;
	}
	/**
	 * @param courseFee the courseFee to set
	 */
	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the professorId
	 */
	public int getProfessorId() {
		return professorId;
	}
	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	/**
	 * @return the offeredSemester
	 */
}
