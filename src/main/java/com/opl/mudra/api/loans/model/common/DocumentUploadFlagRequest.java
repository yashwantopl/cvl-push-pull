package com.opl.mudra.api.loans.model.common;

import java.io.Serializable;

/**
 * @author sanket
 *
 */
/**
 * @author sanket
 *
 */
public class DocumentUploadFlagRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long applicationId;

	private Long proposalMappingId;

	private Long DocumentMappingId;
	
	private Long userId;

	public Long getApplicationId() {
		return applicationId;
	}

	public Long getDocumentMappingId() {
		return DocumentMappingId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public void setDocumentMappingId(Long documentMappingId) {
		DocumentMappingId = documentMappingId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProposalMappingId() {
		return proposalMappingId;
	}

	public void setProposalMappingId(Long proposalMappingId) {
		this.proposalMappingId = proposalMappingId;
	}
}
