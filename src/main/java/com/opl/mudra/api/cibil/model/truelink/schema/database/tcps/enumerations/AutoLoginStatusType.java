
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AutoLoginStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AutoLoginStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Failure"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Success"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AutoLoginStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum AutoLoginStatusType {

    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Success")
    SUCCESS("Success");
    private final String value;

    AutoLoginStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AutoLoginStatusType fromValue(String v) {
        for (AutoLoginStatusType c: AutoLoginStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
