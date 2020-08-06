
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LeadDispStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LeadDispStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="EnrolledComplete"/>
 *     &lt;enumeration value="EnrolledPending"/>
 *     &lt;enumeration value="NoResponseToDM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LeadDispStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum LeadDispStatusType {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("EnrolledComplete")
    ENROLLED_COMPLETE("EnrolledComplete"),
    @XmlEnumValue("EnrolledPending")
    ENROLLED_PENDING("EnrolledPending"),
    @XmlEnumValue("NoResponseToDM")
    NO_RESPONSE_TO_DM("NoResponseToDM");
    private final String value;

    LeadDispStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LeadDispStatusType fromValue(String v) {
        for (LeadDispStatusType c: LeadDispStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
