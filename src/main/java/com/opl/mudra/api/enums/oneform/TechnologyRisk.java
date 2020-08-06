package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum TechnologyRisk {
	COMPLETELY_PROVEN(1,"Completely proven technology and design ","Completely proven technology and design "),
	DESIGN_DIFFERENT(2,"Proven technology/design  maybe in different conditions","Proven technology/design  maybe in different conditions"),
	 STRONG_TECHNOLOGY_PROVIDER(3,"New technology/design but with a strong technology provider and sufficient guarantees","New technology/design but with a strong technology provider and sufficient guarantees"),
	ADEQUATE_GUARANTEES(4,"New technology/design without adequate guarantees","New technology/design without adequate guarantees"),
	NA(5,"NA","NA");

		private final Integer id;
		private final String value;
		private final String description;
		TechnologyRisk(Integer id, String value, String description) {
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
		public static TechnologyRisk getById(Integer id) {
		switch (id) {
		case 1:
			return COMPLETELY_PROVEN;
		case 2:
			return DESIGN_DIFFERENT;
		case 3:
			return STRONG_TECHNOLOGY_PROVIDER;
		case 4:
			return ADEQUATE_GUARANTEES;
		case 5:
			return NA;
		default:
			return null;
		}
	}
		public static TechnologyRisk[] getAll() {
			return TechnologyRisk.values();

		}
}