package com.opl.mudra.api.enums.oneform;

/**
 * 
 * @author rohit.chaudhary
 *
 */
public enum MinorityCastCatergory {

	BUDDHISTS(1,"Buddhists"," Buddhists"),
	MUSLIMS(2,"Muslims","Muslims"),
	CHRISTIANS(3,"Christians","Christians"),
	SIKHS(4,"Sikhs","Sikhs"),
	JAINS(5,"Jains","Jains"),
	ZOROASTRIANS(6,"Zoroastrians","Zoroastrians");

	private final Integer id;
	private final String value;
	private final String description;
	
	MinorityCastCatergory(Integer id, String value, String description) {
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
	public static MinorityCastCatergory getById(Integer id) {
		switch (id) {
		case 1:
			return BUDDHISTS;
		case 2:
			return MUSLIMS;
		case 3:
			return CHRISTIANS;
		case 4:
			return SIKHS;
		case 5:
			return JAINS;
		case 6:
			return ZOROASTRIANS;
		
		default:
			return null;
		}
	}
	
	public static MinorityCastCatergory[] getAll() {
		return MinorityCastCatergory.values();
	
	}

}
