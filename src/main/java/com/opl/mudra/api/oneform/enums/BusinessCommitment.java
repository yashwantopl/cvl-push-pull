package com.opl.mudra.api.oneform.enums;

public enum BusinessCommitment {
	PROMOTER_IN_BUSINESS(1,"Promoter is highly involved in the business ","Promoter is highly involved in the business "),
	PROMOTER_SUBSTANTIAL_INVESTMENT(2,"Promoter is fairly committed to this business but also has substantial investment in other businesses which are more important","Promoter is fairly committed to this business but also has substantial investment in other businesses which are more important"),
	PROMOTER_BUSINESS_INVESTMENT(3,"This business occupies only a small portion of his time and investment, and his most significant business interest lies elsewhere","This business occupies only a small portion of his time and investment, and his most significant business interest lies elsewhere"),
	NO_PROMOTER(4,"No involvement of the promoter; business merely a legacy or promoter diversifying into other areas ","No involvement of the promoter; business merely a legacy or promoter diversifying into other areas ");
	
		private final Integer id;
		private final String value;
		private final String description;
		BusinessCommitment(Integer id, String value, String description) {
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
		public static BusinessCommitment getById(Integer id) {
		switch (id) {
		case 1:
			return PROMOTER_IN_BUSINESS;
		case 2:
			return PROMOTER_SUBSTANTIAL_INVESTMENT;
		case 3:
			return PROMOTER_BUSINESS_INVESTMENT;
		case 4:
			return NO_PROMOTER;
		default:
			return null;
		}
	}
		public static BusinessCommitment[] getAll() {
			return BusinessCommitment.values();

		}
}