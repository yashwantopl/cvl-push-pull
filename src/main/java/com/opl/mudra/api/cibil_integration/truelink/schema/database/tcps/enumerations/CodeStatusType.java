
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CodeStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CodeStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Inactive"/>
 *     &lt;enumeration value="Replaced"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CodeStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CodeStatusType {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Inactive")
    INACTIVE("Inactive"),
    @XmlEnumValue("Replaced")
    REPLACED("Replaced");
    private final String value;

    CodeStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CodeStatusType fromValue(String v) {
        for (CodeStatusType c: CodeStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
