package com.capitaworld.service.loans.model.common;

import java.io.Serializable;

/**
 * @author Sanket
 *
 */
public class RecentProfileViewResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String address;
	private String profilePic;
	private String proPicFileName;
	private Long userId;
	private Long applicationId;
	private Long productId;	
	private String fpProductName;
	private String whoAreYou;
	private String fpType;
	private String imagePath;
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
	public Long getUserId() {
		return userId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public String getProPicFileName() {
		return proPicFileName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public void setProPicFileName(String proPicFileName) {
		this.proPicFileName = proPicFileName;
	}
	
	

}
