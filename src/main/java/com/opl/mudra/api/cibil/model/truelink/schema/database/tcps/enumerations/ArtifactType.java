
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArtifactType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ArtifactType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Alert"/>
 *     &lt;enumeration value="CreditReport"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ArtifactType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ArtifactType {

    @XmlEnumValue("Alert")
    ALERT("Alert"),
    @XmlEnumValue("CreditReport")
    CREDIT_REPORT("CreditReport");
    private final String value;

    ArtifactType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ArtifactType fromValue(String v) {
        for (ArtifactType c: ArtifactType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
