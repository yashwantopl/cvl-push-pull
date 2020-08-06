
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CommStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Failure"/>
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="PendingFailure"/>
 *     &lt;enumeration value="ServiceError"/>
 *     &lt;enumeration value="Success"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CommStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CommStatusType {

    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("PendingFailure")
    PENDING_FAILURE("PendingFailure"),
    @XmlEnumValue("ServiceError")
    SERVICE_ERROR("ServiceError"),
    @XmlEnumValue("Success")
    SUCCESS("Success");
    private final String value;

    CommStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CommStatusType fromValue(String v) {
        for (CommStatusType c: CommStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
