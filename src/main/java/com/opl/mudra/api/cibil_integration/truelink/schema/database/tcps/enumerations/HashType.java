
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HashType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HashType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MD5"/>
 *     &lt;enumeration value="MD5+stretchedSHA-512"/>
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="SHA1"/>
 *     &lt;enumeration value="SHA1+stretchedSHA-512"/>
 *     &lt;enumeration value="stretchedSHA-512"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "HashType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum HashType {

    @XmlEnumValue("MD5")
    MD_5("MD5"),
    @XmlEnumValue("MD5+stretchedSHA-512")
    MD_5_STRETCHED_SHA_512("MD5+stretchedSHA-512"),
    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("SHA1")
    SHA_1("SHA1"),
    @XmlEnumValue("SHA1+stretchedSHA-512")
    SHA_1_STRETCHED_SHA_512("SHA1+stretchedSHA-512"),
    @XmlEnumValue("stretchedSHA-512")
    STRETCHED_SHA_512("stretchedSHA-512");
    private final String value;

    HashType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HashType fromValue(String v) {
        for (HashType c: HashType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
