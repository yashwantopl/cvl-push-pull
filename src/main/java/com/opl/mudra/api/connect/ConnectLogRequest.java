package com.opl.mudra.api.connect;

import java.util.Date;

public class ConnectLogRequest {

	private Date fromDate;
	private Date toDate;
	private Integer businessTypeId;
	private Integer stage;
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	@Override
	public String toString() {
		return "ConnectLogRequest [fromDate=" + fromDate + ", toDate=" + toDate + ", businessTypeId=" + businessTypeId
				+ ", stage=" + stage + "]";
	}
	
	
	
}
