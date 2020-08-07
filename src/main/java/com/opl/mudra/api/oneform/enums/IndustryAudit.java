package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum IndustryAudit {
			EXTERNAL_CA(1,"External CA","External CA"),
		INHOUSE_TEAM(2,"Inhouse Team","Inhouse Team"),
		DOESNT_COMPLY(3,"Doesn't Comply","Doesn't Comply"),
		NA(4,"NA","NA");
	
		private final Integer id;
		private final String value;
		private final String description;
		
		IndustryAudit(Integer id, String value, String description) {
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
		public static IndustryAudit getById(Integer id) {
		switch (id) {
		case 1:
			return EXTERNAL_CA;
		case 2:
			return INHOUSE_TEAM;
		case 3:
			return DOESNT_COMPLY;
		case 4:
			return NA;
		default:
			return null;
		}
	}
		public static IndustryAudit[] getAll() {
			return IndustryAudit.values();

		}
}