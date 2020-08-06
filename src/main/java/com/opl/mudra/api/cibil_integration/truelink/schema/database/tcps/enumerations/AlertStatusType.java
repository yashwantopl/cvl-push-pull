
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AlertStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AlertStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AlertPending"/>
 *     &lt;enumeration value="AlertProcessed"/>
 *     &lt;enumeration value="AlertReceivedPostCancel"/>
 *     &lt;enumeration value="AlertSuspended"/>
 *     &lt;enumeration value="InProgress"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AlertStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum AlertStatusType {

    @XmlEnumValue("AlertPending")
    ALERT_PENDING("AlertPending"),
    @XmlEnumValue("AlertProcessed")
    ALERT_PROCESSED("AlertProcessed"),
    @XmlEnumValue("AlertReceivedPostCancel")
    ALERT_RECEIVED_POST_CANCEL("AlertReceivedPostCancel"),
    @XmlEnumValue("AlertSuspended")
    ALERT_SUSPENDED("AlertSuspended"),
    @XmlEnumValue("InProgress")
    IN_PROGRESS("InProgress");
    private final String value;

    AlertStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlertStatusType fromValue(String v) {
        for (AlertStatusType c: AlertStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
