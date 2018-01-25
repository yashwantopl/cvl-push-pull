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
 * The persistent class for the fs_ddr_credit_card_details database table.
 * 
 */
@Entity
@Table(name="fs_ddr_credit_card_details")
@NamedQuery(name="DDRCreditCardDetails.findAll", query="SELECT a FROM DDRCreditCardDetails a")
public class DDRCreditCardDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fs_ddr_form_id")
	private Long ddrFormId;

	@Column(name="bank_name")
	private String bankName;
	
	@Column(name ="credit_card")
	private Double creditCard;

	@Column(name ="any_other_showroom")
	private String anyOtherShowroom;
	
	@Column(name ="reference_no")
	private String referenceNo;
	
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Double getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Double creditCard) {
		this.creditCard = creditCard;
	}

	public String getAnyOtherShowroom() {
		return anyOtherShowroom;
	}

	public void setAnyOtherShowroom(String anyOtherShowroom) {
		this.anyOtherShowroom = anyOtherShowroom;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDRCreditCardDetails [id=" + id + ", ddrFormId=" + ddrFormId + ", bankName=" + bankName
				+ ", creditCard=" + creditCard + ", anyOtherShowroom=" + anyOtherShowroom + ", referenceNo="
				+ referenceNo + ", isActive=" + isActive + "]";
	}
	
	
	

}
