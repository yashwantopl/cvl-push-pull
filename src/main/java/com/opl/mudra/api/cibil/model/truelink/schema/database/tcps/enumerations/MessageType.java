
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Alert"/>
 *     &lt;enumeration value="CouldNotEnroll"/>
 *     &lt;enumeration value="SuccessfullyEnrolled"/>
 *     &lt;enumeration value="Unknown"/>
 *     &lt;enumeration value="WatchReconciliation"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MessageType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum MessageType {

    @XmlEnumValue("Alert")
    ALERT("Alert"),
    @XmlEnumValue("CouldNotEnroll")
    COULD_NOT_ENROLL("CouldNotEnroll"),
    @XmlEnumValue("SuccessfullyEnrolled")
    SUCCESSFULLY_ENROLLED("SuccessfullyEnrolled"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),
    @XmlEnumValue("WatchReconciliation")
    WATCH_RECONCILIATION("WatchReconciliation");
    private final String value;

    MessageType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessageType fromValue(String v) {
        for (MessageType c: MessageType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
