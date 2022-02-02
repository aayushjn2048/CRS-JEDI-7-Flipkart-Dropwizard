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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L+2;
	private int courseId;
	private String message;
	
	public CourseNotFoundException(int courseId)
	{
		setCourseId(courseId);
		System.out.println(getMessage());
	}
	
	public CourseNotFoundException(String string) {
		System.out.println(string);
	}

	@Override
	public String getMessage() {
		return "The course you are trying to update does not exist. CourseId :"+String.valueOf(courseId);
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	 public String getMessage2() {
		 return "Course is not available ";
	 }

	public void setMessage(String message) {
		this.message = message;
	}
	 
}
