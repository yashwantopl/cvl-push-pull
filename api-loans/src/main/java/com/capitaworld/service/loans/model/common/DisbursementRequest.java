package com.capitaworld.service.loans.model.common;

import com.capitaworld.service.loans.model.LoanDisbursementRequest;

import java.util.List;

public class DisbursementRequest {
private Long  applicationId;
private Long productMappingId;

private String fsName;
private String fpName;
private String fsAddress;
private String fpAddress;
private String fsDesignation;
private String fpDesignation;
private String fsImage;
private String fpImage;
private String loanName;
private String fpOrganisationName;

private Double senctionedAmount;
List<LoanDisbursementRequest> loanDisbursementRequestList;
private Double tenure;
private Double roi;
private Boolean isIneligibleProposal;



public Long getApplicationId() {
	return applicationId;
}
public void setApplicationId(Long applicationId) {
	this.applicationId = applicationId;
}
public Long getProductMappingId() {
	return productMappingId;
}
public void setProductMappingId(Long productMappingId) {
	this.productMappingId = productMappingId;
}
public String getFpName() {
	return fpName;
}
public void setFpName(String fpName) {
	this.fpName = fpName;
}
public String getFsAddress() {
	return fsAddress;
}
public void setFsAddress(String fsAddress) {
	this.fsAddress = fsAddress;
}
public String getFpAddress() {
	return fpAddress;
}
public void setFpAddress(String fpAddress) {
	this.fpAddress = fpAddress;
}
public String getFsDesignation() {
	return fsDesignation;
}
public void setFsDesignation(String fsDesignation) {
	this.fsDesignation = fsDesignation;
}
public String getFpDesignation() {
	return fpDesignation;
}
public void setFpDesignation(String fpDesignation) {
	this.fpDesignation = fpDesignation;
}
public String getFsName() {
	return fsName;
}
public void setFsName(String fsName) {
	this.fsName = fsName;
}
public String getFsImage() {
	return fsImage;
}
public void setFsImage(String fsImage) {
	this.fsImage = fsImage;
}
public String getFpImage() {
	return fpImage;
}
public void setFpImage(String fpImage) {
	this.fpImage = fpImage;
}
public String getLoanName() {
	return loanName;
}
public void setLoanName(String loanName) {
	this.loanName = loanName;
}
public String getFpOrganisationName() {
	return fpOrganisationName;
}
public void setFpOrganisationName(String fpOrganisationName) {
	this.fpOrganisationName = fpOrganisationName;
}

	public Double getSenctionedAmount() {
		return senctionedAmount;
	}

	public void setSenctionedAmount(Double senctionedAmount) {
		this.senctionedAmount = senctionedAmount;
	}

	public List<LoanDisbursementRequest> getLoanDisbursementRequestList() {
		return loanDisbursementRequestList;
	}

	public void setLoanDisbursementRequestList(List<LoanDisbursementRequest> loanDisbursementRequestList) {
		this.loanDisbursementRequestList = loanDisbursementRequestList;
	}

	public Double getTenure() {
		return tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}
	public Boolean getIsIneligibleProposal() {
		return isIneligibleProposal;
	}
	public void setIsIneligibleProposal(Boolean isIneligibleProposal) {
		this.isIneligibleProposal = isIneligibleProposal;
	}
	
}
