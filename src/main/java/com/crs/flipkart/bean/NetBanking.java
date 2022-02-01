/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author HP
 *
 */
public class NetBanking {
	private String accountNumber;
	private String IFSCCode;
	private int amount;
	private int referenceNo;
	
	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * @return the iFSCCode
	 */
	public String getIFSCCode() {
		return IFSCCode;
	}
	/**
	 * @param iFSCCode the iFSCCode to set
	 */
	public void setIFSCCode(String iFSCCode) {
		IFSCCode = iFSCCode;
	}
}
