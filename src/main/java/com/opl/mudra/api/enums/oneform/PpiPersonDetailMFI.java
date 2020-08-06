package com.opl.mudra.api.enums.oneform;

public enum PpiPersonDetailMFI {

    MEMBERS_FAMILY(1, "How many members are there in the family ?","ppiNoFamilyMember"),
    ACADAMIC_STANDARD(2, "General acadamic standard of the female head of the family.In case head of family is male,educational standard of his wife ?","ppiAcadamicHeadFamily"),
    IS_REFRIGERATOR(3, "Is there a refrigerator in the family ?","ppiRafrigeratorInFamily"),
    IS_GAS_BURNER(4, "Does the family use stove / gas burner ?","ppiStoveInFamily"),
    IS_PRESSURE_COOCKER(5, "Does the family have pressure cooker / pressure pan ?","ppiPressureCookerInFamily"),
    IS_TALIVISION(6, "Does family have television ?","ppiTvInFamily"),
    IS_FAN(7, "Does family have electric fan ?","ppiFanInFamily"),
    IS_ALMIRAH(8, "Does family have almirah or Dressing table ?","ppiVehicleInFamily"),
    IS_CHAIR(9, "Does family have chair, stool, bench and table ?","ppiDressingTableInFamily"),
    IS_VEHICLE(10, "Does family have cycle / motorcycle / car / jeep ?","ppiOtherTableInFamily");

    private Integer id;
    private String value;
    private String name;

    private PpiPersonDetailMFI(Integer id, String value,String name) {
        this.id = id;
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PpiPersonDetailMFI fromValue(String v) {
        for (PpiPersonDetailMFI c : PpiPersonDetailMFI.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static PpiPersonDetailMFI fromId(String v) {
        for (PpiPersonDetailMFI c : PpiPersonDetailMFI.values()) {
            if (c.id.equals(v) || Integer.parseInt(v) == c.id) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static PpiPersonDetailMFI[] getAll() {
        return PpiPersonDetailMFI.values();
    }

}
