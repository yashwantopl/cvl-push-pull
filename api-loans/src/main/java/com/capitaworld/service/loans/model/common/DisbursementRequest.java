package com.capitaworld.service.loans.model.common;

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





}
