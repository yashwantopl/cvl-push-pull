
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReflectionTranType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReflectionTranType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Cancel"/>
 *     &lt;enumeration value="Enroll"/>
 *     &lt;enumeration value="Refund"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReflectionTranType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ReflectionTranType {

    @XmlEnumValue("Cancel")
    CANCEL("Cancel"),
    @XmlEnumValue("Enroll")
    ENROLL("Enroll"),
    @XmlEnumValue("Refund")
    REFUND("Refund");
    private final String value;

    ReflectionTranType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReflectionTranType fromValue(String v) {
        for (ReflectionTranType c: ReflectionTranType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
