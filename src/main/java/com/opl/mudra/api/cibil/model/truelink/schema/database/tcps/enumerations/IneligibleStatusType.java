
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IneligibleStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IneligibleStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Ineligible"/>
 *     &lt;enumeration value="Reactivated"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IneligibleStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum IneligibleStatusType {

    @XmlEnumValue("Ineligible")
    INELIGIBLE("Ineligible"),
    @XmlEnumValue("Reactivated")
    REACTIVATED("Reactivated");
    private final String value;

    IneligibleStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IneligibleStatusType fromValue(String v) {
        for (IneligibleStatusType c: IneligibleStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
