/**
 * 
 */
package com.opl.service.loans.domain.sidbi;

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

/**
 * @author vijay.chauhan
 *
 */

@Entity
@Table(name="fs_sidbi_primary_collateral_security")
public class PrimaryCollateralSecurity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="application_id")
	private Long applicationId;
	
	@Column(name="particulars")
	private String particulars;
	
	@Column(name="other_particular")
	private String otherParticular;
	
	@Column(name="name_Of_owner")
	private String nameOfOwner;
	
	@Column(name="relationship_with_applicant")
	private String relationshipWithApplicant;
	
	@Column(name="nature")
	private String nature;
	
	@Column(name="details")
	private String details;
	
	@Column(name="market_value")
	private Double marketValue;
	
	@Column(name="particulars_of_charge")
	private String particularsOfCharge;

	@Column(name="existing_charge_holder")
	private String exisChargeHolder;
	
	@Column(name="charge_offered_to_sidbi")
	private String chargeOfferedToSIDBI;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getOtherParticular() {
		return otherParticular;
	}

	public void setOtherParticular(String otherParticular) {
		this.otherParticular = otherParticular;
	}

	public String getNameOfOwner() {
		return nameOfOwner;
	}

	public void setNameOfOwner(String nameOfOwner) {
		this.nameOfOwner = nameOfOwner;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}

	public String getParticularsOfCharge() {
		return particularsOfCharge;
	}

	public void setParticularsOfCharge(String particularsOfCharge) {
		this.particularsOfCharge = particularsOfCharge;
	}

	public String getChargeOfferedToSIDBI() {
		return chargeOfferedToSIDBI;
	}

	public void setChargeOfferedToSIDBI(String chargeOfferedToSIDBI) {
		this.chargeOfferedToSIDBI = chargeOfferedToSIDBI;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
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

	public String getRelationshipWithApplicant() {
		return relationshipWithApplicant;
	}

	public void setRelationshipWithApplicant(String relationshipWithApplicant) {
		this.relationshipWithApplicant = relationshipWithApplicant;
	}

	public String getExisChargeHolder() {
		return exisChargeHolder;
	}

	public void setExisChargeHolder(String exisChargeHolder) {
		this.exisChargeHolder = exisChargeHolder;
	}

	
	
}
