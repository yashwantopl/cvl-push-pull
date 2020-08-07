
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RoleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Admin"/>
 *     &lt;enumeration value="CommResearch"/>
 *     &lt;enumeration value="Compliance"/>
 *     &lt;enumeration value="Enrollment"/>
 *     &lt;enumeration value="EscalationSLO"/>
 *     &lt;enumeration value="Fraud"/>
 *     &lt;enumeration value="Sales"/>
 *     &lt;enumeration value="Support"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RoleType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum RoleType {

    @XmlEnumValue("Admin")
    ADMIN("Admin"),
    @XmlEnumValue("CommResearch")
    COMM_RESEARCH("CommResearch"),
    @XmlEnumValue("Compliance")
    COMPLIANCE("Compliance"),
    @XmlEnumValue("Enrollment")
    ENROLLMENT("Enrollment"),
    @XmlEnumValue("EscalationSLO")
    ESCALATION_SLO("EscalationSLO"),
    @XmlEnumValue("Fraud")
    FRAUD("Fraud"),
    @XmlEnumValue("Sales")
    SALES("Sales"),
    @XmlEnumValue("Support")
    SUPPORT("Support");
    private final String value;

    RoleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RoleType fromValue(String v) {
        for (RoleType c: RoleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
