package com.opl.mudra.api.scoring_msme.enums;

public enum ScoringModelStatus {
	
	SAVED(1, "Saved"),
	SENT_TO_CHECKER(2,"Sent to Checker"),
	SEND_BACK_BY_CHECKER(3,"Sent Back by Checker"),
	ACTIVE(4,"Active Scoring Models"),
	IN_ACTIVE(5,"Inactive Scoring Models"),
	RECEIVED_BY_MAKER(6,"Received from Maker"),
	SENT_BACK_TO_MAKER(7,"Sent Back to Maker");
	
	private final Integer id;
	private final String value;
	
	
	private ScoringModelStatus(Integer id, String value) {
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
