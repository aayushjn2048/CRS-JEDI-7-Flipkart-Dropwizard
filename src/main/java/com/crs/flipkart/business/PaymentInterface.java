/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;

/**
 * @author HP
 * Interface for PaymentImplementation 
 * 
 */
public interface PaymentInterface {
	
	/**
	 * Method to calculate the total fee for student
	 * @param StudentId
	 * @return totalFee
	 */
	public int calculateFees(int studentId);
}
