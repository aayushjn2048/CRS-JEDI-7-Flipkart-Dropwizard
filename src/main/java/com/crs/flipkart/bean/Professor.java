/**
 * 
 */
package com.crs.flipkart.bean;

import com.crs.flipkart.constants.Designation;

/**
 * @author HP
 *
 */
public class Professor extends User{
	private int professorId;
	private Designation designation;
	/**
	 * @return the designation
	 */
	public Designation getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(Designation designation) {
		this.designation = designation;
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
	
}
