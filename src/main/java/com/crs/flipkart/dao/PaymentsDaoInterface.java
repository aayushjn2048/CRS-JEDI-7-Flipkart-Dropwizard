/**
 * 
 */
package com.crs.flipkart.dao;

import com.crs.flipkart.bean.Challan;
import com.crs.flipkart.bean.PaymentReference;

/**
 * @author HP
 * Interface for Payment Dao Operations
 *
 */
public interface PaymentsDaoInterface {
	
    /**
	 * Method to store Payment Reference
	 * @param paymentReference the details of payment
	 * @return payment reference number
	 */
	public int storePaymentReference(PaymentReference paymentReference);
    
	/**
   	 * Method to store challan
   	 */
	public void storeChallan(Challan challan);
}
