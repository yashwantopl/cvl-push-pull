package com.opl.mudra.api.oneform.enums;

public enum MudraOwningHouseMst {

	RENTED_FOR_MORE_THAN_3_YEAR(1,"Rented for more than 3 year","Rented for more than 3 year"),
	RENTED_FOR_UPTO_3_YEARS(2,"Rented for upto 3 years","Rented for upto 3 years "),
	OWING_A_HOUSE_NOT_MORTGAGED_SAME_PLACE(3,"Owing a house - Not Mortgaged - Same Place","Owing a house - Not Mortgaged - Same Place"),
	OWNING_A_HOUSE_NOT_MORTGAGED_OTHER_PLACE(4,"Owning a house - Not Mortgaged - Other Place","Owning a house - Not Mortgaged - Other Place"),
	OWING_A_HOUSE_MORTGAGED_IN_THE_SAME_PLACE(5,"Owing a house - Mortgaged - In the same place","Owing a house - Mortgaged - In the same place"),
	OWING_A_HOUSE_MORTGAGED_AT_OTHER_PLACE(6,"Owing a house - Mortgaged - At other place","Owing a house - Mortgaged - At other place"),
	HOUSE_OWNED_BY_SPOUSE(7,"House owned by spouse","House owned by spouse"),
	LIVING_WITH_FAMILY(8,"Living with Family","Living with Family"),
	OTHERS(9,"Others","Others");

    private final Integer id;
    private final String value;
    private final String description;

    MudraOwningHouseMst(Integer id, String value, String description) {
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

    public static MudraOwningHouseMst getById(Integer id) {
        switch (id) {
            case 1:
                return RENTED_FOR_MORE_THAN_3_YEAR;
            case 2:
                return RENTED_FOR_UPTO_3_YEARS;
            case 3:
            	return OWING_A_HOUSE_NOT_MORTGAGED_SAME_PLACE;
            case 4:
            	return OWNING_A_HOUSE_NOT_MORTGAGED_OTHER_PLACE;
            case 5:
            	return OWING_A_HOUSE_MORTGAGED_IN_THE_SAME_PLACE;
            case 6:
            	return OWING_A_HOUSE_MORTGAGED_AT_OTHER_PLACE;
            case 7:
            	return HOUSE_OWNED_BY_SPOUSE;
            case 8:
            	return LIVING_WITH_FAMILY;
            case 9:
            	return OTHERS;
            default:
                return null;
        }
    }
    public static MudraOwningHouseMst[] getAll() {
        return MudraOwningHouseMst.values();

    }
}
