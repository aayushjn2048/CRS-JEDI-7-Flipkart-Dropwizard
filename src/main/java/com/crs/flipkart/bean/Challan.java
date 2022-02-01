/**
 * 
 */
package com.crs.flipkart.bean;

import com.crs.flipkart.constants.PaymentStatus;

/**
 * @author HP
 *
 */
public class Challan{
	private int challanNo;
	private PaymentReference paymentReference;
	/**
	 * @return the paymentReference
	 */
	public PaymentReference getPaymentReference() {
		return paymentReference;
	}
	/**
	 * @param paymentReference the paymentReference to set
	 */
	public void setPaymentReference(PaymentReference paymentReference) {
		this.paymentReference = paymentReference;
	}
	/**
	 * @return the challanNo
	 */
	public int getChallanNo() {
		return challanNo;
	}
	/**
	 * @param challanNo the challanNo to set
	 */
	public void setChallanNo(int challanNo) {
		this.challanNo = challanNo;
	}
}
