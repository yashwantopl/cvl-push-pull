
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ScheduleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ScheduleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Duration"/>
 *     &lt;enumeration value="Fulfillments"/>
 *     &lt;enumeration value="Periods"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ScheduleType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ScheduleType {

    @XmlEnumValue("Duration")
    DURATION("Duration"),
    @XmlEnumValue("Fulfillments")
    FULFILLMENTS("Fulfillments"),
    @XmlEnumValue("Periods")
    PERIODS("Periods");
    private final String value;

    ScheduleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ScheduleType fromValue(String v) {
        for (ScheduleType c: ScheduleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
