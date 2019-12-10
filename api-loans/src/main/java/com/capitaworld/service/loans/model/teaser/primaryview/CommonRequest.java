package com.capitaworld.service.loans.model.teaser.primaryview;

/**
 * @author maaz.shaikh
 *
 */
public class CommonRequest {
	
	private String emailId;
	private String mobile;
	private Long userId; 
	private Long applicationId;
	private Long fpProductId;
	private Long proposalId;
	private Integer loanTypeId;
	private Long userOrgId;
	
	
	@Override
	public String toString() {
		return "CommonRequest [emailId=" + emailId + ", mobile=" + mobile + ", userId=" + userId + ", applicationId="
				+ applicationId + ", fpProductId=" + fpProductId + ", proposalId=" + proposalId + ", loanTypeId="
				+ loanTypeId + ", userOrgId=" + userOrgId + "]";
	}
	public Long getUserOrgId() {
		return userOrgId;
	}
	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getFpProductId() {
		return fpProductId;
	}
	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}
	public Long getProposalId() {
		return proposalId;
	}
	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}
	public Integer getLoanTypeId() {
		return loanTypeId;
	}
	public void setLoanTypeId(Integer loanTypeId) {
		this.loanTypeId = loanTypeId;
	}
	
	
	
}
