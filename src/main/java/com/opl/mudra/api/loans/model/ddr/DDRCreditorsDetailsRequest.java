package com.opl.mudra.api.loans.model.ddr;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.exception.LoansException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRCreditorsDetailsRequest  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long ddrFormId;

	private String name;
	
	private Double amount;
	
	private Double avgCreditorTurnoverPeriod;
	
	private String comment;

	private Boolean isActive;
	
	private String amountStr;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public Double getAvgCreditorTurnoverPeriod() {
		return avgCreditorTurnoverPeriod;
	}

	public void setAvgCreditorTurnoverPeriod(Double avgCreditorTurnoverPeriod) {
		this.avgCreditorTurnoverPeriod = avgCreditorTurnoverPeriod;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAmountStr() {
		return amountStr;
	}

	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}

	@Override
	public String toString() {
		return "DDRCreditorsDetailsRequest [id=" + id + ", ddrFormId=" + ddrFormId + ", name=" + name + ", amount="
				+ amount + ", avgCreditorTurnoverPeriod=" + avgCreditorTurnoverPeriod + ", comment=" + comment
				+ ", isActive=" + isActive + "]";
	}

	public static void printFields(Object obj) throws LoansException {
		try{
			Field[] fields = DDRCreditorsDetailsRequest.class.getDeclaredFields();
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
