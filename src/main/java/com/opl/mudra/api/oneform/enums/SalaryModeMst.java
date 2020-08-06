package com.opl.mudra.api.oneform.enums;

public enum SalaryModeMst {
	DIRECT(3,"Direct in account","Direct in account"),
	CHEQUE (2,"Cheque","Cheque"),
	CASH(1,"Cash","Cash");
	
		private final Integer id;
		private final String value;
		private final String description;
		SalaryModeMst(Integer id, String value, String description) {
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
		public static SalaryModeMst getById(Integer id) {
		switch (id) {
		case 1:
			return SalaryModeMst.CASH;
		case 2:
			return SalaryModeMst.CHEQUE;
		case 3:
			return SalaryModeMst.DIRECT;
		default:
			return null;
		}
	}
		public static SalaryModeMst[] getAll() {
			return SalaryModeMst.values();

		}
}