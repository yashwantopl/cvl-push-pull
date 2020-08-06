
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComponentGroupType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ComponentGroupType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Alerts"/>
 *     &lt;enumeration value="Monitoring"/>
 *     &lt;enumeration value="Reports"/>
 *     &lt;enumeration value="Scores"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ComponentGroupType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ComponentGroupType {

    @XmlEnumValue("Alerts")
    ALERTS("Alerts"),
    @XmlEnumValue("Monitoring")
    MONITORING("Monitoring"),
    @XmlEnumValue("Reports")
    REPORTS("Reports"),
    @XmlEnumValue("Scores")
    SCORES("Scores");
    private final String value;

    ComponentGroupType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ComponentGroupType fromValue(String v) {
        for (ComponentGroupType c: ComponentGroupType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
