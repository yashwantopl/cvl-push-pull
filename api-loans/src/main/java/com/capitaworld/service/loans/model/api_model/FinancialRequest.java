package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 * Date : 22-Jun-2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinancialRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Long applicationId;
	private List<BalanceSheetAssetReq> balanceSheetAssetReqList = new ArrayList<>();
	private List<BalanceSheetLiabilitiesReq> balanceSheetLiabilitiesReqList = new ArrayList<>();
	private List<ProfitAndLossStmntReq> profiltAndLossStmntReqList = new ArrayList<>();
	private List<RatioDetailsReq> ratioDetailsReqList = new ArrayList<>();
	
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public List<BalanceSheetAssetReq> getBalanceSheetAssetReqList() {
		return balanceSheetAssetReqList;
	}
	/**
	 * @param balanceSheetAssetReqList the balanceSheetAssetReqList to set
	 */
	public void setBalanceSheetAssetReqList(List<BalanceSheetAssetReq> balanceSheetAssetReqList) {
		this.balanceSheetAssetReqList = balanceSheetAssetReqList;
	}
	public List<BalanceSheetLiabilitiesReq> getBalanceSheetLiabilitiesReqList() {
		return balanceSheetLiabilitiesReqList;
	}
	/**
	 * @param balanceSheetLiabilitiesReqList the balanceSheetLiabilitiesReqList to set
	 */
	public void setBalanceSheetLiabilitiesReqList(List<BalanceSheetLiabilitiesReq> balanceSheetLiabilitiesReqList) {
		this.balanceSheetLiabilitiesReqList = balanceSheetLiabilitiesReqList;
	}
	public List<ProfitAndLossStmntReq> getProfiltAndLossStmntReqList() {
		return profiltAndLossStmntReqList;
	}
	/**
	 * @param profiltAndLossStmntReqList the profiltAndLossStmntReqList to set
	 */
	public void setProfiltAndLossStmntReqList(List<ProfitAndLossStmntReq> profiltAndLossStmntReqList) {
		this.profiltAndLossStmntReqList = profiltAndLossStmntReqList;
	}
	public List<RatioDetailsReq> getRatioDetailsReqList() {
		return ratioDetailsReqList;
	}
	/**
	 * @param ratioDetailsReqList the ratioDetailsReqList to set
	 */
	public void setRatioDetailsReqList(List<RatioDetailsReq> ratioDetailsReqList) {
		this.ratioDetailsReqList = ratioDetailsReqList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FinancialRequest [userId=" + userId + ", applicationId=" + applicationId + ", balanceSheetAssetReqList="
				+ balanceSheetAssetReqList + ", balanceSheetLiabilitiesReqList=" + balanceSheetLiabilitiesReqList
				+ ", profiltAndLossStmntReqList=" + profiltAndLossStmntReqList + ", ratioDetailsReqList="
				+ ratioDetailsReqList + "]";
	}
	
	
	
	
}
