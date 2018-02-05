package com.capitaworld.service.loans.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by dhaval on 21-May-17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromotorBackgroundDetailResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String achivements;

	private String address;

	private Double age;

	private String panNo;

	private String promotorsName;

	private String qualification;

	private Double totalExperience;

	private Double networth;

	public String getAchivements() {
		return achivements;
	}

	public void setAchivements(String achivements) {
		this.achivements = achivements;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getPromotorsName() {
		return promotorsName;
	}

	public void setPromotorsName(String promotorsName) {
		this.promotorsName = promotorsName;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Double getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(Double totalExperience) {
		this.totalExperience = totalExperience;
	}

	public Double getNetworth() {
		return networth;
	}

	public void setNetworth(Double networth) {
		this.networth = networth;
	}

}
