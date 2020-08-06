
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ResponseStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Success"/>
 *     &lt;enumeration value="Pending_Success"/>
 *     &lt;enumeration value="Pending_Failure"/>
 *     &lt;enumeration value="Failure"/>
 *     &lt;enumeration value="ServiceError"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ResponseStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ResponseStatusType {

    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("Pending_Success")
    PENDING_SUCCESS("Pending_Success"),
    @XmlEnumValue("Pending_Failure")
    PENDING_FAILURE("Pending_Failure"),
    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("ServiceError")
    SERVICE_ERROR("ServiceError");
    private final String value;

    ResponseStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResponseStatusType fromValue(String v) {
        for (ResponseStatusType c: ResponseStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
