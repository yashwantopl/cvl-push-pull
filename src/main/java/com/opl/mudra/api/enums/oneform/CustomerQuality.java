package com.opl.mudra.api.enums.oneform;


public enum CustomerQuality {
	DIVERSIFIED_CUSTOMER(1,"Diversified customer base having reasonable size & stable purchase pattern from the borrower.","Diversified customer base having reasonable size & stable purchase pattern from the borrower."),
	GENERALLY_DIVERSIFIED(2,"Generally diversified customer base but not necessarily having either a reasonable size or a  few large customers","Generally diversified customer base but not necessarily having either a reasonable size or a  few large customers"),
	NO_REASONABLE(3,"No reasonable size nor a stable purchase pattern from the borrower. ","No reasonable size nor a stable purchase pattern from the borrower. "),
	CONCENTRATION(4,"Concentration is high and quality of customers is known to be not very good ","Concentration is high and quality of customers is known to be not very good ");
	
		private final Integer id;
		private final String value;
		private final String description;
		CustomerQuality(Integer id, String value, String description) {
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
		public static CustomerQuality getById(Integer id) {
		switch (id) {
		case 1:
			return DIVERSIFIED_CUSTOMER;
		case 2:
			return GENERALLY_DIVERSIFIED;
		case 3:
			return NO_REASONABLE;
		case 4:
			return CONCENTRATION;
		default:
			return null;
		}
	}
		public static CustomerQuality[] getAll() {
			return CustomerQuality.values();

		}
}