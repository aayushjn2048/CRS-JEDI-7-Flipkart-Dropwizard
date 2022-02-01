/**
 * 
 */
package com.crs.flipkart.utils;

import java.util.Random;

/**
 * 
 * @author JEDI-03
 * ServiceUtils class
 * 
 */
public class ServiceUtils {
	
	/**
	 * Method for creating userId
	 * @return userId
	 */
	public static int createUserId()
	{
		Random rand = new Random();
		int userId = rand.nextInt(1000000)+100000;
		return userId;
	}
}
