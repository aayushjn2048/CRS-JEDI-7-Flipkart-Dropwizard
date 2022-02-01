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
}