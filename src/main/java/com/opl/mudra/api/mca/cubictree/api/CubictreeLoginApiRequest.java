package com.opl.mudra.api.mca.cubictree.api;

public class CubictreeLoginApiRequest {

	String url;
	LoginApiRowPayload loginApiRowPayload;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public LoginApiRowPayload getLoginApiRowPayload() {
		return loginApiRowPayload;
	}
	public void setLoginApiRowPayload(LoginApiRowPayload loginApiRowPayload) {
		this.loginApiRowPayload = loginApiRowPayload;
	}
	@Override
	public String toString() {
		return "LoginApiRequest [url=" + url + ", loginApiRowPayload=" + loginApiRowPayload + "]";
	}
	
	
}
