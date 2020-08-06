package com.opl.mudra.api.oneform.enums;
/**
 * @author Sanket
 *
 */
public enum StatusFinancialClosure {
	CLOSURE_COMPLETED(1,"Financial closure of the project has been completed","Financial closure of the project has been completed"),
	HIGH_ABILITY(2,"Significant part of the funding (say >75%) has been tied up and the management has  high ability to tie the remaining funds ","Significant part of the funding (say >75%) has been tied up and the management has  high ability to tie the remaining funds "),
	REASONABLE_CAPABILITY(3,"Part of the funding has been tied up (say 50-75%) and the management has reasonable capability to tie up the remaining funds  ","Part of the funding has been tied up (say 50-75%) and the management has reasonable capability to tie up the remaining funds  "),
	SMALL_PROJECT_COAST(4,"Only a small part of the project cost (say <50%) has been tied up OR the management does not have the capability to tie up remaining funding ","Only a small part of the project cost (say <50%) has been tied up OR the management does not have the capability to tie up remaining funding "),
	NA(5,"NA","NA");
		private final Integer id;
		private final String value;
		private final String description;
		StatusFinancialClosure(Integer id, String value, String description) {
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
		public static StatusFinancialClosure getById(Integer id) {
		switch (id) {
		case 1:
			return CLOSURE_COMPLETED;
		case 2:
			return HIGH_ABILITY;
		case 3:
			return REASONABLE_CAPABILITY;
		case 4:
			return SMALL_PROJECT_COAST;
		case 5:
			return NA;
		default:
			return null;
		}
	}
		public static StatusFinancialClosure[] getAll() {
			return StatusFinancialClosure.values();

		}
}