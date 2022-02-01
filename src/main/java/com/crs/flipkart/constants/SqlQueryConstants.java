/**
 * 
 */
package com.crs.flipkart.constants;

/**
 * @author HP
 *
 */
public class SqlQueryConstants {
    public static final String ADD_COURSE_QUERY = "INSERT INTO course(courseId,name,courseFee) values(?,?,?)";
    public static final String ADD_COURSE_TO_CATALOG_QUERY = "INSERT INTO catalog(catalogId,courseId) values(?,?)";
    public static final String DELETE_COURSE_QUERY = "DELETE FROM course WHERE courseId = ?";
    public static final String VIEW_PROFESSORS_QUERY = "SELECT * FROM professor";
    public static final String VIEW_COURSES_QUERY = "SELECT * FROM course";
    public static final String VIEW_ALLOCATED_COURSES_QUERY = "SELECT * FROM course WHERE professorId IS NOT NULL";
    public static final String ADD_PROFESSOR_QUERY = "INSERT INTO professor(professorId,name,address,gender,contactNo,designation) values(?,?,?,?,?,?)";
    public static final String ADD_USER_QUERY = "INSERT INTO user(userId,username,password) VALUES(?,?,?)";
    public static final String ADD_ROLE_QUERY = "INSERT INTO role(userId,roleName) VALUES(?,?)";
    public static final String DELETE_USER_QUERY = "DELETE FROM user WHERE userId = ?";
    public static final String DELETE_ROLE_QUERY = "DELETE FROM role WHERE userId = ?";
    public static final String DELETE_PROFESSOR_QUERY = "DELETE FROM professor WHERE professorId = ?";
    public static final String AVAILABLE_COURSE_QUERY = "SELECT * FROM course WHERE professorId IS NULL";	
	public static final String ASSIGN_GRADES_QUERY = "INSERT INTO gradeCard(studentId,courseId,grade) values(?,?,?)";
	public static final String SELECT_COURSES_QUERY = "SELECT * FROM course Where courseId = ? AND professorId IS NULL";
	public static final String SELECT_COURSES_QUERY1 = "UPDATE course SET professorId = ? WHERE courseId = ?";
	public static final String 	VIEW_ENROLLED_STUDENTS_QUERY = "SELECT * FROM course WHERE professorId = professorid";
	public static final String 	VIEW_ENROLLED_STUDENTS_QUERY1 = "SELECT * FROM studentRegisteredDetails";
	public static final String 	VIEW_ENROLLED_STUDENTS_QUERY2 = "SELECT * FROM student where studentId = ? ";
}