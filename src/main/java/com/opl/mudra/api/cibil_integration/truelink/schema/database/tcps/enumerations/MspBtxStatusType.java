
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MspBtxStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MspBtxStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandoned"/>
 *     &lt;enumeration value="Complete"/>
 *     &lt;enumeration value="ManualResponse"/>
 *     &lt;enumeration value="ProcessingError"/>
 *     &lt;enumeration value="RequestInProgress"/>
 *     &lt;enumeration value="RequestPending"/>
 *     &lt;enumeration value="ResponsePending"/>
 *     &lt;enumeration value="RetryCallback"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MspBtxStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum MspBtxStatusType {

    @XmlEnumValue("Abandoned")
    ABANDONED("Abandoned"),
    @XmlEnumValue("Complete")
    COMPLETE("Complete"),
    @XmlEnumValue("ManualResponse")
    MANUAL_RESPONSE("ManualResponse"),
    @XmlEnumValue("ProcessingError")
    PROCESSING_ERROR("ProcessingError"),
    @XmlEnumValue("RequestInProgress")
    REQUEST_IN_PROGRESS("RequestInProgress"),
    @XmlEnumValue("RequestPending")
    REQUEST_PENDING("RequestPending"),
    @XmlEnumValue("ResponsePending")
    RESPONSE_PENDING("ResponsePending"),
    @XmlEnumValue("RetryCallback")
    RETRY_CALLBACK("RetryCallback");
    private final String value;

    MspBtxStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MspBtxStatusType fromValue(String v) {
        for (MspBtxStatusType c: MspBtxStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
