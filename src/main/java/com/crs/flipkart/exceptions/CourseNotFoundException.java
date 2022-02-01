/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * Exception to check if course is available in catalog
 * @author JEDI-03
 *
 */
public class CourseNotFoundException extends Exception{
	
	public CourseNotFoundException()
	{
		System.out.println("No courses found");
	}
	
}
