package com.opl.mudra.api.enums.oneform;
/**
 * @author Sanket
 *
 */
public enum CreditRatingAvailable {
	
	YES(1,"Yes","YES"),
	NO(2,"No","NO"),
	NA(3,"NA","NA");
	
	private final Integer id;
	private final String value;
	private final String description;

	CreditRatingAvailable(Integer id, String value, String description) {
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
	
	public static CreditRatingAvailable getById(Integer id) {
		switch (id) {
		case 1:
			return YES;

		case 2:
			return NO;
			
		case 3:
			return NA;

		default:
			return null;
		}
	}

	public static CreditRatingAvailable[] getAll() {
		return CreditRatingAvailable.values();

	}

}
