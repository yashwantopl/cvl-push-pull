package com.capitaworld.service.loans.model;

import java.io.Serializable;

import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_corporate_security_corporate_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityCorporateDetailRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amount;

	private Long applicationId;

	private String primarySecurityName;
	
	private Boolean isActive = true;
	
	private String amountString;
	

	public String getAmountString() {
		return amountString;
	}

	public void setAmountString(String amountString) {
		this.amountString = amountString;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public SecurityCorporateDetailRequest() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public String getPrimarySecurityName() {
		return this.primarySecurityName;
	}

	public void setPrimarySecurityName(String primarySecurityName) {
		this.primarySecurityName = primarySecurityName;
	}

	
	public static void printFields(Object obj) throws Exception {
        Field[] fields = SecurityCorporateDetailRequest.class.getDeclaredFields();
        
        for(Field field : fields) {
            Object value = field.get(obj);
            if(value instanceof String){
             String a = value.toString().replaceAll("&", "&amp;");
             value = a;
             field.set(obj, value);
            }
        }
    }
}