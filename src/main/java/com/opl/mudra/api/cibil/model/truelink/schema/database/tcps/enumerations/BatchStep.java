
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchStep.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BatchStep">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AllSteps"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BatchStep", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BatchStep {

    @XmlEnumValue("AllSteps")
    ALL_STEPS("AllSteps");
    private final String value;

    BatchStep(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BatchStep fromValue(String v) {
        for (BatchStep c: BatchStep.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
