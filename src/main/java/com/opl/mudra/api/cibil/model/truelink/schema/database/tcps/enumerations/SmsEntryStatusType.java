
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SmsEntryStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SmsEntryStatusType">
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
@XmlType(name = "SmsEntryStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum SmsEntryStatusType {

    @XmlEnumValue("Error")
    ERROR("Error"),
    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Successful")
    SUCCESSFUL("Successful");
    private final String value;

    SmsEntryStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SmsEntryStatusType fromValue(String v) {
        for (SmsEntryStatusType c: SmsEntryStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
