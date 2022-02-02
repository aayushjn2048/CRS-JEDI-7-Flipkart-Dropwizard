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
import com.crs.flipkart.exceptions.CourseAlreadyExists;
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
				return;
			}
			else
			{
				throw new CourseNotDeletedException(courseId);
			}
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			return;
		}
	}
	public void addCourse(Course course) throws CourseAlreadyExists
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
			return;
		else
			throw new CourseAlreadyExists();
	}
	public void updateCourse(Course course) throws CourseNotFoundException{

		AdminDaoInterface admin = new AdminDaoOperation();
		try {
			if(admin.updateCourse(course))	//we need to change in the menu so that users can only add the details they can update
				return;
			else
			{
				throw new CourseNotFoundException(course.getCourseId());
			}
		} catch (DatabaseException e) {
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
			throw new CourseNotFoundException(courseId);
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
