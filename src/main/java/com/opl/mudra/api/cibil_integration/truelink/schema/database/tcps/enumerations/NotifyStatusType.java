
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NotifyStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NotifyStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandoned"/>
 *     &lt;enumeration value="Complete"/>
 *     &lt;enumeration value="Failed"/>
 *     &lt;enumeration value="FailedConfig"/>
 *     &lt;enumeration value="NotSent"/>
 *     &lt;enumeration value="RequestPending"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NotifyStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum NotifyStatusType {

    @XmlEnumValue("Abandoned")
    ABANDONED("Abandoned"),
    @XmlEnumValue("Complete")
    COMPLETE("Complete"),
    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("FailedConfig")
    FAILED_CONFIG("FailedConfig"),
    @XmlEnumValue("NotSent")
    NOT_SENT("NotSent"),
    @XmlEnumValue("RequestPending")
    REQUEST_PENDING("RequestPending");
    private final String value;

    NotifyStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NotifyStatusType fromValue(String v) {
        for (NotifyStatusType c: NotifyStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
