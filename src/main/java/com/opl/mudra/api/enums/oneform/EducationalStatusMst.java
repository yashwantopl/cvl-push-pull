package com.opl.mudra.api.enums.oneform;

public enum EducationalStatusMst {

    GRADUATE(1,"Graduate (General)","GRADUATE (GENERAL)","3"),
    UNDER_MATRICULATE(2,"Under Matriculate","UNDER MATRICULATE","1"),
    ILLITERATE(3,"Illiterate","ILLITERATE","99"),
    POST_GRADUATE(4,"Post Graduate (General)","POST GRADUATE (General)","4"),
    MATRICULATE(5,"Matriculate","MATRICULATE","2"),
    NOT_DECLARED(6,"Not Declared","NOT DECLARED","0"),
    MG_PG(7,"Medical Graduate/Post Graduate","MEDICAL GRADUATE/POST GRADUATE","5"),
    EG_PG(8,"Engineering Graduate/Post Graduate","ENGINEERING GRADUATE/POST GRADUATE","6"),
    LG_PG(9,"Law Graduate/Post Graduate","LAW GRADUATE/POST GRADUATE","7"),
    CA(10,"CA/ICWA/MBA/CFA","CA/ICWA/MBA/CFA","8"),
    CD(11,"Computer Degree/Diploma/MCA","COMPUTER DEGREE/DIPLOMA/MCA","10"),
    OTHERS(12,"Other Professional Degree/Diploma","OTHER PROFESSIONAL DEGREE/DIPLOMA","19");

    private final Integer id;
    private final String value;
    private final String description;
    private String sbiCode;

    EducationalStatusMst(Integer id, String value, String description, String sbiCode) {
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


    public static EducationalStatusMst getById(Integer id) {
        switch (id) {
            case 1:
                return GRADUATE;
            case 2:
                return UNDER_MATRICULATE;
            case 3:
                return ILLITERATE;
            case 4:
                return POST_GRADUATE;
            case 5:
                return MATRICULATE;
            case 6:
                return NOT_DECLARED;
            case 7:
                return MG_PG;
            case 8:
                return EG_PG;
            case 9:
                return LG_PG;
            case 10:
                return CA;
            case 11:
                return CD;
            case 12:
                return OTHERS;
            default:
                return null;
        }
    }
    public static EducationalStatusMst[] getAll() {
        return EducationalStatusMst.values();

    }

    public static EducationalStatusMst fromSbiCode(String v) {
        for (EducationalStatusMst c : EducationalStatusMst.values()) {
            if (c.sbiCode.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
