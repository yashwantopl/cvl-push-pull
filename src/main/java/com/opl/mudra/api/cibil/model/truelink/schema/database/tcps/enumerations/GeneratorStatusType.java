
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GeneratorStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GeneratorStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandoned"/>
 *     &lt;enumeration value="Failed"/>
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="RequestInProgress"/>
 *     &lt;enumeration value="Success"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GeneratorStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum GeneratorStatusType {

    @XmlEnumValue("Abandoned")
    ABANDONED("Abandoned"),
    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("RequestInProgress")
    REQUEST_IN_PROGRESS("RequestInProgress"),
    @XmlEnumValue("Success")
    SUCCESS("Success");
    private final String value;

    GeneratorStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GeneratorStatusType fromValue(String v) {
        for (GeneratorStatusType c: GeneratorStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
