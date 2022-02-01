/**
 * 
 */
package com.crs.flipkart.constants;

/**
 * @author HP
 *
 */
public enum PaymentStatus {
	SUCCESSFUL, PENDING, FAILED;

	public PaymentStatus stringToEnum(String val){
		PaymentStatus paymentStatus = PaymentStatus.SUCCESSFUL;
		if(val.equalsIgnoreCase("pending"))
			paymentStatus = paymentStatus.PENDING;
		else if(val.equalsIgnoreCase("failed"))
			paymentStatus = paymentStatus.FAILED;
		return paymentStatus;
	}
}
