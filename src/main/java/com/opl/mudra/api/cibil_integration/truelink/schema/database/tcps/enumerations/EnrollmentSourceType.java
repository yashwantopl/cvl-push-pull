
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnrollmentSourceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnrollmentSourceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CSPT"/>
 *     &lt;enumeration value="PartnerBatch"/>
 *     &lt;enumeration value="PartnerPost"/>
 *     &lt;enumeration value="ReplyCard"/>
 *     &lt;enumeration value="Web"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnrollmentSourceType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum EnrollmentSourceType {

    CSPT("CSPT"),
    @XmlEnumValue("PartnerBatch")
    PARTNER_BATCH("PartnerBatch"),
    @XmlEnumValue("PartnerPost")
    PARTNER_POST("PartnerPost"),
    @XmlEnumValue("ReplyCard")
    REPLY_CARD("ReplyCard"),
    @XmlEnumValue("Web")
    WEB("Web");
    private final String value;

    EnrollmentSourceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnrollmentSourceType fromValue(String v) {
        for (EnrollmentSourceType c: EnrollmentSourceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
