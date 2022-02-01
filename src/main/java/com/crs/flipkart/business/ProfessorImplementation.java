/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.Map;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.AdminDaoInterface;
import com.crs.flipkart.dao.AdminDaoOperation;
import com.crs.flipkart.dao.ProfessorDaoInterface;
import com.crs.flipkart.dao.ProfessorDaoOperation;

/**
 * @author HP
 * ProfessorImplementation class
 * 
 */
public class ProfessorImplementation {
	private static ArrayList<Professor> professorData = new ArrayList<Professor>();
	private static volatile ProfessorImplementation instance = null;
	ProfessorDaoOperation professorDaoOperation = ProfessorDaoOperation.getInstance();
	 private ProfessorImplementation(){}

	    public static ProfessorImplementation getInstance() {
	        if (instance == null) {
	            synchronized (ProfessorImplementation.class) {
	                instance = new ProfessorImplementation();
	            }
	        }
	        return instance;
	    }
	/**
	 * Method to view the professor data
	 * @return the list of professors
	 */
	public static ArrayList<Professor> viewProfessorData()
	{
		return professorData;
	}
	
	/**
	 * Method to add the professor data
	 * @param professor object
	 */
	public static void addProfessordata(Professor professor) {
		professorData.add(professor);
	}
	
	/**
	 * Method to remove the professor data
	 * @param professor id
	 * @return boolean data 
	 */
	public static boolean removeProfessordata(int professorId) {
		int pos=0;
		
		for(Professor professor: professorData) {
			if(professor.getProfessorId()==professorId) {
				professorData.remove(pos);
				return true;
			}
			pos++;
		}
		return false;
	}
	
	/**
	 * Method to update the professor data
	 * @param professor object
	 */
	public static void updateProfessordata(Professor professor) {
		int pos=0;
		for(Professor prof: professorData) {
			if(prof.getProfessorId()==professor.getProfessorId()) {
				professorData.remove(pos);
				professorData.add(professor);
				break;
			}
			pos++;
		}
	}
	
	/**
	 * Method to view all the available courses
	 */
	public ArrayList<Course>  viewAvailableCourses() {
		ArrayList<Course> clist = professorDaoOperation.viewAvailableCourses();
		return clist;
		
	}
	
	/**
	 * Method to view list of enrolled Students
	 * @param: professorId the id of professor
	 */
public ArrayList<Student> viewEnrolledStudents(int professorid, int courseid) {
		
		Map<Integer,ArrayList<Student>> stulist = professorDaoOperation.viewEnrolledStudents(professorid);
		ArrayList<Student> student = new ArrayList<Student>();
		for(Map.Entry<Integer,ArrayList<Student>> entry: stulist.entrySet())
		{
			if(entry.getKey()==courseid) {
				student = entry.getValue();
				break;
			}
		}
		return student;
	}
	
	/**
	 * Method to get Courses by Professor Id
	 * @param professorId the id of professor
	 * @param courseId the id of course
	 * @return boolean data whether offered to the professor or not.
	 */
	public boolean selectCourse(int professorId, int courseId) {
		
		//ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();
		return professorDaoOperation.selectCourse(professorId,courseId);
	}
	
	/**
	 * Assign grades to student
	 * @param studentId the student id
	 * @param courseId the course id
     * @param grade the grade to be assigned
	 */
	public void assignGrade(int studentId, int courseId, float grade) {
		//ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();
		professorDaoOperation.assignGrade(studentId,courseId,grade);
	}
	
}
