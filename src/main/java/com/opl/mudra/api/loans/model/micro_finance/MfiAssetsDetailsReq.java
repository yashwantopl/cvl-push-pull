package com.opl.mudra.api.loans.model.micro_finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MfiAssetsDetailsReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long applicationId;
	private Long userId;

	private Integer assetsLiabilityType;

	private String assetOwnerDetail;

	private Double amount;

	private Double outstanding;

	private Integer particulars;

	private Integer type; // for assets or liability

	private List<MfiAssetsDetailsReq> assetsDetails;
	private List<MfiAssetsDetailsReq> liabilityDetails;
	
	private Boolean isAssetsDetailsFilled;
	
	private boolean isCurrentAssetsFilled;
	private boolean isFixedAssetsFilled;
	private boolean isCurrntLiabilityFilled;
	
	public MfiAssetsDetailsReq() {
		super();
	}

	// for assets
	public MfiAssetsDetailsReq(Long applicationId, Integer assetsLiabilityType, String assetOwnerDetail, Double amount,
			Integer particulars, Integer type) {
		this.applicationId = applicationId;
		this.assetsLiabilityType = assetsLiabilityType;
		this.assetOwnerDetail = assetOwnerDetail;
		this.amount = amount;
		this.particulars = particulars;
		this.type = type;
	}
	
	//for liability
	public MfiAssetsDetailsReq(Long applicationId, Integer assetsLiabilityType, Double amount, Double outstanding,
			Integer type,Integer particulars) {
		this.applicationId = applicationId;
		this.assetsLiabilityType = assetsLiabilityType;
		this.amount = amount;
		this.particulars = particulars;
		this.outstanding = outstanding;
		this.type = type;
	}
	
	

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

	public Integer getAssetsLiabilityType() {
		return assetsLiabilityType;
	}

	public void setAssetsLiabilityType(Integer assetsLiabilityType) {
		this.assetsLiabilityType = assetsLiabilityType;
	}

	public String getAssetOwnerDetail() {
		return assetOwnerDetail;
	}

	public void setAssetOwnerDetail(String assetOwnerDetail) {
		this.assetOwnerDetail = assetOwnerDetail;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
	}

	public Integer getParticulars() {
		return particulars;
	}

	public void setParticulars(Integer particulars) {
		this.particulars = particulars;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<MfiAssetsDetailsReq> getAssetsDetails() {
		return assetsDetails;
	}

	public void setAssetsDetails(List<MfiAssetsDetailsReq> assetsDetails) {
		this.assetsDetails = assetsDetails;
	}

	public List<MfiAssetsDetailsReq> getLiabilityDetails() {
		return liabilityDetails;
	}

	public void setLiabilityDetails(List<MfiAssetsDetailsReq> liabilityDetails) {
		this.liabilityDetails = liabilityDetails;
	}

	public Boolean getIsAssetsDetailsFilled() {
		return isAssetsDetailsFilled;
	}

	public void setIsAssetsDetailsFilled(Boolean isAssetsDetailsFilled) {
		this.isAssetsDetailsFilled = isAssetsDetailsFilled;
	}

	public boolean isCurrentAssetsFilled() {
		return isCurrentAssetsFilled;
	}

	public void setCurrentAssetsFilled(boolean isCurrentAssetsFilled) {
		this.isCurrentAssetsFilled = isCurrentAssetsFilled;
	}

	public boolean isFixedAssetsFilled() {
		return isFixedAssetsFilled;
	}

	public void setFixedAssetsFilled(boolean isFixedAssetsFilled) {
		this.isFixedAssetsFilled = isFixedAssetsFilled;
	}

	public boolean isCurrntLiabilityFilled() {
		return isCurrntLiabilityFilled;
	}

	public void setCurrntLiabilityFilled(boolean isCurrntLiabilityFilled) {
		this.isCurrntLiabilityFilled = isCurrntLiabilityFilled;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
