
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnrollmentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnrollmentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AuthorizedTwoPhase"/>
 *     &lt;enumeration value="PrimaryOnePhase"/>
 *     &lt;enumeration value="PrimaryTwoPhase"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnrollmentType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum EnrollmentType {

    @XmlEnumValue("AuthorizedTwoPhase")
    AUTHORIZED_TWO_PHASE("AuthorizedTwoPhase"),
    @XmlEnumValue("PrimaryOnePhase")
    PRIMARY_ONE_PHASE("PrimaryOnePhase"),
    @XmlEnumValue("PrimaryTwoPhase")
    PRIMARY_TWO_PHASE("PrimaryTwoPhase");
    private final String value;

    EnrollmentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnrollmentType fromValue(String v) {
        for (EnrollmentType c: EnrollmentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
