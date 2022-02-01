/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * Exception class for when a course cannot be removed from the course catalogue
 */
public class CourseNotDeletedException extends Exception{
    private int courseCode;

    /**
     * constructor for the exception class
     * @param courseCode --> this is the ID of the course which is cannot be removed
     */
    public CourseNotDeletedException () {
       System.out.println("Course is not deleted");
    }
    
    
}