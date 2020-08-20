/**
 * 
 */
package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author nilay.darji
 *
 */

public class CommonReportUsersRespose implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<CommonUsersInsertedResponse> commonUsersInserted;
	private List<CommonUsersNotInsertedResponse> commonUsersNotInserted;
	private Long dataUploadCount;
	private Long linkSentCount;
	private Long completedCount;
	private Long optOutCount;
	private Long eligibleCount;
	private Long inEligibleCount;
	
	

	public Long getEligibleCount() {
		return eligibleCount;
	}

	public void setEligibleCount(Long eligibleCount) {
		this.eligibleCount = eligibleCount;
	}

	public Long getInEligibleCount() {
		return inEligibleCount;
	}

	public void setInEligibleCount(Long inEligibleCount) {
		this.inEligibleCount = inEligibleCount;
	}

	public List<CommonUsersInsertedResponse> getCommonUsersInserted() {
		return commonUsersInserted;
	}

	public void setCommonUsersInserted(List<CommonUsersInsertedResponse> commonUsersInserted) {
		this.commonUsersInserted = commonUsersInserted;
	}

	public List<CommonUsersNotInsertedResponse> getCommonUsersNotInserted() {
		return commonUsersNotInserted;
	}

	public void setCommonUsersNotInserted(List<CommonUsersNotInsertedResponse> commonUsersNotInserted) {
		this.commonUsersNotInserted = commonUsersNotInserted;
	}

	public Long getDataUploadCount() {
		return dataUploadCount;
	}

	public void setDataUploadCount(Long dataUploadCount) {
		this.dataUploadCount = dataUploadCount;
	}

	public Long getLinkSentCount() {
		return linkSentCount;
	}

	public void setLinkSentCount(Long linkSentCount) {
		this.linkSentCount = linkSentCount;
	}

	public Long getCompletedCount() {
		return completedCount;
	}

	public void setCompletedCount(Long completedCount) {
		this.completedCount = completedCount;
	}

	public Long getOptOutCount() {
		return optOutCount;
	}

	public void setOptOutCount(Long optOutCount) {
		this.optOutCount = optOutCount;
	}

}
