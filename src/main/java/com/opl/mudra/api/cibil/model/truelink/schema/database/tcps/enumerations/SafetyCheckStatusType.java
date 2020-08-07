
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SafetyCheckStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SafetyCheckStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Failed"/>
 *     &lt;enumeration value="Passed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SafetyCheckStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum SafetyCheckStatusType {

    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("Passed")
    PASSED("Passed");
    private final String value;

    SafetyCheckStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SafetyCheckStatusType fromValue(String v) {
        for (SafetyCheckStatusType c: SafetyCheckStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
