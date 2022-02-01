/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.bean.GradeCard;

/**
 * @author HP
 * Interface for GradeCard Dao Operations
 * 
 */
public interface GradeCardDaoInterface {
	
	 /**
		 * Method for GettingCard
		 * @param studentid of student
		 * @returns GradeCard of student
		 */
	public GradeCard getGradeCard(int studentId);
	
	/**
	 * Method for Getting Grade From CourseId
	 * @param Studentid of student
	 * @param courseId of course
	 * returns The grade
	 */
	public float getGradeFromCourseId(int studentId, int CourseId);
}
