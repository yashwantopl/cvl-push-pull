package com.opl.mudra.api.enums.oneform.scoring_msme;

public enum ScoringModelCheckerDecision {

	SENT_BACK(20, "Sent Back"),
	REJECTED(52, "Rejected");
	
	private final Integer id;
	private final String value;
	
	private ScoringModelCheckerDecision(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	
}
