package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum Constitution {
	PRIVATE_LIMITED(1,"Private Limited","Private Limited"),
	PUBLIC_LISTED(2,"Public Ltd-Listed","Public Ltd-Listed"),
	PUBLIC_UNLISTED(3,"Public Ltd-Unlisted","Public Ltd-Unlisted"),
	PARTNERSHIP(5,"Partnership","Partnership"),
	SOLE_PROPRIETORSHIP(7,"Sole Proprietorship","Sole Proprietorship"),
	/*TRUST(12,"Trust","Trust"),*/
	LLP(13,"Limited Liability Partnership (LLP)","Limited Liability Partnership (LLP)"),
	HUF(14,"Hindu Undivided Family-HUF","Hindu Undivided Family-HUF"),
	GOVERNMENT_ENTITY(15,"Government Entity","Government Entity"),
	FOREIGN_COMPANY(16,"Foreign Company/Subsidiary of a Foreign Company","Foreign Company/Subsidiary of a Foreign Company"),
	ONE_PERSON(18,"One Person Company","One Person Company"),
	/*UNDER_INCORPORATION(19,"Under Incorporation","Under Incorporation"),*/
	GENERAL_ASSOCIATION(20,"General Association","General Association"),
	/*NOT_FOR_PROFIT(21,"Not For Profit License Company","Not For Profit License Company"),*/
	OTHERS(22,"Others","Others");
	/*CLOSELY_HELD_LTD_COMPANY(23,"Closely held limited companies","Closely held limited companies");*/

	private final Integer id;
	private final String value;
	private final String description;

	Constitution(Integer id, String value, String description) {
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
	
	public static Constitution getById(Integer id) {
		switch (id) {
		case 1:
			return PRIVATE_LIMITED;

		case 2:
			return PUBLIC_LISTED;
			
		case 3:
			return PUBLIC_UNLISTED;
			
		case 5:
			return PARTNERSHIP;

		case 7:
			return SOLE_PROPRIETORSHIP;
			
		/*case 12:
			return TRUST;*/
			
		case 13:
			return LLP;

		case 14:
			return HUF;
			
		case 15:
			return GOVERNMENT_ENTITY;
		
		case 16:
			return FOREIGN_COMPANY;

		case 18:
			return ONE_PERSON;
			
		/*case 19:
			return UNDER_INCORPORATION;*/
			
		case 20:
			return GENERAL_ASSOCIATION;

		/*case 21:
			return NOT_FOR_PROFIT;*/
			
		case 22:
			return OTHERS;

		/*case 23:
				return CLOSELY_HELD_LTD_COMPANY;*/

		default:
			return null;
		}
	}

	public static Constitution[] getAll() {
		return Constitution.values();

	}

	

}
