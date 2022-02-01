/**
 * 
 */
package com.crs.flipkart.business;

/**
 * @author HP
 *
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.crs.flipkart.application.CRSApplication;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Notification;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.constants.Role;
import com.crs.flipkart.dao.AdminDaoInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.CourseDaoImplementation;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.utils.ServiceUtils;

/**
 * @author HP
 * PaymentImplementation class
 * 
 */
public class PaymentImplementation implements PaymentInterface {

	private CourseDaoInterface courseDaoImplementation = CourseDaoImplementation.getInstance();

	/**
	 * Method to calculate the total fee for student
	 * @param StudentId
	 * @return totalFee
	 */
	@Override
	public int calculateFees(int studentId) {
		// TODO Auto-generated method stub
		StudentRegisteredCourses studentRegistereCourses = courseDaoImplementation
				.getStudentRegisteredCourses(studentId);
		int totalFee = 0;
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId1())
				.getCourseFee();
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId2())
				.getCourseFee();
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId3())
				.getCourseFee();
		totalFee += courseDaoImplementation.getCourseFromCourseId(studentRegistereCourses.getCourseId4())
				.getCourseFee();
		return totalFee;
	}

}