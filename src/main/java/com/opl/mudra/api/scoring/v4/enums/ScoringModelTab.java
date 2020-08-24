package com.opl.mudra.api.scoring.v4.enums;

public enum ScoringModelTab {
	
	SAVED(1, "Saved"),
	SENT_TO_CHECKER(2,"Sent to Checker"),
	SEND_BACK_BY_CHECKER(3,"Sent Back by Checker"),
	ACTIVE(4,"Active Scoring Models"),
	IN_ACTIVE(5,"Inactive Scoring Models"),
	RECEIVED_BY_MAKER(6,"Received from Maker"),
	SENT_BACK_TO_MAKER(7,"Sent Back to Maker");
	
	private final Integer id;
	private final String value;
	
	
	private ScoringModelTab(Integer id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public Integer getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	
	public static String getValueById(Integer id) {
		for(ScoringModelTab scoringModelStatus : ScoringModelTab.values()) {
			if(id == scoringModelStatus.getId()) {
				return scoringModelStatus.getValue();
			}
		}
		return null;
	}
	
	
}
