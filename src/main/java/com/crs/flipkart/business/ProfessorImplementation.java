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
import com.crs.flipkart.exceptions.CourseNotFoundException;

/**
 * @author HP
 *
 */
public class ProfessorImplementation implements ProfessorInterface{
   
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
	private static ArrayList<Professor> professorData = new ArrayList<Professor>();
	public  ArrayList<Professor> viewProfessorData()
	{
		return professorData;
	}
	
	public  void addProfessordata(Professor professor) {
		professorData.add(professor);
	}
	
	public  boolean removeProfessordata(int professorId) {
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
	
	public  void updateProfessordata(Professor professor) {
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
	
	public ArrayList<Course>  viewAvailableCourses() throws CourseNotFoundException {
		
		
		ArrayList<Course> clist = professorDaoOperation.viewAvailableCourses();
		if(clist.size()==0) {
			throw new CourseNotFoundException("No courses available");
		}
		return clist;
		
	}
	
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
	
	public void selectCourse(int professorId, int courseId) throws CourseNotFoundException{
		
		if(!professorDaoOperation.selectCourse(professorId,courseId)) {
			throw new CourseNotFoundException(courseId);
		}
		
	}
	
	public void assignGrade(int studentId, int courseId, float grade) throws CourseNotFoundException {
		
		
			if(!professorDaoOperation.assignGrade(studentId,courseId,grade)) {
				throw new CourseNotFoundException(courseId);
			}
		
	}
	
}
