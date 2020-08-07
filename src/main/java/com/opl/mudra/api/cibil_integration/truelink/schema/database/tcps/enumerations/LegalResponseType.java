
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LegalResponseType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LegalResponseType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Accept"/>
 *     &lt;enumeration value="Decline"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LegalResponseType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum LegalResponseType {

    @XmlEnumValue("Accept")
    ACCEPT("Accept"),
    @XmlEnumValue("Decline")
    DECLINE("Decline");
    private final String value;

    LegalResponseType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LegalResponseType fromValue(String v) {
        for (LegalResponseType c: LegalResponseType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
