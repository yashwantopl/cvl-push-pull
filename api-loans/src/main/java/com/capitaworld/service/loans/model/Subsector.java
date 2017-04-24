package com.capitaworld.service.loans.model;


public class Subsector {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	
	private Long sectorSubsectorTransactionId;

	public Subsector() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}



	public Long getSectorSubsectorTransactionId() {
		return this.sectorSubsectorTransactionId;
	}

	public void setSectorSubsectorTransactionId(Long sectorSubsectorTransactionId) {
		this.sectorSubsectorTransactionId = sectorSubsectorTransactionId;
	}
}
