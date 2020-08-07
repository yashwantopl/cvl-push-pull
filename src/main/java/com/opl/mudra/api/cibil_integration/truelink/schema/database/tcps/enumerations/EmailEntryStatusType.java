
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EmailEntryStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EmailEntryStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Error"/>
 *     &lt;enumeration value="Failed"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Successful"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EmailEntryStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum EmailEntryStatusType {

    @XmlEnumValue("Error")
    ERROR("Error"),
    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Successful")
    SUCCESSFUL("Successful");
    private final String value;

    EmailEntryStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EmailEntryStatusType fromValue(String v) {
        for (EmailEntryStatusType c: EmailEntryStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
