
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorStatusEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorStatusEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="InternalError"/>
 *     &lt;enumeration value="ValidationError"/>
 *     &lt;enumeration value="VendorUnavailable"/>
 *     &lt;enumeration value="VendorTimedOut"/>
 *     &lt;enumeration value="InvalidVendorRequest"/>
 *     &lt;enumeration value="InvalidVendorResponse"/>
 *     &lt;enumeration value="FailedToEnrollMonitoring"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ErrorStatusEnum")
@XmlEnum
public enum ErrorStatusEnum {

    @XmlEnumValue("InternalError")
    INTERNAL_ERROR("InternalError"),
    @XmlEnumValue("ValidationError")
    VALIDATION_ERROR("ValidationError"),
    @XmlEnumValue("VendorUnavailable")
    VENDOR_UNAVAILABLE("VendorUnavailable"),
    @XmlEnumValue("VendorTimedOut")
    VENDOR_TIMED_OUT("VendorTimedOut"),
    @XmlEnumValue("InvalidVendorRequest")
    INVALID_VENDOR_REQUEST("InvalidVendorRequest"),
    @XmlEnumValue("InvalidVendorResponse")
    INVALID_VENDOR_RESPONSE("InvalidVendorResponse"),
    @XmlEnumValue("FailedToEnrollMonitoring")
    FAILED_TO_ENROLL_MONITORING("FailedToEnrollMonitoring");
    private final String value;

    ErrorStatusEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorStatusEnum fromValue(String v) {
        for (ErrorStatusEnum c: ErrorStatusEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
