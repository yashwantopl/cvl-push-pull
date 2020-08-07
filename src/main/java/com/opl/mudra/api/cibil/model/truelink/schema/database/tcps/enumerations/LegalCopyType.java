
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LegalCopyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LegalCopyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PermissionStatement"/>
 *     &lt;enumeration value="UserAgreement"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LegalCopyType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum LegalCopyType {

    @XmlEnumValue("PermissionStatement")
    PERMISSION_STATEMENT("PermissionStatement"),
    @XmlEnumValue("UserAgreement")
    USER_AGREEMENT("UserAgreement");
    private final String value;

    LegalCopyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LegalCopyType fromValue(String v) {
        for (LegalCopyType c: LegalCopyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
