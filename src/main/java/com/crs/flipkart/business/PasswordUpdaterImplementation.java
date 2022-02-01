/**
 * 
 */
package com.crs.flipkart.business;

import java.util.Scanner;

import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.dao.StudentDaoOperation;

/**
 * @author HP
 *
 */
public class PasswordUpdaterImplementation implements PasswordUpdaterInterface {
    public void update(){
        Scanner scanner=new Scanner(System.in);

        System.out.println("\n==========================================\n");

        System.out.print("Enter username: ");
        String username = scanner.next();

        System.out.print("Enter old password: ");
        String oldPassword = scanner.next();

        System.out.print("Enter new password: ");
        String newPassword = scanner.next();

        StudentDaoInterface updaterDao = new StudentDaoOperation();

        if(updaterDao.update(username,oldPassword,newPassword)){
            System.out.println("Password Updated Successfully !!");
        }
        else{
            System.out.println("Try again !");
        }
    }
}