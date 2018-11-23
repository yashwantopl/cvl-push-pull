package com.capitaworld.service.loans.domain.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the fs_corporate_achievement_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_achievement_details")
public class AchievementDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@ManyToOne
	@JoinColumn(name = "proposal_id")
	private ApplicationProposalMapping applicationProposalMapping;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="is_active")
	private Boolean isActive;

	@Lob
	@Column(name="milestone_achieved_detail")
	private String milestoneAchievedDetail;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	private String year;

	public AchievementDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getMilestoneAchievedDetail() {
		return this.milestoneAchievedDetail;
	}

	public void setMilestoneAchievedDetail(String milestoneAchievedDetail) {
		this.milestoneAchievedDetail = milestoneAchievedDetail;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ApplicationProposalMapping getApplicationProposalMapping() {
		return applicationProposalMapping;
	}

	public void setApplicationProposalMapping(ApplicationProposalMapping applicationProposalMapping) {
		this.applicationProposalMapping = applicationProposalMapping;
	}
}