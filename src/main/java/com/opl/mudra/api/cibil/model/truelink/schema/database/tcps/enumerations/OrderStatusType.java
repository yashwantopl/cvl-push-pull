
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OrderStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandoned"/>
 *     &lt;enumeration value="Accepted"/>
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Canceled"/>
 *     &lt;enumeration value="Config"/>
 *     &lt;enumeration value="Expired"/>
 *     &lt;enumeration value="Finished"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OrderStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum OrderStatusType {

    @XmlEnumValue("Abandoned")
    ABANDONED("Abandoned"),
    @XmlEnumValue("Accepted")
    ACCEPTED("Accepted"),
    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Canceled")
    CANCELED("Canceled"),
    @XmlEnumValue("Config")
    CONFIG("Config"),
    @XmlEnumValue("Expired")
    EXPIRED("Expired"),
    @XmlEnumValue("Finished")
    FINISHED("Finished");
    private final String value;

    OrderStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OrderStatusType fromValue(String v) {
        for (OrderStatusType c: OrderStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
