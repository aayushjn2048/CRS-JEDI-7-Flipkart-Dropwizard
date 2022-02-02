/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author utkarsh.chauhan
 *
 */
public class CourseAlreadyExists extends Exception{
	
	public CourseAlreadyExists() {}
	
	@Override
	public String getMessage() 
	{
		return "The course already exists";
	}

}
