/**
 * 
 */
package com.crs.flipkart.controller;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudentData(Student student) {
		UserImplementation userImplementation = new UserImplementation();
		userImplementation.addUserdata(student);//convert business class function to boolean return type
		
	   return Response.status(201).entity("Student is succesfully registered!!!").build();
		
	} 
	
	@PUT
	@Path("/resetPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetPassword(Map<String,String> params) {
		StudentDaoInterface updaterDao = new StudentDaoOperation();

        if(params.size()==3 &&  updaterDao.update(params.get("username"),params.get("password"),params.get("newPassword"))){
            return Response.status(201).entity("Password Updated Successfully !!").build();
        }
        else{
            return Response.status(201).entity("Try again !").build();
        }
	   
	} 
}
