
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FraudActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FraudActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="IgnoreAlways"/>
 *     &lt;enumeration value="IgnoreOnEnrollment"/>
 *     &lt;enumeration value="IgnoreOnSubsequentPull"/>
 *     &lt;enumeration value="ObserveAlways"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FraudActionType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum FraudActionType {

    @XmlEnumValue("IgnoreAlways")
    IGNORE_ALWAYS("IgnoreAlways"),
    @XmlEnumValue("IgnoreOnEnrollment")
    IGNORE_ON_ENROLLMENT("IgnoreOnEnrollment"),
    @XmlEnumValue("IgnoreOnSubsequentPull")
    IGNORE_ON_SUBSEQUENT_PULL("IgnoreOnSubsequentPull"),
    @XmlEnumValue("ObserveAlways")
    OBSERVE_ALWAYS("ObserveAlways");
    private final String value;

    FraudActionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FraudActionType fromValue(String v) {
        for (FraudActionType c: FraudActionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
