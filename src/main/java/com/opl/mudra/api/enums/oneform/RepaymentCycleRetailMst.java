package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum RepaymentCycleRetailMst {
			MONTHLY(2,"Monthly","Monthly"),
		BIMONTHLY(3,"Bi-Monthly","Bi-Monthly"),
		QUARTERLY(4,"Quarterly","Quarterly"),
		ANNUALLY(5,"Annually","Annually");
	
		private final Integer id;
		private final String value;
		private final String description;
		RepaymentCycleRetailMst(Integer id, String value, String description) {
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
		public static RepaymentCycleRetailMst getById(Integer id) {
		switch (id) {
		case 2:
			return MONTHLY;
		case 3:
			return BIMONTHLY;
		case 4:
			return QUARTERLY;
		case 5:
			return ANNUALLY;
		default:
			return null;
		}
	}
		public static RepaymentCycleRetailMst[] getAll() {
			return RepaymentCycleRetailMst.values();

		}
}