package com.capitaworld.service.loans.domain.fundseeker.ddr;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by : harshit
 * The persistent class for the fs_ddr_other_bank_loan_details database table.
 * 
 */
@Entity
@Table(name="fs_ddr_other_bank_loan_details")
@NamedQuery(name="DDROtherBankLoanDetails.findAll", query="SELECT a FROM DDROtherBankLoanDetails a")
public class DDROtherBankLoanDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fs_ddr_form_id")
	private Long ddrFormId;

	@Column(name="type_of_rel")
	private String typeOfRel;
	
	@Column(name ="reference_no")
	private Double referenceNo;

	private String comment;
	
	@Column(name = "is_active")
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDdrFormId() {
		return ddrFormId;
	}

	public void setDdrFormId(Long ddrFormId) {
		this.ddrFormId = ddrFormId;
	}

	public String getTypeOfRel() {
		return typeOfRel;
	}

	public void setTypeOfRel(String typeOfRel) {
		this.typeOfRel = typeOfRel;
	}

	public Double getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(Double referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDROtherBankLoanDetails [id=" + id + ", ddrFormId=" + ddrFormId + ", typeOfRel=" + typeOfRel
				+ ", referenceNo=" + referenceNo + ", comment=" + comment + ", isActive=" + isActive + "]";
	}
	
	

}
