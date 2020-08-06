package com.opl.mudra.api.enums.oneform;


import com.opl.mudra.api.utils.oneform.CommonUtils;

public enum EmploymentWithPLScoring {
    CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK(1, CommonUtils.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK, CommonUtils.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK),
    STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK(2, CommonUtils.STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK, CommonUtils.STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK),
    PSU_SALARY_ACCOUNT_NOT_WITH_BANK(3, CommonUtils.PSU_SALARY_ACCOUNT_NOT_WITH_BANK, CommonUtils.PSU_SALARY_ACCOUNT_NOT_WITH_BANK),
    CORPORATE_SALARY_ACCOUNT_NOT_WITH_BANK(4,CommonUtils.CORPORATE_SALARY_ACCOUNT_NOT_WITH_BANK,CommonUtils.CORPORATE_SALARY_ACCOUNT_NOT_WITH_BANK),
    EDUCATIONAL_INSTITUTE(5,"Educational Institute","Educational Institute"),
    OTHERS(6,"Others (Small Sector - Partnership/ Proprietorship/ Unorganized Sector)","Others (Small Sector - Partnership/ Proprietorship/ Unorganized Sector)"),
    CENTRAL_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK(7, CommonUtils.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK, CommonUtils.CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK),
    STATE_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK(8, CommonUtils.STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK, CommonUtils.STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK),
    PSU_SALARY_ACCOUNT_WITH_BANK(9, CommonUtils.PSU_SALARY_ACCOUNT_NOT_WITH_BANK, CommonUtils.PSU_SALARY_ACCOUNT_NOT_WITH_BANK),
    CORPORATE_SALARY_ACCOUNT_WITH_BANK(10,CommonUtils.CORPORATE_SALARY_ACCOUNT_WITH_BANK,CommonUtils.CORPORATE_SALARY_ACCOUNT_WITH_BANK),
    QUASI_GOVERNMENT_NOT_WITH_BANK(11,CommonUtils.QUASI_GOVERNMENT_NOT_WITH_BANK,CommonUtils.QUASI_GOVERNMENT_NOT_WITH_BANK),
    QUASI_GOVERNMENT_WITH_BANK(12,CommonUtils.QUASI_GOVERNMENT_WITH_BANK,CommonUtils.QUASI_GOVERNMENT_WITH_BANK),
	BANK(13,CommonUtils.BANK,CommonUtils.BANK),
	INSURANCE_COMPANY(14,CommonUtils.INSURANCE_COMPANY,CommonUtils.INSURANCE_COMPANY);

    private final Integer id;
    private final String value;
    private final String description;

    EmploymentWithPLScoring(Integer id, String value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public static EmploymentWithPLScoring[] getAll() {
        return EmploymentWithPLScoring.values();

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

    public static EmploymentWithPLScoring getById(Integer id) {
        switch (id) {
            case 1:
                return CENTRAL_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK;
            case 2:
                return STATE_GOVERNMENT_SALARY_ACCOUNT_NOT_WITH_BANK;
            case 3:
                return PSU_SALARY_ACCOUNT_NOT_WITH_BANK;
            case 4:
                return CORPORATE_SALARY_ACCOUNT_NOT_WITH_BANK;
            case 5:
                return EDUCATIONAL_INSTITUTE;
            case 6:
                return OTHERS;
            case 7:
                return CENTRAL_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK;
            case 8:
                return STATE_GOVERNMENT_SALARY_ACCOUNT_WITH_BANK;
            case 9:
                return PSU_SALARY_ACCOUNT_WITH_BANK;
            case 10:
                return CORPORATE_SALARY_ACCOUNT_WITH_BANK;
            case 11:
                return QUASI_GOVERNMENT_NOT_WITH_BANK;
            case 12:
                return QUASI_GOVERNMENT_WITH_BANK;
            case 13:
                return BANK;
            case 14:
            	return INSURANCE_COMPANY;
            default:
                return null;
        }
    }
}
