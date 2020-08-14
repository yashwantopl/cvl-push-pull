package com.opl.mudra.api.user.model.mobile;

import java.io.Serializable;

public class MobileUserResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private boolean isOtpVerified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isOtpVerified() {
		return isOtpVerified;
	}

	public void setOtpVerified(boolean isOtpVerified) {
		this.isOtpVerified = isOtpVerified;
	}
	
	
	
	
}
