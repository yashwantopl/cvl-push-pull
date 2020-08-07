package com.opl.mudra.api.oneform.enums;

public enum ODOPResidenceStatus {

	OWNED_HOUSE(1, "Owned House", "Owned House"),
	PARENTAL_HOUSE(2, "Parental House", "Parental House"),
	RENTAL_HOUSE(3, "Rental House" ,"Rental House");
	
	private final Integer id;
    private final String value;
    private final String description;
	
    ODOPResidenceStatus(Integer id, String value, String description) {
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

    public static ODOPResidenceStatus getById(Integer id) {
        switch (id) {
            case 1:
                return OWNED_HOUSE;
            case 2:
                return PARENTAL_HOUSE;
            case 3:
                return RENTAL_HOUSE;
            default:
                return null;
        }
    }
    
    public static ODOPResidenceStatus[] getAll() {
        return ODOPResidenceStatus.values();
    }

}
