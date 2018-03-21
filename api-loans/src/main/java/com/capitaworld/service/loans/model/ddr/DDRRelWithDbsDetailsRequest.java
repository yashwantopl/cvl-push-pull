package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRRelWithDbsDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long ddrFormId;

	private String typeOfRel;
	
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

	public String getTypeOfRel() {
		return typeOfRel;
	}

	public void setTypeOfRel(String typeOfRel) {
		this.typeOfRel = typeOfRel;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDRRelWithDbsDetailsRequest [id=" + id + ", ddrFormId=" + ddrFormId + ", typeOfRel=" + typeOfRel
				+ ", referenceNo=" + referenceNo + ", comment=" + comment + ", isActive=" + isActive + "]";
	}
	
	public static void printFields(Object obj) throws Exception {
        Field[] fields = DDRRelWithDbsDetailsRequest.class.getDeclaredFields();
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
