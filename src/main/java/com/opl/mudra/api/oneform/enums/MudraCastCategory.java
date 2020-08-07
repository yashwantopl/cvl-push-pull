package com.opl.mudra.api.oneform.enums;


public enum MudraCastCategory {

	GENERAL(1,"General","General"),
	SC(2,"SC","SC"),
	ST(3,"ST","ST"),
	MINORITY_COMMUNITY (4,"Minority Community","Minority Community"),
	//	WOMEN(5,"Women","Women"),  
	OTHERS(6,"Others","Others");
	

	private final Integer id;
	private final String value;
	private final String description;
	
	MudraCastCategory(Integer id, String value, String description) {
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
	public static MudraCastCategory getById(Integer id) {
		switch (id) {
		case 1:
			return GENERAL;
		case 2:
			return SC;
		case 3:
			return ST;
		case 4:
			return MINORITY_COMMUNITY;
//		case 5:
//			return WOMEN;
		case 6:
			return OTHERS;
		default:
			return null;
		}
	}

	public static MudraCastCategory[] getAll() {
		return MudraCastCategory.values();
	}

}
