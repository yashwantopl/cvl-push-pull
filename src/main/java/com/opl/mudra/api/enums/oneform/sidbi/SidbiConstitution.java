/**
 * 
 */
package com.opl.mudra.api.enums.oneform.sidbi;

/**
 * @author vijay.chauhan
 *
 */
public enum SidbiConstitution {

//	Individual / Proprietorship concern / Partnership / Limited Liability Partnership / Private Limited Company / Limited Company / Trust
	INDIVIDUAL(23,"individual","Individual"),
	PROPRIETORSHIP(7,"proprietorship_concern","Proprietorship concern"),
	PARTNERSHIP(5,"Partnership","Partnership"),
	LTD_LIAB_PARTNERSHIP(13,"ltd_liability_partnership","Limited Liability Partnership"),
	PVT_LTD_COMPANY(1,"Private Limited Company","Private Limited Company"),
	LTD_COMPANY(24,"limited_company","Limited Company"),
	TRUST(12,"trust","Trust");
	
	private final Integer id;
	private final String value;
	private final String description;

	SidbiConstitution(Integer id, String value, String description) {
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
	
	
	public static SidbiConstitution getById(Integer id) {
		switch (id) {
		case 23:
			return INDIVIDUAL;

		case 7:
			return PROPRIETORSHIP;
			
		case 5:
			return PARTNERSHIP;

		case 13:
			return LTD_LIAB_PARTNERSHIP;
		
		case 1:
			return PVT_LTD_COMPANY;

		case 24:
			return LTD_COMPANY;
			
		case 12:
			return TRUST;

		default:
			return null;
		}
	}

	public static SidbiConstitution[] getAll() {
		return SidbiConstitution.values();

	}
}
