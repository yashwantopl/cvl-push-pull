package com.opl.mudra.api.itr.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRMobileRequest implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private Long applicationId;
	private Long userId;
	private String appReq;
	private String req;
	private String mobile;
	private String model;
	private String os;
	private String tk;
	private String osvrsn;
	private String imei;
	private Long id;
	private Object data;
	
	public ITRMobileRequest() {
		//Do Nothing because of X and Y.
	}
	
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAppReq() {
		return appReq;
	}
	public void setAppReq(String appReq) {
		this.appReq = appReq;
	}
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getTk() {
		return tk;
	}
	public void setTk(String tk) {
		this.tk = tk;
	}
	public String getOsvrsn() {
		return osvrsn;
	}
	public void setOsvrsn(String osvrsn) {
		this.osvrsn = osvrsn;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}