
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EmployeeStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EmployeeStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Good"/>
 *     &lt;enumeration value="Locked"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EmployeeStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum EmployeeStatusType {

    @XmlEnumValue("Good")
    GOOD("Good"),
    @XmlEnumValue("Locked")
    LOCKED("Locked");
    private final String value;

    EmployeeStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EmployeeStatusType fromValue(String v) {
        for (EmployeeStatusType c: EmployeeStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
