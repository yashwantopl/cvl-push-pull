
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MessageStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CustomerAssetNotFound"/>
 *     &lt;enumeration value="CustomerNotFound"/>
 *     &lt;enumeration value="Failure"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="ServiceError"/>
 *     &lt;enumeration value="Success"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MessageStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum MessageStatusType {

    @XmlEnumValue("CustomerAssetNotFound")
    CUSTOMER_ASSET_NOT_FOUND("CustomerAssetNotFound"),
    @XmlEnumValue("CustomerNotFound")
    CUSTOMER_NOT_FOUND("CustomerNotFound"),
    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("ServiceError")
    SERVICE_ERROR("ServiceError"),
    @XmlEnumValue("Success")
    SUCCESS("Success");
    private final String value;

    MessageStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessageStatusType fromValue(String v) {
        for (MessageStatusType c: MessageStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
