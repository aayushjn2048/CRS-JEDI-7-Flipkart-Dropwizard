/**
 * 
 */
package com.crs.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.crs.flipkart.controller.AdminRestApi;
import com.crs.flipkart.controller.ProfessorRestApi;
import com.crs.flipkart.controller.StudentRestApi;

//import com.crs.flipkart.restcontroller;

/**
 * @author Rama
 *
 */
public class ApplicationConfig  extends ResourceConfig{

	public ApplicationConfig() {
	//register(CustomerController.class);
	register(StudentRestApi.class);
	register(ProfessorRestApi.class);
	register(AdminRestApi.class);
	}
	
}
