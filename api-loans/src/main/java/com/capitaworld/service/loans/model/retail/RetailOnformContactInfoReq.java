package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

import com.capitaworld.service.loans.model.Address;

/**
 * @author harshit
 * 03/06/2019
 */
public class RetailOnformContactInfoReq implements Serializable {
    
	private static final long serialVersionUID = 1L;
	// Common Fields
    private Long id;
    private Long clientId;
    private Long applicationId;
    private Address contactAddress;
    private Long coAppId;
    private Long userId;
    private Boolean isContactInfoFilled;
    private Integer residenceType;
    private Integer residenceSinceYear;
    private Integer residenceSinceMonth;
    private Integer type;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Address getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(Address contactAddress) {
		this.contactAddress = contactAddress;
	}
	public Long getCoAppId() {
		return coAppId;
	}
	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Boolean getIsContactInfoFilled() {
		return isContactInfoFilled;
	}
	public void setIsContactInfoFilled(Boolean isContactInfoFilled) {
		this.isContactInfoFilled = isContactInfoFilled;
	}
	public Integer getResidenceType() {
		return residenceType;
	}
	public void setResidenceType(Integer residenceType) {
		this.residenceType = residenceType;
	}
	public Integer getResidenceSinceYear() {
		return residenceSinceYear;
	}
	public void setResidenceSinceYear(Integer residenceSinceYear) {
		this.residenceSinceYear = residenceSinceYear;
	}
	public Integer getResidenceSinceMonth() {
		return residenceSinceMonth;
	}
	public void setResidenceSinceMonth(Integer residenceSinceMonth) {
		this.residenceSinceMonth = residenceSinceMonth;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
    
    
    
    
    
    
}
