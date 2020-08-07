package com.opl.mudra.api.fraudanalytics.model;

import java.util.Date;
import java.util.List;

public class FdRbiCaCompanyDetailRel {

	private Long id;
	
	private Long codAdvNo;

	private Boolean codAssisted;

	private String codBranchName;

	private String codCompAdmnAddr;

	private String codCompFacAddr;

	private String codCompName;

	private String codCompRegAddr;

	private Long codCompSrNo;

	private Double codDisbAmt;

	private String codGrCompName;

	private String codIrregular;

	private String codReference;

	private String codRemarks;

	private Double codSancAmt;

	private String codSancDt;
	
	private List<FdRbiCaCustomerDetailRel> fdRbiCaCustomerDetailRels;

	private Long createdBy;

	private Date createdDate;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodAdvNo() {
		return codAdvNo;
	}

	public void setCodAdvNo(Long codAdvNo) {
		this.codAdvNo = codAdvNo;
	}

	public Boolean getCodAssisted() {
		return codAssisted;
	}

	public void setCodAssisted(Boolean codAssisted) {
		this.codAssisted = codAssisted;
	}

	public String getCodBranchName() {
		return codBranchName;
	}

	public void setCodBranchName(String codBranchName) {
		this.codBranchName = codBranchName;
	}

	public String getCodCompAdmnAddr() {
		return codCompAdmnAddr;
	}

	public void setCodCompAdmnAddr(String codCompAdmnAddr) {
		this.codCompAdmnAddr = codCompAdmnAddr;
	}

	public String getCodCompFacAddr() {
		return codCompFacAddr;
	}

	public void setCodCompFacAddr(String codCompFacAddr) {
		this.codCompFacAddr = codCompFacAddr;
	}

	public String getCodCompName() {
		return codCompName;
	}

	public void setCodCompName(String codCompName) {
		this.codCompName = codCompName;
	}

	public String getCodCompRegAddr() {
		return codCompRegAddr;
	}

	public void setCodCompRegAddr(String codCompRegAddr) {
		this.codCompRegAddr = codCompRegAddr;
	}

	public Double getCodDisbAmt() {
		return codDisbAmt;
	}

	public void setCodDisbAmt(Double codDisbAmt) {
		this.codDisbAmt = codDisbAmt;
	}

	public String getCodGrCompName() {
		return codGrCompName;
	}

	public void setCodGrCompName(String codGrCompName) {
		this.codGrCompName = codGrCompName;
	}

	public String getCodIrregular() {
		return codIrregular;
	}

	public void setCodIrregular(String codIrregular) {
		this.codIrregular = codIrregular;
	}

	public String getCodReference() {
		return codReference;
	}

	public void setCodReference(String codReference) {
		this.codReference = codReference;
	}

	public String getCodRemarks() {
		return codRemarks;
	}

	public void setCodRemarks(String codRemarks) {
		this.codRemarks = codRemarks;
	}

	public Double getCodSancAmt() {
		return codSancAmt;
	}

	public void setCodSancAmt(Double codSancAmt) {
		this.codSancAmt = codSancAmt;
	}

	public String getCodSancDt() {
		return codSancDt;
	}

	public void setCodSancDt(String codSancDt) {
		this.codSancDt = codSancDt;
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

	public List<FdRbiCaCustomerDetailRel> getFdRbiCaCustomerDetailRels() {
		return fdRbiCaCustomerDetailRels;
	}

	public void setFdRbiCaCustomerDetailRels(List<FdRbiCaCustomerDetailRel> fdRbiCaCustomerDetailRels) {
		this.fdRbiCaCustomerDetailRels = fdRbiCaCustomerDetailRels;
	}

	public Long getCodCompSrNo() {
		return codCompSrNo;
	}

	public void setCodCompSrNo(Long codCompSrNo) {
		this.codCompSrNo = codCompSrNo;
	}
	
	
	
	
}
