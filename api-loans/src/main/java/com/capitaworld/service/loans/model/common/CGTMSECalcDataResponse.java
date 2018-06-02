/**
 * 
 */
package com.capitaworld.service.loans.model.common;

import java.util.List;

import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;

/**
 * @author sanket
 *
 */
public class CGTMSECalcDataResponse {

	private List<DirectorBackgroundDetailResponse> directorRespo;
	
	/**
	 * @return the directorRespo
	 */
	public List<DirectorBackgroundDetailResponse> getDirectorRespo() {
		return directorRespo;
	}

	/**
	 * @param directorRespo the directorRespo to set
	 */
	public void setDirectorRespo(List<DirectorBackgroundDetailResponse> directorRespo) {
		this.directorRespo = directorRespo;
	}

	private String subSector;
	
	private Double grossBlock;
	
	private Double loanAmount;
	
	private Double colleteralValue;

	private Long stateId;
	
	/**
	 * @return the stateId
	 */
	public Long getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the subSector
	 */
	public String getSubSector() {
		return subSector;
	}

	/**
	 * @param subSector the subSector to set
	 */
	public void setSubSector(String subSector) {
		this.subSector = subSector;
	}

	/**
	 * @return the grossBlock
	 */
	public Double getGrossBlock() {
		return grossBlock;
	}

	/**
	 * @param grossBlock the grossBlock to set
	 */
	public void setGrossBlock(Double grossBlock) {
		this.grossBlock = grossBlock;
	}

	/**
	 * @return the loanAmount
	 */
	public Double getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	/**
	 * @return the colleteralValue
	 */
	public Double getColleteralValue() {
		return colleteralValue;
	}

	/**
	 * @param colleteralValue the colleteralValue to set
	 */
	public void setColleteralValue(Double colleteralValue) {
		this.colleteralValue = colleteralValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CGTMSECalcDataResponse [directorRespo=" + directorRespo + ", subSector=" + subSector + ", grossBlock="
				+ grossBlock + ", loanAmount=" + loanAmount + ", colleteralValue=" + colleteralValue + "]";
	}
	
	
	public DirectorBackgroundDetailResponse addDirectorDetail(DirectorBackgroundDetailResponse directorDetail) {
		getDirectorRespo().add(directorDetail);
		return directorDetail;
	}
	
	
	
}
