package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.AuditActivity;

/**
 * The persistent class for the fp_product_master database table.
 * 
 */
@Entity
@Table(name = "retail_model")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RetailModel extends AuditActivity implements Serializable {
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
	
	@Column(name = "retail_model_temp_ref_id")
	private Long retailModelTempRefId;
	
	public RetailModel() {
	}
	
	public RetailModel(Long id) {
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

	public Long getRetailModelTempRefId() {
		return retailModelTempRefId;
	}

	public void setRetailModelTempRefId(Long retailModelTempRefId) {
		this.retailModelTempRefId = retailModelTempRefId;
	}
}