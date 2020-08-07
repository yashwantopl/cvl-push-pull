package com.opl.mudra.api.fraudanalytics.model;

import java.util.Date;


public class FdIbaFraudRel {

	private Long id;
	
	private Long createdBy;

	private Date createdDate;

	private String frdAdvisingBank;

	private Double frdAmt;

	private Double frdAmtRecovered;

	private String frdComplaint;

	private Date frdDetectionDt;

	private String frdModusOperandi;

	private String frdNatureOfFraud;

	private String frdNo;

	private Date frdOccurenceDt;

	private String frdOperationArea;

	private String frdPrinAcName;

	private String frdPromoterName;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getFrdAdvisingBank() {
		return frdAdvisingBank;
	}

	public void setFrdAdvisingBank(String frdAdvisingBank) {
		this.frdAdvisingBank = frdAdvisingBank;
	}

	public Double getFrdAmt() {
		return frdAmt;
	}

	public void setFrdAmt(Double frdAmt) {
		this.frdAmt = frdAmt;
	}

	public Double getFrdAmtRecovered() {
		return frdAmtRecovered;
	}

	public void setFrdAmtRecovered(Double frdAmtRecovered) {
		this.frdAmtRecovered = frdAmtRecovered;
	}

	public String getFrdComplaint() {
		return frdComplaint;
	}

	public void setFrdComplaint(String frdComplaint) {
		this.frdComplaint = frdComplaint;
	}

	public Date getFrdDetectionDt() {
		return frdDetectionDt;
	}

	public void setFrdDetectionDt(Date frdDetectionDt) {
		this.frdDetectionDt = frdDetectionDt;
	}

	public String getFrdModusOperandi() {
		return frdModusOperandi;
	}

	public void setFrdModusOperandi(String frdModusOperandi) {
		this.frdModusOperandi = frdModusOperandi;
	}

	public String getFrdNatureOfFraud() {
		return frdNatureOfFraud;
	}

	public void setFrdNatureOfFraud(String frdNatureOfFraud) {
		this.frdNatureOfFraud = frdNatureOfFraud;
	}

	public String getFrdNo() {
		return frdNo;
	}

	public void setFrdNo(String frdNo) {
		this.frdNo = frdNo;
	}

	public Date getFrdOccurenceDt() {
		return frdOccurenceDt;
	}

	public void setFrdOccurenceDt(Date frdOccurenceDt) {
		this.frdOccurenceDt = frdOccurenceDt;
	}

	public String getFrdOperationArea() {
		return frdOperationArea;
	}

	public void setFrdOperationArea(String frdOperationArea) {
		this.frdOperationArea = frdOperationArea;
	}

	public String getFrdPrinAcName() {
		return frdPrinAcName;
	}

	public void setFrdPrinAcName(String frdPrinAcName) {
		this.frdPrinAcName = frdPrinAcName;
	}

	public String getFrdPromoterName() {
		return frdPromoterName;
	}

	public void setFrdPromoterName(String frdPromoterName) {
		this.frdPromoterName = frdPromoterName;
	}
	
	
}
