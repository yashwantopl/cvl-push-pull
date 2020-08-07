
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CsptReasonType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CsptReasonType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AccountSetupFraud"/>
 *     &lt;enumeration value="Affinion"/>
 *     &lt;enumeration value="AlreadyHasSimilarCoverage"/>
 *     &lt;enumeration value="AuthorizationPending"/>
 *     &lt;enumeration value="BillingFraud"/>
 *     &lt;enumeration value="BillingIssues"/>
 *     &lt;enumeration value="BureauNotResponding"/>
 *     &lt;enumeration value="CPP"/>
 *     &lt;enumeration value="CPPCharterOne"/>
 *     &lt;enumeration value="CPPCitizensBank"/>
 *     &lt;enumeration value="CanceledBillingAccount"/>
 *     &lt;enumeration value="ChangetoOffline"/>
 *     &lt;enumeration value="ChangetoOnline"/>
 *     &lt;enumeration value="ChangingPaymentMethod"/>
 *     &lt;enumeration value="ChargeInquiry"/>
 *     &lt;enumeration value="Chargeback"/>
 *     &lt;enumeration value="ChaseServices"/>
 *     &lt;enumeration value="CompletedSaveWithActivationCode"/>
 *     &lt;enumeration value="CompletedSavewithAlertsBenefit"/>
 *     &lt;enumeration value="CompletedSavewithBillingScript"/>
 *     &lt;enumeration value="CompletedSavewithFreeTrialBenefit"/>
 *     &lt;enumeration value="CompletedSavewithIDTheftExpReimbBenefit"/>
 *     &lt;enumeration value="CompletedSavewithIDTheftinsuranceBenefit"/>
 *     &lt;enumeration value="CompletedSavewithPricingScript"/>
 *     &lt;enumeration value="CompletedSavewithUnlimitedReportBenefit"/>
 *     &lt;enumeration value="CompletedSavewithUnlimitedScoresBenefit"/>
 *     &lt;enumeration value="ContactedByAuthorities"/>
 *     &lt;enumeration value="CouldNotBeProcessedThroughTPE"/>
 *     &lt;enumeration value="CouponRedemption"/>
 *     &lt;enumeration value="Credentity"/>
 *     &lt;enumeration value="CreditAlerts"/>
 *     &lt;enumeration value="CreditCardDeclined"/>
 *     &lt;enumeration value="CreditReportDispute"/>
 *     &lt;enumeration value="CreditReports"/>
 *     &lt;enumeration value="CreditScores"/>
 *     &lt;enumeration value="CreditView"/>
 *     &lt;enumeration value="CustomerComplaint"/>
 *     &lt;enumeration value="CustomerProcessedCancel"/>
 *     &lt;enumeration value="CustomerSplitFile"/>
 *     &lt;enumeration value="DoesNotRecallEnrolling"/>
 *     &lt;enumeration value="DriversReport"/>
 *     &lt;enumeration value="EAGeico"/>
 *     &lt;enumeration value="Enrollment"/>
 *     &lt;enumeration value="EuropAssistance"/>
 *     &lt;enumeration value="ExceptionHandling"/>
 *     &lt;enumeration value="FailedSafetyCheck"/>
 *     &lt;enumeration value="FinancialIssues"/>
 *     &lt;enumeration value="FraudDispute"/>
 *     &lt;enumeration value="FraudVictimsAssistance"/>
 *     &lt;enumeration value="FreeProductOrder"/>
 *     &lt;enumeration value="HandsetCompatibility"/>
 *     &lt;enumeration value="HowToRedeemActivationCode"/>
 *     &lt;enumeration value="HowToSelectSubscription"/>
 *     &lt;enumeration value="HowtoChangeBillingInformation"/>
 *     &lt;enumeration value="HowtoChangePreferences"/>
 *     &lt;enumeration value="HowtoChangeUserInformation"/>
 *     &lt;enumeration value="HowtoDisputeOnline"/>
 *     &lt;enumeration value="HowtoLogin"/>
 *     &lt;enumeration value="HowtoPrintReport"/>
 *     &lt;enumeration value="HowtoRefreshCreditReport"/>
 *     &lt;enumeration value="HowtoUseTrackingCode"/>
 *     &lt;enumeration value="HowtoViewLearningCenter"/>
 *     &lt;enumeration value="HowtoViewProducts"/>
 *     &lt;enumeration value="IDConfirmed"/>
 *     &lt;enumeration value="IDNotConfirmed"/>
 *     &lt;enumeration value="IDReconfirmed"/>
 *     &lt;enumeration value="IDWatchdog"/>
 *     &lt;enumeration value="IdentityTheftExpenseReimbursement"/>
 *     &lt;enumeration value="IneligibleForIDTheft"/>
 *     &lt;enumeration value="Irate"/>
 *     &lt;enumeration value="Login"/>
 *     &lt;enumeration value="MIBReport"/>
 *     &lt;enumeration value="NeedsPassword"/>
 *     &lt;enumeration value="NeedsUserName"/>
 *     &lt;enumeration value="NeedsUserNamePassword"/>
 *     &lt;enumeration value="NeverAuthorizedEnrollment"/>
 *     &lt;enumeration value="NeverReceivedMembershipKit"/>
 *     &lt;enumeration value="NoHit"/>
 *     &lt;enumeration value="NoLongerWantsProduct"/>
 *     &lt;enumeration value="NonFraudDispute"/>
 *     &lt;enumeration value="NotConcernedWithIDTheft"/>
 *     &lt;enumeration value="OneTech"/>
 *     &lt;enumeration value="OnlyWantedFreeTrial"/>
 *     &lt;enumeration value="OnlyWantedIncentive"/>
 *     &lt;enumeration value="OnlyWantedInformation"/>
 *     &lt;enumeration value="OnlyWantedReportAndScore"/>
 *     &lt;enumeration value="PINproblem"/>
 *     &lt;enumeration value="PartnerActivationCodeNotWorking"/>
 *     &lt;enumeration value="PartnerBillingIssue"/>
 *     &lt;enumeration value="PartnerCancellation"/>
 *     &lt;enumeration value="PartnerReferral"/>
 *     &lt;enumeration value="PartnerRequest"/>
 *     &lt;enumeration value="PositiveExperience"/>
 *     &lt;enumeration value="PremiumProductOrder"/>
 *     &lt;enumeration value="PreviousCreditIssued"/>
 *     &lt;enumeration value="ProductDetailsUnclear"/>
 *     &lt;enumeration value="ProductExpiration"/>
 *     &lt;enumeration value="ProductNotReceived"/>
 *     &lt;enumeration value="ProductNotValuable"/>
 *     &lt;enumeration value="ProductSuggestions"/>
 *     &lt;enumeration value="RefreshReport"/>
 *     &lt;enumeration value="ReprintResend"/>
 *     &lt;enumeration value="ReturnedMail"/>
 *     &lt;enumeration value="SSAReport"/>
 *     &lt;enumeration value="SameSSNExistsinsystem"/>
 *     &lt;enumeration value="SystemEmails"/>
 *     &lt;enumeration value="TCMobile"/>
 *     &lt;enumeration value="TCMobileApplication"/>
 *     &lt;enumeration value="TPERefundFailed"/>
 *     &lt;enumeration value="TUAutomationDispute"/>
 *     &lt;enumeration value="TUAutomationServices"/>
 *     &lt;enumeration value="TechnicalIssues"/>
 *     &lt;enumeration value="TechnicalProblems"/>
 *     &lt;enumeration value="TelemarketerTooPushy"/>
 *     &lt;enumeration value="ThinFile"/>
 *     &lt;enumeration value="ThoughtAlreadyCanceled"/>
 *     &lt;enumeration value="TooExpensive"/>
 *     &lt;enumeration value="TransUnionDispute"/>
 *     &lt;enumeration value="TransUnionServices"/>
 *     &lt;enumeration value="USBank"/>
 *     &lt;enumeration value="Bluestem"/>
 *     &lt;enumeration value="Unabletoaddcustomertocreditmonitoring"/>
 *     &lt;enumeration value="Underage"/>
 *     &lt;enumeration value="UndocumentedIssue"/>
 *     &lt;enumeration value="UpdatePersonalInformation"/>
 *     &lt;enumeration value="UpgradeProduct"/>
 *     &lt;enumeration value="UsedAlternativeScriptDocumentedinComments"/>
 *     &lt;enumeration value="VendorDown"/>
 *     &lt;enumeration value="Vertrue"/>
 *     &lt;enumeration value="ViewProductInformation"/>
 *     &lt;enumeration value="WelcomeKit"/>
 *     &lt;enumeration value="WillNotProvideSSN"/>
 *     &lt;enumeration value="WrittenCancellationRequest"/>
 *     &lt;enumeration value="UpdatedByAccountUpdater"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CsptReasonType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CsptReasonType {

    @XmlEnumValue("AccountSetupFraud")
    ACCOUNT_SETUP_FRAUD("AccountSetupFraud"),
    @XmlEnumValue("Affinion")
    AFFINION("Affinion"),
    @XmlEnumValue("AlreadyHasSimilarCoverage")
    ALREADY_HAS_SIMILAR_COVERAGE("AlreadyHasSimilarCoverage"),
    @XmlEnumValue("AuthorizationPending")
    AUTHORIZATION_PENDING("AuthorizationPending"),
    @XmlEnumValue("BillingFraud")
    BILLING_FRAUD("BillingFraud"),
    @XmlEnumValue("BillingIssues")
    BILLING_ISSUES("BillingIssues"),
    @XmlEnumValue("BureauNotResponding")
    BUREAU_NOT_RESPONDING("BureauNotResponding"),
    CPP("CPP"),
    @XmlEnumValue("CPPCharterOne")
    CPP_CHARTER_ONE("CPPCharterOne"),
    @XmlEnumValue("CPPCitizensBank")
    CPP_CITIZENS_BANK("CPPCitizensBank"),
    @XmlEnumValue("CanceledBillingAccount")
    CANCELED_BILLING_ACCOUNT("CanceledBillingAccount"),
    @XmlEnumValue("ChangetoOffline")
    CHANGETO_OFFLINE("ChangetoOffline"),
    @XmlEnumValue("ChangetoOnline")
    CHANGETO_ONLINE("ChangetoOnline"),
    @XmlEnumValue("ChangingPaymentMethod")
    CHANGING_PAYMENT_METHOD("ChangingPaymentMethod"),
    @XmlEnumValue("ChargeInquiry")
    CHARGE_INQUIRY("ChargeInquiry"),
    @XmlEnumValue("Chargeback")
    CHARGEBACK("Chargeback"),
    @XmlEnumValue("ChaseServices")
    CHASE_SERVICES("ChaseServices"),
    @XmlEnumValue("CompletedSaveWithActivationCode")
    COMPLETED_SAVE_WITH_ACTIVATION_CODE("CompletedSaveWithActivationCode"),
    @XmlEnumValue("CompletedSavewithAlertsBenefit")
    COMPLETED_SAVEWITH_ALERTS_BENEFIT("CompletedSavewithAlertsBenefit"),
    @XmlEnumValue("CompletedSavewithBillingScript")
    COMPLETED_SAVEWITH_BILLING_SCRIPT("CompletedSavewithBillingScript"),
    @XmlEnumValue("CompletedSavewithFreeTrialBenefit")
    COMPLETED_SAVEWITH_FREE_TRIAL_BENEFIT("CompletedSavewithFreeTrialBenefit"),
    @XmlEnumValue("CompletedSavewithIDTheftExpReimbBenefit")
    COMPLETED_SAVEWITH_ID_THEFT_EXP_REIMB_BENEFIT("CompletedSavewithIDTheftExpReimbBenefit"),
    @XmlEnumValue("CompletedSavewithIDTheftinsuranceBenefit")
    COMPLETED_SAVEWITH_ID_THEFTINSURANCE_BENEFIT("CompletedSavewithIDTheftinsuranceBenefit"),
    @XmlEnumValue("CompletedSavewithPricingScript")
    COMPLETED_SAVEWITH_PRICING_SCRIPT("CompletedSavewithPricingScript"),
    @XmlEnumValue("CompletedSavewithUnlimitedReportBenefit")
    COMPLETED_SAVEWITH_UNLIMITED_REPORT_BENEFIT("CompletedSavewithUnlimitedReportBenefit"),
    @XmlEnumValue("CompletedSavewithUnlimitedScoresBenefit")
    COMPLETED_SAVEWITH_UNLIMITED_SCORES_BENEFIT("CompletedSavewithUnlimitedScoresBenefit"),
    @XmlEnumValue("ContactedByAuthorities")
    CONTACTED_BY_AUTHORITIES("ContactedByAuthorities"),
    @XmlEnumValue("CouldNotBeProcessedThroughTPE")
    COULD_NOT_BE_PROCESSED_THROUGH_TPE("CouldNotBeProcessedThroughTPE"),
    @XmlEnumValue("CouponRedemption")
    COUPON_REDEMPTION("CouponRedemption"),
    @XmlEnumValue("Credentity")
    CREDENTITY("Credentity"),
    @XmlEnumValue("CreditAlerts")
    CREDIT_ALERTS("CreditAlerts"),
    @XmlEnumValue("CreditCardDeclined")
    CREDIT_CARD_DECLINED("CreditCardDeclined"),
    @XmlEnumValue("CreditReportDispute")
    CREDIT_REPORT_DISPUTE("CreditReportDispute"),
    @XmlEnumValue("CreditReports")
    CREDIT_REPORTS("CreditReports"),
    @XmlEnumValue("CreditScores")
    CREDIT_SCORES("CreditScores"),
    @XmlEnumValue("CreditView")
    CREDIT_VIEW("CreditView"),
    @XmlEnumValue("CustomerComplaint")
    CUSTOMER_COMPLAINT("CustomerComplaint"),
    @XmlEnumValue("CustomerProcessedCancel")
    CUSTOMER_PROCESSED_CANCEL("CustomerProcessedCancel"),
    @XmlEnumValue("CustomerSplitFile")
    CUSTOMER_SPLIT_FILE("CustomerSplitFile"),
    @XmlEnumValue("DoesNotRecallEnrolling")
    DOES_NOT_RECALL_ENROLLING("DoesNotRecallEnrolling"),
    @XmlEnumValue("DriversReport")
    DRIVERS_REPORT("DriversReport"),
    @XmlEnumValue("EAGeico")
    EA_GEICO("EAGeico"),
    @XmlEnumValue("Enrollment")
    ENROLLMENT("Enrollment"),
    @XmlEnumValue("EuropAssistance")
    EUROP_ASSISTANCE("EuropAssistance"),
    @XmlEnumValue("ExceptionHandling")
    EXCEPTION_HANDLING("ExceptionHandling"),
    @XmlEnumValue("FailedSafetyCheck")
    FAILED_SAFETY_CHECK("FailedSafetyCheck"),
    @XmlEnumValue("FinancialIssues")
    FINANCIAL_ISSUES("FinancialIssues"),
    @XmlEnumValue("FraudDispute")
    FRAUD_DISPUTE("FraudDispute"),
    @XmlEnumValue("FraudVictimsAssistance")
    FRAUD_VICTIMS_ASSISTANCE("FraudVictimsAssistance"),
    @XmlEnumValue("FreeProductOrder")
    FREE_PRODUCT_ORDER("FreeProductOrder"),
    @XmlEnumValue("HandsetCompatibility")
    HANDSET_COMPATIBILITY("HandsetCompatibility"),
    @XmlEnumValue("HowToRedeemActivationCode")
    HOW_TO_REDEEM_ACTIVATION_CODE("HowToRedeemActivationCode"),
    @XmlEnumValue("HowToSelectSubscription")
    HOW_TO_SELECT_SUBSCRIPTION("HowToSelectSubscription"),
    @XmlEnumValue("HowtoChangeBillingInformation")
    HOWTO_CHANGE_BILLING_INFORMATION("HowtoChangeBillingInformation"),
    @XmlEnumValue("HowtoChangePreferences")
    HOWTO_CHANGE_PREFERENCES("HowtoChangePreferences"),
    @XmlEnumValue("HowtoChangeUserInformation")
    HOWTO_CHANGE_USER_INFORMATION("HowtoChangeUserInformation"),
    @XmlEnumValue("HowtoDisputeOnline")
    HOWTO_DISPUTE_ONLINE("HowtoDisputeOnline"),
    @XmlEnumValue("HowtoLogin")
    HOWTO_LOGIN("HowtoLogin"),
    @XmlEnumValue("HowtoPrintReport")
    HOWTO_PRINT_REPORT("HowtoPrintReport"),
    @XmlEnumValue("HowtoRefreshCreditReport")
    HOWTO_REFRESH_CREDIT_REPORT("HowtoRefreshCreditReport"),
    @XmlEnumValue("HowtoUseTrackingCode")
    HOWTO_USE_TRACKING_CODE("HowtoUseTrackingCode"),
    @XmlEnumValue("HowtoViewLearningCenter")
    HOWTO_VIEW_LEARNING_CENTER("HowtoViewLearningCenter"),
    @XmlEnumValue("HowtoViewProducts")
    HOWTO_VIEW_PRODUCTS("HowtoViewProducts"),
    @XmlEnumValue("IDConfirmed")
    ID_CONFIRMED("IDConfirmed"),
    @XmlEnumValue("IDNotConfirmed")
    ID_NOT_CONFIRMED("IDNotConfirmed"),
    @XmlEnumValue("IDReconfirmed")
    ID_RECONFIRMED("IDReconfirmed"),
    @XmlEnumValue("IDWatchdog")
    ID_WATCHDOG("IDWatchdog"),
    @XmlEnumValue("IdentityTheftExpenseReimbursement")
    IDENTITY_THEFT_EXPENSE_REIMBURSEMENT("IdentityTheftExpenseReimbursement"),
    @XmlEnumValue("IneligibleForIDTheft")
    INELIGIBLE_FOR_ID_THEFT("IneligibleForIDTheft"),
    @XmlEnumValue("Irate")
    IRATE("Irate"),
    @XmlEnumValue("Login")
    LOGIN("Login"),
    @XmlEnumValue("MIBReport")
    MIB_REPORT("MIBReport"),
    @XmlEnumValue("NeedsPassword")
    NEEDS_PASSWORD("NeedsPassword"),
    @XmlEnumValue("NeedsUserName")
    NEEDS_USER_NAME("NeedsUserName"),
    @XmlEnumValue("NeedsUserNamePassword")
    NEEDS_USER_NAME_PASSWORD("NeedsUserNamePassword"),
    @XmlEnumValue("NeverAuthorizedEnrollment")
    NEVER_AUTHORIZED_ENROLLMENT("NeverAuthorizedEnrollment"),
    @XmlEnumValue("NeverReceivedMembershipKit")
    NEVER_RECEIVED_MEMBERSHIP_KIT("NeverReceivedMembershipKit"),
    @XmlEnumValue("NoHit")
    NO_HIT("NoHit"),
    @XmlEnumValue("NoLongerWantsProduct")
    NO_LONGER_WANTS_PRODUCT("NoLongerWantsProduct"),
    @XmlEnumValue("NonFraudDispute")
    NON_FRAUD_DISPUTE("NonFraudDispute"),
    @XmlEnumValue("NotConcernedWithIDTheft")
    NOT_CONCERNED_WITH_ID_THEFT("NotConcernedWithIDTheft"),
    @XmlEnumValue("OneTech")
    ONE_TECH("OneTech"),
    @XmlEnumValue("OnlyWantedFreeTrial")
    ONLY_WANTED_FREE_TRIAL("OnlyWantedFreeTrial"),
    @XmlEnumValue("OnlyWantedIncentive")
    ONLY_WANTED_INCENTIVE("OnlyWantedIncentive"),
    @XmlEnumValue("OnlyWantedInformation")
    ONLY_WANTED_INFORMATION("OnlyWantedInformation"),
    @XmlEnumValue("OnlyWantedReportAndScore")
    ONLY_WANTED_REPORT_AND_SCORE("OnlyWantedReportAndScore"),
    @XmlEnumValue("PINproblem")
    PI_NPROBLEM("PINproblem"),
    @XmlEnumValue("PartnerActivationCodeNotWorking")
    PARTNER_ACTIVATION_CODE_NOT_WORKING("PartnerActivationCodeNotWorking"),
    @XmlEnumValue("PartnerBillingIssue")
    PARTNER_BILLING_ISSUE("PartnerBillingIssue"),
    @XmlEnumValue("PartnerCancellation")
    PARTNER_CANCELLATION("PartnerCancellation"),
    @XmlEnumValue("PartnerReferral")
    PARTNER_REFERRAL("PartnerReferral"),
    @XmlEnumValue("PartnerRequest")
    PARTNER_REQUEST("PartnerRequest"),
    @XmlEnumValue("PositiveExperience")
    POSITIVE_EXPERIENCE("PositiveExperience"),
    @XmlEnumValue("PremiumProductOrder")
    PREMIUM_PRODUCT_ORDER("PremiumProductOrder"),
    @XmlEnumValue("PreviousCreditIssued")
    PREVIOUS_CREDIT_ISSUED("PreviousCreditIssued"),
    @XmlEnumValue("ProductDetailsUnclear")
    PRODUCT_DETAILS_UNCLEAR("ProductDetailsUnclear"),
    @XmlEnumValue("ProductExpiration")
    PRODUCT_EXPIRATION("ProductExpiration"),
    @XmlEnumValue("ProductNotReceived")
    PRODUCT_NOT_RECEIVED("ProductNotReceived"),
    @XmlEnumValue("ProductNotValuable")
    PRODUCT_NOT_VALUABLE("ProductNotValuable"),
    @XmlEnumValue("ProductSuggestions")
    PRODUCT_SUGGESTIONS("ProductSuggestions"),
    @XmlEnumValue("RefreshReport")
    REFRESH_REPORT("RefreshReport"),
    @XmlEnumValue("ReprintResend")
    REPRINT_RESEND("ReprintResend"),
    @XmlEnumValue("ReturnedMail")
    RETURNED_MAIL("ReturnedMail"),
    @XmlEnumValue("SSAReport")
    SSA_REPORT("SSAReport"),
    @XmlEnumValue("SameSSNExistsinsystem")
    SAME_SSN_EXISTSINSYSTEM("SameSSNExistsinsystem"),
    @XmlEnumValue("SystemEmails")
    SYSTEM_EMAILS("SystemEmails"),
    @XmlEnumValue("TCMobile")
    TC_MOBILE("TCMobile"),
    @XmlEnumValue("TCMobileApplication")
    TC_MOBILE_APPLICATION("TCMobileApplication"),
    @XmlEnumValue("TPERefundFailed")
    TPE_REFUND_FAILED("TPERefundFailed"),
    @XmlEnumValue("TUAutomationDispute")
    TU_AUTOMATION_DISPUTE("TUAutomationDispute"),
    @XmlEnumValue("TUAutomationServices")
    TU_AUTOMATION_SERVICES("TUAutomationServices"),
    @XmlEnumValue("TechnicalIssues")
    TECHNICAL_ISSUES("TechnicalIssues"),
    @XmlEnumValue("TechnicalProblems")
    TECHNICAL_PROBLEMS("TechnicalProblems"),
    @XmlEnumValue("TelemarketerTooPushy")
    TELEMARKETER_TOO_PUSHY("TelemarketerTooPushy"),
    @XmlEnumValue("ThinFile")
    THIN_FILE("ThinFile"),
    @XmlEnumValue("ThoughtAlreadyCanceled")
    THOUGHT_ALREADY_CANCELED("ThoughtAlreadyCanceled"),
    @XmlEnumValue("TooExpensive")
    TOO_EXPENSIVE("TooExpensive"),
    @XmlEnumValue("TransUnionDispute")
    TRANS_UNION_DISPUTE("TransUnionDispute"),
    @XmlEnumValue("TransUnionServices")
    TRANS_UNION_SERVICES("TransUnionServices"),
    @XmlEnumValue("USBank")
    US_BANK("USBank"),
    @XmlEnumValue("Bluestem")
    BLUESTEM("Bluestem"),
    @XmlEnumValue("Unabletoaddcustomertocreditmonitoring")
    UNABLETOADDCUSTOMERTOCREDITMONITORING("Unabletoaddcustomertocreditmonitoring"),
    @XmlEnumValue("Underage")
    UNDERAGE("Underage"),
    @XmlEnumValue("UndocumentedIssue")
    UNDOCUMENTED_ISSUE("UndocumentedIssue"),
    @XmlEnumValue("UpdatePersonalInformation")
    UPDATE_PERSONAL_INFORMATION("UpdatePersonalInformation"),
    @XmlEnumValue("UpgradeProduct")
    UPGRADE_PRODUCT("UpgradeProduct"),
    @XmlEnumValue("UsedAlternativeScriptDocumentedinComments")
    USED_ALTERNATIVE_SCRIPT_DOCUMENTEDIN_COMMENTS("UsedAlternativeScriptDocumentedinComments"),
    @XmlEnumValue("VendorDown")
    VENDOR_DOWN("VendorDown"),
    @XmlEnumValue("Vertrue")
    VERTRUE("Vertrue"),
    @XmlEnumValue("ViewProductInformation")
    VIEW_PRODUCT_INFORMATION("ViewProductInformation"),
    @XmlEnumValue("WelcomeKit")
    WELCOME_KIT("WelcomeKit"),
    @XmlEnumValue("WillNotProvideSSN")
    WILL_NOT_PROVIDE_SSN("WillNotProvideSSN"),
    @XmlEnumValue("WrittenCancellationRequest")
    WRITTEN_CANCELLATION_REQUEST("WrittenCancellationRequest"),
    @XmlEnumValue("UpdatedByAccountUpdater")
    UPDATED_BY_ACCOUNT_UPDATER("UpdatedByAccountUpdater");
    private final String value;

    CsptReasonType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CsptReasonType fromValue(String v) {
        for (CsptReasonType c: CsptReasonType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
