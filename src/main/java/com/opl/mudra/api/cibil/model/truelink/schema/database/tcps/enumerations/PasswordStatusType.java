
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PasswordStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PasswordStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ChangeRequired"/>
 *     &lt;enumeration value="Normal"/>
 *     &lt;enumeration value="SingleSignOn"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PasswordStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum PasswordStatusType {

    @XmlEnumValue("ChangeRequired")
    CHANGE_REQUIRED("ChangeRequired"),
    @XmlEnumValue("Normal")
    NORMAL("Normal"),
    @XmlEnumValue("SingleSignOn")
    SINGLE_SIGN_ON("SingleSignOn");
    private final String value;

    PasswordStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PasswordStatusType fromValue(String v) {
        for (PasswordStatusType c: PasswordStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
