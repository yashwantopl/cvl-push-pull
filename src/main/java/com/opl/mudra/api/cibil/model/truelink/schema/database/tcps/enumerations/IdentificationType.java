
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdentificationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdentificationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CoreAttributeMatch"/>
 *     &lt;enumeration value="IVChallengeService"/>
 *     &lt;enumeration value="IVservice"/>
 *     &lt;enumeration value="LegacySystem"/>
 *     &lt;enumeration value="NotRequired"/>
 *     &lt;enumeration value="Support"/>
 *     &lt;enumeration value="TrackingCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IdentificationType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum IdentificationType {

    @XmlEnumValue("CoreAttributeMatch")
    CORE_ATTRIBUTE_MATCH("CoreAttributeMatch"),
    @XmlEnumValue("IVChallengeService")
    IV_CHALLENGE_SERVICE("IVChallengeService"),
    @XmlEnumValue("IVservice")
    I_VSERVICE("IVservice"),
    @XmlEnumValue("LegacySystem")
    LEGACY_SYSTEM("LegacySystem"),
    @XmlEnumValue("NotRequired")
    NOT_REQUIRED("NotRequired"),
    @XmlEnumValue("Support")
    SUPPORT("Support"),
    @XmlEnumValue("TrackingCode")
    TRACKING_CODE("TrackingCode");
    private final String value;

    IdentificationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdentificationType fromValue(String v) {
        for (IdentificationType c: IdentificationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
