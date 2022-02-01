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
import com.crs.flipkart.bean.GradeCard;

/**
 * 
 *
* Class to implement Grade Card Operations
 */
public class GradeCardDaoOperation implements GradeCardDaoInterface{
	
	private static Logger logger = Logger.getLogger(GradeCardDaoOperation.class);
	
	private static GradeCardDaoOperation instance = null;
    private Connection conn = DBConnection.connectDB();
    private CourseDaoInterface courseDaoOperation = CourseDaoImplementation.getInstance();

    /**
	 * Default Constructor
	 */
    private GradeCardDaoOperation() {
    }

    /**
	 * Method to make GradeCardDaoOperation Singleton
	 * @return instance
	 */
    public static GradeCardDaoOperation getInstance() {
        if (instance == null) {
            synchronized (GradeCardDaoOperation.class) {
                instance = new GradeCardDaoOperation();
            }
        }
        return instance;
    }
	

    /**
	 * Method for GettingCard
	 * @param studentid of student
	 * @returns GradeCard of student
	 */
    @Override
	public GradeCard getGradeCard(int studentId) {
    	//logger.info("Instance creation for getting grade card in Dao class");
		try {
			PreparedStatement stmt = null;
			String sql = "select * from gradeCard where studentId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			sql = "select * from student where studentId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs1 = stmt.executeQuery();
			GradeCard gradeCard = new GradeCard();
			while(rs1.next())
				gradeCard.setPublished(rs1.getInt("gradeCardVisibility")==1);
			float cgpa = (float)0.0;
			ArrayList<Course> courseList = new ArrayList<>();
			gradeCard.setStudentId(studentId);
			while(rs.next())
			{
				cgpa += rs.getFloat("grade");
				courseList.add(courseDaoOperation.getCourseFromCourseId(rs.getInt("courseId")));
			}
			gradeCard.setSgpa((float)(cgpa/4.0));
			gradeCard.setRegisteredCourse(courseList);
			return gradeCard;
			
		} catch (SQLException se) {
			// Handle errors for JDBC
			logger.error("Exception raised"+se.getMessage());
		}  finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		
		return null;
	}

    /**
	 * Method for Getting Grade From CourseId
	 * @param Studentid of student
	 * @param courseId of course
	 * returns The grade
	 */
	public float getGradeFromCourseId(int studentId, int courseId) {
		//logger.info("Instance creation for getting grade from courseId in Dao class");
		 try {
	            PreparedStatement stmt = null;
	            String sql = "select grade from gradeCard where studentId = ? AND courseId=?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, studentId);
	            stmt.setInt(2, courseId);
	            ResultSet rs = stmt.executeQuery();
	            float grade = 0;
	            while(rs.next()){
					grade=rs.getFloat("grade");

				}
	            return grade;
	        } catch (SQLException se) {
	            // Handle errors for JDBC
	        	logger.error("Exception raised"+se.getMessage());
	        }
		return 0;
	}

}
