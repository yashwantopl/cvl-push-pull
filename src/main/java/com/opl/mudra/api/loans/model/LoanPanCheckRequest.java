/*
* @author harshit
*/
/**
 * @author harshit
 */
package com.opl.mudra.api.loans.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanPanCheckRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer typeId;
	private Integer selectedLoanTypeId;
	private Long applicationId;
	private String pan;
	private Boolean isExist;
	private String message;
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getSelectedLoanTypeId() {
		return selectedLoanTypeId;
	}
	public void setSelectedLoanTypeId(Integer selectedLoanTypeId) {
		this.selectedLoanTypeId = selectedLoanTypeId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public Boolean getIsExist() {
		return isExist;
	}
	public void setIsExist(Boolean isExist) {
		this.isExist = isExist;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "LoanPanCheckRequest [typeId=" + typeId + ", selectedLoanTypeId=" + selectedLoanTypeId
				+ ", applicationId=" + applicationId + ", pan=" + pan + ", isExist=" + isExist + ", message=" + message
				+ "]";
	}
	
	
	
	

}
