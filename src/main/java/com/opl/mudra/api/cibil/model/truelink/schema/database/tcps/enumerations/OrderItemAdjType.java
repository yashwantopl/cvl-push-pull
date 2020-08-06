
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderItemAdjType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OrderItemAdjType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Discount"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OrderItemAdjType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum OrderItemAdjType {

    @XmlEnumValue("Discount")
    DISCOUNT("Discount");
    private final String value;

    OrderItemAdjType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OrderItemAdjType fromValue(String v) {
        for (OrderItemAdjType c: OrderItemAdjType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
