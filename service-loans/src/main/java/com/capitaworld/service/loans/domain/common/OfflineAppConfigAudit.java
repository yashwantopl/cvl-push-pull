package com.capitaworld.service.loans.domain.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.AuditActivity;

/**
 * @author harshit
 * Date : 09-Jun-2018
 * About :- USER LOAN AMOUNT MAPPING STOARE MIN AND MAX AMOUNT BASED ON FP USERID AND PRODUCT ID FOR DISBURSEMENT
 */
@Entity
@Table(name = "fp_offline_app_config_audit")
public class OfflineAppConfigAudit extends AuditActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "org_id")
	private Long orgId;
	
	@Column(name = "loan_type")
	private Integer loanType;
	
	@Column(name = "business_type_id")
	private Integer businessTypeId;
	
	@Column(name = "field_value")
	private String fieldValue ;
	
	@Column(name = "field_type")
	private Integer fieldType ;
	
	@Column(name = "difference")
	private String difference;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="from_date")
    private Date fromDate ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Integer getFieldType() {
		return fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}
	
}
