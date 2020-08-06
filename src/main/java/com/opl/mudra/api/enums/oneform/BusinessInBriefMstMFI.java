package com.opl.mudra.api.enums.oneform;

public enum  BusinessInBriefMstMFI {
    EXPANSION(2,"Expansion"),
    NEW_ACTIVITY(1,"New Activity"),
    OTHER(3,"Other");

    private Integer id;
    private String value;

    private BusinessInBriefMstMFI(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static BusinessInBriefMstMFI fromValue(String v) {
        for (BusinessInBriefMstMFI c : BusinessInBriefMstMFI.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static BusinessInBriefMstMFI fromId(String v) {
        for (BusinessInBriefMstMFI c : BusinessInBriefMstMFI.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static BusinessInBriefMstMFI[] getAll() {
        return BusinessInBriefMstMFI.values();
    }
    
    public static BusinessInBriefMstMFI getById(Integer id) {
		switch (id) {
		case 1:
			return NEW_ACTIVITY;
		case 2:
			return EXPANSION;
		case 3:
			return OTHER;
		default:
			return null;
		}
	}

}
