/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.dao.AdminDaoInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.CourseDaoImplementation;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.exceptions.CourseNotDeletedException;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.DatabaseException;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author HP
 *
 */
public class CourseImplementation implements CourseInterface{
	
	private static CourseImplementation instance = null;
	private CourseDaoInterface courseDaoImplementation = CourseDaoImplementation.getInstance();

	private CourseImplementation(){}
	
	public static CourseImplementation getInstance(){
		if(instance==null){
			synchronized (CourseImplementation.class){
				instance = new CourseImplementation();
			}
		}
		return instance;
	}
	
	public void removeCourse(int courseId) throws CourseNotDeletedException
	{
		AdminDaoInterface admin = new AdminDaoOperation();
		try {
			if(admin.deleteCourse(courseId))
			{
				System.out.println("Course details deleted from the database");
			}
			else
			{
				throw new CourseNotFoundException();
			}
		} catch (DatabaseException | CourseNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addCourse(Course course)
	{
		AdminDaoInterface admin = new AdminDaoOperation();
		Boolean ans = null;
		try {
			ans = admin.addCourse(course);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ans)
			System.out.println("Course added successfully!!!");
		else
			System.out.println("Error while executing operation");
	}
	public void updateCourse(Course course) throws CourseNotFoundException{

		AdminDaoInterface admin = new AdminDaoOperation();
		try {
			if(admin.updateCourse(course))	//we need to change in the menu so that users can only add the details they can update
				System.out.println("Course updated successfully!!!");
			else
			{
				throw new CourseNotFoundException();
			}
		} catch (DatabaseException | CourseNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Course> getAllCourses() {
		StudentDaoInterface studao = new StudentDaoOperation();
		return studao.viewAllCourses();
	}

	@Override
	public Course getCourseFromCatalog(int courseId) throws CourseNotFoundException{
		// TODO Auto-generated method stub
		ArrayList<Course> courseList = getAllCourses();
		if(courseList.size()==0)
		{
			throw new CourseNotFoundException();
		}
		else
		{
			for(Course course:courseList)
			{
				if(course.getCourseId() == courseId)
				{
					return course;
				}
			}	
			return null;
			
		}
	}
}
