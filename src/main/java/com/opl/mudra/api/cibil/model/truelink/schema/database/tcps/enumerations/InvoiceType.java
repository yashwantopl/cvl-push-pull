
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvoiceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InvoiceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AccountValidation"/>
 *     &lt;enumeration value="Authorization"/>
 *     &lt;enumeration value="BonusCheck"/>
 *     &lt;enumeration value="Charge"/>
 *     &lt;enumeration value="Credit"/>
 *     &lt;enumeration value="Refund"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InvoiceType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum InvoiceType {

    @XmlEnumValue("AccountValidation")
    ACCOUNT_VALIDATION("AccountValidation"),
    @XmlEnumValue("Authorization")
    AUTHORIZATION("Authorization"),
    @XmlEnumValue("BonusCheck")
    BONUS_CHECK("BonusCheck"),
    @XmlEnumValue("Charge")
    CHARGE("Charge"),
    @XmlEnumValue("Credit")
    CREDIT("Credit"),
    @XmlEnumValue("Refund")
    REFUND("Refund");
    private final String value;

    InvoiceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InvoiceType fromValue(String v) {
        for (InvoiceType c: InvoiceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
