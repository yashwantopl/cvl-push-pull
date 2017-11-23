package com.capitaworld.service.loans.model.mobile;

import java.io.Serializable;

public class MobileFPMatchesRequest extends MobileLoanRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long fpProductId;

	private String fpProductName;

	private Long proposalStatusId;

	private Long proposalStageId = null;

	private Boolean isProposalAuto = null;

	private Long initiatedBy = null;

	private Long lastActionPerformedBy = null;

	private String applicantName;

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public String getFpProductName() {
		return fpProductName;
	}

	public void setFpProductName(String fpProductName) {
		this.fpProductName = fpProductName;
	}

	public Long getProposalStatusId() {
		return proposalStatusId;
	}

	public void setProposalStatusId(Long proposalStatusId) {
		this.proposalStatusId = proposalStatusId;
	}

	public Long getProposalStageId() {
		return proposalStageId;
	}

	public void setProposalStageId(Long proposalStageId) {
		this.proposalStageId = proposalStageId;
	}

	public Boolean getIsProposalAuto() {
		return isProposalAuto;
	}

	public void setIsProposalAuto(Boolean isProposalAuto) {
		this.isProposalAuto = isProposalAuto;
	}

	public Long getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(Long initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public Long getLastActionPerformedBy() {
		return lastActionPerformedBy;
	}

	public void setLastActionPerformedBy(Long lastActionPerformedBy) {
		this.lastActionPerformedBy = lastActionPerformedBy;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

}
