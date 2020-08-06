
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderItemStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OrderItemStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Canceled"/>
 *     &lt;enumeration value="Replaced"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OrderItemStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum OrderItemStatusType {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Canceled")
    CANCELED("Canceled"),
    @XmlEnumValue("Replaced")
    REPLACED("Replaced");
    private final String value;

    OrderItemStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OrderItemStatusType fromValue(String v) {
        for (OrderItemStatusType c: OrderItemStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
