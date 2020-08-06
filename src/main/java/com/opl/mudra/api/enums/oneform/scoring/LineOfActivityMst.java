package com.opl.mudra.api.enums.oneform.scoring;

public enum LineOfActivityMst {
	PRIORITY_SECTOR(1,"Priority Sector","Priority Sector"),
	NON_PRIORITY_SECTOR(2,"Non Priority Sector","Non Priority Sector");

		private final Integer id;
		private final String value;
		private final String description;
		LineOfActivityMst(Integer id, String value, String description) {
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
		public static LineOfActivityMst getById(Integer id) {
		switch (id) {
		case 1:
			return PRIORITY_SECTOR;
		case 2:
			return NON_PRIORITY_SECTOR;
		default:
			return null;
		}
	}
		public static LineOfActivityMst[] getAll() {
			return LineOfActivityMst.values();

		}
}