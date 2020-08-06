package com.opl.mudra.api.enums.oneform;

public enum AnnualIncomeUrban {
    FROM_1_40_TO_1_60_LAKH_AND_ABOVE(1, "1.40 lakh to 1.60 lakh or above", "1.40 lakh to 1.60 lakh or above"),
    FROM_1_20_AND_LESS_THAN_1_40_LAKH(2, "1.20 lakh - less than 1.40 lakh", "1.20 lakh - less than 1.40 lakh"),
    FROM_90_AND_LESS_THAN_1_20_LAKH(3, "90 lakh - less than 1.20 lakh", "90 lakh - less than 1.20 lakh"),
    LESS_THAN_90_LAKH(4, "less than 0.90", "less than 0.90");

    private final Integer id;
    private final String value;
    private final String description;

    AnnualIncomeUrban(Integer id, String value, String description) {
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

    public static AnnualIncomeUrban getById(Integer id) {
        switch (id) {
            case 1:
                return FROM_1_40_TO_1_60_LAKH_AND_ABOVE;
            case 2:
                return FROM_1_20_AND_LESS_THAN_1_40_LAKH;
            case 3:
                return FROM_90_AND_LESS_THAN_1_20_LAKH;
            case 4:
                return LESS_THAN_90_LAKH;
            default:
                return null;
        }
    }

    public static AnnualIncomeUrban[] getAll() {
        return AnnualIncomeUrban.values();

    }
}