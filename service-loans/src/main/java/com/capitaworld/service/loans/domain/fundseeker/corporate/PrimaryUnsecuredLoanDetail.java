package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

@Entity
@Table(name = "fs_corporate_primary_unsecured_loan_details")
@NamedQuery(name = "PrimaryUnsecuredLoanDetail.findAll", query = "SELECT p FROM PrimaryUnsecuredLoanDetail p")
public class PrimaryUnsecuredLoanDetail extends LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;
	
	@Column(name = "credit_rating_id")
	private Integer creditRatingId;

	@Lob
	@Column(name = "purpose_of_loan")
	private String purposeOfLoan;
	
	@Column(name = "share_price_face_value")
	private Double sharePriceFace;
	
	@Column(name = "share_price_market_value")
	private Double sharePriceMarket;

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getCreditRatingId() {
		return creditRatingId;
	}

	public void setCreditRatingId(Integer creditRatingId) {
		this.creditRatingId = creditRatingId;
	}

	public String getPurposeOfLoan() {
		return purposeOfLoan;
	}

	public void setPurposeOfLoan(String purposeOfLoan) {
		this.purposeOfLoan = purposeOfLoan;
	}

	public Double getSharePriceFace() {
		return sharePriceFace;
	}

	public void setSharePriceFace(Double sharePriceFace) {
		this.sharePriceFace = sharePriceFace;
	}

	public Double getSharePriceMarket() {
		return sharePriceMarket;
	}

	public void setSharePriceMarket(Double sharePriceMarket) {
		this.sharePriceMarket = sharePriceMarket;
	}

	
	
}