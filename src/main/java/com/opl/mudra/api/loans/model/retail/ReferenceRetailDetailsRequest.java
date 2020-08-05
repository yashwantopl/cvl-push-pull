package com.opl.mudra.api.loans.model.retail;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.opl.mudra.api.loans.exception.LoansException;


/**
 * @author Sanket
 *
 */
public class ReferenceRetailDetailsRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String address;

	private String email;

	private Boolean isActive = true ;

	private String mobile;

	private String name;

	private Integer referencesListId;

	private String telephone;
	
	private String relationshipWithApplicant;

	private Long pincode;
	
	//FOR CAM
	private String referncesList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getReferencesListId() {
		return referencesListId;
	}

	public void setReferencesListId(Integer referencesListId) {
		this.referencesListId = referencesListId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRelationshipWithApplicant() {
		return relationshipWithApplicant;
	}

	public void setRelationshipWithApplicant(String relationshipWithApplicant) {
		this.relationshipWithApplicant = relationshipWithApplicant;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	public String getReferncesList() {
		return referncesList;
	}

	public void setReferncesList(String referncesList) {
		this.referncesList = referncesList;
	}

	public static void printFields(Object obj) throws LoansException {
		try {
			Field[] fields = ReferenceRetailDetailsRequest.class.getDeclaredFields();

			for(Field field : fields) {
				Object value = field.get(obj);
				if(value instanceof String){
					String a = value.toString().replaceAll("&", "&amp;");
					value = a;
					field.set(obj, value);
				}
			}
		}
		catch (Exception e){
			throw new LoansException(e);
		}

    }
	
}
