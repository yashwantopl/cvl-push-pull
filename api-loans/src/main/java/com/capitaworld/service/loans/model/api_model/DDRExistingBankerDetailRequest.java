package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRExistingBankerDetailRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long applicationId;

    private String financialInstitutionName;

    private String address;

    private String relationshipSince;

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    public void setFinancialInstitutionName(String financialInstitutionName) {
        this.financialInstitutionName = financialInstitutionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRelationshipSince() {
        return relationshipSince;
    }

    public void setRelationshipSince(String relationshipSince) {
        this.relationshipSince = relationshipSince;
    }

   
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "DDRExistingBankerDetailRequest{" +
                "id=" + id +
                ", applicationId=" + applicationId +
                ", financialInstitutionName='" + financialInstitutionName + '\'' +
                ", address='" + address + '\'' +
                ", relationshipSince=" + relationshipSince +
                ", isActive=" + isActive +
                '}';
    }
}
