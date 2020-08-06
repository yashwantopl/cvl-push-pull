
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommRequestType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CommRequestType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AddCreditGoalSubscription"/>
 *     &lt;enumeration value="AddMember"/>
 *     &lt;enumeration value="CancelCustomer"/>
 *     &lt;enumeration value="CreateCustomerAndOrder"/>
 *     &lt;enumeration value="CreateCustomerLogin"/>
 *     &lt;enumeration value="FulfillProduct"/>
 *     &lt;enumeration value="GetAlerts"/>
 *     &lt;enumeration value="GetCatalogItemLegalCopyForOffer"/>
 *     &lt;enumeration value="GetCreditXpertProducts"/>
 *     &lt;enumeration value="GetCustomer"/>
 *     &lt;enumeration value="GetCustomerAssets"/>
 *     &lt;enumeration value="GetCustomerStatus"/>
 *     &lt;enumeration value="GetEnrollmentToken"/>
 *     &lt;enumeration value="GetIVQuestions"/>
 *     &lt;enumeration value="GetIVWebToken"/>
 *     &lt;enumeration value="GetLatestAssetsForMobile"/>
 *     &lt;enumeration value="GetLegalCopyForCustomer"/>
 *     &lt;enumeration value="GetMemberInfo"/>
 *     &lt;enumeration value="GetMicroSmallBusinessScore"/>
 *     &lt;enumeration value="GetProductWebToken"/>
 *     &lt;enumeration value="GetScoreTrack"/>
 *     &lt;enumeration value="GetTransRiskScore"/>
 *     &lt;enumeration value="HealthCheck"/>
 *     &lt;enumeration value="IdentityVerification"/>
 *     &lt;enumeration value="IndicativeEnrichment"/>
 *     &lt;enumeration value="IsUserSubscribed"/>
 *     &lt;enumeration value="LoginCustomer"/>
 *     &lt;enumeration value="LookupAuthentication"/>
 *     &lt;enumeration value="OfferReplacement"/>
 *     &lt;enumeration value="OrderCR"/>
 *     &lt;enumeration value="OrderFulfillment"/>
 *     &lt;enumeration value="PrepareEnrollmentToken"/>
 *     &lt;enumeration value="ProcessMSPTransaction"/>
 *     &lt;enumeration value="ReactivateCustomer"/>
 *     &lt;enumeration value="RegisterCustomer"/>
 *     &lt;enumeration value="SendPIN"/>
 *     &lt;enumeration value="SimulateTransRiskScore"/>
 *     &lt;enumeration value="StartCreditXpertWhatif"/>
 *     &lt;enumeration value="StartSubscriptionBilling"/>
 *     &lt;enumeration value="Subscribe"/>
 *     &lt;enumeration value="SubsequentFulfillment"/>
 *     &lt;enumeration value="SubsequentOrderFulfillment"/>
 *     &lt;enumeration value="Unsubscribe"/>
 *     &lt;enumeration value="UpdateAllMemberSubscriptions"/>
 *     &lt;enumeration value="UpdateCustomerInformation"/>
 *     &lt;enumeration value="UpdateCustomerLogin"/>
 *     &lt;enumeration value="UpdateFreeToPaid"/>
 *     &lt;enumeration value="UpdateIVStatus"/>
 *     &lt;enumeration value="UpdateMember"/>
 *     &lt;enumeration value="VerifyIVAnswer"/>
 *     &lt;enumeration value="VerifyIVChallenge"/>
 *     &lt;enumeration value="VerifyPIN"/>
 *     &lt;enumeration value="ViewCR"/>
 *     &lt;enumeration value="ViewMonitoringReportByDateRange"/>
 *     &lt;enumeration value="getTrustedSignOnToken"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CommRequestType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CommRequestType {

    @XmlEnumValue("AddCreditGoalSubscription")
    ADD_CREDIT_GOAL_SUBSCRIPTION("AddCreditGoalSubscription"),
    @XmlEnumValue("AddMember")
    ADD_MEMBER("AddMember"),
    @XmlEnumValue("CancelCustomer")
    CANCEL_CUSTOMER("CancelCustomer"),
    @XmlEnumValue("CreateCustomerAndOrder")
    CREATE_CUSTOMER_AND_ORDER("CreateCustomerAndOrder"),
    @XmlEnumValue("CreateCustomerLogin")
    CREATE_CUSTOMER_LOGIN("CreateCustomerLogin"),
    @XmlEnumValue("FulfillProduct")
    FULFILL_PRODUCT("FulfillProduct"),
    @XmlEnumValue("GetAlerts")
    GET_ALERTS("GetAlerts"),
    @XmlEnumValue("GetCatalogItemLegalCopyForOffer")
    GET_CATALOG_ITEM_LEGAL_COPY_FOR_OFFER("GetCatalogItemLegalCopyForOffer"),
    @XmlEnumValue("GetCreditXpertProducts")
    GET_CREDIT_XPERT_PRODUCTS("GetCreditXpertProducts"),
    @XmlEnumValue("GetCustomer")
    GET_CUSTOMER("GetCustomer"),
    @XmlEnumValue("GetCustomerAssets")
    GET_CUSTOMER_ASSETS("GetCustomerAssets"),
    @XmlEnumValue("GetCustomerStatus")
    GET_CUSTOMER_STATUS("GetCustomerStatus"),
    @XmlEnumValue("GetEnrollmentToken")
    GET_ENROLLMENT_TOKEN("GetEnrollmentToken"),
    @XmlEnumValue("GetIVQuestions")
    GET_IV_QUESTIONS("GetIVQuestions"),
    @XmlEnumValue("GetIVWebToken")
    GET_IV_WEB_TOKEN("GetIVWebToken"),
    @XmlEnumValue("GetLatestAssetsForMobile")
    GET_LATEST_ASSETS_FOR_MOBILE("GetLatestAssetsForMobile"),
    @XmlEnumValue("GetLegalCopyForCustomer")
    GET_LEGAL_COPY_FOR_CUSTOMER("GetLegalCopyForCustomer"),
    @XmlEnumValue("GetMemberInfo")
    GET_MEMBER_INFO("GetMemberInfo"),
    @XmlEnumValue("GetMicroSmallBusinessScore")
    GET_MICRO_SMALL_BUSINESS_SCORE("GetMicroSmallBusinessScore"),
    @XmlEnumValue("GetProductWebToken")
    GET_PRODUCT_WEB_TOKEN("GetProductWebToken"),
    @XmlEnumValue("GetScoreTrack")
    GET_SCORE_TRACK("GetScoreTrack"),
    @XmlEnumValue("GetTransRiskScore")
    GET_TRANS_RISK_SCORE("GetTransRiskScore"),
    @XmlEnumValue("HealthCheck")
    HEALTH_CHECK("HealthCheck"),
    @XmlEnumValue("IdentityVerification")
    IDENTITY_VERIFICATION("IdentityVerification"),
    @XmlEnumValue("IndicativeEnrichment")
    INDICATIVE_ENRICHMENT("IndicativeEnrichment"),
    @XmlEnumValue("IsUserSubscribed")
    IS_USER_SUBSCRIBED("IsUserSubscribed"),
    @XmlEnumValue("LoginCustomer")
    LOGIN_CUSTOMER("LoginCustomer"),
    @XmlEnumValue("LookupAuthentication")
    LOOKUP_AUTHENTICATION("LookupAuthentication"),
    @XmlEnumValue("OfferReplacement")
    OFFER_REPLACEMENT("OfferReplacement"),
    @XmlEnumValue("OrderCR")
    ORDER_CR("OrderCR"),
    @XmlEnumValue("OrderFulfillment")
    ORDER_FULFILLMENT("OrderFulfillment"),
    @XmlEnumValue("PrepareEnrollmentToken")
    PREPARE_ENROLLMENT_TOKEN("PrepareEnrollmentToken"),
    @XmlEnumValue("ProcessMSPTransaction")
    PROCESS_MSP_TRANSACTION("ProcessMSPTransaction"),
    @XmlEnumValue("ReactivateCustomer")
    REACTIVATE_CUSTOMER("ReactivateCustomer"),
    @XmlEnumValue("RegisterCustomer")
    REGISTER_CUSTOMER("RegisterCustomer"),
    @XmlEnumValue("SendPIN")
    SEND_PIN("SendPIN"),
    @XmlEnumValue("SimulateTransRiskScore")
    SIMULATE_TRANS_RISK_SCORE("SimulateTransRiskScore"),
    @XmlEnumValue("StartCreditXpertWhatif")
    START_CREDIT_XPERT_WHATIF("StartCreditXpertWhatif"),
    @XmlEnumValue("StartSubscriptionBilling")
    START_SUBSCRIPTION_BILLING("StartSubscriptionBilling"),
    @XmlEnumValue("Subscribe")
    SUBSCRIBE("Subscribe"),
    @XmlEnumValue("SubsequentFulfillment")
    SUBSEQUENT_FULFILLMENT("SubsequentFulfillment"),
    @XmlEnumValue("SubsequentOrderFulfillment")
    SUBSEQUENT_ORDER_FULFILLMENT("SubsequentOrderFulfillment"),
    @XmlEnumValue("Unsubscribe")
    UNSUBSCRIBE("Unsubscribe"),
    @XmlEnumValue("UpdateAllMemberSubscriptions")
    UPDATE_ALL_MEMBER_SUBSCRIPTIONS("UpdateAllMemberSubscriptions"),
    @XmlEnumValue("UpdateCustomerInformation")
    UPDATE_CUSTOMER_INFORMATION("UpdateCustomerInformation"),
    @XmlEnumValue("UpdateCustomerLogin")
    UPDATE_CUSTOMER_LOGIN("UpdateCustomerLogin"),
    @XmlEnumValue("UpdateFreeToPaid")
    UPDATE_FREE_TO_PAID("UpdateFreeToPaid"),
    @XmlEnumValue("UpdateIVStatus")
    UPDATE_IV_STATUS("UpdateIVStatus"),
    @XmlEnumValue("UpdateMember")
    UPDATE_MEMBER("UpdateMember"),
    @XmlEnumValue("VerifyIVAnswer")
    VERIFY_IV_ANSWER("VerifyIVAnswer"),
    @XmlEnumValue("VerifyIVChallenge")
    VERIFY_IV_CHALLENGE("VerifyIVChallenge"),
    @XmlEnumValue("VerifyPIN")
    VERIFY_PIN("VerifyPIN"),
    @XmlEnumValue("ViewCR")
    VIEW_CR("ViewCR"),
    @XmlEnumValue("ViewMonitoringReportByDateRange")
    VIEW_MONITORING_REPORT_BY_DATE_RANGE("ViewMonitoringReportByDateRange"),
    @XmlEnumValue("getTrustedSignOnToken")
    GET_TRUSTED_SIGN_ON_TOKEN("getTrustedSignOnToken");
    private final String value;

    CommRequestType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CommRequestType fromValue(String v) {
        for (CommRequestType c: CommRequestType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
