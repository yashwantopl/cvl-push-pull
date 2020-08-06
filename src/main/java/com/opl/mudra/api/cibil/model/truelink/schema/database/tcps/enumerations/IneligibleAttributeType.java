
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IneligibleAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IneligibleAttributeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VertrueMembershipID"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IneligibleAttributeType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum IneligibleAttributeType {

    @XmlEnumValue("VertrueMembershipID")
    VERTRUE_MEMBERSHIP_ID("VertrueMembershipID");
    private final String value;

    IneligibleAttributeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IneligibleAttributeType fromValue(String v) {
        for (IneligibleAttributeType c: IneligibleAttributeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
