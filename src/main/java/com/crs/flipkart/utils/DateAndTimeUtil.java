/**
 * 
 */
package com.crs.flipkart.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateAndTimeUtil class
 * 
 */
public class DateAndTimeUtil {
	
	/**
	 * Method to display login date and time
	 */
	public static void loginDisplayDateAndTime()
	{
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println("\033[0;1m" +"--------------------------------------------------------------");
		   System.out.println("\t\tLogin time : "+dtf.format(now));  
		   System.out.println("--------------------------------------------------------------\n"+"\033[0m");
	}
	
	/**
	 * Method to display logout date and time
	 */
	public static void logoutDisplayDateAndTime()
	{
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println("\033[0;1m" +"--------------------------------------------------------------");
		   System.out.println("\t\tLogout time : "+dtf.format(now));  
		   System.out.println("--------------------------------------------------------------\n"+"\033[0m");
	}

}
