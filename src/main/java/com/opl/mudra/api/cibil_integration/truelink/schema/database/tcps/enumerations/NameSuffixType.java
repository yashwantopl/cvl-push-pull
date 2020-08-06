
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NameSuffixType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NameSuffixType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="II"/>
 *     &lt;enumeration value="III"/>
 *     &lt;enumeration value="IV"/>
 *     &lt;enumeration value="Jr"/>
 *     &lt;enumeration value="Sr"/>
 *     &lt;enumeration value="V"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NameSuffixType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum NameSuffixType {

    I("I"),
    II("II"),
    III("III"),
    IV("IV"),
    @XmlEnumValue("Jr")
    JR("Jr"),
    @XmlEnumValue("Sr")
    SR("Sr"),
    V("V");
    private final String value;

    NameSuffixType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NameSuffixType fromValue(String v) {
        for (NameSuffixType c: NameSuffixType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
