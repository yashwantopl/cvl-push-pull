package com.opl.mudra.api.enums.oneform;

public enum RelationMstMFI {
    SELF(1,"Self (Applicant)"),
    WIFE(2,"Spouse"),
    FATHER(3,"Father"),
    MOTHER(4,"Mother"),
    DAUTER(6,"Daughter"),
    SON(5,"Son");


    private Integer id;
    private String value;

    private RelationMstMFI(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public static RelationMstMFI fromValue(String v) {
        for (RelationMstMFI c : RelationMstMFI.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static RelationMstMFI fromId(String v) {
        for (RelationMstMFI c : RelationMstMFI.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static RelationMstMFI[] getAll() {
        return RelationMstMFI.values();
    }
    
    public static RelationMstMFI getById(Integer id) {
		switch (id) {
		case 1:
			return SELF;
		case 2:
			return WIFE;
		case 3:
			return FATHER;
		case 4:
			return MOTHER;
		case 5:
			return DAUTER;	
		case 6:
			return SON;
		default:
			return null;
		}
    }
    
    
}
