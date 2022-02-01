/**
 *
 */
package com.crs.flipkart.constants;

/**
 * The enum Role.
 *
 * @author HP
 */
public enum Role {
    /**
     * Admin role.
     */
    ADMIN,
    /**
     * Professor role.
     */
    PROFESSOR,
    /**
     * Student role.
     */
    STUDENT;

    /**
     * String to name role.
     *
     * @param role the role
     * @return the role
     */
    public static Role stringToName(String role) {
        Role userRole = null;

        if (role.equalsIgnoreCase("ADMIN"))
            userRole = Role.ADMIN;
        else if (role.equalsIgnoreCase("PROFESSOR"))
            userRole = Role.PROFESSOR;
        else if (role.equalsIgnoreCase("STUDENT"))
            userRole = Role.STUDENT;
        return userRole;
    }


}
