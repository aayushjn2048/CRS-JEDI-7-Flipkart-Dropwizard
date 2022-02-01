/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.constants.SqlQueryConstants;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author HP
 * Dao Class Operations for Professor
 * 
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {

	Connection conn = DBConnection.connectDB();
	private static Logger logger = Logger.getLogger(ProfessorDaoOperation.class);
	
	 private static volatile ProfessorDaoOperation instance = null;

		private ProfessorDaoOperation(){}
	    /**
	     * Singleton pattern to get only one instance of the class
	     * @return Instance of the class
	     */
	    public static ProfessorDaoOperation getInstance() {
	        if (instance == null) {
	            synchronized (ProfessorDaoOperation.class) {
	                instance = new ProfessorDaoOperation();
	            }
	        }
	        return instance;
	    }
	/**
	 * Method to get Courses by Professor Id using SQL Commands
	 * @param professorId the id of professor
	 * @param courseId the id of course
	 * @return get the courses offered by the professor.
*/
	@Override
	public boolean selectCourse(int professorId, int courseId) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBConnection.connectDB();
			PreparedStatement stmt = null;
			String sql = SqlQueryConstants.SELECT_COURSES_QUERY;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, courseId);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()){
				 PreparedStatement stmt1 = null;
				 String sqlUpdate = SqlQueryConstants.SELECT_COURSES_QUERY1;
				 stmt1=conn.prepareStatement(sqlUpdate);
				System.out.println("Course Available");
				 stmt1.setInt(1,professorId);
				 stmt1.setInt(2,courseId);
				 
				 int r=stmt1.executeUpdate();
				
				 return true; 
				 //System.out.println("Wow: "+rs.getInt("courseId"));
		     }
			 
			 	return false;
			 	
			}
			catch(Exception e){
				logger.error("Exception raised" + e.getMessage());
			}
		return false;
		
	}
	
	/**
	 * Method to view list of enrolled Students using SQL Commands
	 * @param: professorId the id of professor
	 * @return: return the enrolled students for the corresponding professor and course code.
*/

	@Override
	public Map<Integer,ArrayList<Student>> viewEnrolledStudents(int professorid) {
		// TODO Auto-generated method stub
		try {
			// System.out.println("hello");
			PreparedStatement stmt = null;
			String sql = "SELECT * FROM course WHERE professorId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, professorid);
			ResultSet rs = stmt.executeQuery();
			
			Map<Integer,ArrayList<Student>> registered = new HashMap<>();
			while(rs.next()){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				 
				ArrayList<Student> registeredstudents = new ArrayList<Student>();
				int courseid=rs.getInt("courseId");
		 		PreparedStatement stmt1 = null;
				String sql1 = SqlQueryConstants.VIEW_ENROLLED_STUDENTS_QUERY1;
				stmt1 = conn.prepareStatement(sql1);
				 ResultSet rs1 = stmt1.executeQuery(sql1);
				 while(rs1.next()) {
					
					 int courseid1=rs1.getInt("courseId1");
					 int courseid2=rs1.getInt("courseId2");
					 int courseid3=rs1.getInt("courseId3");
					 int courseid4=rs1.getInt("courseId4");
					
					 if(courseid1==courseid || courseid2==courseid || courseid3==courseid || courseid4==courseid) {
						
						 temp.add(rs1.getInt("studentId"));
							 
					 }
				 }
				for(int i=0;i<temp.size();i++) {
					int k=temp.get(i);
					PreparedStatement stmt2 = null;
					String sql2 = SqlQueryConstants.VIEW_ENROLLED_STUDENTS_QUERY2;
					stmt2 = conn.prepareStatement(sql2);
					stmt2.setInt(1, k);
					ResultSet rs3 = stmt2.executeQuery();
					Map<Student,Integer> check = new HashMap<>();
					while(rs3.next()) {
						Student stu = new Student();
						stu.setStudentId(rs3.getInt("studentId"));
						stu.setName(rs3.getString("name"));
						stu.setContactNo(rs3.getString("contactNo"));
							registeredstudents.add(stu);
							check.put(stu,1);
						
					}			
				}
					
				
				registered.put(courseid,registeredstudents );
			}
			 return registered;
		}
		catch(Exception e){
			logger.error("Exception raised" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * Assign grades to student using SQL commands
	 * @param studentId the student id
	 * @param courseId the course id
     * @param grade the grade to be assigned
	 * @return Grades to student
*/
	public Boolean assignGrade(int studentId, int courseId,float grade) {
		try {
			PreparedStatement stmt = null;
			
			String sql = SqlQueryConstants.ASSIGN_GRADES_QUERY;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			stmt.setFloat(3, grade);
			int rs = stmt.executeUpdate();
			if(rs==0)
				return false;
			return true;
		} catch (Exception e) {
			logger.error("Exception raised" + e.getMessage());
		} finally {
			
		}
		return false;
	}

	/**
	 * Method to view all the available courses using SQL Commands
	 * @return: return the list of all the available courses
	 */
	@Override
	public ArrayList<Course> viewAvailableCourses() {
		Connection conn = DBUtils.getConnection();
		try {
			ArrayList<Course> clist = new ArrayList<Course>();
			PreparedStatement stmt = null;
			String sql = SqlQueryConstants.AVAILABLE_COURSE_QUERY;
			stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery(sql);
			 while(rs.next()){
		            //Display values
				 	Course c = new Course();
				 	c.setCourseId(rs.getInt("courseId"));
				 	c.setName(rs.getString("name"));
				 	c.setCourseFee(rs.getInt("courseFee"));
				 	PreparedStatement stmt1 = null;
				 	String sql1 = "Select catalogId from catalog where courseId = ?";
				 	stmt1 = conn.prepareStatement(sql1);
				 	stmt1.setInt(1, rs.getInt("courseId"));
				 	ResultSet rs3 = stmt1.executeQuery();
				 	while(rs3.next()) {
				 		c.setCatalogId(rs3.getInt("catalogId"));
				 	}
				 	clist.add(c);
		         }
			 return clist;
			}
			catch(Exception e){
				logger.error("Exception raised" + e.getMessage());
			}
		return null;
	}

}
