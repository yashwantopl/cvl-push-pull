package com.opl.mudra.api.oneform.enums;

public enum CertificationCourse {

	SKILL_CERTIFICATION_COURSE(1,"Skill Certification Course","Skill Certification Course"),
	RURAL_SELF_EMPLOYMENT_TRAINING_INSTITUTE(2,"Rural Self Employment Training Institute","Rural Self Employment Training Institute"),
	INDUSTRIAL_TRAINING_INSTITUTE(3,"Industrial Training Institute","Industrial Training Institute"),
	OTHERS(4,"Others","Others");

    private final Integer id;
    private final String value;
    private final String description;

    CertificationCourse(Integer id, String value, String description) {
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

    public static CertificationCourse getById(Integer id) {
        switch (id) {
            case 1:
                return SKILL_CERTIFICATION_COURSE;
            case 2:
                return RURAL_SELF_EMPLOYMENT_TRAINING_INSTITUTE;
            case 3:
            	return INDUSTRIAL_TRAINING_INSTITUTE;
            case 4:
            	return OTHERS;
            default:
                return null;
        }
    }
    public static CertificationCourse[] getAll() {
        return CertificationCourse.values();

    }

}
