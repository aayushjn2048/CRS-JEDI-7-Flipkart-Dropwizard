/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.List;

import com.crs.flipkart.bean.Challan;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.SemesterRegistration;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.exceptions.GradeCardNotPublishedException;
import com.crs.flipkart.exceptions.ProfessorNotFoundException;
import com.crs.flipkart.validator.StandardResponse;

/**
 * @author HP
 *
 */
public interface AdminInterface {
	public void activateGradeCard() throws GradeCardNotPublishedException;//1
	public int addProfessor(Professor professor) throws ProfessorNotFoundException;
	public void removeProfessor(int professorId) throws ProfessorNotFoundException;
	public void updateProfessor(Professor professor) throws ProfessorNotFoundException;
	public Boolean approveStudentRegistration(int studentId);
	public List<Student> viewAllStudents();
	public List<Professor> viewAllProfessors();
	public List<Course> viewAllCourses();
	public void allocatePendingCourses();
	public Challan generateChallan(SemesterRegistration semesterRegistration);
}
