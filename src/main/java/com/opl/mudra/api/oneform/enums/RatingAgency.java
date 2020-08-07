package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum RatingAgency {
			CRISIL(2,"CRISIL","CRISIL"),
		ICRA(3,"ICRA","ICRA"),
		CARE(4,"CARE","CARE"),
		INDIARATING(8,"IndiaRating","IndiaRating"),
		BRICKWORKS(9,"Brickworks","Brickworks"),
		SMERA(10,"SMERA","SMERA");
	
		private final Integer id;
		private final String value;
		private final String description;
		RatingAgency(Integer id, String value, String description) {
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
		public static RatingAgency getById(Integer id) {
		switch (id) {
		case 2:
			return CRISIL;
		case 3:
			return ICRA;
		case 4:
			return CARE;
		case 8:
			return INDIARATING;
		case 9:
			return BRICKWORKS;
		case 10:
			return SMERA;
		default:
			return null;
		}
	}
		public static RatingAgency[] getAll() {
			return RatingAgency.values();

		}
}