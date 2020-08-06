
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MemberNameType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MemberNameType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Former"/>
 *     &lt;enumeration value="Primary"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MemberNameType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum MemberNameType {

    @XmlEnumValue("Former")
    FORMER("Former"),
    @XmlEnumValue("Primary")
    PRIMARY("Primary");
    private final String value;

    MemberNameType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MemberNameType fromValue(String v) {
        for (MemberNameType c: MemberNameType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
