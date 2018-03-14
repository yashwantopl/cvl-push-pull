package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRCMACalculationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer keyId;
	private String keyName;
	private Double provisionalYear;
	private Double lastYear;
	private Double lastToLastYear;
	private Double diffPvsnlAndLastYear;
	
	private String provisionalYearString;
	private String lastYearString;
	private String lastToLastYearString;
	private String diffPvsnlAndLastYearString;
	
	
	
	
	public String getProvisionalYearString() {
		return provisionalYearString;
	}
	public void setProvisionalYearString(String provisionalYearString) {
		this.provisionalYearString = provisionalYearString;
	}
	public String getLastYearString() {
		return lastYearString;
	}
	public void setLastYearString(String lastYearString) {
		this.lastYearString = lastYearString;
	}
	public String getLastToLastYearString() {
		return lastToLastYearString;
	}
	public void setLastToLastYearString(String lastToLastYearString) {
		this.lastToLastYearString = lastToLastYearString;
	}
	public String getDiffPvsnlAndLastYearString() {
		return diffPvsnlAndLastYearString;
	}
	public void setDiffPvsnlAndLastYearString(String diffPvsnlAndLastYearString) {
		this.diffPvsnlAndLastYearString = diffPvsnlAndLastYearString;
	}
	public Integer getKeyId() {
		return keyId;
	}
	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public Double getProvisionalYear() {
		return provisionalYear;
	}
	public void setProvisionalYear(Double provisionalYear) {
		this.provisionalYear = provisionalYear;
	}
	public Double getLastYear() {
		return lastYear;
	}
	public void setLastYear(Double lastYear) {
		this.lastYear = lastYear;
	}
	public Double getLastToLastYear() {
		return lastToLastYear;
	}
	public void setLastToLastYear(Double lastToLastYear) {
		this.lastToLastYear = lastToLastYear;
	}
	public Double getDiffPvsnlAndLastYear() {
		return diffPvsnlAndLastYear;
	}
	public void setDiffPvsnlAndLastYear(Double diffPvsnlAndLastYear) {
		this.diffPvsnlAndLastYear = diffPvsnlAndLastYear;
	}
	
	
	public static void printFields(Object obj) throws Exception {
        Field[] fields = DDRCMACalculationResponse.class.getDeclaredFields();
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
