package com.opl.mudra.api.oneform.enums;

public enum SupplierQuality {
	GOOD_CHOICE(1,"Good choice of suppliers supplying quality goods & services.","Good choice of suppliers supplying quality goods & services."),
	REASONABLE_CHOICE(2,"Reasonable choice of suppliers, but who supply average quality goods & services.","Reasonable choice of suppliers, but who supply average quality goods & services."),
	LIMITED_CHOICE(3,"Limited choice of suppliers supplying goods & services.  Quality of goods & services is also not very good.","Limited choice of suppliers supplying goods & services.  Quality of goods & services is also not very good."),
	MONOPOLISTIC_SUPPLY(4,"Monopolistic supply situation and has no handle over improving its quality of supplies","Monopolistic supply situation and has no handle over improving its quality of supplies");
	
		private final Integer id;
		private final String value;
		private final String description;
		SupplierQuality(Integer id, String value, String description) {
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
		public static SupplierQuality getById(Integer id) {
		switch (id) {
		case 1:
			return GOOD_CHOICE;
		case 2:
			return REASONABLE_CHOICE;
		case 3:
			return LIMITED_CHOICE;
		case 4:
			return MONOPOLISTIC_SUPPLY;
		default:
			return null;
		}
	}
		public static SupplierQuality[] getAll() {
			return SupplierQuality.values();

		}
}