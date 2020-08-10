package com.opl.service.loans.domain.fundseeker.mfi;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fs_mfi_assets_liability_details")
public class MfiAssetsLiabilityDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "application_id")
	private Long applicationId;

	@Column(name = "assets_liability_type")
	private Integer assetsLiabilityType;

	@Column(name = "asset_owner_detail")
	private String assetOwnerDetail;

	private Double amount;

	private Double outstanding;

	private Integer particulars;

	private Integer type; // for assets or liability

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

}
