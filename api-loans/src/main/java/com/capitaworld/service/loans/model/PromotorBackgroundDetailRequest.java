package com.capitaworld.service.loans.model;

import java.io.Serializable;


/**
 * The persistent class for the fs_corporate_promotor_background_details database table.
 * 
 */
public class PromotorBackgroundDetailRequest implements Serializable {

	private Long id;

	private String achivements;

	private String address;

	private Double age;

	private Long applicationId;

	private Boolean mrOrMrs;

	private String panNo;

	private String promotorsName;

	private String qualification;

	private Double totalExperience;

	public PromotorBackgroundDetailRequest() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAchivements() {
		return this.achivements;
	}

	public void setAchivements(String achivements) {
		this.achivements = achivements;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getAge() {
		return this.age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public Boolean getMrOrMrs() {
		return this.mrOrMrs;
	}

	public void setMrOrMrs(Boolean mrOrMrs) {
		this.mrOrMrs = mrOrMrs;
	}

	public String getPanNo() {
		return this.panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getPromotorsName() {
		return this.promotorsName;
	}

	public void setPromotorsName(String promotorsName) {
		this.promotorsName = promotorsName;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Double getTotalExperience() {
		return this.totalExperience;
	}

	public void setTotalExperience(Double totalExperience) {
		this.totalExperience = totalExperience;
	}

}