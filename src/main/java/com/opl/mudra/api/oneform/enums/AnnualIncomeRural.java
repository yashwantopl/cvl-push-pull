package com.opl.mudra.api.oneform.enums;

public enum AnnualIncomeRural {
    FROM_80_TO_1_LAKH_AND_ABOVE_RULAR(1, "Rual 0.80 lakh to 1.00 lakh or above", "0.80 lakh to 1.00 lakh or above"),
    FROM_70_AND_LESS_THAN_80_LAKH_RULAR(2, "Rural 0.70 lakh - less than .80 lakh", "0.70 lakh - less than .80 lakh"),
    FROM_60_AND_LESS_THAN_70_LAKH_RULAR(3, "Rural 0.60 lakh - less than .70 lakh", "0.60 lakh - less than .70 lakh"),
    LESS_THAN_60_LAKH_RULAR(4, "Rural less than 0.60", "Rural less than 0.60"),
    FROM_1_40_TO_1_60_LAKH_AND_ABOVE_URBAN(5, "Urban 1.40 lakh to 1.60 lakh or above", "Urban 1.40 lakh to 1.60 lakh or above"),
    FROM_1_20_AND_LESS_THAN_1_40_LAKH_URBAN(6, "Urban 1.20 lakh - less than 1.40 lakh", "Urban 1.20 lakh - less than 1.40 lakh"),
    FROM_90_AND_LESS_THAN_1_20_LAKH_URBAN(7, "Urban 0.90 lakh - less than 1.20 lakh", "Urban 0.90 lakh - less than 1.20 lakh"),
    LESS_THAN_90_LAKH_URBAN(8, "Urban less than 0.90", "Urban less than 0.90");

    private final Integer id;
    private final String value;
    private final String description;

    AnnualIncomeRural(Integer id, String value, String description) {
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

    public static AnnualIncomeRural getRangeByValue(Double value,Integer type){

        if(type == 1){
            if(value >= 80000){
                return FROM_80_TO_1_LAKH_AND_ABOVE_RULAR;
            }else if(value >= 70000 && value <80000){
                return FROM_70_AND_LESS_THAN_80_LAKH_RULAR;
            }else if(value >=60000 && value <70000){
                return FROM_60_AND_LESS_THAN_70_LAKH_RULAR;
            }else if(value <60000){
                return LESS_THAN_60_LAKH_RULAR;
            }
        }else{
            if(value >= 140000){
                return FROM_1_40_TO_1_60_LAKH_AND_ABOVE_URBAN;
            }else if(value >= 120000 && value <140000){
                return FROM_1_20_AND_LESS_THAN_1_40_LAKH_URBAN;
            }else if(value >=90000 && value <120000){
                return FROM_90_AND_LESS_THAN_1_20_LAKH_URBAN;
            }else if(value <90000){
                return LESS_THAN_90_LAKH_URBAN;
            }
        }
        return null;
    }

    public static AnnualIncomeRural getById(Integer id) {
        switch (id) {
            case 1:
                return FROM_80_TO_1_LAKH_AND_ABOVE_RULAR;
            case 2:
                return FROM_70_AND_LESS_THAN_80_LAKH_RULAR;
            case 3:
                return FROM_60_AND_LESS_THAN_70_LAKH_RULAR;
            case 4:
                return LESS_THAN_60_LAKH_RULAR;
            case 5:
                return FROM_1_40_TO_1_60_LAKH_AND_ABOVE_URBAN;
            case 6:
                return FROM_1_20_AND_LESS_THAN_1_40_LAKH_URBAN;
            case 7:
                return FROM_90_AND_LESS_THAN_1_20_LAKH_URBAN;
            case 8:
                return LESS_THAN_90_LAKH_URBAN;
            default:
                return null;
        }
    }

    public static AnnualIncomeRural[] getAll() {
        return AnnualIncomeRural.values();

    }
}