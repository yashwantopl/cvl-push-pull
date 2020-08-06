package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum OwnershipTypeRetailMst {
	PRIVATELTD(1,"PRIVATELTD","PRIVATELTD"),
		INDIVIDUAL(2,"Individual","Individual"),
	PROPRIETARY(3,"Proprietary","Proprietary"),
	PARTNERSHIP(4,"Partnership","Partnership"),
		OTHERS(5,"Others","Others"),
		HUF(6,"HUF","HUF"),
		TRUST(7,"Trust","Trust"),
		LIMITEDPVTLTD(8,"Limited/ Private Limited","Limited/ Private Limited");
	
	
		private final Integer id;
		private final String value;
		private final String description;
		OwnershipTypeRetailMst(Integer id, String value, String description) {
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
		public static OwnershipTypeRetailMst getById(Integer id) {
		switch (id) {
		case 1:
			return PRIVATELTD;
		case 2:
			return INDIVIDUAL;
		case 3:
			return PROPRIETARY;
		case 4:
			return PARTNERSHIP;
		case 5:
			return OTHERS;
		case 6:
			return HUF;
		case 7:
			return TRUST;
		case 8:
			return LIMITEDPVTLTD;
		default:
			return null;
		}
	}
		public static OwnershipTypeRetailMst[] getAll() {
			return OwnershipTypeRetailMst.values();

		}
}