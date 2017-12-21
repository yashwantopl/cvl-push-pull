package com.capitaworld.service.loans.model;


public class FundProviderProposalDetails {

	
	private Long id;
	
	private String name;
	
	private String fpProductName;
	
	private String whoAreYou;
	
	private String fpType;
	
	private String imagePath;
	
	private Long productId;
	
	private Long proposalMappingId;
	
	private Double elAmount;
	
	private Double elTenure;
	
	private Double elRoi;
	

	
	
	public String getFpProductName() {
		return fpProductName;
	}

	public void setFpProductName(String fpProductName) {
		this.fpProductName = fpProductName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWhoAreYou() {
		return whoAreYou;
	}

	public void setWhoAreYou(String whoAreYou) {
		this.whoAreYou = whoAreYou;
	}

	public String getFpType() {
		return fpType;
	}

	public void setFpType(String fpType) {
		this.fpType = fpType;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getProposalMappingId() {
		return proposalMappingId;
	}

	public void setProposalMappingId(Long proposalMappingId) {
		this.proposalMappingId = proposalMappingId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getElAmount() {
		return elAmount;
	}

	public void setElAmount(Double elAmount) {
		this.elAmount = elAmount;
	}

	public Double getElTenure() {
		return elTenure;
	}

	public void setElTenure(Double elTenure) {
		this.elTenure = elTenure;
	}

	public Double getElRoi() {
		return elRoi;
	}

	public void setElRoi(Double elRoi) {
		this.elRoi = elRoi;
	}
	
	
	
}
