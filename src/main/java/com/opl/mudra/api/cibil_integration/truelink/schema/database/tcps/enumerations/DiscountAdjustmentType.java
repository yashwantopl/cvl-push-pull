
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DiscountAdjustmentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DiscountAdjustmentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AmountReduction"/>
 *     &lt;enumeration value="FixedPrice"/>
 *     &lt;enumeration value="PercentReduction"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DiscountAdjustmentType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum DiscountAdjustmentType {

    @XmlEnumValue("AmountReduction")
    AMOUNT_REDUCTION("AmountReduction"),
    @XmlEnumValue("FixedPrice")
    FIXED_PRICE("FixedPrice"),
    @XmlEnumValue("PercentReduction")
    PERCENT_REDUCTION("PercentReduction");
    private final String value;

    DiscountAdjustmentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DiscountAdjustmentType fromValue(String v) {
        for (DiscountAdjustmentType c: DiscountAdjustmentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
