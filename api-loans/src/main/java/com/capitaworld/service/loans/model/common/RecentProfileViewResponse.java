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
