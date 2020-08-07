/**
 * 
 */
package com.opl.mudra.api.oneform.enums;

/**
 * @author sanket.devare
 *
 */
public enum QualificationODOP {
	
	INTERMEDIATE_OR_MORE(1,"Intermediate or more","INTERMEDIATE_OR_MORE","H"),
	GRADUATION(2,"Graduation","GRADUATION","F"),
	METRIC(3,"Metric","METRIC","O"),
	BELOW_METRIC(4,"Below Metric","BELOW_METRIC","T");

	
	private final Integer id;
    private final String value;
    private final String description;
    private final String sbiCode;

    QualificationODOP(Integer id, String value, String description, String sbiCode) {
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

    public static QualificationODOP getById(Integer id) {
        switch (id) {
            case 1:
                return INTERMEDIATE_OR_MORE;
            case 2:
                return GRADUATION;
            case 3:
                return METRIC;
            case 4:
                return BELOW_METRIC;
            default:
                return null;
        }
    }
    public static QualificationODOP[] getAll() {
        return QualificationODOP.values();

    }

    public static QualificationODOP fromSbiCode(String v) {
        for (QualificationODOP c : QualificationODOP.values()) {
            if (c.sbiCode.equalsIgnoreCase(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
