
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SocialType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SocialType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DriversLicenseId"/>
 *     &lt;enumeration value="PassportId"/>
 *     &lt;enumeration value="SocialId"/>
 *     &lt;enumeration value="TaxId"/>
 *     &lt;enumeration value="UniversalId"/>
 *     &lt;enumeration value="VoterId"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SocialType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum SocialType {

    @XmlEnumValue("DriversLicenseId")
    DRIVERS_LICENSE_ID("DriversLicenseId"),
    @XmlEnumValue("PassportId")
    PASSPORT_ID("PassportId"),
    @XmlEnumValue("SocialId")
    SOCIAL_ID("SocialId"),
    @XmlEnumValue("TaxId")
    TAX_ID("TaxId"),
    @XmlEnumValue("UniversalId")
    UNIVERSAL_ID("UniversalId"),
    @XmlEnumValue("VoterId")
    VOTER_ID("VoterId");
    private final String value;

    SocialType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SocialType fromValue(String v) {
        for (SocialType c: SocialType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
