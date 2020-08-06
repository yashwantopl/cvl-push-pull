package com.opl.mudra.api.oneform.enums;

public enum EmploymentCategory {
	
	
	CENTRAL_GOVERNMENT(1, "Central Government", "Central Government"), 
	STATE_GOVERNMENT(2, "State Government", "State Government"),
	PSU(3, "PSU", "PSU"),
	BANK(4, "Bank", "Bank"),
	INSURANCE_COMPANY(5, "Insurance Company", "Insurance Company"),
	SMALL_SECTOR_PVT_LTD_COMPANIES(6, "Small Sector (Pvt Ltd. Companies)", "Small Sector (Pvt Ltd. Companies)"),
	SMALL_SECTOR_PARTNERSHIP(7, "Small Sector (Partnership)", "Small Sector (Partnership)"),
	SMALL_SECTOR_PROPRIETORSHIP(8, "Small Sector (Proprietorship)", "Small Sector (Proprietorship)"),
	UNORGANISED_SECTOR(9, "Unorganised Sector", "Unorganised Sector)"),
	OTHERS(10, "Others", "Others");

	private final Integer id;
	private final String value;
	private final String description;

	EmploymentCategory(Integer id, String value, String description) {
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

	public static EmploymentCategory getById(Integer id) {
		switch (id) {
		case 1:
			return CENTRAL_GOVERNMENT;
		case 2:
			return STATE_GOVERNMENT;
		case 3:
			return PSU;
		case 4:
			return BANK;
		case 5:
			return INSURANCE_COMPANY;
		case 6:
			return SMALL_SECTOR_PVT_LTD_COMPANIES;
		case 7:
			return SMALL_SECTOR_PARTNERSHIP;
		case 8:
			return SMALL_SECTOR_PROPRIETORSHIP;
		case 9:
			return UNORGANISED_SECTOR;
		case 10:
			return OTHERS;
		default:
			return null;
		}
	}

	public static EmploymentCategory[] getAll() {
		return EmploymentCategory.values();

	}

}
