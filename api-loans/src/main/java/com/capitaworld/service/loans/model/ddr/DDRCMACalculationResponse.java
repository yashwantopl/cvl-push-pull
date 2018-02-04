package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;

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
	
	
	
	
	

	
	
}
