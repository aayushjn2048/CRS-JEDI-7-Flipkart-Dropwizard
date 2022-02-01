/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.constants.Role;
import com.crs.flipkart.dao.AuthorizeDaoInterface;
import com.crs.flipkart.dao.AuthorizeDaoOperation;
import com.crs.flipkart.exceptions.UserNotFoundException;

/**
 * @author HP
 *
 */
public class AuthorizationService {
	public Role authorize(String username, String password) throws UserNotFoundException
	{
		AuthorizeDaoInterface auth = new AuthorizeDaoOperation();
		Role role = auth.authorizeUser(username, password);
		if(role == null)
		{
			throw new UserNotFoundException();
		}
		return role;
	}
}
