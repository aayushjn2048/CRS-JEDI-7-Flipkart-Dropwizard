/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentCourseChoice;
import com.crs.flipkart.constants.Gender;
import com.crs.flipkart.constants.SqlQueryConstants;
import com.crs.flipkart.exceptions.UserNotFoundException;

/*
* Class to implement Student Dao Operations
*
*/
public class StudentDaoOperation implements StudentDaoInterface {

    private static StudentDaoOperation instance = null;
    private Connection conn = DBConnection.connectDB();
    private static Logger logger = Logger.getLogger(StudentDaoOperation.class);

    public static StudentDaoOperation getInstance(){
        if(instance==null){
            synchronized (com.crs.flipkart.business.StudentImplementation.class){
                instance = new StudentDaoOperation();
            }
        }
        return instance;
    }
    
    /**
	 * Method to add student to database
	 * @param student: student object containing all the fields
	 * @return true if student is added, else false
	 */
    @Override
	public boolean addStudentData(Student student) {
		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO student(studentId,gradeCardVisibility) values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, student.getStudentId());
			stmt.setInt(2, student.getGradeCardVisibility()==true?1:0);
			
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
	 * Method to view all students from database
	 * @return List of student 
	 */
    @Override
    public ArrayList<Student> viewAllStudents() {
        try {
            ArrayList<Student> list = new ArrayList<>();
            PreparedStatement stmt = null;
            String sql = "select * from student";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Student student= new Student();
                student.setStudentId(rs.getInt("studentId"));
                student.setName(rs.getNString("name"));
                student.setAddress(rs.getNString("address"));
                student.setGender(Gender.stringToGender(rs.getNString("gender")));
                student.setContactNo(rs.getNString("contactNo"));
                student.setGradeCardVisibility(rs.getInt("gradeCardVisibility")==1);
                list.add(student);
            }
            return list;
        } catch (SQLException se) {
            // Handle errors for JDBC
        	logger.error("Exception raised" + se.getMessage());
        }
        return null;
    }

    /**
	 * Method to view activate grade card for students
	 * @return true if grade card is activated for student, else false
	 */
    @Override
    public Boolean activateGradeCard() {
        try {
            PreparedStatement stmt = null;
            String sql = "UPDATE student set gradeCardVisibility=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1);
            int rs = stmt.executeUpdate();
            if(rs!=0)
            	return true;
            return false;
        } catch (SQLException se) {
            // Handle errors for JDBC
        	logger.error("Exception raised" + se.getMessage());
        }
        return false;
    }

    /**
	 * Method to view student from database
	 * @param studentId: studentId
	 * @return student: student object containing all the fields
	 */

    @Override
    public Student viewStudentDetails(int studentId) {
        try {
            PreparedStatement stmt = null;
            String sql = "select * from student where studentId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            Student student = new Student();
            while(rs.next())
            {
                student.setStudentId(rs.getInt("studentId"));
                student.setName(rs.getNString("name"));
                student.setAddress(rs.getNString("address"));
                student.setGender(Gender.stringToGender(rs.getNString("gender")));
                student.setContactNo(rs.getNString("contactNo"));
                break;
                //System.out.println(student.getStudentId()+" "+student.getName()+" "+student.getAddress()+" "+student.getGender()+" "+student.getGradeCardVisibility());
            }
            return student;

        } catch (SQLException se) {
            // Handle errors for JDBC
        	logger.error("Exception raised" + se.getMessage());
        }
        return null;
    }
    
    /**
	 * Method to Store student course choice to database
	 * @param studentCourseChoice StudentCourseChoice object containing all the fields
	 */
    @Override
	public void storeStudentCourseChoice(StudentCourseChoice studentCourseChoice) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO registrationDetails(studentId,courseId1,courseId2,courseId3,courseId4,courseId5,courseId6) values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentCourseChoice.getStudentId());
			stmt.setInt(2, studentCourseChoice.getCourses().get(0).getCourseId());
			stmt.setInt(3, studentCourseChoice.getCourses().get(1).getCourseId());
			stmt.setInt(4, studentCourseChoice.getCourses().get(2).getCourseId());
			stmt.setInt(5, studentCourseChoice.getCourses().get(3).getCourseId());
			stmt.setInt(6, studentCourseChoice.getCourses().get(4).getCourseId());
			stmt.setInt(7, studentCourseChoice.getCourses().get(5).getCourseId());
			
			int rs = stmt.executeUpdate();
		
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

    /**
	 * Method to view all courses from database
	 * @return List of course 
	 */
	@Override
	public ArrayList<Course> viewAllCourses() {
		try {
			ArrayList<Course> clist = new ArrayList<Course>();
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement(SqlQueryConstants.VIEW_ALLOCATED_COURSES_QUERY);
			 ResultSet rs = stmt.executeQuery(SqlQueryConstants.VIEW_ALLOCATED_COURSES_QUERY);
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
	 * Method to view student is registered or not.
	 * @param studentId: studentId
	 * @return true if student is already registered, else false
	 */

	@Override
	public Boolean studentAlreadyRegistered(int studentId) {
		try {
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("SELECT * FROM registrationDetails WHERE studentId = ?");
			stmt.setInt(1, studentId);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()){
		            return true;
		         }
			 return false;
			}
			catch(Exception e){
				logger.error("Exception raised" + e.getMessage());
			}
		return true;
	}

	/**
	 * Method to make payment succesfull.
	 * @param studentId: studentId
	 */
	@Override
	public void makePaymentSuccessful(int studentId, String referenceNo) {
		try {

			String sql = "UPDATE registrationDetails SET paymentStatus = ?, paymentNo = ? WHERE studentId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,"SUCCESS");
			stmt.setString(2,referenceNo);
			stmt.setInt(3, studentId);
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
	
	@Override
	public  String getPaymentStatus(int studentId) {
		String result = null;
		try {

			String sql = "SELECT paymentStatus FROM registrationDetails where studentId = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String s = rs.getString("paymentStatus");
				result = s;
				logger.info(s);
				break;
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			logger.error("Exception raised" + se.getMessage());
		} catch (Exception e) {
			// Handle errors for Class.forName
			logger.error("Exception raised" + e.getMessage());
		} finally {
			// finally block used to close resources // nothing we can do//end finally try
		}
		return result;
	}

	
	/**
	 * Method to update student's password.
	 * @param username the username of user
	 * @param oldPassword the old password of user
	 * @param newPassword the new password of user
	 * */
	public void update(String username,String oldPassword,String newPassword) throws UserNotFoundException{
        PreparedStatement stmt = null;

        

            {
                String sql = "SELECT * FROM user WHERE username = ?";
      
               
                boolean ok = false;
                try {
                	stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
	                ResultSet rs = stmt.executeQuery();
	            
            
					while (rs.next()) {
	                    ok = true;
	                    //System.out.println("Username exists");
	                    break;
	                }
                }
            	catch (Exception e) {
	                // Handle errors for Class.forName
	            	logger.error("Exception raised" + e.getMessage());
	            }
                if(!ok){
                    //System.out.println("Username Doesn't exist !");
                    throw new UserNotFoundException("Username doesn't exist!!!");
                }
            }

            {
                String sql = "SELECT password FROM user WHERE username = ?";
                ResultSet rs = null;
                String s = null;
                try
                {
                PreparedStatement stmt1 = conn.prepareStatement(sql);
                stmt1.setString(1, username);
          
                
                	rs = stmt1.executeQuery();
                	while (rs.next()) {
                        s = rs.getString("password");
                        break;
                    }
                }
            catch (Exception e) {
                // Handle errors for Class.forName
            	logger.error("Exception raised" + e.getMessage());
            }
   
                if (!s.equals(oldPassword)) {
                    throw new UserNotFoundException("Wrong password!!!");
                }
            }

            {
                String sql = "UPDATE user SET password = ? WHERE username = ?";
                try
                {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, newPassword);
                stmt.setString(2, username);

                	int sz = stmt.executeUpdate();
                }
                catch (Exception e) {
                    // Handle errors for Class.forName
                	logger.error("Exception raised" + e.getMessage());
                }
            }

        }


	@Override
	public Boolean isStudentRegistered(int studentId) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try
		{
            String sql = "SELECT * FROM registrationDetails WHERE studentId = ? AND paymentStatus = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            stmt.setString(2, "SUCCESS");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
		} catch (Exception e) {
	        // Handle errors for Class.forName
	    	logger.error("Exception raised" + e.getMessage());
	    }
		return false;
	}

}