
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccUpdaterStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccUpdaterStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Error"/>
 *     &lt;enumeration value="Failure"/>
 *     &lt;enumeration value="InProgress"/>
 *     &lt;enumeration value="Success"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccUpdaterStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum AccUpdaterStatusType {

    @XmlEnumValue("Error")
    ERROR("Error"),
    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("InProgress")
    IN_PROGRESS("InProgress"),
    @XmlEnumValue("Success")
    SUCCESS("Success");
    private final String value;

    AccUpdaterStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccUpdaterStatusType fromValue(String v) {
        for (AccUpdaterStatusType c: AccUpdaterStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
