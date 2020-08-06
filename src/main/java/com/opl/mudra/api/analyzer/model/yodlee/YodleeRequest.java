package com.opl.mudra.api.analyzer.model.yodlee;

import java.util.List;

public class YodleeRequest {

	private Long applicationId;
	private Long userId;
	private Long yodleeBankId;
	private String userToken;

	private List<Row> rows;
	private String uname;
	private String pass;

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getYodleeBankId() {
		return yodleeBankId;
	}

	public void setYodleeBankId(Long yodleeBankId) {
		this.yodleeBankId = yodleeBankId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	@Override
	public String toString() {
		return "YodleeRequest [applicationId=" + applicationId + ", yodleeBankId=" + yodleeBankId + ", uname=" + uname
				+ ", pass=" + pass + "]";
	}

}
