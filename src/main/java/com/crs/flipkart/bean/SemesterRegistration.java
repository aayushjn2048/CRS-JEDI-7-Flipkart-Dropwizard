/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author HP
 *
 */
public class SemesterRegistration {
	private int studentId;
	private int semesterNo;
	private String dateOfRegistration;
	private int totalFee;
	/**
	 * @return the totalFee
	 */
	public int getTotalFee() {
		return totalFee;
	}
	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
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
	/**
	 * @return the semesterNo
	 */
	public int getSemesterNo() {
		return semesterNo;
	}
	/**
	 * @param semesterNo the semesterNo to set
	 */
	public void setSemesterNo(int semesterNo) {
		this.semesterNo = semesterNo;
	}
	/**
	 * @return the dateOfRegistration
	 */
	public String getDateOfRegistration() {
		return dateOfRegistration;
	}
	/**
	 * @param dateOfRegistration the dateOfRegistration to set
	 */
	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
}
