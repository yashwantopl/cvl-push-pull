
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvoiceStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InvoiceStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandoned"/>
 *     &lt;enumeration value="BillingUpdateRequired"/>
 *     &lt;enumeration value="Canceled"/>
 *     &lt;enumeration value="Complete"/>
 *     &lt;enumeration value="Configuration"/>
 *     &lt;enumeration value="Failed"/>
 *     &lt;enumeration value="Open"/>
 *     &lt;enumeration value="RefundOpen"/>
 *     &lt;enumeration value="Refunded"/>
 *     &lt;enumeration value="Retry"/>
 *     &lt;enumeration value="TaxPending"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InvoiceStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum InvoiceStatusType {

    @XmlEnumValue("Abandoned")
    ABANDONED("Abandoned"),
    @XmlEnumValue("BillingUpdateRequired")
    BILLING_UPDATE_REQUIRED("BillingUpdateRequired"),
    @XmlEnumValue("Canceled")
    CANCELED("Canceled"),
    @XmlEnumValue("Complete")
    COMPLETE("Complete"),
    @XmlEnumValue("Configuration")
    CONFIGURATION("Configuration"),
    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("Open")
    OPEN("Open"),
    @XmlEnumValue("RefundOpen")
    REFUND_OPEN("RefundOpen"),
    @XmlEnumValue("Refunded")
    REFUNDED("Refunded"),
    @XmlEnumValue("Retry")
    RETRY("Retry"),
    @XmlEnumValue("TaxPending")
    TAX_PENDING("TaxPending");
    private final String value;

    InvoiceStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InvoiceStatusType fromValue(String v) {
        for (InvoiceStatusType c: InvoiceStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
