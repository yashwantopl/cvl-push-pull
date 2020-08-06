
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SmsVendorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SmsVendorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Clickatel"/>
 *     &lt;enumeration value="RedOxygen"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SmsVendorType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum SmsVendorType {

    @XmlEnumValue("Clickatel")
    CLICKATEL("Clickatel"),
    @XmlEnumValue("RedOxygen")
    RED_OXYGEN("RedOxygen");
    private final String value;

    SmsVendorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SmsVendorType fromValue(String v) {
        for (SmsVendorType c: SmsVendorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
