package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum RepaymentModeRetailMst {
			CHECKOFF(2,"Check-off","Check-off"),
		ECS_ELECTRONIC_CLEARING_SYSTEM(3,"ECS (Electronic Clearing System)","ECS (Electronic Clearing System)"),
		PDCS_POST_DATED_CHEQUES(4,"PDCs (Post Dated Cheques)","PDCs (Post Dated Cheques)"),
		SI_STANDING_INSTRUCTION(5,"SI (Standing Instruction)","SI (Standing Instruction)");
	
		private final Integer id;
		private final String value;
		private final String description;
		RepaymentModeRetailMst(Integer id, String value, String description) {
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
		public static RepaymentModeRetailMst getById(Integer id) {
		switch (id) {
		case 2:
			return CHECKOFF;
		case 3:
			return ECS_ELECTRONIC_CLEARING_SYSTEM;
		case 4:
			return PDCS_POST_DATED_CHEQUES;
		case 5:
			return SI_STANDING_INSTRUCTION;
		default:
			return null;
		}
	}
		public static RepaymentModeRetailMst[] getAll() {
			return RepaymentModeRetailMst.values();

		}
}