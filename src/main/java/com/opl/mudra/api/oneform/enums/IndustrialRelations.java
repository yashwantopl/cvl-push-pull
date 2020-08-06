package com.opl.mudra.api.oneform.enums;

public enum IndustrialRelations {
	RELATIONSHIP_EXISTS(1,"No labour/ employee  problems in past two years and a cordial relationship exists ","No labour/ employee  problems in past two years and a cordial relationship exists "),
	RELATIONSHIP_EXISTS_NOW(2,"Minor labour/ employee problems in past two years but a cordial relationship exists now ","Minor labour/ employee problems in past two years but a cordial relationship exists now "),
	DISRUPTIONS(3,"Labour/ employee  problems in past two years leading to a few disruptions ","Labour/ employee  problems in past two years leading to a few disruptions "),
	INDUSTRIAL_RELATIONS(4,"Frequent  labour/ employee  problems and weak industrial relations","Frequent  labour/ employee  problems and weak industrial relations");
	
		private final Integer id;
		private final String value;
		private final String description;
		IndustrialRelations(Integer id, String value, String description) {
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
		public static IndustrialRelations getById(Integer id) {
		switch (id) {
		case 1:
			return RELATIONSHIP_EXISTS;
		case 2:
			return RELATIONSHIP_EXISTS_NOW;
		case 3:
			return DISRUPTIONS;
		case 4:
			return INDUSTRIAL_RELATIONS;
		default:
			return null;
		}
	}
		public static IndustrialRelations[] getAll() {
			return IndustrialRelations.values();

		}
}