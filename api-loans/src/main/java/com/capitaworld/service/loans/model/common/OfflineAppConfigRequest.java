package com.capitaworld.service.loans.model.common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author akshay
 *
 */
@Entity
@Table(name = "fp_offline_app_config")
public class OfflineAppConfigRequest extends AuditActivityRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long orgId;
	
	private Integer loanType;
	
	private Integer businessTypeId;
	
	private String marketPlace;
	
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
