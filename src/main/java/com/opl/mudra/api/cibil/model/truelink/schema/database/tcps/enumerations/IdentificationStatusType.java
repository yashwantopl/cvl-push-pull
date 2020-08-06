
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdentificationStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdentificationStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Failed"/>
 *     &lt;enumeration value="Passed"/>
 *     &lt;enumeration value="RecordedInService"/>
 *     &lt;enumeration value="Started"/>
 *     &lt;enumeration value="Superseded"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IdentificationStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum IdentificationStatusType {

    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("Passed")
    PASSED("Passed"),
    @XmlEnumValue("RecordedInService")
    RECORDED_IN_SERVICE("RecordedInService"),
    @XmlEnumValue("Started")
    STARTED("Started"),
    @XmlEnumValue("Superseded")
    SUPERSEDED("Superseded");
    private final String value;

    IdentificationStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdentificationStatusType fromValue(String v) {
        for (IdentificationStatusType c: IdentificationStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
