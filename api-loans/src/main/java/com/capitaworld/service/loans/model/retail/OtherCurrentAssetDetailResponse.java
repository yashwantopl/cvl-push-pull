package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

public class OtherCurrentAssetDetailResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String assetType;
	private String assetDescription;
	private String assetValue;
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetDescription() {
		return assetDescription;
	}
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}
	public String getAssetValue() {
		return assetValue;
	}
	public void setAssetValue(String assetValue) {
		this.assetValue = assetValue;
	}
	
	
	
}
