
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrendDataType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TrendDataType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CreditReport"/>
 *     &lt;enumeration value="CreditScore"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TrendDataType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum TrendDataType {

    @XmlEnumValue("CreditReport")
    CREDIT_REPORT("CreditReport"),
    @XmlEnumValue("CreditScore")
    CREDIT_SCORE("CreditScore");
    private final String value;

    TrendDataType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TrendDataType fromValue(String v) {
        for (TrendDataType c: TrendDataType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
