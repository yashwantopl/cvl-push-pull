package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum CastCategory {
		GENERAL(5,"General","General"),
		SC(2,"SC","SC"),
		ST(3,"ST","ST"),
		OBC(4,"OBC","OBC"),
		OTHERS(6,"Others","Others"),
		MINORITY_COMMUNITY (7,"Minority Community","Minority Community");
	
	
		private final Integer id;
		private final String value;
		private final String description;
		
		CastCategory(Integer id, String value, String description) {
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
	public static CastCategory getById(Integer id) {
		switch (id) {
		case 2:
			return SC;
		case 3:
			return ST;
		case 4:
			return OBC;
		case 5:
			return GENERAL;
		case 6:
			return OTHERS;
		case 7:
			return MINORITY_COMMUNITY;
		default:
			return null;
		}
	}
	
	public static CastCategory[] getAll() {
		return CastCategory.values();

	}
}