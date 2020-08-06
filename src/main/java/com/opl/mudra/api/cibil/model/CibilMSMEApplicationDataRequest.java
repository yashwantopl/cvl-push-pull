package com.opl.mudra.api.cibil.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilMSMEApplicationDataRequest implements Serializable {

	private static final long serialVersionUID = 5320677886683244080L;

	private String amount;
	private String purpose;
	private String referenceNumber;
	private String nTCProductType;
	private String iDVMemberCode;
	private String consumerConsentForUIDAIAuthentication;
	private String gSTStateCode;
	private String mFIMemberCode;
	private String centerReferenceNo;
	private String branchReferenceNo;
	private String cibilBureauFlag;
	private String dSTuNtcFlag;
	private String iDVerificationFlag;
	private String mFIBureauFlag;
	private String cIBILPDFReport;
	private String mFIPDFReport;
	private String iDVPDFReport;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getConsumerConsentForUIDAIAuthentication() {
		return consumerConsentForUIDAIAuthentication;
	}

	public void setConsumerConsentForUIDAIAuthentication(String consumerConsentForUIDAIAuthentication) {
		this.consumerConsentForUIDAIAuthentication = consumerConsentForUIDAIAuthentication;
	}

	public String getCenterReferenceNo() {
		return centerReferenceNo;
	}

	public void setCenterReferenceNo(String centerReferenceNo) {
		this.centerReferenceNo = centerReferenceNo;
	}

	public String getBranchReferenceNo() {
		return branchReferenceNo;
	}

	public void setBranchReferenceNo(String branchReferenceNo) {
		this.branchReferenceNo = branchReferenceNo;
	}

	public String getCibilBureauFlag() {
		return cibilBureauFlag;
	}

	public void setCibilBureauFlag(String cibilBureauFlag) {
		this.cibilBureauFlag = cibilBureauFlag;
	}

	public String getNTCProductType() {
		return nTCProductType;
	}

	public void setNTCProductType(String nTCProductType) {
		this.nTCProductType = nTCProductType;
	}

	public String getIDVMemberCode() {
		return iDVMemberCode;
	}

	public void setIDVMemberCode(String iDVMemberCode) {
		this.iDVMemberCode = iDVMemberCode;
	}

	public String getGSTStateCode() {
		return gSTStateCode;
	}

	public void setGSTStateCode(String gSTStateCode) {
		this.gSTStateCode = gSTStateCode;
	}

	public String getMFIMemberCode() {
		return mFIMemberCode;
	}

	public void setMFIMemberCode(String mFIMemberCode) {
		this.mFIMemberCode = mFIMemberCode;
	}

	public String getDSTuNtcFlag() {
		return dSTuNtcFlag;
	}

	public void setDSTuNtcFlag(String dSTuNtcFlag) {
		this.dSTuNtcFlag = dSTuNtcFlag;
	}

	public String getIDVerificationFlag() {
		return iDVerificationFlag;
	}

	public void setIDVerificationFlag(String iDVerificationFlag) {
		this.iDVerificationFlag = iDVerificationFlag;
	}

	public String getMFIBureauFlag() {
		return mFIBureauFlag;
	}

	public void setMFIBureauFlag(String mFIBureauFlag) {
		this.mFIBureauFlag = mFIBureauFlag;
	}

	public String getCIBILPDFReport() {
		return cIBILPDFReport;
	}

	public void setCIBILPDFReport(String cIBILPDFReport) {
		this.cIBILPDFReport = cIBILPDFReport;
	}

	public String getMFIPDFReport() {
		return mFIPDFReport;
	}

	public void setMFIPDFReport(String mFIPDFReport) {
		this.mFIPDFReport = mFIPDFReport;
	}

	public String getIDVPDFReport() {
		return iDVPDFReport;
	}

	public void setIDVPDFReport(String iDVPDFReport) {
		this.iDVPDFReport = iDVPDFReport;
	}
}
