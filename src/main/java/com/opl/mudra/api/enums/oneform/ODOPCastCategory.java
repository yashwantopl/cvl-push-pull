package com.opl.mudra.api.enums.oneform;

public enum ODOPCastCategory {

	GENERAL(1,"General","General"),
	OBC(2, "OBC","OBC"),
	SC(3,"SC","SC"),
	ST(4,"ST","ST"),
	MINORITY_COMMUNITY (5,"Minority Community","Minority Community"),
	OTHERS(6,"Others","Others");
	

	private final Integer id;
	private final String value;
	private final String description;
	
	ODOPCastCategory(Integer id, String value, String description) {
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
	public static ODOPCastCategory getById(Integer id) {
		switch (id) {
		case 1:
			return GENERAL;
		case 2:
			return OBC;
		case 3:
			return SC;
		case 4:
			return ST;
		case 5:
			return MINORITY_COMMUNITY;
		case 6:
			return OTHERS;
		default:
			return null;
		}
	}

	public static ODOPCastCategory[] getAll() {
		return ODOPCastCategory.values();
	}
}
