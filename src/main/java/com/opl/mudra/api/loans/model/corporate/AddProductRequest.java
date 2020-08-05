package com.opl.mudra.api.loans.model.corporate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddProductRequest {

	private Integer productId;

	private Long productMappingId;

	private Long userId;

	private Long clientId;

	private String name;
	
	private String fpName;
	
	private Integer stage;

	private Long businessTypeId;
	
	private Long loanId;

	private Integer wcRenewalStatus;
	
	private Integer finId;
	
	private List<Integer> gstType;

	private Long importFromId;

	private Boolean isGst;

	private Boolean isItr;

	private Boolean isBankStatement;

	private Boolean isMca;

	private Boolean isBureuPersonal;

	private Boolean isBureuCommercial;

	private Boolean isManualFill;
	
	private Integer productType;
	
	private Long roleId;
	
	private List<Integer> bankStatementOptions;

	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Long getProductMappingId() {
		return productMappingId;
	}

	public void setProductMappingId(Long productMappingId) {
		this.productMappingId = productMappingId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFpName() {
		return fpName;
	}

	public void setFpName(String fpName) {
		this.fpName = fpName;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public Integer getWcRenewalStatus() {
		return wcRenewalStatus;
	}

	public void setWcRenewalStatus(Integer wcRenewalStatus) {
		this.wcRenewalStatus = wcRenewalStatus;
	}

	public Integer getFinId() {
		return finId;
	}

	public void setFinId(Integer finId) {
		this.finId = finId;
	}

	public List<Integer> getGstType() {
		return gstType;
	}

	public void setGstType(List<Integer> gstType) {
		this.gstType = gstType;
	}


	public Long getImportFromId() {
		return importFromId;
	}

	public void setImportFromId(Long importFromId) {
		this.importFromId = importFromId;
	}

	public Boolean getIsGst() {
		return isGst;
	}

	public void setIsGst(Boolean isGst) {
		this.isGst = isGst;
	}

	public Boolean getIsItr() {
		return isItr;
	}

	public void setIsItr(Boolean isItr) {
		this.isItr = isItr;
	}

	public Boolean getIsBankStatement() {
		return isBankStatement;
	}

	public void setIsBankStatement(Boolean isBankStatement) {
		this.isBankStatement = isBankStatement;
	}

	public Boolean getIsMca() {
		return isMca;
	}

	public void setIsMca(Boolean isMca) {
		this.isMca = isMca;
	}

	public Boolean getIsBureuPersonal() {
		return isBureuPersonal;
	}

	public void setIsBureuPersonal(Boolean isBureuPersonal) {
		this.isBureuPersonal = isBureuPersonal;
	}

	public Boolean getIsBureuCommercial() {
		return isBureuCommercial;
	}

	public void setIsBureuCommercial(Boolean isBureuCommercial) {
		this.isBureuCommercial = isBureuCommercial;
	}

	public Boolean getIsManualFill() {
		return isManualFill;
	}

	public void setIsManualFill(Boolean isManualFill) {
		this.isManualFill = isManualFill;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getBankStatementOptions() {
		return bankStatementOptions;
	}

	public void setBankStatementOptions(List<Integer> bankStatementOptions) {
		this.bankStatementOptions = bankStatementOptions;
	}
}
