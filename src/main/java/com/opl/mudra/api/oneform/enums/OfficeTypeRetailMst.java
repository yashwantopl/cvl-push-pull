package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum OfficeTypeRetailMst {
			SELF_OWNED(2,"Self owned","Self owned"),
		RENTED(3,"Rented","Rented"),
		FREE_HOLD(4,"Free Hold","Free Hold"),
		LEASE_HOLD(5,"Lease Hold","Lease Hold");
	
		private final Integer id;
		private final String value;
		private final String description;
		OfficeTypeRetailMst(Integer id, String value, String description) {
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
		public static OfficeTypeRetailMst getById(Integer id) {
		switch (id) {
		case 2:
			return SELF_OWNED;
		case 3:
			return RENTED;
		case 4:
			return FREE_HOLD;
		case 5:
			return LEASE_HOLD;
		default:
			return null;
		}
	}
		public static OfficeTypeRetailMst[] getAll() {
			return OfficeTypeRetailMst.values();

		}
}