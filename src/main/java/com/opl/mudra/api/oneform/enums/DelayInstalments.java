package com.opl.mudra.api.oneform.enums;

public enum DelayInstalments {
	PAYMENTS_RECEIVED(1,"Payments are received on or before due date","Payments are received on or before due date"),
	WITHIN_ONEMONTH(2,"Payments are received within 1 month of the due date","Payments are received within 1 month of the due date"),
	WITHIN_DUEDATE(3,"Payments are received within 1 quarter of the due date","Payments are received within 1 quarter of the due date"),
	PAYMENTS_RECEIVED_MORETHAN_DUEDATE(4,"Payments are delayed more than 1 quarter from the due date","Payments are delayed more than 1 quarter from the due date");
	
		private final Integer id;
		private final String value;
		private final String description;
		DelayInstalments(Integer id, String value, String description) {
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
		public static DelayInstalments getById(Integer id) {
		switch (id) {
		case 1:
			return PAYMENTS_RECEIVED;
		case 2:
			return WITHIN_ONEMONTH;
		case 3:
			return WITHIN_DUEDATE;
		case 4:
			return PAYMENTS_RECEIVED_MORETHAN_DUEDATE;
		default:
			return null;
		}
	}
		public static DelayInstalments[] getAll() {
			return DelayInstalments.values();

		}
}