package com.capitaworld.service.loans.domain.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.AuditActivity;

/**
 * @author harshit
 * Date : 09-Jun-2018
 * About :- USER LOAN AMOUNT MAPPING STOARE MIN AND MAX AMOUNT BASED ON FP USERID AND PRODUCT ID FOR DISBURSEMENT
 */
@Entity
@Table(name = "fp_offline_app_config")
public class OfflineAppConfig extends AuditActivity implements Serializable {

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
	
	@Column(name = "market_place")
	private String marketPlace;
	
	@Column(name = "bank_specific")
	private String bankSpecific;

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

	public String getMarketPlace() {
		return marketPlace;
	}

	public void setMarketPlace(String marketPlace) {
		this.marketPlace = marketPlace;
	}

	public String getBankSpecific() {
		return bankSpecific;
	}

	public void setBankSpecific(String bankSpecific) {
		this.bankSpecific = bankSpecific;
	}
		
}
