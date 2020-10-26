package com.opl.mudra.api.cibil_integration;

import java.util.Arrays;

public class EncryptionDetail {

	public String headerKey;
	public String encryptionRequestBody;
	public String digitalSignature;

	// public byte[] IV;

	private String userName;

	private String password;

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getEncryptionRequestBody() {
		return encryptionRequestBody;
	}

	public void setEncryptionRequestBody(String encryptionRequestBody) {
		this.encryptionRequestBody = encryptionRequestBody;
	}

	public String getDigitalSignature() {
		return digitalSignature;
	}

	public void setDigitalSignature(String digitalSignature) {
		this.digitalSignature = digitalSignature;
	}

	/*public byte[] getIV() {
		return IV;
	}

	public void setIV(byte[] iV) {
		IV = iV;
	}*/

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EncryptionDetail [headerKey=" + headerKey + ", encryptionRequestBody=" + encryptionRequestBody
				+ ", digitalSignature=" + digitalSignature + ", userName=" + userName
				+ ", password=" + password + "]";
	}


}