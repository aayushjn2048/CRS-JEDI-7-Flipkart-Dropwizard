/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.utils.DBUtils;

/**
 * 
 * Dao Class Operations for Course
 * 
 */
public class CourseDaoImplementation implements CourseDaoInterface{
	private static Logger logger = Logger.getLogger(CourseDaoImplementation.class);
	private static CourseDaoImplementation instance = null;
    private Connection conn = DBUtils.getConnection();

    private CourseDaoImplementation() {
    }

    public static CourseDaoImplementation getInstance() {
        if (instance == null) {
            synchronized (CourseDaoImplementation.class) {
                instance = new CourseDaoImplementation();
            }
        }
        return instance;
    }

    /**
	 * Method for Adding Course to Catalog DataBase
	 * @param course the course object whose details need to be added
	 */
    @Override
    public void addCourse(Course course) {
    	//logger.info("Instance creation of Adding Course in Dao class");
        try {
            PreparedStatement stmt = null;
            String sql = "INSERT INTO courseCatalog(name) values(?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getName());
            int rs = stmt.executeUpdate();
        } catch (SQLException se) {
            // Handle errors for JDBC
        	logger.error("Exception raised"+se.getMessage());
        } catch (Exception e) {
            // Handle errors for Class.forName
        	logger.error("Exception raised"+e.getMessage());
        }
    }

    /**
	 * Method for Deleting Course to DataBase
	 * @param courseid Of subject
	 */
    @Override
    public void deleteCourse(int courseId) {
    	//logger.info("Instance creation of Deleting Course in Dao class");
        try {
            PreparedStatement stmt = null;
            String sql = "delete from courseCatalog where courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseId);
            int rs = stmt.executeUpdate();

        } catch (Exception se) {
            // Handle errors for JDBC
        	logger.error("Exception raised"+se.getMessage());
        }
    }

    /**
	 * Method for Updating Course to DataBase
	 * @param course the course object which contains course updated details
	 */
    @Override
    public void updateCourse(Course course) {
    	//logger.info("Instance creation of Updating Course in Dao class");
        try {
            PreparedStatement stmt = null;
            String sql = "UPDATE courseCatalog set name=? where courseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getCourseId());
            int rs = stmt.executeUpdate();
        } catch (Exception se) {
            // Handle errors for JDBC
        	logger.error("Exception raised"+se.getMessage());
        }
    }
    
    /**
	 * Method for Getting Student Registered Courses
	 * @param studentId of student
	 * returns List of courses registered by student
	 */
    @Override
   	public StudentRegisteredCourses getStudentRegisteredCourses(int studentId) {
   		// TODO Auto-generated method stub
    	//logger.info("Instance creation of Getting Student Registered Courses in Dao class");
   		try {
               PreparedStatement stmt = null;
               String sql = "select * from studentRegisteredDetails where studentId = ?";
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, studentId);
               ResultSet rs = stmt.executeQuery();
               StudentRegisteredCourses registeredCourses=new StudentRegisteredCourses();
               while(rs.next())
               {
               	registeredCourses.setStudentId(rs.getInt("studentId"));
               	registeredCourses.setCourseId1(rs.getInt("courseId1"));
               	registeredCourses.setCourseId2(rs.getInt("courseId2"));
               	registeredCourses.setCourseId3(rs.getInt("courseId3"));
               	registeredCourses.setCourseId4(rs.getInt("courseId4"));
               }
               return registeredCourses;
           } catch (Exception se) {
               // Handle errors for JDBC
        	   logger.error("Exception raised"+se.getMessage());
           }
   		return null;
   	}

    /**
   	 * Method for Getting Course From CourseId
   	 * @param courseId of course
   	 * returns Course object consisting course details
   	 */
   	@Override
   	public Course getCourseFromCourseId(int courseId) {
   	//logger.info("Instance creation of Getting Course From CourseId in Dao class");
           try {
               PreparedStatement stmt = null;
               String sql = "SELECT * from course where courseId = ?";
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, courseId);
               ResultSet rs = stmt.executeQuery();
               while(rs.next()){
                   Course c = new Course();
                   c.setCourseId(rs.getInt("courseId"));
                   c.setName(rs.getString("name"));
                   c.setProfessorId(rs.getInt("professorId"));
                   c.setCourseFee(rs.getInt("courseFee"));
                   return c;
               }
           } catch (Exception se) {
               // Handle errors for JDBC
        	   logger.error("Exception raised"+se.getMessage());
           }
           return null;
   	}
   	
   	/**
	 * Method for Getting All Courses
	 * returns list of courses
	 */
   	@Override
	public ArrayList<Course> getAllCourse() {
		// TODO Auto-generated method stub
   	//logger.info("Instance creation of Getting All Courses in Dao class");
		try {
			ArrayList<Course> clist = new ArrayList<Course>();
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM course";
			stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next()){
		            //Display values
				 	Course c = new Course();
				 	c.setCourseId(rs.getInt("courseId"));
				 	c.setName(rs.getString("name"));
				 	c.setProfessorId(rs.getInt("professorId"));
				 	c.setCourseFee(rs.getInt("courseFee"));
				 	clist.add(c);
		         }
			 return clist;
			}
			catch(Exception e){
				logger.error("Exception raised"+e.getMessage());
			}
		return null;
	}
}
