package com.opl.mudra.api.loans.model.ddr;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.exception.LoansException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRCreditCardDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long ddrFormId;

	private String bankName;
	
	private String creditCard;

	private String referenceNo;
	
	private String comment;
	
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDdrFormId() {
		return ddrFormId;
	}

	public void setDdrFormId(Long ddrFormId) {
		this.ddrFormId = ddrFormId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
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
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	@Override
	public String toString() {
		return "DDRCreditCardDetailsRequest [id=" + id + ", ddrFormId=" + ddrFormId + ", bankName=" + bankName
				+ ", creditCard=" + creditCard + ", referenceNo=" + referenceNo + ", comment=" + comment + ", isActive="
				+ isActive + "]";
	}

	public static void printFields(Object obj) throws LoansException {
		try {
			Field[] fields = DDRCreditCardDetailsRequest.class.getDeclaredFields();
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
