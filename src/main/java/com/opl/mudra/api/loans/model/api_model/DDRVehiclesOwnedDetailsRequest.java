package com.opl.mudra.api.loans.model.api_model;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRVehiclesOwnedDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long applicationId;

	private String bankName;
	
	private String vehiclesOwned;

	private String referenceNo;
	
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


	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getVehiclesOwned() {
		return vehiclesOwned;
	}

	public void setVehiclesOwned(String vehiclesOwned) {
		this.vehiclesOwned = vehiclesOwned;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDRVehiclesOwnedDetailsReqeust [id=" + id + ", applicationId=" + applicationId + ", bankName=" + bankName
				+ ", vehiclesOwned=" + vehiclesOwned + ", referenceNo=" + referenceNo + ", isActive=" + isActive + "]";
	}
	
	public static void printFields(Object obj) throws Exception {
        Field[] fields = DDRVehiclesOwnedDetailsRequest.class.getDeclaredFields();
        System.out.println("length : "+fields.length);
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
