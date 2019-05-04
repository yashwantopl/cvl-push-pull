package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.AuditActivity;

@Entity
@Table(name = "retail_model_temp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RetailModelTemp extends AuditActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "org_id")
	private Long orgId;
	
	@Column(name = "business_type_id")
	private Integer businessTypeId;

	@Column(name = "job_id")
	private Long jobId;
	
	@Column(name="is_approved")
    private Boolean isApproved;

    @Column(name="is_deleted")
    private Boolean isDeleted;

    @Column(name="is_copied")
    private Boolean isCopied;

    @Column(name="is_edit")
    private Boolean isEdit;

    @Column(name="status_id")
    private Integer statusId;

    @Column(name="approval_date")
    private Date approvalDate;
	
	public RetailModelTemp() {
	}
	
	public RetailModelTemp(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsCopied() {
		return isCopied;
	}

	public void setIsCopied(Boolean isCopied) {
		this.isCopied = isCopied;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	
}