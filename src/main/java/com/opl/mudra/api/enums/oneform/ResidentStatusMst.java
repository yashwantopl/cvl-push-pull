package com.opl.mudra.api.enums.oneform;

public enum ResidentStatusMst {

    RI(1,"Resident India","RI","1"),
    NRI(2,"Non Resident India","NRI","2"),
    RNOR(3,"Resident but Not Ordinary Resident","RNOR","3"),
    ROR(4,"Ordinary Resident","ROR","4"),
    FC(5,"Foreign Correspondent","FC","5");

    private final Integer id;
    private final String value;
    private final String description;
    private final String sbiCode;

    ResidentStatusMst(Integer id, String value, String description, String sbiCode) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.sbiCode = sbiCode;
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

    public String getSbiCode() {
        return sbiCode;
    }

    public static ResidentStatusMst getById(Integer id) {
        switch (id) {
            case 1:
                return RI;
            case 2:
                return NRI;
            case 3:
                return RNOR;
            case 4:
                return ROR;
            case 5:
                return FC;
            default:
                return null;
        }
    }
    public static ResidentStatusMst[] getAll() {
        return ResidentStatusMst.values();

    }
    
    public static ResidentStatusMst[] getAllForHomeLoan() {
    	ResidentStatusMst[] resArra = new ResidentStatusMst[2];
    	resArra[0] = RI;
    	resArra[1] = NRI;
    	return resArra;
    }

    public static ResidentStatusMst fromSbiCode(String v) {
        for (ResidentStatusMst c : ResidentStatusMst.values()) {
            if (c.sbiCode.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
