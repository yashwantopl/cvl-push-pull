
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BlacklistStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BlacklistStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Exempt"/>
 *     &lt;enumeration value="Inactive"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BlacklistStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BlacklistStatusType {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Exempt")
    EXEMPT("Exempt"),
    @XmlEnumValue("Inactive")
    INACTIVE("Inactive");
    private final String value;

    BlacklistStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BlacklistStatusType fromValue(String v) {
        for (BlacklistStatusType c: BlacklistStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
