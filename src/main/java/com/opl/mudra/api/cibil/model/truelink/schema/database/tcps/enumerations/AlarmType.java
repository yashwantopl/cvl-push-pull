
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AlarmType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AlarmType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DIAAlarm"/>
 *     &lt;enumeration value="HCPAlarm"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AlarmType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum AlarmType {

    @XmlEnumValue("DIAAlarm")
    DIA_ALARM("DIAAlarm"),
    @XmlEnumValue("HCPAlarm")
    HCP_ALARM("HCPAlarm");
    private final String value;

    AlarmType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlarmType fromValue(String v) {
        for (AlarmType c: AlarmType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
