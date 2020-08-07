package com.opl.mudra.api.scoring_msme.enums;

public enum SourceMasterMsme {
	BANK_STATEMENT(1, "Bank Statement", "Bank Statement"),
	BORROWER_INPUT(2, "Borrower Input", "Borrower Input"),
	BUREAU(3, "Bureau", "Bureau"),
	ELIGBLITY(4, "Eligblity", "Eligblity"),
	GST(5, "GST", "GST"),
	ITR(6, "ITR", "ITR"),
	ITR_GST(7, "ITR + GST", "ITR + GST");

	private final Integer id;
	private final String value;
	private final String description;

	SourceMasterMsme(Integer id, String value, String description) {
		this.id = id;
		this.value = value;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public static SourceMasterMsme getById(Integer id) {
		switch (id) {
		case 1:
			return BANK_STATEMENT;
		case 2:
			return BORROWER_INPUT;
		case 3:
			return BUREAU;
		case 4:
			return ELIGBLITY;
		case 5:
			return GST;
		case 6:
			return ITR;
		case 7:
			return ITR_GST;
		default:
			return null;
		}
	}

	public static SourceMasterMsme[] getAll() {
		return SourceMasterMsme.values();

	}
}