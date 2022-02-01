/**
 * 
 */
package com.crs.flipkart.bean;

import java.util.Date;

/**
 * @author HP
 *
 */
public class CardPayment {
	private String cardNo;
	private String cardHolderName;
	private String cardType;
	private Date dateOfExpiry;
	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}
	/**
	 * @param cardHolderName the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * @return the dateOfExpiry
	 */
	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}
	/**
	 * @param dateOfExpiry the dateOfExpiry to set
	 */
	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
}
