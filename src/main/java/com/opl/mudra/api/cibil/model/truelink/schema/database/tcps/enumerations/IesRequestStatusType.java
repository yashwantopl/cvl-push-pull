
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IesRequestStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IesRequestStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Failure"/>
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="ServiceError"/>
 *     &lt;enumeration value="Success"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IesRequestStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum IesRequestStatusType {

    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("ServiceError")
    SERVICE_ERROR("ServiceError"),
    @XmlEnumValue("Success")
    SUCCESS("Success");
    private final String value;

    IesRequestStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IesRequestStatusType fromValue(String v) {
        for (IesRequestStatusType c: IesRequestStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
