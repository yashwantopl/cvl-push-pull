package com.opl.mudra.api.fraudanalytics.model;

import java.util.Date;

public class FdRbiDefaulterListRel {

	private Long id;
	
	private Date bsAsondt;

	private Integer bsBrCd;

	private String bsCust;

	private String bsFlag;

	private String bsLender;

	private String bsPan;

	private Long createdBy;

	private Date createdDate;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBsAsondt() {
		return bsAsondt;
	}

	public void setBsAsondt(Date bsAsondt) {
		this.bsAsondt = bsAsondt;
	}

	public Integer getBsBrCd() {
		return bsBrCd;
	}

	public void setBsBrCd(Integer bsBrCd) {
		this.bsBrCd = bsBrCd;
	}

	public String getBsCust() {
		return bsCust;
	}

	public void setBsCust(String bsCust) {
		this.bsCust = bsCust;
	}

	public String getBsFlag() {
		return bsFlag;
	}

	public void setBsFlag(String bsFlag) {
		this.bsFlag = bsFlag;
	}

	public String getBsLender() {
		return bsLender;
	}

	public void setBsLender(String bsLender) {
		this.bsLender = bsLender;
	}

	public String getBsPan() {
		return bsPan;
	}

	public void setBsPan(String bsPan) {
		this.bsPan = bsPan;
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
	
}
