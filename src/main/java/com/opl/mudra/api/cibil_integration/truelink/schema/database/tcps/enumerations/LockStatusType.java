
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LockStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LockStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Lock"/>
 *     &lt;enumeration value="PermanentUnlock"/>
 *     &lt;enumeration value="Unlock"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LockStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum LockStatusType {

    @XmlEnumValue("Lock")
    LOCK("Lock"),
    @XmlEnumValue("PermanentUnlock")
    PERMANENT_UNLOCK("PermanentUnlock"),
    @XmlEnumValue("Unlock")
    UNLOCK("Unlock");
    private final String value;

    LockStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LockStatusType fromValue(String v) {
        for (LockStatusType c: LockStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
