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
