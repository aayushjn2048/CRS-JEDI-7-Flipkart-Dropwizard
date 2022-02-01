/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * Exception to check if the user found or not
 * @author JEDI-03
 *
 */
public class UserNotFoundException extends Exception{
	public UserNotFoundException()
	{
		System.out.println("User not found!!!");
	}
}
