
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnrollmentStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnrollmentStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandoned"/>
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Canceled"/>
 *     &lt;enumeration value="FailurePending"/>
 *     &lt;enumeration value="Pending"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnrollmentStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum EnrollmentStatusType {

    @XmlEnumValue("Abandoned")
    ABANDONED("Abandoned"),
    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Canceled")
    CANCELED("Canceled"),
    @XmlEnumValue("FailurePending")
    FAILURE_PENDING("FailurePending"),
    @XmlEnumValue("Pending")
    PENDING("Pending");
    private final String value;

    EnrollmentStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnrollmentStatusType fromValue(String v) {
        for (EnrollmentStatusType c: EnrollmentStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
