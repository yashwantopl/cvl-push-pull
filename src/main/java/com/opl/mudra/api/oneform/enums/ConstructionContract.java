package com.opl.mudra.api.oneform.enums;

public enum ConstructionContract {
	COMPLETION_GUARANTEES(1,"Fixed-price date certain turnkey construction contract with substantial liquidated damages and/ or strong completion guarantees ","Fixed-price date certain turnkey construction contract with substantial liquidated damages and/ or strong completion guarantees"),
	LOSS_OF_PROFITS(2,"Fixed-price date certain turnkey construction contract with significant LD's or completion guarantees compensating for loss of profits ","Fixed-price date certain turnkey construction contract with significant LD's or completion guarantees compensating for loss of profits "),
	CONTRACT_WITH_CONTRACTORS(3,"Fixed-price date certain turnkey construction contract with several contractors  ","Fixed-price date certain turnkey construction contract with several contractors  "),
	MULTIPLE_CONTRACTORS(4,"No or partial fixed-price turnkey contract  and/ or interfacing issues with multiple contractors. No LD's and/ or completion guarantees ","No or partial fixed-price turnkey contract  and/ or interfacing issues with multiple contractors. No LD's and/ or completion guarantees "),
	NA(5,"NA","NA");
		private final Integer id;
		private final String value;
		private final String description;
		ConstructionContract(Integer id, String value, String description) {
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
		public static ConstructionContract getById(Integer id) {
		switch (id) {
		case 1:
			return COMPLETION_GUARANTEES;
		case 2:
			return LOSS_OF_PROFITS;
		case 3:
			return CONTRACT_WITH_CONTRACTORS;
		case 4:
			return MULTIPLE_CONTRACTORS;
		case 5:
				return NA;
		default:
			return null;
		}
	}
		public static ConstructionContract[] getAll() {
			return ConstructionContract.values();

		}
}