/**
 * 
 */
package com.crs.flipkart.controller;

/**
 * @author HP
 *
 */

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.business.AdminImplementation;
import com.crs.flipkart.business.CourseImplementation;
import com.crs.flipkart.business.CourseInterface;
import com.crs.flipkart.business.StudentImplementation;

import org.apache.log4j.Logger;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;



@Path("/AdminApi")
public class AdminRestApi{
	/**
	 * /admin/viewCourseCatalogue
	 * REST-service for getting courses in the catalog
	 * @return
	 */

	CourseInterface courseImplementation = CourseImplementation.getInstance();
	StudentImplementation studentImplementation = StudentImplementation.getInstance();
	//private static Logger logger = Logger.getLogger(CRSAdminMenu.class);


	/**
	 * /admin/courses
	 * REST-service for getting courses in the catalog
	 * @return list of courses
	 */
	@GET
	@Path("/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewAllCourses() {

		AdminImplementation adminImplementation = new AdminImplementation();
		return adminImplementation.viewAllCourses();
		
	}

	/**
	 * /admin/courses
	 * REST-service for adding a new course in catalog
	 * @param course the course details to be added
	 * @return response
	 */
	@POST
	@Path("/courses")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@Valid Course course){
		
		try {

			courseImplementation.addCourse(course);
			return Response.status(201).entity("Course with courseCode: " + course.getCourseId() + " added to catalog").build();
		
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}
			
	}


	/**
	 * REST-service for updating a course in catalog
	 * @param course
	 * @return response
	 */
	@PUT
	@Path("/courses")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCourse(@Valid Course course){

		try {

			courseImplementation.updateCourse(course);
			return Response.status(201).entity("Course with courseCode: " + course.getCourseId() + " updated in catalog").build();

		} catch (Exception e) {

			return Response.status(409).entity(e.getMessage()).build();

		}

	}
	/**
	 * /admin/courses
	 * REST-services for dropping a course from catalog
	 * @param courseId
	 * @return
	 */
	@DELETE
	@Path("/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCourse(
			@NotNull
			@QueryParam("courseId") int courseId){
		
		try {

			courseImplementation.removeCourse(courseId);
			return Response.status(201).entity("Course with courseCode: " + courseId + " deleted from catalog").build();
		
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}	
	}

	/**
	 * REST-service for activating gradecard
	 * @param course
	 * @return
	 */
	@POST
	@Path("/gradeCard")
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateGradecard(){

		try {

			AdminImplementation adminImplementation = new AdminImplementation();
			adminImplementation.activateGradeCard();
			return Response.status(201).entity("Grade Card Activated").build();

		} catch (Exception e) {

			return Response.status(409).entity(e.getMessage()).build();

		}

	}

	/**
	 * /admin/professor
	 * REST-service for addding a new professor
	 * @param professor
	 * @return response
	 */
	@POST
	@Path("/professor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProf(@Valid Professor professor) {
		 
		try {
			AdminImplementation admin = new AdminImplementation();
			int profId = admin.addProfessor(professor);
			return Response.status(201).entity("Professor with professorId: " + profId + " added").build();
			
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
			
		}
		
	}

	/**
	 * REST-service for removing a professor
	 * @param professorId the professor Id of the user
	 * @return response
	 */
	@DELETE
	@Path("/professor")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProf(
			@NotNull
			@QueryParam("professorId") int ProfessorId) {
		
		try {
			AdminImplementation admin = new AdminImplementation();
			admin.removeProfessor(ProfessorId);
			return Response.status(201).entity("Prof with profId: " + ProfessorId + " has been removed").build();
		
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}	
	}

	
	/**
	 * REST-service for updating a professor
	 * @param professor the professor object
	 * @return response
	 */
	@PUT
	@Path("/professor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProf(@Valid Professor professor) {

		try {
			AdminImplementation admin = new AdminImplementation();
			admin.updateProfessor(professor);
			return Response.status(201).entity("Professor with professorId: " + professor.getProfessorId() + " updated").build();

		} catch (Exception e) {

			return Response.status(409).entity(e.getMessage()).build();

		}

	}

	/**
	 * /admin/approveStudent
	 * REST-service for approving the student admission
	 * @param studentId
	 * @return
	 */
	@PUT
	@Path("/approveStudent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approveStudent(
			// @Min(value = 1, message = "Student ID should not be less than 1")
			// @Max(value = 9999, message = "Student ID should be less than 10000") //TODO: Check if this is necessary
			@NotNull
			@QueryParam("studentId") int studentId) {
		
		try {
			AdminImplementation admin = new AdminImplementation();
			return Response.status(201).entity(admin.approveStudentRegistration(studentImplementation.viewStudentDetails(studentId).getStudentId())).build();
		
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}
		
	}
	
	/**
	 * REST-service for viewing all students
	 * @return list of students
	 */
	@GET
	@Path("/students")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> viewAllStudents() {

		AdminImplementation adminImplementation = new AdminImplementation();
		return adminImplementation.viewAllStudents();
		
	}
	
	/**
	 * REST-service for viewing all professors
	 * @return list of professors
	 */
	@GET
	@Path("/professors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> viewAllProfessors() {

		AdminImplementation adminImplementation = new AdminImplementation();
		return adminImplementation.viewAllProfessors();
		
	}
	
	/**
	 * REST-service for allocating pending course
	 * @return response
	 */
	@POST
	@Path("/allocatePendingCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allocatePendingCourses() {

		AdminImplementation adminImplementation = new AdminImplementation();
		adminImplementation.allocatePendingCourses();
		return Response.status(200).entity("Course allocation successful").build();
	}
	
	
	

}