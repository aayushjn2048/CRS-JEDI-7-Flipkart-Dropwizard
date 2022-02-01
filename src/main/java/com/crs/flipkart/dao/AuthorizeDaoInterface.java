/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.constants.Role;

/**
 * @author HP
 * Interface for Authorization Operations
 *
 */
public interface AuthorizeDaoInterface {
	
	/**
	 * Method for authorizeUser
	 * @param username of user , password of user
	 * returns The role of user
	 */
	public Role authorizeUser(String username, String password);
}
