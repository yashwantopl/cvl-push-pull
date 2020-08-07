
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EmailType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EmailType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PromoEmail1"/>
 *     &lt;enumeration value="PromoEmail2"/>
 *     &lt;enumeration value="PromoEmail3"/>
 *     &lt;enumeration value="PromoEmail4"/>
 *     &lt;enumeration value="PromoEmail5"/>
 *     &lt;enumeration value="PromoEmail6"/>
 *     &lt;enumeration value="PromoEmail7"/>
 *     &lt;enumeration value="PromoEmail8"/>
 *     &lt;enumeration value="PromoEmail9"/>
 *     &lt;enumeration value="PromoEmail10"/>
 *     &lt;enumeration value="NonSubscriptionProductReceipt"/>
 *     &lt;enumeration value="SubscriptionProductReceipt"/>
 *     &lt;enumeration value="NonSubscriptionAddOnReceipt"/>
 *     &lt;enumeration value="AlertNotification"/>
 *     &lt;enumeration value="AllClearNotification"/>
 *     &lt;enumeration value="CreditMonitoring QuarterlyReportReady"/>
 *     &lt;enumeration value="CreditReportAboutToExpireWarning"/>
 *     &lt;enumeration value="AccountSuspended"/>
 *     &lt;enumeration value="SubscriptionProductCancellation"/>
 *     &lt;enumeration value="CreditMonitoringCancellationDueToFraud"/>
 *     &lt;enumeration value="CreditMonitoringRenewalErrorDueToFraud"/>
 *     &lt;enumeration value="PaymentFailed"/>
 *     &lt;enumeration value="SubscriptionRenewalPaymentFailed"/>
 *     &lt;enumeration value="CustomerFailedIdentitySecurity"/>
 *     &lt;enumeration value="CustomerDidNotCompleteIdentitySecurity"/>
 *     &lt;enumeration value="MiscellaneousOrderError"/>
 *     &lt;enumeration value="AutomatedHelp"/>
 *     &lt;enumeration value="IdentityConfirmedWithCSR"/>
 *     &lt;enumeration value="FraudIndicatorOverrideByCSR"/>
 *     &lt;enumeration value="PasswordResetInitiatedByCSR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EmailType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum EmailType {

    @XmlEnumValue("PromoEmail1")
    PROMO_EMAIL_1("PromoEmail1"),
    @XmlEnumValue("PromoEmail2")
    PROMO_EMAIL_2("PromoEmail2"),
    @XmlEnumValue("PromoEmail3")
    PROMO_EMAIL_3("PromoEmail3"),
    @XmlEnumValue("PromoEmail4")
    PROMO_EMAIL_4("PromoEmail4"),
    @XmlEnumValue("PromoEmail5")
    PROMO_EMAIL_5("PromoEmail5"),
    @XmlEnumValue("PromoEmail6")
    PROMO_EMAIL_6("PromoEmail6"),
    @XmlEnumValue("PromoEmail7")
    PROMO_EMAIL_7("PromoEmail7"),
    @XmlEnumValue("PromoEmail8")
    PROMO_EMAIL_8("PromoEmail8"),
    @XmlEnumValue("PromoEmail9")
    PROMO_EMAIL_9("PromoEmail9"),
    @XmlEnumValue("PromoEmail10")
    PROMO_EMAIL_10("PromoEmail10"),
    @XmlEnumValue("NonSubscriptionProductReceipt")
    NON_SUBSCRIPTION_PRODUCT_RECEIPT("NonSubscriptionProductReceipt"),
    @XmlEnumValue("SubscriptionProductReceipt")
    SUBSCRIPTION_PRODUCT_RECEIPT("SubscriptionProductReceipt"),
    @XmlEnumValue("NonSubscriptionAddOnReceipt")
    NON_SUBSCRIPTION_ADD_ON_RECEIPT("NonSubscriptionAddOnReceipt"),
    @XmlEnumValue("AlertNotification")
    ALERT_NOTIFICATION("AlertNotification"),
    @XmlEnumValue("AllClearNotification")
    ALL_CLEAR_NOTIFICATION("AllClearNotification"),
    @XmlEnumValue("CreditMonitoring QuarterlyReportReady")
    CREDIT_MONITORING_QUARTERLY_REPORT_READY("CreditMonitoring QuarterlyReportReady"),
    @XmlEnumValue("CreditReportAboutToExpireWarning")
    CREDIT_REPORT_ABOUT_TO_EXPIRE_WARNING("CreditReportAboutToExpireWarning"),
    @XmlEnumValue("AccountSuspended")
    ACCOUNT_SUSPENDED("AccountSuspended"),
    @XmlEnumValue("SubscriptionProductCancellation")
    SUBSCRIPTION_PRODUCT_CANCELLATION("SubscriptionProductCancellation"),
    @XmlEnumValue("CreditMonitoringCancellationDueToFraud")
    CREDIT_MONITORING_CANCELLATION_DUE_TO_FRAUD("CreditMonitoringCancellationDueToFraud"),
    @XmlEnumValue("CreditMonitoringRenewalErrorDueToFraud")
    CREDIT_MONITORING_RENEWAL_ERROR_DUE_TO_FRAUD("CreditMonitoringRenewalErrorDueToFraud"),
    @XmlEnumValue("PaymentFailed")
    PAYMENT_FAILED("PaymentFailed"),
    @XmlEnumValue("SubscriptionRenewalPaymentFailed")
    SUBSCRIPTION_RENEWAL_PAYMENT_FAILED("SubscriptionRenewalPaymentFailed"),
    @XmlEnumValue("CustomerFailedIdentitySecurity")
    CUSTOMER_FAILED_IDENTITY_SECURITY("CustomerFailedIdentitySecurity"),
    @XmlEnumValue("CustomerDidNotCompleteIdentitySecurity")
    CUSTOMER_DID_NOT_COMPLETE_IDENTITY_SECURITY("CustomerDidNotCompleteIdentitySecurity"),
    @XmlEnumValue("MiscellaneousOrderError")
    MISCELLANEOUS_ORDER_ERROR("MiscellaneousOrderError"),
    @XmlEnumValue("AutomatedHelp")
    AUTOMATED_HELP("AutomatedHelp"),
    @XmlEnumValue("IdentityConfirmedWithCSR")
    IDENTITY_CONFIRMED_WITH_CSR("IdentityConfirmedWithCSR"),
    @XmlEnumValue("FraudIndicatorOverrideByCSR")
    FRAUD_INDICATOR_OVERRIDE_BY_CSR("FraudIndicatorOverrideByCSR"),
    @XmlEnumValue("PasswordResetInitiatedByCSR")
    PASSWORD_RESET_INITIATED_BY_CSR("PasswordResetInitiatedByCSR");
    private final String value;

    EmailType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EmailType fromValue(String v) {
        for (EmailType c: EmailType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
