package com.capitaworld.service.loans.model.common;

public class SanctioningDetailResponse {

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

    private Double sanctionAmount;
    private Double tenure;
    private Double roi;
    private Double processingFee;
    private Long branch;
    private Long userOrgId;
    private Long userId;


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

    public String getFsName() {
        return fsName;
    }

    public void setFsName(String fsName) {
        this.fsName = fsName;
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

    public Double getSanctionAmount() {
        return sanctionAmount;
    }

    public void setSanctionAmount(Double sanctionAmount) {
        this.sanctionAmount = sanctionAmount;
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

    public Double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Double processingFee) {
        this.processingFee = processingFee;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public Long getUserOrgId() {
        return userOrgId;
    }

    public void setUserOrgId(Long userOrgId) {
        this.userOrgId = userOrgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
