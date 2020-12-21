package com.opl.mudra.api.oneform.enums;

public enum MudraOwningHouseMst {

	HOME_OWNER(1,"Home Owner","Home Owner"),
	OWNED_BY_BLOOD_RELATIVE(2,"Owned by Blood Relative","Owned by Blood Relative"),
	OWNED_BY_SPOUSE(3,"Owned by Spouse","Owned by Spouse"),
	LIVING_IN_FAMILY(4,"Living in Family","Living in Family"),
	TENANT_ACCOMODATION_GIVEN_BY_RESIDENCE(5,"Tenant (Accomodation given by residence) - Range","Tenant (Accomodation given by residence) - Range"),
	TENANT_ACCOMODATION_GIVEN_BY_PSU_AND_GOVT(6,"Tenant (Accomodation given by PSU and Govt)","Tenant (Accomodation given by PSU and Govt)"),
//	HOUSE_OWNED_BY_SPOUSE(7,"House owned by spouse","House owned by spouse"),
//	LIVING_WITH_FAMILY(8,"Living with Family","Living with Family"),
	OTHERS(7,"Others","Others");

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
                return HOME_OWNER;
            case 2:
                return OWNED_BY_BLOOD_RELATIVE;
            case 3:
            	return OWNED_BY_SPOUSE;
            case 4:
            	return LIVING_IN_FAMILY;
            case 5:
            	return TENANT_ACCOMODATION_GIVEN_BY_RESIDENCE;
            case 6:
            	return TENANT_ACCOMODATION_GIVEN_BY_PSU_AND_GOVT;
            case 7:
            	return OTHERS;
            default:
                return null;
        }
    }
    public static MudraOwningHouseMst[] getAll() {
        return MudraOwningHouseMst.values();

    }
}
