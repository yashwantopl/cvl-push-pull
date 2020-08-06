
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServicingLetterType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ServicingLetterType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FRAUD"/>
 *     &lt;enumeration value="ADDRESS_MISMATCH"/>
 *     &lt;enumeration value="NO_HIT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ServicingLetterType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ServicingLetterType {

    FRAUD,
    ADDRESS_MISMATCH,
    NO_HIT;

    public String value() {
        return name();
    }

    public static ServicingLetterType fromValue(String v) {
        return valueOf(v);
    }

}
