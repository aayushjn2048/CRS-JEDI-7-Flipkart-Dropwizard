/**
 * 
 */
package com.crs.flipkart.controller;

import java.util.ArrayList;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Grade;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.ProfessorImplementation;
import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.UserNotFoundException;

/**
 * @author HP
 *
 */

/**
 * @author DELL
 *
 */

/**
 * Rest API class for professor 
 */
@Path("/ProfessorApi")
public class ProfessorRestApi {
	
	ProfessorImplementation professorImplementation = ProfessorImplementation.getInstance();
	
	
	/**
	 * View all All available Courses
	 */
	@GET
	@Path("/availableCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course> viewAvailableCourses() {
		ArrayList<Course> courses=new ArrayList<Course>();
		try {
		
		courses=professorImplementation.viewAvailableCourses();
		}
		catch(Exception e){
			return null;
		}
		return courses;
	}
	
	/**
	 * View all students registered in a given course
	 */
	@GET
	@Path("/enrolledStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ArrayList> viewEnrolledStudents(@NotNull
			@QueryParam("profId") int profId, 
			@NotNull 
			@QueryParam("courseId") int courseId) throws ValidationException {
		
		ArrayList<Student> students=new ArrayList<Student>();
	
		try{
			students = professorImplementation.viewEnrolledStudents(profId, courseId);
			ArrayList<ArrayList> ans = new ArrayList<ArrayList>();
			for(Student student: students)
			{
				ArrayList tmp = new ArrayList();
				tmp.add(student.getStudentId());
				tmp.add(student.getName());
				tmp.add(student.getContactNo());
				ans.add(tmp);
			}
			return ans;
		}
		catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Assign Grades to a student
	 */
	@POST
	@Path("/grades")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignGrade(Grade grade) throws ValidationException, CourseNotFoundException, UserNotFoundException  {
		
		try {
			professorImplementation.assignGrade(grade.getStudentId(), grade.getCourseId(), grade.getGrade());
		}
		catch(Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}
		return Response.status(200).entity("Grade added for student with studentId: "+grade.getStudentId()).build();
	}
	
	/**
	 * Selecting the Courses
	 */
	@POST
	@Path("/courses")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response selectCourses(Course course ) throws CourseNotFoundException {
			try {
				if(professorImplementation.selectCourse(course.getProfessorId(),course.getCourseId())) {
					return Response.status(200).entity("Course has been succesfully allocated: ").build();
				}
				else {
					
					return Response.status(200).entity("Course has been already allocated: ").build();
				}
			}
			catch(Exception e) {
				 return Response.status(400).entity(e.getMessage()).build();

			}
			
		
	}

	
}