
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssetStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AssetStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Canceled"/>
 *     &lt;enumeration value="Unavailable"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AssetStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum AssetStatusType {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Canceled")
    CANCELED("Canceled"),
    @XmlEnumValue("Unavailable")
    UNAVAILABLE("Unavailable");
    private final String value;

    AssetStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AssetStatusType fromValue(String v) {
        for (AssetStatusType c: AssetStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
