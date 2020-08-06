
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeliveryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DeliveryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EMail"/>
 *     &lt;enumeration value="Print"/>
 *     &lt;enumeration value="SMS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DeliveryType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum DeliveryType {

    @XmlEnumValue("EMail")
    E_MAIL("EMail"),
    @XmlEnumValue("Print")
    PRINT("Print"),
    SMS("SMS");
    private final String value;

    DeliveryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DeliveryType fromValue(String v) {
        for (DeliveryType c: DeliveryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
