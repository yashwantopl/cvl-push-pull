package com.opl.mudra.api.enums.oneform;

public enum ODOPSkillCertificate {

	INDUSTRIAL_TRAINING_INTERNATIONAL(1, "Industrial Traning Institute", "Industrial Traning Institute"),
	RURAL_SELF_EMPLOYMENT_TRAINING_INSTITUTES(2 , "Rural Self Employment Training Institutes", "Rural Self Employment Training Institutes"),
	OTHERS(3, "Others", "Others"),
	NONE(4, "None", "None");
	
	private final Integer id;
    private final String value;
    private final String description;
    
    ODOPSkillCertificate(Integer id, String value, String description) {
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
    
    public static ODOPSkillCertificate getById(Integer id) {
        switch (id) {
            case 1:
                return INDUSTRIAL_TRAINING_INTERNATIONAL;
            case 2:
                return RURAL_SELF_EMPLOYMENT_TRAINING_INSTITUTES;
            case 3:
                return OTHERS;
            case 4:
                return NONE;
            default:
                return null;
        }
    }

    public static ODOPSkillCertificate[] getAll() {
        return ODOPSkillCertificate.values();

    }
}
