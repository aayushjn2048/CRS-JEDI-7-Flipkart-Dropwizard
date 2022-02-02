/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * Exception class for when a course cannot be removed from the course catalogue
 */
public class CourseNotDeletedException extends Exception{
    private int courseCode;

private int courseId;
	
	public CourseNotDeletedException(int courseId)
	{
		setCourseId(courseId);
	}
	
	@Override
	public String getMessage() {
		return "The course you are trying to remove does not exist. CourseId :"+String.valueOf(courseId);
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
}