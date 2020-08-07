/**
 * 
 */
package com.opl.mudra.api.oneform.enums;


import com.opl.mudra.api.utils.oneform.CommonUtils;

/**
 * @author sanket
 *
 */
public enum ConstitutionForGST {
	
	PRIVATE_LIMITED(1,"Private Limited Company","Private Limited Company"),
	PUBLIC_LISTED(2, CommonUtils.PUBLIC_LIMITED_COMPANY,CommonUtils.PUBLIC_LIMITED_COMPANY),
	PUBLIC_UNLISTED(3,CommonUtils.PUBLIC_LIMITED_COMPANY,CommonUtils.PUBLIC_LIMITED_COMPANY),
	PARTNERSHIP(5,"Partnership","Partnership"),
	SOLE_PROPRIETORSHIP(7,"Proprietorship","Proprietorship"),
	/*TRUST(12,"Trust","Trust"),*/
	LLP(13,"Limited Liability Partnership","Limited Liability Partnership"),
	HUF(14,"Hindu Undivided Family","Hindu Undivided Family"),
	GOVERNMENT_ENTITY(15,"Government Department","Government Department"),
	FOREIGN_COMPANY(16,"Foreign Company","Foreign Company"),
	ONE_PERSON(18,"One Person Company","One Person Company"),
	/*UNDER_INCORPORATION(19,"Under Incorporation","Under Incorporation"),*/
	GENERAL_ASSOCIATION(20,"General Association","General Association"),
	/*NOT_FOR_PROFIT(21,"Not For Profit License Company","Not For Profit License Company"),*/
	OTHERS(22,"Others","Others");
	/*CLOSELY_HELD_LTD_COMPANY(23,"Closely held limited companies","Closely held limited companies");*/

	private final Integer id;
	private final String value;
	private final String description;

	ConstitutionForGST(Integer id, String value, String description) {
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
	
	public static ConstitutionForGST getById(Integer id) {
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

	public static ConstitutionForGST[] getAll() {
		return ConstitutionForGST.values();

	}

	

}
