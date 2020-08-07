
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BureauStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BureauStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Canceled"/>
 *     &lt;enumeration value="Failure"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Success"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BureauStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BureauStatusType {

    @XmlEnumValue("Canceled")
    CANCELED("Canceled"),
    @XmlEnumValue("Failure")
    FAILURE("Failure"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Success")
    SUCCESS("Success");
    private final String value;

    BureauStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BureauStatusType fromValue(String v) {
        for (BureauStatusType c: BureauStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
