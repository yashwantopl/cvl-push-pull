package com.opl.mudra.api.scoring_msme.enums;

public enum ITRRequestType {
	ALL(134,"All", ""),
	ITR_LESS_THAN_3_YEAR(1, "ITR less than 3 years", " AND itr_less_than_three_year = TRUE AND itr_three_year = FALSE AND itr_presumptive = FALSE"),
	ITR_3_YEAR(3, "ITR 3 years", " AND itr_less_than_three_year = FALSE AND itr_three_year = TRUE AND itr_presumptive = FALSE"),
	PRESUMPTIVE(4, "Presumptive", " AND itr_less_than_three_year = FALSE AND itr_three_year = FALSE AND itr_presumptive = TRUE"),
	ITR_3_YEAR_AND_LESS_THAN_3_YEAR(31, "ITR 3 years & ITR less than 3 years", " AND itr_less_than_three_year = TRUE AND itr_three_year = TRUE AND itr_presumptive = FALSE"),
	ITR_3_YEAR_AND_PRESUMPTIVE(34, "ITR 3 years & Presumptive", " AND itr_less_than_three_year = FALSE AND itr_three_year = TRUE AND itr_presumptive = TRUE"),
	ITR_LESS_THAN_3_YEAR_AND_PRESUMPTIVE(14, "ITR less than 3 years & Presumptive", " AND itr_less_than_three_year = TRUE AND itr_three_year = FALSE AND itr_presumptive = TRUE");

	private final Integer id;
	private final String value;
	private final String sqlWhereCondition;

	ITRRequestType(Integer id, String value, String sqlWhereCondition) {
		this.id = id;
		this.value = value;
		this.sqlWhereCondition = sqlWhereCondition;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getSqlWhereCondition() {
		return sqlWhereCondition;
	}

	public static ITRRequestType getById(Integer id) {
		for(ITRRequestType type : ITRRequestType.values()) {
			if(type.getId() == id) {
				return type;
			}
		}
		return null;
	}

	public static ITRRequestType[] getAll() {
		return ITRRequestType.values();

	}
}
