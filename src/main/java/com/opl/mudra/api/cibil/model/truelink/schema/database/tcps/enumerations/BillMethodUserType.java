
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillMethodUserType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BillMethodUserType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AuthorizedUser"/>
 *     &lt;enumeration value="Primary"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BillMethodUserType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BillMethodUserType {

    @XmlEnumValue("AuthorizedUser")
    AUTHORIZED_USER("AuthorizedUser"),
    @XmlEnumValue("Primary")
    PRIMARY("Primary");
    private final String value;

    BillMethodUserType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BillMethodUserType fromValue(String v) {
        for (BillMethodUserType c: BillMethodUserType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
