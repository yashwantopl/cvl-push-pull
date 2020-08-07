
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoginStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LoginStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="MigrationDuplicate"/>
 *     &lt;enumeration value="ReAuthenticated"/>
 *     &lt;enumeration value="Retired"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LoginStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum LoginStatusType {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("MigrationDuplicate")
    MIGRATION_DUPLICATE("MigrationDuplicate"),
    @XmlEnumValue("ReAuthenticated")
    RE_AUTHENTICATED("ReAuthenticated"),
    @XmlEnumValue("Retired")
    RETIRED("Retired");
    private final String value;

    LoginStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LoginStatusType fromValue(String v) {
        for (LoginStatusType c: LoginStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
