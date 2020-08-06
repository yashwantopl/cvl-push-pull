
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BenefitType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BenefitType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DMV"/>
 *     &lt;enumeration value="IDTheft"/>
 *     &lt;enumeration value="MIB"/>
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="SSN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BenefitType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BenefitType {

    DMV("DMV"),
    @XmlEnumValue("IDTheft")
    ID_THEFT("IDTheft"),
    MIB("MIB"),
    NONE("NONE"),
    SSN("SSN");
    private final String value;

    BenefitType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BenefitType fromValue(String v) {
        for (BenefitType c: BenefitType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
