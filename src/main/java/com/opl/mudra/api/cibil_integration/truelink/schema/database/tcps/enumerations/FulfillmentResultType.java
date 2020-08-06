
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FulfillmentResultType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FulfillmentResultType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AgeRestriction"/>
 *     &lt;enumeration value="CommunicationError"/>
 *     &lt;enumeration value="Deceased"/>
 *     &lt;enumeration value="Fraud"/>
 *     &lt;enumeration value="NameMismatch"/>
 *     &lt;enumeration value="NoHit"/>
 *     &lt;enumeration value="ParseError"/>
 *     &lt;enumeration value="SSNMismatch"/>
 *     &lt;enumeration value="ServiceError"/>
 *     &lt;enumeration value="Success"/>
 *     &lt;enumeration value="Unknown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FulfillmentResultType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum FulfillmentResultType {

    @XmlEnumValue("AgeRestriction")
    AGE_RESTRICTION("AgeRestriction"),
    @XmlEnumValue("CommunicationError")
    COMMUNICATION_ERROR("CommunicationError"),
    @XmlEnumValue("Deceased")
    DECEASED("Deceased"),
    @XmlEnumValue("Fraud")
    FRAUD("Fraud"),
    @XmlEnumValue("NameMismatch")
    NAME_MISMATCH("NameMismatch"),
    @XmlEnumValue("NoHit")
    NO_HIT("NoHit"),
    @XmlEnumValue("ParseError")
    PARSE_ERROR("ParseError"),
    @XmlEnumValue("SSNMismatch")
    SSN_MISMATCH("SSNMismatch"),
    @XmlEnumValue("ServiceError")
    SERVICE_ERROR("ServiceError"),
    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown");
    private final String value;

    FulfillmentResultType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FulfillmentResultType fromValue(String v) {
        for (FulfillmentResultType c: FulfillmentResultType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
