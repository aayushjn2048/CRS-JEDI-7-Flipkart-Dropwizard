/**
 * 
 */
package com.crs.flipkart.controller;

import java.util.ArrayList;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
 
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.StudentRegisteredCourses;
import com.crs.flipkart.business.StudentImplementation;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.constants.Gender;
import com.crs.flipkart.constants.Role;
import com.crs.flipkart.dao.CourseDaoImplementation;
import com.crs.flipkart.dao.CourseDaoInterface;
import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.dao.StudentDaoOperation;
 
 
/**
 * @author Rama
 *
 */
 
@Path("/StudentApi")
public class StudentRestApi {
    
    
    /**
     * Method to handle API request to view student
     * @param studentId
     * @return student
     * @throws SQLException 
     */
    @GET
    @Path("/student/{studentId}")
//  @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Student viewStudent(@PathParam("studentId") int studentId){
        
        StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
        Student student = new Student();
        try {
            student = studentDaoOperation.viewStudentDetails(studentId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
    
 
    /**
     * Method to handle API request to add student data
     * @return response 
     */
    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudentData(Student student) {
        StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
        studentDaoOperation.addStudentData(student);//convert business class function to boolean return type
        
       return Response.status(201).entity("student is succesfully added "+student).build();
        
    } 
    
    
    /**
     * Method to handle API request to view catalog
     * @return list of courses
     * @throws SQLException 
     */
    @GET
    @Path("/catalogue")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourseCatalogue() {
        System.out.println("before");
        //StudentImplementation studentImplementation = StudentImplementation.getInstance(); //error is here..
        StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
        System.out.println("after");
       ArrayList<Course> result = studentDaoOperation.viewAllCourses();
       System.out.print(result);
       return Response.status(Status.OK).entity(result).build();
    }
    
    
    /**
     * Method to handle API request to view payment status
     * @param studentId
     * @return status ("SUCCESS"/"PENDING")
     * @throws SQLException 
     */
    @GET
    @Path("/paymentStatus/{studentId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response paymentStatus(@PathParam("studentId") int studentId) {          //returns null
       StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
       String result = null;
       try {
           result = studentDaoOperation.getPaymentStatus(studentId);
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       return Response.status(Status.OK).entity(result).build();
    }
    
    /**
     * Method to handle API request to view all students
     * @return list of students
     * @throws SQLException 
     */
    @GET
    @Path("/student")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewStudentData() {
       StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
       
       try {
           ArrayList<Student> result = studentDaoOperation.viewAllStudents();
           return Response.status(Status.OK).entity(result).build();
       } catch (Exception e) {
           e.printStackTrace();
           return Response.status(Status.EXPECTATION_FAILED).entity("Exception occured").build();
       }
       
    }
    
    /**
     * Method to handle API request to view catalog
     * @return list of courses
     * @throws SQLException 
     */
    @GET
    @Path("/studentRegistered/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response studentAlreadyRegistered(@PathParam("studentId") int studentId) {
       StudentDaoInterface studentDaoOperation = new StudentDaoOperation();
       try {
           Boolean result = studentDaoOperation.studentAlreadyRegistered(studentId);
           return Response.status(Status.OK).entity(result).build();
       } catch (Exception e) {
           e.printStackTrace();
           return Response.status(Status.EXPECTATION_FAILED).entity("Exception occured").build();
       }
    }
    
    /**
     * Method to handle API request to view grade card of a student
     * @param studentId
     * @return gradecard
     * @throws SQLException 
     */
    @GET
    @Path("/gradecard/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response displayGradeCard(@PathParam("studentId") int studentId) {
        CourseDaoInterface courseDaoImplementation = CourseDaoImplementation.getInstance();
        
        try {
            StudentRegisteredCourses studentRegisteredCourses = courseDaoImplementation.getStudentRegisteredCourses(studentId);
            return Response.status(Status.OK).entity(studentRegisteredCourses).build();
           } catch (Exception e) {
               e.printStackTrace();
               return Response.status(Status.EXPECTATION_FAILED).entity("Exception occured").build();
           }
    }
    
}