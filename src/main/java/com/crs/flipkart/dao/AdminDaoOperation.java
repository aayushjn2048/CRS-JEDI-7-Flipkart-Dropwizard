/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.crs.flipkart.application.CRSApplication;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Notification;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.constants.Designation;
import com.crs.flipkart.constants.Role;
import com.crs.flipkart.constants.SqlQueryConstants;
import com.crs.flipkart.exceptions.DatabaseException;

/**
 * @author HP
 * Dao Class Operations for Admin
 * 
 */
public class AdminDaoOperation implements AdminDaoInterface {
	
	private static Logger logger = Logger.getLogger(AdminDaoOperation.class);
	Connection conn = DBConnection.connectDB();

	/**
	 * Method for Adding Course to DataBase
	 * @param course the course object whose details to be added
	 * returns whether course is added successfully or not
	 */
	@Override
	public Boolean addCourse(Course course) throws DatabaseException{

		//add the course to 'course' table
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQueryConstants.ADD_COURSE_QUERY);
			stmt.setInt(1, course.getCourseId());
			stmt.setString(2, course.getName());
			stmt.setInt(3,course.getCourseFee());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
		} catch (SQLException se) {
			// Handle errors for JDBC
			throw new DatabaseException();
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
			return false;
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}

		//add (catalogId,courseId) to catalog table
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQueryConstants.ADD_COURSE_TO_CATALOG_QUERY);
			stmt.setInt(1, course.getCatalogId());
			stmt.setInt(2, course.getCourseId());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			logger.error("Exception raised" + se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}


		return false;
	}

	/**
	 * Method for Deleting Course to DataBase
	 * @param courseId the id of course which is to be deleted
	 * returns whether course is deleted successfully or not
	 */
	@Override
	public Boolean deleteCourse(int courseId) throws DatabaseException{
		// TODO Auto-generated method stub
		// logger.info("Instance creation of Deleting Course in Dao class");
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQueryConstants.DELETE_COURSE_QUERY);
			stmt.setInt(1, courseId);		//we can only pass string as argument in setString
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			throw new DatabaseException();
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
		
	}

	/**
	 * Method for Updating Course to DataBase
	 * @param course contains the details of updated course
	 * returns whether course is updated or not
	 */
	@Override
	//Considers the fact that courseId cannot be changed
	public Boolean updateCourse(Course course) throws DatabaseException{
		// logger.info("Instance creation of Updating Course in Dao class");
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = null;
			String sql = "UPDATE course SET ";
			if(course.getName()!=null)
				sql += " name = ?,";
			if(course.getCourseFee()!=-1)
				sql += " courseFee = ?,";
			if(course.getProfessorId() !=-1)
				sql += " professorId = ?,";
			sql = sql.substring(0, sql.length() - 1);
			sql += " WHERE courseId = ?;";
			stmt = conn.prepareStatement(sql);
			int count = 1;
			if(course.getName()!=null)
			{
				stmt.setString(count, course.getName());
				count++;
			}
			if(course.getCourseFee()!=-1)
			{
				stmt.setInt(count, course.getCourseFee());
				count++;
			}
			if(course.getProfessorId() !=-1)
			{
				stmt.setInt(count, course.getProfessorId());
				count++;
			}
			stmt.setInt(count, course.getCourseId());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			throw new DatabaseException();
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
	}
	
	/**
	 * Method for Getting All Professor Details 
	 * returns Arraylist of professor details
	 */
	@Override
	public ArrayList<Professor> getAllProfessorDetails()  {
		try {
			 
			 PreparedStatement stmt = null;
			 stmt = conn.prepareStatement(SqlQueryConstants.VIEW_PROFESSORS_QUERY);
			 ResultSet rs = stmt.executeQuery();
			 ArrayList<Professor> profList = new ArrayList<Professor>();
			 while(rs.next()){
		            //Display values
				 System.out.println("ID: "+rs.getInt("professorId"));
				 	Professor prof = new Professor();
				 	prof.setProfessorId(rs.getInt("professorId"));
				 	prof.setName(rs.getString("name"));
				 	prof.setDesignation(Designation.valueOf(rs.getString("designation").toUpperCase()));
				 	profList.add(prof);
		         }
			 return profList;
		}
		catch(Exception e){
			logger.error("Exception raised" + e.getMessage());
		}
		
		return null;
		
	}

	/**
	 * Method for Viewing All Courses Details 
	 * returns Arraylist of Courses details
	 */
	@Override
	public ArrayList<Course> viewAllCourses() {
		// logger.info("Instance creation of Getting Course Details in Dao class");
		try {
			ArrayList<Course> clist = new ArrayList<Course>();
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQueryConstants.VIEW_COURSES_QUERY);
			 ResultSet rs = stmt.executeQuery(SqlQueryConstants.VIEW_COURSES_QUERY);
			 while(rs.next()){
		            //Display values
				 	Course c = new Course();
				 	c.setCourseId(rs.getInt("courseId"));
				 	c.setName(rs.getString("name"));
				 	c.setProfessorId(rs.getInt("professorId"));	//handle if no professor is yet alloted
					c.setCourseFee(rs.getInt("courseFee"));
				 	clist.add(c);
		         }
			 return clist;
			}
			catch(Exception e){
				logger.error("Exception raised" + e.getMessage());
			}
		return null;
	}
	
	
	/**
	 * Method for Student Self Registration
	 * @param  studentId of Student
	 * returns whether studentselfregistration is succesful or not
	 */
	@Override
	public boolean studentSelfRegistration(int studentId) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM studentDetails WHERE studentId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()){
				 return !this.studentAlreadyRegistered(studentId);
		     }
			 return false;
			 
			} catch (SQLException e) {
				logger.error("Exception raised" + e.getMessage());
			}
		
		return false;
	}

	/**
	 * Method for Adding Professor to DataBase
	 * @param professor the professor object whose details to be added
	 * returns whether AddingProfessor is successfully or not
	 */
	@Override
	public Boolean addProfessor(Professor professor) {
		// TODO Auto-generated method stub
		// logger.info("Instance creation of Adding Professor in Dao class");
		PreparedStatement stmt = null;
		int val;
		// As per current schema this whole query is supposed to be run as a Transaction , but right now there are no concurrent queries and assumng connection doesn't break so left it simple.
		try {

			//Checting if username is already taken or not
			{
				String sql = "SELECT * FROM user WHERE username = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,professor.getUsername());
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					System.out.println("Username already Taken");
					return false;
				}
			}

			//adding user
			{
				stmt = conn.prepareStatement(SqlQueryConstants.ADD_USER_QUERY);
				stmt.setInt(1, professor.getProfessorId());
				stmt.setString(2, professor.getUsername());
				stmt.setString(3, professor.getPasswordHash());
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
			}

			//adding role
			{
				stmt = conn.prepareStatement(SqlQueryConstants.ADD_ROLE_QUERY);
				stmt.setInt(1, professor.getProfessorId());
				stmt.setString(2,"PROFESSOR");
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
			}

			//adding professor
			{
				stmt = conn.prepareStatement(SqlQueryConstants.ADD_PROFESSOR_QUERY);
				stmt.setInt(1, professor.getProfessorId());
				stmt.setString(2, professor.getName());
				stmt.setString(3, professor.getAddress());
				stmt.setString(4, professor.getGender().toString());
				stmt.setString(5, professor.getContactNo());
				stmt.setString(6, professor.getDesignation().toString());
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
			}
			return true;

		} catch (SQLException se) {
			// Handle errors for JDBC
			logger.error("Exception raised" + se.getMessage());
			return false;
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
			return false;
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
	}

	/**
	 * Method for Removing Professor to DataBase
	 * @param professorId the id of professor whose details need to be removed
	 * returns whether removal of Professor is successful or not
	 */
	@Override
	public Boolean removeProfessor(int professorId) throws DatabaseException{
		// TODO Auto-generated method stub
		// logger.info("Instance creation of Removing Professor in Dao class");
		PreparedStatement stmt = null;
		try {

			stmt = conn.prepareStatement(SqlQueryConstants.DELETE_PROFESSOR_QUERY);
			stmt.setInt(1, professorId);		
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;

			stmt = conn.prepareStatement(SqlQueryConstants.DELETE_USER_QUERY);
			stmt.setInt(1, professorId);		
			rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			
			stmt = conn.prepareStatement(SqlQueryConstants.DELETE_ROLE_QUERY);
			stmt.setInt(1, professorId);		
			rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			throw new DatabaseException();
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
		
	}

	/**
	 * Method for Updating Professor to DataBase
	 * @param professorOld object, professorNew object
	 * returns whether updating professor is succesfully or not
	 */
	@Override
	public Boolean updateProfessor(Professor professorOld,Professor professorNew) throws DatabaseException{
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE user SET username = ? ,password = ? ,name = ? ,address = ? ,contactNo = ? WHERE userId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, professorNew.getUsername());
			stmt.setString(2, professorNew.getPasswordHash());
			stmt.setString(3, professorNew.getName());
			stmt.setString(4, professorNew.getAddress());
			stmt.setString(5, professorNew.getContactNo());
			stmt.setInt(6, professorOld.getProfessorId());
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			
		} catch (SQLException se) {
			// Handle errors for JDBC
			throw new DatabaseException();
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
			return false;
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}

		/*
		 * We are not taking change in Designation for now
		 * if(!professorOld.getDesignation().equals(professorNew.getDesignation())) {
		 * 
		 * try { String sql =
		 * "UPDATE professor SET designation = ? WHERE professorId = ?"; stmt =
		 * conn.prepareStatement(sql); stmt.setString(1,
		 * professorNew.getDesignation().toString()); stmt.setInt(2,
		 * professorOld.getProfessorId()); int rs = stmt.executeUpdate(); if (rs == 0)
		 * return false; return true;
		 * 
		 * } catch (SQLException se) { // Handle errors for JDBC se.printStackTrace();
		 * return false; } catch (Exception e) { // Handle errors for Class.forName
		 * e.printStackTrace(); return false; } finally { // finally block used to close
		 * resources // nothing we can do//end finally try }
		 * 
		 * }
		 */
		
		return true;
		
	}

	/**
	 * Method for Geting All Course Details from database
	 * returns Arraylists of course
	 */
	@Override
	public ArrayList<Course> getAllCourseDetails() {
		ArrayList<Course> courseDetails = new ArrayList<Course>();
		try {
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM course WHERE professorId IS NOT NULL";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Course c = new Course();
				c.setCourseId(rs.getInt("courseId"));
				c.setName(rs.getString("name"));
				courseDetails.add(c);
			}
			return courseDetails;
		} catch (SQLException se) {
			// Handle errors for JDBC
			logger.error("Exception raised" + se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return null;
	}

	/**
	 * Method for Get All Course Choices 
	 * returns Map of studentid with his choices of coursesid
	 */
	@Override
	public Map<Integer, ArrayList<Course>> getAllCourseChoices() {
		Map<Integer, ArrayList<Course>> mp = new HashMap<>();
		try {
			ArrayList<Course> courseDetails = this.getAllCourseDetails();
			PreparedStatement stmt = null;
			Map<Integer,Course> courseDetailer = new HashMap<>();
			for(Course c: courseDetails)
				courseDetailer.put(c.getCourseId(), c);
			stmt = conn.prepareStatement("SELECT * FROM registrationDetails");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				int studentId = rs.getInt("studentId");
				ArrayList<Course> tmp = new ArrayList<Course>();
				for(int i=1;i<=6;i++)
					tmp.add(courseDetailer.get(rs.getInt("courseId"+i)));
				mp.put(studentId, tmp);
			}
			return mp;
		} catch (SQLException se) {
			// Handle errors for JDBC
			logger.error("Exception raised" + se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return null;
	}

	/**
	 * Method for AddStudentCourseChoices to DataBase
	 * @param studentId and arrayList of courseList 
	 * returns whether addStudentCourseChoices is succesfully or not
	 */
	@Override
	public Boolean addStudentCourseChoices(int studentId, ArrayList<Integer> courseList) {
		//include paymentStatus and payment number in this as well
		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO studentCourseChoices values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			for(int i=2;i<=7;i++)
				stmt.setInt(i, courseList.get(i-2));
			int rs = stmt.executeUpdate();
			if (rs == 0)
				return false;
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			logger.error("Exception raised" + se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
	}

	/**
	 * Method for updateAllocatedStudentCoursesto DataBase
	 * @param list contains student id and its choices of courses 
	 * returns whether updateAllocatedStudentCourses is successfully or not
	 */
	@Override
	public Boolean updateAllocatedStudentCourses(Map<Integer, ArrayList<Integer>> list) {
		// TODO Auto-generated method stub
		try {
			for(Map.Entry<Integer,ArrayList<Integer>> entry: list.entrySet())
			{
				String sql = "INSERT INTO studentRegisteredDetails values (?,?,?,?,?) ";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, entry.getKey());
				ArrayList<Integer> tmp = entry.getValue();
				for(int i=0;i<tmp.size();i++)
					stmt.setInt(i+2, tmp.get(i));
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
			}
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			logger.error("Exception raised" + se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
	}

	/**
	 * Method for studentAlreadyRegistered 
	 * @param studentId of student
	 * returns whether studentAlreadyRegistered is succesfully or not
	 */
	@Override
	public Boolean studentAlreadyRegistered(int studentId) {
		try {
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM student WHERE studentId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()){
				 return true;
		     }
			 return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception raised" + e.getMessage());
			}
		
		return true;
	}

	/**
	 * Method for updatePaymentStatus to DataBase
	 * @param studentList the list of student Ids
	 * returns whether updatePaymentStatus is succesfully or not
	 */
	@Override
	public Boolean updatePaymentStatus(ArrayList<Integer> studentList) {
		try {
			for(int i=0;i<studentList.size();i++)
			{
				String sql = "UPDATE registrationDetails SET paymentStatus = ? WHERE studentId = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "PENDING");
				stmt.setInt(2, studentList.get(i));
				int rs = stmt.executeUpdate();
				if (rs == 0)
					return false;
				else
				{
					Notification notification = new Notification();
					notification.setUserId(studentList.get(i));
					notification.setMessage("Course has been allocated!!! Next, you need to pay the course fee for the courses allocated to you.");
					Date date = new Date();
					notification.setDateTime(date);
					notification.setUserType(Role.STUDENT);
					generateNotification(notification);
				}
			}
			return true;
		} catch (SQLException se) {
			// Handle errors for JDBC
			logger.error("Exception raised" + se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return false;
	}

	@Override
	public void generateNotification(Notification notification) {
		if(notification.getUserType().toString().equals("STUDENT"))
		{
			try {
				String sql = "INSERT INTO notification(userId,message,dateTime) values (?,?,?) ";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1,notification.getUserId());
				stmt.setString(2, notification.getMessage());
				Timestamp timestamp = new Timestamp(notification.getDateTime().getTime());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				stmt.setTimestamp(3, timestamp);
				stmt.executeUpdate();
			} catch (SQLException se) {
				// Handle errors for JDBC
				logger.error("Exception raised" + se.getMessage());
			} catch (Exception e) {
				// Handle errors for Class.forName
				logger.error("Exception raised" + e.getMessage());
			} finally {
				// finally block used to close resources // nothing we can do//end finally try
			}
		}
	}

	@Override
	public ArrayList<Notification> viewNotifications(int userId) {
		try {
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM notification WHERE userId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			 ResultSet rs = stmt.executeQuery();
			 ArrayList<Notification> notifications = new ArrayList<Notification>();
			 while(rs.next()){
				 Notification noti = new Notification();
				 noti.setMessage(rs.getString("message"));
				 noti.setDateTime(new Date(rs.getTimestamp("dateTime").getTime()));
				 notifications.add(noti);
		     }
			 return notifications;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Exception raised" + e.getMessage());
			}
		return null;
	}
	

}
