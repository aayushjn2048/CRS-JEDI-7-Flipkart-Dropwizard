/**
 * 
 */
package com.crs.flipkart.bean;

import com.crs.flipkart.constants.Gender;
import com.crs.flipkart.constants.Role;

/**
 * @author HP
 *
 */
public abstract class User {
	
		private int userId;
		private String username;
		private String passwordHash;
		private String name;
		private String address;
		private Gender gender;
		private String contactNo;
		private Role role;
		/**
		 * @return the userId
		 */
		public int getUserId() {
			return userId;
		}
		/**
		 * @param userId the userId to set
		 */
		public void setUserId(int userId) {
			this.userId = userId;
		}
		/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}
		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}
		/**
		 * @return the passwordHash
		 */
		public String getPasswordHash() {
			return passwordHash;
		}
		/**
		 * @param passwordHash the passwordHash to set
		 */
		public void setPasswordHash(String passwordHash) {
			this.passwordHash = passwordHash;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the address
		 */
		public String getAddress() {
			return address;
		}
		/**
		 * @param address the address to set
		 */
		public void setAddress(String address) {
			this.address = address;
		}
		/**
		 * @return the gender
		 */
		public Gender getGender() {
			return gender;
		}
		/**
		 * @param gender the gender to set
		 */
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		/**
		 * @return the contactNo
		 */
		public String getContactNo() {
			return contactNo;
		}
		/**
		 * @param contactNo the contactNo to set
		 */
		public void setContactNo(String contactNo) {
			this.contactNo = contactNo;
		}
		/**
		 * @return the role
		 */
		public Role getRole() {
			return role;
		}
		/**
		 * @param role the role to set
		 */
		public void setRole(Role role) {
			this.role = role;
		}
}
