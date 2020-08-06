package com.opl.mudra.api.enums.oneform;

public enum EducationalStatusMudraMst {


    GRADUATE(1,"Graduate (General)","GRADUATE (GENERAL)"),
    ILLITERATE(3,"Illiterate","ILLITERATE"),
    POST_GRADUATE(4,"Post Graduate (General)","POST GRADUATE (General)"),
    MATRICULATE(5,"Matriculate","MATRICULATE"),
    NOT_DECLARED(6,"Not Declared","NOT DECLARED"),
    MG_PG(7,"Medical Graduate/Post Graduate","MEDICAL GRADUATE/POST GRADUATE"),
    EG_PG(8,"Engineering Graduate/Post Graduate","ENGINEERING GRADUATE/POST GRADUATE"),
    LG_PG(9,"Law Graduate/Post Graduate","LAW GRADUATE/POST GRADUATE"),
    CA(10,"CA/ICWA/MBA/CFA","CA/ICWA/MBA/CFA"),
    CD(11,"Computer Degree/Diploma/MCA","COMPUTER DEGREE/DIPLOMA/MCA"),
    OTHERS(12,"Other Professional Degree/Diploma","OTHER PROFESSIONAL DEGREE/DIPLOMA"),
    EIGHT_AND_ABOVE(13,"Eight and Above","Eight and Above"),
	LITERATE_UPTO_CLASS_7(14,"Literate/upto class VII","Literate/upto class VII");

    private final Integer id;
    private final String value;
    private final String description;
    

    EducationalStatusMudraMst(Integer id, String value, String description) {
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


    public static EducationalStatusMudraMst getById(Integer id) {
        switch (id) {
            case 1:
                return GRADUATE;
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
            case 13:
            	return EIGHT_AND_ABOVE;
            case 14:
            	return LITERATE_UPTO_CLASS_7;
            default:
                return null;
        }
    }
    public static EducationalStatusMudraMst[] getAll() {
        return EducationalStatusMudraMst.values();
    }

}
