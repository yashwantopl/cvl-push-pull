
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillingStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BillingStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandoned"/>
 *     &lt;enumeration value="AcctValidationFailed"/>
 *     &lt;enumeration value="AuthorizationFailed"/>
 *     &lt;enumeration value="BillingInfoRequired"/>
 *     &lt;enumeration value="BreachAccount"/>
 *     &lt;enumeration value="Canceled"/>
 *     &lt;enumeration value="Complete"/>
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="NoBilling"/>
 *     &lt;enumeration value="NotInitiated"/>
 *     &lt;enumeration value="Paid"/>
 *     &lt;enumeration value="PaymentFailed"/>
 *     &lt;enumeration value="PaymentPending"/>
 *     &lt;enumeration value="Subscribed"/>
 *     &lt;enumeration value="Unsubscribed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BillingStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum BillingStatusType {

    @XmlEnumValue("Abandoned")
    ABANDONED("Abandoned"),
    @XmlEnumValue("AcctValidationFailed")
    ACCT_VALIDATION_FAILED("AcctValidationFailed"),
    @XmlEnumValue("AuthorizationFailed")
    AUTHORIZATION_FAILED("AuthorizationFailed"),
    @XmlEnumValue("BillingInfoRequired")
    BILLING_INFO_REQUIRED("BillingInfoRequired"),
    @XmlEnumValue("BreachAccount")
    BREACH_ACCOUNT("BreachAccount"),
    @XmlEnumValue("Canceled")
    CANCELED("Canceled"),
    @XmlEnumValue("Complete")
    COMPLETE("Complete"),
    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("NoBilling")
    NO_BILLING("NoBilling"),
    @XmlEnumValue("NotInitiated")
    NOT_INITIATED("NotInitiated"),
    @XmlEnumValue("Paid")
    PAID("Paid"),
    @XmlEnumValue("PaymentFailed")
    PAYMENT_FAILED("PaymentFailed"),
    @XmlEnumValue("PaymentPending")
    PAYMENT_PENDING("PaymentPending"),
    @XmlEnumValue("Subscribed")
    SUBSCRIBED("Subscribed"),
    @XmlEnumValue("Unsubscribed")
    UNSUBSCRIBED("Unsubscribed");
    private final String value;

    BillingStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BillingStatusType fromValue(String v) {
        for (BillingStatusType c: BillingStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
