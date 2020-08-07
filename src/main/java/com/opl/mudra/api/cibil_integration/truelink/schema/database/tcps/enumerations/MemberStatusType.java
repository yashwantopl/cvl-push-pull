
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MemberStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MemberStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DuplicateMember"/>
 *     &lt;enumeration value="FullMember"/>
 *     &lt;enumeration value="PartialMember"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MemberStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum MemberStatusType {

    @XmlEnumValue("DuplicateMember")
    DUPLICATE_MEMBER("DuplicateMember"),
    @XmlEnumValue("FullMember")
    FULL_MEMBER("FullMember"),
    @XmlEnumValue("PartialMember")
    PARTIAL_MEMBER("PartialMember");
    private final String value;

    MemberStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MemberStatusType fromValue(String v) {
        for (MemberStatusType c: MemberStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
