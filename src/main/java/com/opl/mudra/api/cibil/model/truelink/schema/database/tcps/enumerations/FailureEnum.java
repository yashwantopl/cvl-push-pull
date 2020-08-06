
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FailureEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FailureEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CUSTOMER_NOT_FOUND"/>
 *     &lt;enumeration value="ORDER_NOT_FOUND"/>
 *     &lt;enumeration value="INVOICE_NOT_FOUND"/>
 *     &lt;enumeration value="FULFILLMENT_SCHEDULE_NOT_FOUND"/>
 *     &lt;enumeration value="FULFILLMENT_NOT_FOUND"/>
 *     &lt;enumeration value="PARTNER_NOT_FOUND"/>
 *     &lt;enumeration value="CUSTOMER_LOCKED"/>
 *     &lt;enumeration value="NO_HIT"/>
 *     &lt;enumeration value="ADDRESS_MISMATCH"/>
 *     &lt;enumeration value="FRAUD"/>
 *     &lt;enumeration value="PRINTING_FAILED"/>
 *     &lt;enumeration value="WATCH_FAILED"/>
 *     &lt;enumeration value="INVALID_CLIENT_IDENTITY"/>
 *     &lt;enumeration value="INSUFFICIENT_REQUEST_INFO"/>
 *     &lt;enumeration value="MULTIPLE_HITS"/>
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="FAILURE"/>
 *     &lt;enumeration value="LEAD_NOT_FOUND"/>
 *     &lt;enumeration value="DUPLICATE_SESSION_IDENTIFIER"/>
 *     &lt;enumeration value="UNKNOWN_CLIENT_SESSION_IDENTIFIER"/>
 *     &lt;enumeration value="UNKNOWN_SERVER_SESSION_IDENTIFIER"/>
 *     &lt;enumeration value="UNKNOWN_SITE_SESSION_ENTRY_ID"/>
 *     &lt;enumeration value="UNKNOWN_ORDER_ID"/>
 *     &lt;enumeration value="INCOMPLETE_REPLY_CARD"/>
 *     &lt;enumeration value="SSN_EXISTS"/>
 *     &lt;enumeration value="NO_ACTIVEFULFILLMENT"/>
 *     &lt;enumeration value="PENDING_FAILURE"/>
 *     &lt;enumeration value="FATAL"/>
 *     &lt;enumeration value="THIN_FILE"/>
 *     &lt;enumeration value="DUPLICATE_USER_NAME"/>
 *     &lt;enumeration value="INVALID_OFFER_ID"/>
 *     &lt;enumeration value="BLACKLISTED_CUSTOMER"/>
 *     &lt;enumeration value="NO_MONITORING"/>
 *     &lt;enumeration value="ENROLLMENT_TERMINATED"/>
 *     &lt;enumeration value="TOO_MANY_PAYMENT_AUTH_ATTEMPTS"/>
 *     &lt;enumeration value="TOO_MANY_LOGIN_ATTEMPT"/>
 *     &lt;enumeration value="TOO_MANY_PARTIAL_CUSTOMER_MATCH_FOUND"/>
 *     &lt;enumeration value="EXP_EQF_NO_HIT"/>
 *     &lt;enumeration value="ALREADY_UPGRADED"/>
 *     &lt;enumeration value="DUPLICATE_REFUND_INVOICE"/>
 *     &lt;enumeration value="FRAUD_ON_SUBSEQUENT_PULL"/>
 *     &lt;enumeration value="CORE_FAILURE_OFFLINE"/>
 *     &lt;enumeration value="CORE_FAILURE_ONLINE"/>
 *     &lt;enumeration value="DECEASED"/>
 *     &lt;enumeration value="AUTH_PASSED_WITH_EXPIRED_ASSET"/>
 *     &lt;enumeration value="ASSET_EXPIRED"/>
 *     &lt;enumeration value="ASSET_NOT_FOUND"/>
 *     &lt;enumeration value="CREDIT_XPERT_FAILURE"/>
 *     &lt;enumeration value="CUSTOMER_ONLINE"/>
 *     &lt;enumeration value="NO_ACTIVE_ENROLLMENT"/>
 *     &lt;enumeration value="PENDING_ENROLLMENT"/>
 *     &lt;enumeration value="EXCEED_ATTEMPTS_ALLOWED"/>
 *     &lt;enumeration value="SSN_NAME_MISMATCH"/>
 *     &lt;enumeration value="ACTIVE_ENROLLMENT"/>
 *     &lt;enumeration value="PARTNER_MEMBERSHIP_CANCELED"/>
 *     &lt;enumeration value="SSN_EXISITS_ACTIVE_OFFLINE"/>
 *     &lt;enumeration value="SSN_EXISITS_ACTIVE_ONLINE"/>
 *     &lt;enumeration value="SSN_EXISITS_PENDING_OFFLINE"/>
 *     &lt;enumeration value="SSN_EXISITS_PENDING_ONLINE"/>
 *     &lt;enumeration value="MULTIPLE_CUST_FOUND"/>
 *     &lt;enumeration value="PENDING_ONLINE_CUSTOMER"/>
 *     &lt;enumeration value="SUBSEQUENT_ORDER_NOT_ALLOWED"/>
 *     &lt;enumeration value="ALREADY_REQUESTED"/>
 *     &lt;enumeration value="NAME_ZIP_EXISTS"/>
 *     &lt;enumeration value="PARTNER_CUST_CODE_NOT_FOUND"/>
 *     &lt;enumeration value="PARTNER_CUST_CODE_EMPTY"/>
 *     &lt;enumeration value="LEGAL_COPY_NOT_FOUND"/>
 *     &lt;enumeration value="INVALID_AUTH_DATA"/>
 *     &lt;enumeration value="SUBSCRIBER_NOT_FOUND"/>
 *     &lt;enumeration value="INVALID_APPLICANT_CHALLENGE"/>
 *     &lt;enumeration value="BUSINESS_NOT_FOUND"/>
 *     &lt;enumeration value="APPLICANT_NOT_FOUND"/>
 *     &lt;enumeration value="INVALID_TOKEN"/>
 *     &lt;enumeration value="USED_TOKEN"/>
 *     &lt;enumeration value="EXPIRED_TOKEN"/>
 *     &lt;enumeration value="INVALID_CREDIT_REPORT"/>
 *     &lt;enumeration value="SAFETY_CHECK_FAILED"/>
 *     &lt;enumeration value="CHALLENGE_NOT_INPROGRESS"/>
 *     &lt;enumeration value="NO_QUESTIONS_REMAINING"/>
 *     &lt;enumeration value="SSN_NOT_FOUND"/>
 *     &lt;enumeration value="INVALID_ANSWER_CHOICE"/>
 *     &lt;enumeration value="AGE_RESTRICTION"/>
 *     &lt;enumeration value="NAME_MISMATCH"/>
 *     &lt;enumeration value="SSN_MISMATCH"/>
 *     &lt;enumeration value="INVALID_PHONE_CHALLENGE"/>
 *     &lt;enumeration value="CALL_FAILED"/>
 *     &lt;enumeration value="PA_SECURITY_CODE_FAILURE"/>
 *     &lt;enumeration value="WRONG_NUMBER_SELECTED"/>
 *     &lt;enumeration value="NO_CR_PHONE_NUMBER"/>
 *     &lt;enumeration value="WRONG_CODE_PREFIX"/>
 *     &lt;enumeration value="AUTHENTICATION_REQUIRED"/>
 *     &lt;enumeration value="DUPLICATE_PARTNER_CUST_CODE"/>
 *     &lt;enumeration value="MAX_PIN_SENT_COUNT"/>
 *     &lt;enumeration value="PIN_NOT_FOUND"/>
 *     &lt;enumeration value="MAX_PIN_COUNT"/>
 *     &lt;enumeration value="INVALID_PIN"/>
 *     &lt;enumeration value="SITE_NOT_FOUND"/>
 *     &lt;enumeration value="AVC_NOT_FOUND"/>
 *     &lt;enumeration value="AVC_NO_MORE_USES"/>
 *     &lt;enumeration value="AVC_EXPIRED"/>
 *     &lt;enumeration value="AVC_NOT_ACTIVE"/>
 *     &lt;enumeration value="REQUIRES_PARTNER_CUST_CODE"/>
 *     &lt;enumeration value="CREDIT_LOCK_SERVICE_UNAVAILABLE"/>
 *     &lt;enumeration value="CREDIT_FREEZE_ON_FILE"/>
 *     &lt;enumeration value="ALREADY_LOCKED"/>
 *     &lt;enumeration value="ALREADY_UNLOCKED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FailureEnum", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum FailureEnum {

    CUSTOMER_NOT_FOUND,
    ORDER_NOT_FOUND,
    INVOICE_NOT_FOUND,
    FULFILLMENT_SCHEDULE_NOT_FOUND,
    FULFILLMENT_NOT_FOUND,
    PARTNER_NOT_FOUND,
    CUSTOMER_LOCKED,
    NO_HIT,
    ADDRESS_MISMATCH,
    FRAUD,
    PRINTING_FAILED,
    WATCH_FAILED,
    INVALID_CLIENT_IDENTITY,
    INSUFFICIENT_REQUEST_INFO,
    MULTIPLE_HITS,
    SUCCESS,
    FAILURE,
    LEAD_NOT_FOUND,
    DUPLICATE_SESSION_IDENTIFIER,
    UNKNOWN_CLIENT_SESSION_IDENTIFIER,
    UNKNOWN_SERVER_SESSION_IDENTIFIER,
    UNKNOWN_SITE_SESSION_ENTRY_ID,
    UNKNOWN_ORDER_ID,
    INCOMPLETE_REPLY_CARD,
    SSN_EXISTS,
    NO_ACTIVEFULFILLMENT,
    PENDING_FAILURE,
    FATAL,
    THIN_FILE,
    DUPLICATE_USER_NAME,
    INVALID_OFFER_ID,
    BLACKLISTED_CUSTOMER,
    NO_MONITORING,
    ENROLLMENT_TERMINATED,
    TOO_MANY_PAYMENT_AUTH_ATTEMPTS,
    TOO_MANY_LOGIN_ATTEMPT,
    TOO_MANY_PARTIAL_CUSTOMER_MATCH_FOUND,
    EXP_EQF_NO_HIT,
    ALREADY_UPGRADED,
    DUPLICATE_REFUND_INVOICE,
    FRAUD_ON_SUBSEQUENT_PULL,
    CORE_FAILURE_OFFLINE,
    CORE_FAILURE_ONLINE,
    DECEASED,
    AUTH_PASSED_WITH_EXPIRED_ASSET,
    ASSET_EXPIRED,
    ASSET_NOT_FOUND,
    CREDIT_XPERT_FAILURE,
    CUSTOMER_ONLINE,
    NO_ACTIVE_ENROLLMENT,
    PENDING_ENROLLMENT,
    EXCEED_ATTEMPTS_ALLOWED,
    SSN_NAME_MISMATCH,
    ACTIVE_ENROLLMENT,
    PARTNER_MEMBERSHIP_CANCELED,
    SSN_EXISITS_ACTIVE_OFFLINE,
    SSN_EXISITS_ACTIVE_ONLINE,
    SSN_EXISITS_PENDING_OFFLINE,
    SSN_EXISITS_PENDING_ONLINE,
    MULTIPLE_CUST_FOUND,
    PENDING_ONLINE_CUSTOMER,
    SUBSEQUENT_ORDER_NOT_ALLOWED,
    ALREADY_REQUESTED,
    NAME_ZIP_EXISTS,
    PARTNER_CUST_CODE_NOT_FOUND,
    PARTNER_CUST_CODE_EMPTY,
    LEGAL_COPY_NOT_FOUND,
    INVALID_AUTH_DATA,
    SUBSCRIBER_NOT_FOUND,
    INVALID_APPLICANT_CHALLENGE,
    BUSINESS_NOT_FOUND,
    APPLICANT_NOT_FOUND,
    INVALID_TOKEN,
    USED_TOKEN,
    EXPIRED_TOKEN,
    INVALID_CREDIT_REPORT,
    SAFETY_CHECK_FAILED,
    CHALLENGE_NOT_INPROGRESS,
    NO_QUESTIONS_REMAINING,
    SSN_NOT_FOUND,
    INVALID_ANSWER_CHOICE,
    AGE_RESTRICTION,
    NAME_MISMATCH,
    SSN_MISMATCH,
    INVALID_PHONE_CHALLENGE,
    CALL_FAILED,
    PA_SECURITY_CODE_FAILURE,
    WRONG_NUMBER_SELECTED,
    NO_CR_PHONE_NUMBER,
    WRONG_CODE_PREFIX,
    AUTHENTICATION_REQUIRED,
    DUPLICATE_PARTNER_CUST_CODE,
    MAX_PIN_SENT_COUNT,
    PIN_NOT_FOUND,
    MAX_PIN_COUNT,
    INVALID_PIN,
    SITE_NOT_FOUND,
    AVC_NOT_FOUND,
    AVC_NO_MORE_USES,
    AVC_EXPIRED,
    AVC_NOT_ACTIVE,
    REQUIRES_PARTNER_CUST_CODE,
    CREDIT_LOCK_SERVICE_UNAVAILABLE,
    CREDIT_FREEZE_ON_FILE,
    ALREADY_LOCKED,
    ALREADY_UNLOCKED,
    SSN_EXISTS_ACTIVE_ONLINE;

    public String value() {
        return name();
    }

    public static FailureEnum fromValue(String v) {
        return valueOf(v);
    }

}
