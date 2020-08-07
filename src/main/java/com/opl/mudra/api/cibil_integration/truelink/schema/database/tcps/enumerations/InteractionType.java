
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InteractionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InteractionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Offline"/>
 *     &lt;enumeration value="Online"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InteractionType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum InteractionType {

    @XmlEnumValue("Offline")
    OFFLINE("Offline"),
    @XmlEnumValue("Online")
    ONLINE("Online");
    private final String value;

    InteractionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InteractionType fromValue(String v) {
        for (InteractionType c: InteractionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
