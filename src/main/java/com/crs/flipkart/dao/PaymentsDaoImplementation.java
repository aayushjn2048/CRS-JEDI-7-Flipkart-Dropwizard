/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.Challan;
import com.crs.flipkart.bean.PaymentReference;

/**
 * 
 *
 * Class to implement Payment Operations
 */
public class PaymentsDaoImplementation implements PaymentsDaoInterface {

    private static PaymentsDaoImplementation instance = null;
    Connection conn = DBConnection.connectDB();
    private static Logger logger = Logger.getLogger(PaymentsDaoImplementation.class);
    
    /**
	 * Default Constructor
	 */
    private PaymentsDaoImplementation() {
    }

    /**
	 * Method to make PaymentsDaoOperation Singleton
	 * @return
	 */
    public static PaymentsDaoImplementation getInstance() {
        if (instance == null) {
            synchronized (PaymentsDaoImplementation.class) {
                instance = new PaymentsDaoImplementation();
            }
        }
        return instance;
    }

    /**
	 * Method to store Payment Reference
	 * @param paymentReference the details of payment
	 * @return payment reference number
	 */
    @Override
    public int storePaymentReference(PaymentReference paymentReference) {
        try {
            PreparedStatement stmt = null;
            String sql = "INSERT INTO paymentRefernce(payeeName, amount, paymentStatus) values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, paymentReference.getPayeeName());
            stmt.setInt(2, paymentReference.getAmount());
            stmt.setString(3, paymentReference.getPaymentStatus().toString());
            int rs = stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            return generatedKeys.getInt(1); //TODO get payment reference number
        } catch (Exception se) {
            // Handle errors for JDBC
        	logger.error("Exception raised" + se.getMessage());
        }
        return 0;
    }

    /**
   	 * Method to store challan
   	 */
    @Override
    public void storeChallan(Challan challan) {
        try {
            PreparedStatement stmt = null;
            String sql = "INSERT INTO challan(challanNo,paymentReferenceNo) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, challan.getChallanNo());
            stmt.setInt(2, challan.getPaymentReference().getReferenceNo());
            int rs = stmt.executeUpdate();
        } catch (Exception se) {
            // Handle errors for JDBC
        	logger.error("Exception raised" + se.getMessage());
        }
    }
}