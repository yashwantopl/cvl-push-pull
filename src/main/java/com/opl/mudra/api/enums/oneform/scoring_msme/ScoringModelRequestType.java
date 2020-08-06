package com.opl.mudra.api.enums.oneform.scoring_msme;

public enum ScoringModelRequestType {
	NEW(1, "New Scoring Model"),
	MODIFICATION(2, "Modification"),
	ACTIVATION(3, "Activation"),
	DEACTIVATION(4,"Deactivation");
	
	private final Integer id;
	private final String value;
	
	private ScoringModelRequestType(Integer id, String value) {
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
