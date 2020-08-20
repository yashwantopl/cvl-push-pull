/**
 * 
 */
package com.opl.mudra.api.user.model;

import java.io.Serializable;


/**
 * 
 * @author nilay.darji
 *
 */
public class CommonUsersNotInsertedResponse  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mobile;
    private String email;
    private String pan;
    private String message;
    private Integer amountOfLoan;
    private String cin;
    private String companyName;
    
    public CommonUsersNotInsertedResponse(String mobile, String email,String pan ,String message) {
		this.mobile = mobile;
		this.email = email;
		this.message = message;
		this.pan = pan;
	}
    public CommonUsersNotInsertedResponse(String mobile, String email,String pan ,String message,String cin,String companyName) {
		this.mobile = mobile;
		this.email = email;
		this.message = message;
		this.pan = pan;
		this.cin = cin;
		this.companyName=companyName;
	}
	public CommonUsersNotInsertedResponse() {
	}
    
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	public Integer getAmountOfLoan() {
		return amountOfLoan;
	}
	public void setAmountOfLoan(Integer amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
	}
	
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "GstUsersNotInsertedResponse [mobile=" + mobile + ", email=" + email + ", pan=" + pan + ", message="
				+ message + ", amountOfLoan=" + amountOfLoan + "]";
	}
    
}
