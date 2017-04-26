package com.capitaworld.service.loans.model;

import java.io.Serializable;


/**
 * The persistent class for the fs_corporate_achievement_details database table.
 * 
 */
public class AchievementDetailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private String milestoneAchievedDetail;

	private String year;

	
	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

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

}