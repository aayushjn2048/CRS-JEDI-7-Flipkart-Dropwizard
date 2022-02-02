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
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.ProfessorImplementation;
import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.UserNotFoundException;

/**
 * @author DELL
 *
 */

/**
 * Rest API class for professor 
 */

@Path("/ProfessorApi")
public class ProfessorRestApi {
	
	
	private static Logger logger = Logger.getLogger(ProfessorDaoOperation.class);
	/**
     * Method to View all All available Courses
     * @return list of all available courses
     * @throws SQLException, CourseNotFoundException ,ValidationException 
     */
	@GET
	@Path("/viewCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Course>viewAvailableCourses() throws ValidationException, CourseNotFoundException  {
		logger.info("Viewing Available Courses");
		ArrayList<Course> courses=new ArrayList<Course>();
		ProfessorInterface professorInterface = ProfessorImplementation.getInstance();
		try {
		
		courses=professorInterface.viewAvailableCourses();
		}
		catch(CourseNotFoundException ex) {
			return null;
		}
		catch(Exception e){
			return null;
		}
		
		return courses;
	}
	
	/**
     * Method to View all students registered in a given course
     * @param profId
	 *@param courseId
     * @return list of students registered
     * @throws SQLException , UserNotFoundException
     */
	@GET
	@Path("/viewEnrolledStudent")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Student> viewEnrolledStudents(@NotNull
			@QueryParam("profId") int profId, 
			@NotNull 
			@QueryParam("courseId") int courseId) throws ValidationException, UserNotFoundException {
		logger.info("Viewing Enrolled Students");
		ArrayList<Student> students=new ArrayList<Student>();
		ProfessorInterface professorInterface = ProfessorImplementation.getInstance();
	
		try{
			
			students = professorInterface.viewEnrolledStudents(profId, courseId);
			
		}
		catch(Exception e){
			return null;
		}
		
		return students;
	}
	
	
	/**
     * Method to assign grade
     * @param StudentId
	 *@param courseId
	 *@param grade
     * @return Response
     * @throws SQLException , UserNotFoundException
     */
	@POST
	@Path("/assignGrade")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignGrade(
			@NotNull
			@QueryParam("studentId") int studentId,
			
			@NotNull
			@QueryParam("courseId") int courseId,
			
			
			@NotNull
			@QueryParam("grade") float grade) throws ValidationException, CourseNotFoundException, UserNotFoundException  {
		logger.info("Assigning grades");
		ProfessorInterface professorInterface = ProfessorImplementation.getInstance();
		try {
			
			professorInterface.assignGrade(studentId, courseId, grade);
			return Response.status(200).entity("Grade added for student with studentId: "+studentId).build();
		}
		catch (CourseNotFoundException ex) {
			return Response.status(400).entity(ex.getMessage()).build();
			
		}
		catch(Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}
		
	}
	
	/**
     * Method to select Course 
     * @param profId
	 *@param courseId
     * @return Response
     * @throws SQLException , UserNotFoundException
     */
	
	@POST
	@Path("/selectCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response selectCourses(@NotNull @QueryParam("profId") int profId,
			@NotNull @QueryParam("courseId") int courseId ) throws CourseNotFoundException {
		logger.info("Selecting Courses");
		ProfessorInterface professorInterface = ProfessorImplementation.getInstance();
			try {
				professorInterface.selectCourse(profId,courseId); 
				return Response.status(200).entity("Course has been succesfully allocated: ").build();
				
			}
			catch(CourseNotFoundException ex) {
				return Response.status(400).entity(ex.getMessage()).build();
			}
			catch(Exception e) {
				 return Response.status(400).entity(e.getMessage()).build();

			}
			
			
		
	}
	
	/**
     * Professor Menu
     */
	 @GET
	    @Path("/")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response welcomeToCRS() {
		 logger.info("Welcome to CRS");
	        return Response.status(Status.OK).entity("Welcome To Professor Menu").build();
	    }

	
}
