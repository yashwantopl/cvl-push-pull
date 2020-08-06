
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransEntryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransEntryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Authorize"/>
 *     &lt;enumeration value="Capture"/>
 *     &lt;enumeration value="Charge"/>
 *     &lt;enumeration value="Credit"/>
 *     &lt;enumeration value="Refund"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransEntryType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum TransEntryType {

    @XmlEnumValue("Authorize")
    AUTHORIZE("Authorize"),
    @XmlEnumValue("Capture")
    CAPTURE("Capture"),
    @XmlEnumValue("Charge")
    CHARGE("Charge"),
    @XmlEnumValue("Credit")
    CREDIT("Credit"),
    @XmlEnumValue("Refund")
    REFUND("Refund");
    private final String value;

    TransEntryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransEntryType fromValue(String v) {
        for (TransEntryType c: TransEntryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
