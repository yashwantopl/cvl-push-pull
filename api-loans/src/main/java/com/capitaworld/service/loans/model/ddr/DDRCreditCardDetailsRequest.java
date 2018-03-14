package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRCreditCardDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long ddrFormId;

	private String bankName;
	
	private String creditCard;

	private String referenceNo;
	
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

	@Override
	public String toString() {
		return "DDRCreditCardDetailsRequest [id=" + id + ", ddrFormId=" + ddrFormId + ", bankName=" + bankName
				+ ", creditCard=" + creditCard  + ", referenceNo="
				+ referenceNo + ", isActive=" + isActive + "]";
	}
	
	public static void printFields(Object obj) throws Exception {
        Field[] fields = DDRCreditCardDetailsRequest.class.getDeclaredFields();
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
