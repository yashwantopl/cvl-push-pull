
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FlowStateType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FlowStateType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FastTrack"/>
 *     &lt;enumeration value="Normal"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FlowStateType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum FlowStateType {

    @XmlEnumValue("FastTrack")
    FAST_TRACK("FastTrack"),
    @XmlEnumValue("Normal")
    NORMAL("Normal");
    private final String value;

    FlowStateType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FlowStateType fromValue(String v) {
        for (FlowStateType c: FlowStateType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
