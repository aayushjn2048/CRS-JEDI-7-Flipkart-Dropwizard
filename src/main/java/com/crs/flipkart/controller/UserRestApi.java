/**
 * 
 */
package com.crs.flipkart.controller;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.business.AuthorizationService;
import com.crs.flipkart.business.UserImplementation;
import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.exceptions.UserNotFoundException;

/**
 * @author HP
 *
 */

@Path("/UserApi")
public class UserRestApi {

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewStudent(@NotNull @QueryParam("username") String username, @NotNull  @QueryParam("password") String password) throws UserNotFoundException{
		
		AuthorizationService auth = new AuthorizationService();
		try
		{
			System.out.println("password: "+password);
			auth.authorize(username, password);
			return Response.status(201).entity("User credentials verified").build();
		}
		catch (Exception e) {
			throw new UserNotFoundException();
		}
	}

	@POST
	@Path("/studentRegistration")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudentData(Student student) {
		UserImplementation userImplementation = new UserImplementation();
		userImplementation.addUserdata(student);//convert business class function to boolean return type
		
	   return Response.status(201).entity("Student is succesfully registered!!!").build();
		
	} 
}
