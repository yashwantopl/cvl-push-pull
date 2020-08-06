package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum LandSize {
			BELOW_2_5_ACRES_OF_LAND(2,"Below 2.5 Acres of Land","Below 2.5 Acres of Land"),
		LAND_2_5_to_5_ACRES_OF_LAND(3,"2.5 - 5 Acres of Land","2.5 - 5 Acres of Land"),
		ABOVE_5_ACRES_OF_LAND(4,"Above 5 Acres of Land","Above 5 Acres of Land"),
		LANDLESS_LABORER(5,"Landless Laborer","Landless Laborer");
	
	
		private final Integer id;
		private final String value;
		private final String description;
		LandSize(Integer id, String value, String description) {
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
		public static LandSize getById(Integer id) {
		switch (id) {
		case 2:
			return BELOW_2_5_ACRES_OF_LAND;
		case 3:
			return LAND_2_5_to_5_ACRES_OF_LAND;
		case 4:
			return ABOVE_5_ACRES_OF_LAND;
		case 5:
			return LANDLESS_LABORER;
		default:
			return null;
		}
	}
		public static LandSize[] getAll() {
			return LandSize.values();

		}
}