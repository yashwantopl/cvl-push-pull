package com.opl.mudra.api.user.model;

/**
 * @author rahul.meena
 *
 */
public enum GrievanceStatusEnum {

	NBFC(1,"NBFC"),
	BANK(2,"BANK"),

	PHONE(3,"BANK"),
	SMS(4,"BANK"),
	EMAIL(5,"BANK"),
	IN_PRESON(6,"BANK"),
	
	OPEN(7,"OPEN"),
	IN_PROGRESS(8,"In-Progress"),
	RESOLVED(9,"Resolved"),
	ASSIGN_TO_NBFC(10,"Assign To NBFC"),
	REQUEST_BANK(11,"REQUEST BANK");
	
	private Integer id;
	private String value;
	
	GrievanceStatusEnum(Integer id, String value) {
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
