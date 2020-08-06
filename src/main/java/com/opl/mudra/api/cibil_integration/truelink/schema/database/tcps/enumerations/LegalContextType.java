
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LegalContextType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LegalContextType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Customer"/>
 *     &lt;enumeration value="Fulfillment"/>
 *     &lt;enumeration value="Order"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LegalContextType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum LegalContextType {

    @XmlEnumValue("Customer")
    CUSTOMER("Customer"),
    @XmlEnumValue("Fulfillment")
    FULFILLMENT("Fulfillment"),
    @XmlEnumValue("Order")
    ORDER("Order");
    private final String value;

    LegalContextType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LegalContextType fromValue(String v) {
        for (LegalContextType c: LegalContextType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
