package com.opl.mudra.api.enums.oneform;

public enum AccountingQuality {
	ACCOUNTS_AUDITED(1,"Accounts audited by leading CA firm with no adverse remarks from auditors","Accounts audited by leading CA firm with no adverse remarks from auditors"),
	CA_NOT_WELLKNOWN(2,"The CA firm auditing the accounts is not well known, but no adverse comments","The CA firm auditing the accounts is not well known, but no adverse comments"),
	AUDITED_BY_LEADING_CA(3,"Accounts audited by leading CA firm with adverse remarks on accounting quality","Accounts audited by leading CA firm with adverse remarks on accounting quality"),
	ACCOUNTS_NOT_RELIABLE(4,"Accounts are not reliable. The borrower's actual financial condition cannot be deduced from the financial statements ","Accounts are not reliable. The borrower's actual financial condition cannot be deduced from the financial statements ");
	
		private final Integer id;
		private final String value;
		private final String description;
		AccountingQuality(Integer id, String value, String description) {
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
		public static AccountingQuality getById(Integer id) {
		switch (id) {
		case 1:
			return ACCOUNTS_AUDITED;
		case 2:
			return CA_NOT_WELLKNOWN;
		case 3:
			return AUDITED_BY_LEADING_CA;
		case 4:
			return ACCOUNTS_NOT_RELIABLE;
			default:
				return null;
		}
	}
		public static AccountingQuality[] getAll() {
			return AccountingQuality.values();

		}
}