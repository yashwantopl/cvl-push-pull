package com.opl.mudra.api.oneform.enums;

public enum InternalControl {
	INTERNAL_CONTROL_FAIRLYGOOD(1,"Internal Control is fairly good and is dependent on the management's long standing relationship with employees.","Internal Control is fairly good and is dependent on the management's long standing relationship with employees."),
	INTERNAL_CONTROL_GOOD(2,"Internal Control is good, largely because of promotoer's/manager's physical participation in business","Internal Control is good, largely because of promotoer's/manager's physical participation in business"),
	EMPLOYEES_DISCRETION(3,"Internal Control is not very tight and employees have too much discretion.","Internal Control is not very tight and employees have too much discretion."),
	NO_INTERNAL_CONTROL(4,"No internal control at all - the management does not have a clue as  to what is happening","No internal control at all - the management does not have a clue as  to what is happening");
	
		private final Integer id;
		private final String value;
		private final String description;
		InternalControl(Integer id, String value, String description) {
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
		public static InternalControl getById(Integer id) {
		switch (id) {
		case 1:
			return INTERNAL_CONTROL_FAIRLYGOOD;
		case 2:
			return INTERNAL_CONTROL_GOOD;
		case 3:
			return EMPLOYEES_DISCRETION;
		case 4:
			return NO_INTERNAL_CONTROL;
		default:
			return null;
		}
	}
		public static InternalControl[] getAll() {
			return InternalControl.values();

		}
}