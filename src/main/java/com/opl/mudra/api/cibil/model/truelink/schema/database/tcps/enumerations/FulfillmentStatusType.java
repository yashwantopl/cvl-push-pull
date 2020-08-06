
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FulfillmentStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FulfillmentStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandoned"/>
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Canceled"/>
 *     &lt;enumeration value="Complete"/>
 *     &lt;enumeration value="Failure"/>
 *     &lt;enumeration value="Retry"/>
 *     &lt;enumeration value="Unsubscribe"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FulfillmentStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum FulfillmentStatusType {

    @XmlEnumValue("Abandoned")
    ABANDONED("Abandoned"),
    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Canceled")
    CANCELED("Canceled"),
    @XmlEnumValue("Complete")
    COMPLETE("Complete"),
    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("Retry")
    RETRY("Retry"),
    @XmlEnumValue("Unsubscribe")
    UNSUBSCRIBE("Unsubscribe");
    private final String value;

    FulfillmentStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FulfillmentStatusType fromValue(String v) {
        for (FulfillmentStatusType c: FulfillmentStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
