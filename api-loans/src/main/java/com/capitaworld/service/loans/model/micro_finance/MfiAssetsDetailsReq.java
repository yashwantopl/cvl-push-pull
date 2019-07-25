package com.capitaworld.service.loans.model.micro_finance;

import java.io.Serializable;
import java.util.List;


public class MfiAssetsDetailsReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private Integer assetsLiabilityType;

	private String assetOwnerDetail;

	private Double amount;

	private Double outstanding;

	private Integer particulars;

	private Integer type; // for assets or liability

	private List<MfiAssetsDetailsReq> assetsDetails;
	private List<MfiAssetsDetailsReq> liabilityDetails;

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
			Integer type) {
		this.applicationId = applicationId;
		this.assetsLiabilityType = assetsLiabilityType;
		this.amount = amount;
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

}
