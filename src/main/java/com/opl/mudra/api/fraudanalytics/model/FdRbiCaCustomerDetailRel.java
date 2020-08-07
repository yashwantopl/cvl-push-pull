package com.opl.mudra.api.fraudanalytics.model;

import java.util.Date;

public class FdRbiCaCustomerDetailRel {

	private Long id;
	
	private Long createdBy;

	private Date createdDate;

	private String cudAddr;

	private Long cudAdvNo;

	private Long cudCompSrNo;

	private String cudCustName;

	private Integer cudCustSrNo;

	private Integer cudDesgn;
	
	

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

	public String getCudAddr() {
		return cudAddr;
	}

	public void setCudAddr(String cudAddr) {
		this.cudAddr = cudAddr;
	}

	public Long getCudAdvNo() {
		return cudAdvNo;
	}

	public void setCudAdvNo(Long cudAdvNo) {
		this.cudAdvNo = cudAdvNo;
	}

	public String getCudCustName() {
		return cudCustName;
	}

	public void setCudCustName(String cudCustName) {
		this.cudCustName = cudCustName;
	}

	public Integer getCudCustSrNo() {
		return cudCustSrNo;
	}

	public void setCudCustSrNo(Integer cudCustSrNo) {
		this.cudCustSrNo = cudCustSrNo;
	}

	public Integer getCudDesgn() {
		return cudDesgn;
	}

	public void setCudDesgn(Integer cudDesgn) {
		this.cudDesgn = cudDesgn;
	}

	public Long getCudCompSrNo() {
		return cudCompSrNo;
	}

	public void setCudCompSrNo(Long cudCompSrNo) {
		this.cudCompSrNo = cudCompSrNo;
	}
	
	
}
