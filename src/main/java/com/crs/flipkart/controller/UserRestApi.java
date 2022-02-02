/**
 * 
 */
package com.crs.flipkart.controller;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.business.AuthorizationService;
import com.crs.flipkart.business.UserImplementation;
import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
import com.crs.flipkart.exceptions.PasswordIsWeakException;
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
	public Response viewStudent(@NotNull @QueryParam("username") String username, @NotNull  @QueryParam("password") String password){
		
		AuthorizationService auth = new AuthorizationService();
		try
		{
			auth.authorize(username, password);
			return Response.status(201).entity("User credentials verified").build();
		}
		catch (UserNotFoundException e) {
			return Response.status(Status.NOT_FOUND).entity("Wrong username or password").build();
		}
	}

	@POST
	@Path("/studentRegistration")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudentData(Student student) {
		UserImplementation userImplementation = new UserImplementation();
		if(student.getUsername().length()==0||student.getUserId()<1||student.getPasswordHash().length()==0)
			return Response.status(Status.BAD_REQUEST).entity("Insufficient parameters").build();
		try
		{
			userImplementation.addUserdata(student);
		} catch(PasswordIsWeakException e)
		{
			return Response.status(Status.FORBIDDEN).entity("Weak password").build();
		}
	   return Response.status(201).entity("Student is succesfully registered!!!").build();
		
	} 
	
	@PUT
	@Path("/resetPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetPassword(Map<String,String> params) {
		if(params.size()==3){
			StudentDaoOperation updaterDao = new StudentDaoOperation();
			try
			{
				updaterDao.update(params.get("username"),params.get("password"),params.get("newPassword"));
				System.out.println("Hello World");
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Hello World!!!");
				return Response.status(Status.UNAUTHORIZED).entity(e.getMessage()).build();
			}
			return Response.status(Status.ACCEPTED).entity("Password Updated Successfully !!").build();
		}
		else{
		    return Response.status(Status.BAD_REQUEST).entity("Try again!!!").build();
		}
	   
	} 
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response welcomeToUserAPI() {
	    return Response.status(Status.OK).entity("Welcome To User API").build();
	}
}
