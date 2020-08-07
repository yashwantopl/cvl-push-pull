package com.opl.mudra.api.oneform.enums;

public enum Integrity {
	PROMOTER_WELLESTABLISHED(1,"Promoter / Group has a well-established reputation and track record of honouring its commitment even under adverse circumstances","Promoter / Group has a well-established reputation and track record of honouring its commitment even under adverse circumstances"),
	PROMOTER_GOOD_STANDING_IN_BUSINESS(2,"Promoter/Group has a good standing in the business community and, by and large, is known to honour its commitments","Promoter/Group has a good standing in the business community and, by and large, is known to honour its commitments"),
	PROMOTER_MIXED_REPUTATION(3,"Promoter/Group has a mixed reputation, and has reneged on some of its business commitments in the past for a variety of reasons","Promoter/Group has a mixed reputation, and has reneged on some of its business commitments in the past for a variety of reasons"),
	NO_POSITIVE_INFORMATION(4,"Promoter/Group has a history of wilful defaul and/ or diverting borrowerd funds, or no positive information could be obtained","Promoter/Group has a history of wilful defaul and/ or diverting borrowerd funds, or no positive information could be obtained");
	
		private final Integer id;
		private final String value;
		private final String description;
		Integrity(Integer id, String value, String description) {
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
		public static Integrity getById(Integer id) {
		switch (id) {
		case 1:
			return PROMOTER_WELLESTABLISHED;
		case 2:
			return PROMOTER_GOOD_STANDING_IN_BUSINESS;
		case 3:
			return PROMOTER_MIXED_REPUTATION;
		case 4:
			return NO_POSITIVE_INFORMATION;
		default:
			return null;
		}
	}
		public static Integrity[] getAll() {
			return Integrity.values();

		}
}