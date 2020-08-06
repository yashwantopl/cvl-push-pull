
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentTransStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PaymentTransStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Authorized"/>
 *     &lt;enumeration value="Init"/>
 *     &lt;enumeration value="Paid"/>
 *     &lt;enumeration value="Refunded"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PaymentTransStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum PaymentTransStatusType {

    @XmlEnumValue("Authorized")
    AUTHORIZED("Authorized"),
    @XmlEnumValue("Init")
    INIT("Init"),
    @XmlEnumValue("Paid")
    PAID("Paid"),
    @XmlEnumValue("Refunded")
    REFUNDED("Refunded");
    private final String value;

    PaymentTransStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PaymentTransStatusType fromValue(String v) {
        for (PaymentTransStatusType c: PaymentTransStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
