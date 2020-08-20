/**
 * 
 */
package com.opl.mudra.api.user.model;

import java.io.Serializable;


/**
 * @author vijay.chauhan
 *
 */
public class BSUsersNotInsertedResponse  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mobile;
    private String email;
    private String pan;
    private String message;
    private Integer amountOfLoan;
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
	@Override
	public String toString() {
		return "GstUsersNotInsertedResponse [mobile=" + mobile + ", email=" + email + ", pan=" + pan + ", message="
				+ message + ", amountOfLoan=" + amountOfLoan + "]";
	}
	public BSUsersNotInsertedResponse(String mobile, String email,String pan ,String message) {
		this.mobile = mobile;
		this.email = email;
		this.message = message;
		this.pan = pan;
	}
	public BSUsersNotInsertedResponse() {
	}

    
}
