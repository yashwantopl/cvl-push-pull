
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AvcFileStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AvcFileStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FileDownloaded"/>
 *     &lt;enumeration value="FileGenerationComplete"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AvcFileStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum AvcFileStatusType {

    @XmlEnumValue("FileDownloaded")
    FILE_DOWNLOADED("FileDownloaded"),
    @XmlEnumValue("FileGenerationComplete")
    FILE_GENERATION_COMPLETE("FileGenerationComplete");
    private final String value;

    AvcFileStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AvcFileStatusType fromValue(String v) {
        for (AvcFileStatusType c: AvcFileStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
