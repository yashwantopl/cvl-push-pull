package com.capitaworld.service.loans.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_corporate_achievement_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AchievementDetailRequest implements Serializable {

	/**
	 * 
	 */

	private Long id;

	private String milestoneAchievedDetail;

	private String year;

	private Boolean isActive=true;
	
	

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMilestoneAchievedDetail() {
		return this.milestoneAchievedDetail;
	}

	public void setMilestoneAchievedDetail(String milestoneAchievedDetail) {
		this.milestoneAchievedDetail = milestoneAchievedDetail;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	

}