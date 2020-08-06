
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentProcessorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PaymentProcessorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACHBatch"/>
 *     &lt;enumeration value="Concord"/>
 *     &lt;enumeration value="PaymentechBatch"/>
 *     &lt;enumeration value="PaymentechWS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PaymentProcessorType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum PaymentProcessorType {

    @XmlEnumValue("ACHBatch")
    ACH_BATCH("ACHBatch"),
    @XmlEnumValue("Concord")
    CONCORD("Concord"),
    @XmlEnumValue("PaymentechBatch")
    PAYMENTECH_BATCH("PaymentechBatch"),
    @XmlEnumValue("PaymentechWS")
    PAYMENTECH_WS("PaymentechWS");
    private final String value;

    PaymentProcessorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PaymentProcessorType fromValue(String v) {
        for (PaymentProcessorType c: PaymentProcessorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
