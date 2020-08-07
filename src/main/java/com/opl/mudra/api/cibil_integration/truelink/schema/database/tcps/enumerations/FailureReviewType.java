
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FailureReviewType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FailureReviewType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AcctValidationFailure"/>
 *     &lt;enumeration value="AddressMismatch"/>
 *     &lt;enumeration value="AgeRestriction"/>
 *     &lt;enumeration value="AuthorizationFailure"/>
 *     &lt;enumeration value="BillingFailure"/>
 *     &lt;enumeration value="BillingFailureRecycle"/>
 *     &lt;enumeration value="BillingFailureExpiredRecycle"/>
 *     &lt;enumeration value="BillingFailureAccountUpdater"/>
 *     &lt;enumeration value="CoreFailure"/>
 *     &lt;enumeration value="EndOfBreach"/>
 *     &lt;enumeration value="FraudAlert"/>
 *     &lt;enumeration value="FulfillmentFailure"/>
 *     &lt;enumeration value="IdentityVerification"/>
 *     &lt;enumeration value="IncompleteEnrollment"/>
 *     &lt;enumeration value="IncompleteIV"/>
 *     &lt;enumeration value="NoEQFmonitoring"/>
 *     &lt;enumeration value="NoEXPmonitoring"/>
 *     &lt;enumeration value="PartialCustomer"/>
 *     &lt;enumeration value="ReturnedPostalMail"/>
 *     &lt;enumeration value="Unsubscribe"/>
 *     &lt;enumeration value="BillingFailureAccUpdater"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FailureReviewType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum FailureReviewType {

    @XmlEnumValue("AcctValidationFailure")
    ACCT_VALIDATION_FAILURE("AcctValidationFailure"),
    @XmlEnumValue("AddressMismatch")
    ADDRESS_MISMATCH("AddressMismatch"),
    @XmlEnumValue("AgeRestriction")
    AGE_RESTRICTION("AgeRestriction"),
    @XmlEnumValue("AuthorizationFailure")
    AUTHORIZATION_FAILURE("AuthorizationFailure"),
    @XmlEnumValue("BillingFailure")
    BILLING_FAILURE("BillingFailure"),
    @XmlEnumValue("BillingFailureRecycle")
    BILLING_FAILURE_RECYCLE("BillingFailureRecycle"),
    @XmlEnumValue("BillingFailureExpiredRecycle")
    BILLING_FAILURE_EXPIRED_RECYCLE("BillingFailureExpiredRecycle"),
    @XmlEnumValue("BillingFailureAccountUpdater")
    BILLING_FAILURE_ACCOUNT_UPDATER("BillingFailureAccountUpdater"),
    @XmlEnumValue("CoreFailure")
    CORE_FAILURE("CoreFailure"),
    @XmlEnumValue("EndOfBreach")
    END_OF_BREACH("EndOfBreach"),
    @XmlEnumValue("FraudAlert")
    FRAUD_ALERT("FraudAlert"),
    @XmlEnumValue("FulfillmentFailure")
    FULFILLMENT_FAILURE("FulfillmentFailure"),
    @XmlEnumValue("IdentityVerification")
    IDENTITY_VERIFICATION("IdentityVerification"),
    @XmlEnumValue("IncompleteEnrollment")
    INCOMPLETE_ENROLLMENT("IncompleteEnrollment"),
    @XmlEnumValue("IncompleteIV")
    INCOMPLETE_IV("IncompleteIV"),
    @XmlEnumValue("NoEQFmonitoring")
    NO_EQ_FMONITORING("NoEQFmonitoring"),
    @XmlEnumValue("NoEXPmonitoring")
    NO_EX_PMONITORING("NoEXPmonitoring"),
    @XmlEnumValue("PartialCustomer")
    PARTIAL_CUSTOMER("PartialCustomer"),
    @XmlEnumValue("ReturnedPostalMail")
    RETURNED_POSTAL_MAIL("ReturnedPostalMail"),
    @XmlEnumValue("Unsubscribe")
    UNSUBSCRIBE("Unsubscribe"),
    @XmlEnumValue("BillingFailureAccUpdater")
    BILLING_FAILURE_ACC_UPDATER("BillingFailureAccUpdater");
    private final String value;

    FailureReviewType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FailureReviewType fromValue(String v) {
        for (FailureReviewType c: FailureReviewType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
