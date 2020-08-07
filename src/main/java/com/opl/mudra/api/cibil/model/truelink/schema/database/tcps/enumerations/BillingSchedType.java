
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillingSchedType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BillingSchedType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Billing"/>
 *     &lt;enumeration value="BonusCheck"/>
 *     &lt;enumeration value="Credit"/>
 *     &lt;enumeration value="ExternalBilling"/>
 *     &lt;enumeration value="Validation"/>
 *     &lt;enumeration value="AccValidation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BillingSchedType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BillingSchedType {

    @XmlEnumValue("Billing")
    BILLING("Billing"),
    @XmlEnumValue("BonusCheck")
    BONUS_CHECK("BonusCheck"),
    @XmlEnumValue("Credit")
    CREDIT("Credit"),
    @XmlEnumValue("ExternalBilling")
    EXTERNAL_BILLING("ExternalBilling"),
    @XmlEnumValue("Validation")
    VALIDATION("Validation"),
    @XmlEnumValue("AccValidation")
    ACC_VALIDATION("AccValidation");
    private final String value;

    BillingSchedType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BillingSchedType fromValue(String v) {
        for (BillingSchedType c: BillingSchedType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
