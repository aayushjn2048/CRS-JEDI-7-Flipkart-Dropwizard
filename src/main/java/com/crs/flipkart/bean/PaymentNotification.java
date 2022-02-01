/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author HP
 *
 */
public class PaymentNotification extends Notification{
	private int transactionId;

	/**
	 * @return the transactionId
	 */
	public int getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
}
