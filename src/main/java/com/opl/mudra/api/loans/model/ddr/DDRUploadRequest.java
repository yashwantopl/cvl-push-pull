package com.opl.mudra.api.loans.model.ddr;

public class DDRUploadRequest {

	private Long applicationId;
	private Long proposalMappingId;
	private Long docId;
	private String modelName;
	private Integer totalDocs;
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Integer getTotalDocs() {
		return totalDocs;
	}
	public void setTotalDocs(Integer totalDocs) {
		this.totalDocs = totalDocs;
	}
	public Long getProposalMappingId() {return proposalMappingId;}
	public void setProposalMappingId(Long proposalMappingId) {this.proposalMappingId = proposalMappingId;}
}
