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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L+1;
	
	private String message = "User not found!!!";

	public UserNotFoundException()
	{
		System.out.println("User not found!!!");
	}
	
	public UserNotFoundException(String msg)
	{
		System.out.println(msg);
		setMessage(msg);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
