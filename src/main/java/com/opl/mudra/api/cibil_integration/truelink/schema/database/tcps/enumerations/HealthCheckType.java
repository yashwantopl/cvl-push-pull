
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HealthCheckType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HealthCheckType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Service"/>
 *     &lt;enumeration value="Web"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "HealthCheckType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum HealthCheckType {

    @XmlEnumValue("Service")
    SERVICE("Service"),
    @XmlEnumValue("Web")
    WEB("Web");
    private final String value;

    HealthCheckType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HealthCheckType fromValue(String v) {
        for (HealthCheckType c: HealthCheckType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
