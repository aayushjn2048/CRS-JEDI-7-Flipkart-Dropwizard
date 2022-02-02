/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * Exception to check if GradeCard not published 
 * @author JEDI-03
 *
 */
public class GradeCardNotPublishedException extends Exception{
	public GradeCardNotPublishedException()
	{
		System.out.println("GradeCard is not published yet");
	}
	
	public String getMessage() {
		return "Grade card is not published yet !";
	}
}