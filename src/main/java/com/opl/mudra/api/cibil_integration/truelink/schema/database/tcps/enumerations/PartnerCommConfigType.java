
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerCommConfigType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PartnerCommConfigType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AVCCancel"/>
 *     &lt;enumeration value="AVCCancelConfirmation"/>
 *     &lt;enumeration value="Affinion1BOnlineMonitoringAlerts"/>
 *     &lt;enumeration value="Affinion1Band3BAlertPinningStatus"/>
 *     &lt;enumeration value="Affinion1Band3BMonitoringHits"/>
 *     &lt;enumeration value="Affinion1Band3BMonitoringSubscription"/>
 *     &lt;enumeration value="Affinion1Band3BMonitoringSubscriptionAck"/>
 *     &lt;enumeration value="Affinion3BOnlineMonitoringAlerts"/>
 *     &lt;enumeration value="AffinionCXProducts"/>
 *     &lt;enumeration value="AffinionCreditAssistSubscription"/>
 *     &lt;enumeration value="AffinionCreditAssistSubscriptionAck"/>
 *     &lt;enumeration value="AffinionOfflineCreditReportAcknowledgement"/>
 *     &lt;enumeration value="AffinionOfflineCreditReportFeedback"/>
 *     &lt;enumeration value="AffinionOfflineCreditReportRequest"/>
 *     &lt;enumeration value="AffinionScoreTrackerSubscription"/>
 *     &lt;enumeration value="AffinionScoreTrackerSubscriptionAck"/>
 *     &lt;enumeration value="AffinionScoreTrackerSubscriptionFeedback"/>
 *     &lt;enumeration value="CESCancel"/>
 *     &lt;enumeration value="CESCancelConfirmation"/>
 *     &lt;enumeration value="CESDailyAlertFile"/>
 *     &lt;enumeration value="CESFailedIV"/>
 *     &lt;enumeration value="CPPCancel"/>
 *     &lt;enumeration value="CPPCancelConfirmation"/>
 *     &lt;enumeration value="Cancel"/>
 *     &lt;enumeration value="CancelConfirmation"/>
 *     &lt;enumeration value="CardAcctValIn"/>
 *     &lt;enumeration value="CardAcctValOut"/>
 *     &lt;enumeration value="CardDispFile"/>
 *     &lt;enumeration value="CardLeadFile"/>
 *     &lt;enumeration value="CardMemHist"/>
 *     &lt;enumeration value="EACancel"/>
 *     &lt;enumeration value="EACancelConfirmation"/>
 *     &lt;enumeration value="Feedback"/>
 *     &lt;enumeration value="FeedbackRetail"/>
 *     &lt;enumeration value="IDWCancel"/>
 *     &lt;enumeration value="IDWCancelConfirmation"/>
 *     &lt;enumeration value="IDWDailyAlertFile"/>
 *     &lt;enumeration value="LLEnrollment"/>
 *     &lt;enumeration value="LLFeedback"/>
 *     &lt;enumeration value="OBTMTPE"/>
 *     &lt;enumeration value="OTCancel"/>
 *     &lt;enumeration value="OTCancelConfirmation"/>
 *     &lt;enumeration value="OTDailyAlertFile"/>
 *     &lt;enumeration value="OTFailedIV"/>
 *     &lt;enumeration value="PrimaryCardDataIn"/>
 *     &lt;enumeration value="PrimaryCardDataOut"/>
 *     &lt;enumeration value="QPASSInbound"/>
 *     &lt;enumeration value="QPASSOutbound"/>
 *     &lt;enumeration value="RetAcctValIn"/>
 *     &lt;enumeration value="RetAcctValOut"/>
 *     &lt;enumeration value="RetBonusCheck"/>
 *     &lt;enumeration value="RetMemHist"/>
 *     &lt;enumeration value="SSNValIn"/>
 *     &lt;enumeration value="SSNValOut"/>
 *     &lt;enumeration value="TPE"/>
 *     &lt;enumeration value="TPERetail"/>
 *     &lt;enumeration value="TUAutoPartner1Enrollment"/>
 *     &lt;enumeration value="TUAutoPartner1Feedback"/>
 *     &lt;enumeration value="TUAutoPartner2Enrollment"/>
 *     &lt;enumeration value="TUAutoPartner2Feedback"/>
 *     &lt;enumeration value="TUAutoPartner3Enrollment"/>
 *     &lt;enumeration value="TUAutoPartner3Feedback"/>
 *     &lt;enumeration value="TUEnrollment"/>
 *     &lt;enumeration value="TUFeedback"/>
 *     &lt;enumeration value="ToPaymenTech_TUCRSAccountUpdater"/>
 *     &lt;enumeration value="VertruePartnerNotification"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PartnerCommConfigType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum PartnerCommConfigType {

    @XmlEnumValue("AVCCancel")
    AVC_CANCEL("AVCCancel"),
    @XmlEnumValue("AVCCancelConfirmation")
    AVC_CANCEL_CONFIRMATION("AVCCancelConfirmation"),
    @XmlEnumValue("Affinion1BOnlineMonitoringAlerts")
    AFFINION_1_B_ONLINE_MONITORING_ALERTS("Affinion1BOnlineMonitoringAlerts"),
    @XmlEnumValue("Affinion1Band3BAlertPinningStatus")
    AFFINION_1_BAND_3_B_ALERT_PINNING_STATUS("Affinion1Band3BAlertPinningStatus"),
    @XmlEnumValue("Affinion1Band3BMonitoringHits")
    AFFINION_1_BAND_3_B_MONITORING_HITS("Affinion1Band3BMonitoringHits"),
    @XmlEnumValue("Affinion1Band3BMonitoringSubscription")
    AFFINION_1_BAND_3_B_MONITORING_SUBSCRIPTION("Affinion1Band3BMonitoringSubscription"),
    @XmlEnumValue("Affinion1Band3BMonitoringSubscriptionAck")
    AFFINION_1_BAND_3_B_MONITORING_SUBSCRIPTION_ACK("Affinion1Band3BMonitoringSubscriptionAck"),
    @XmlEnumValue("Affinion3BOnlineMonitoringAlerts")
    AFFINION_3_B_ONLINE_MONITORING_ALERTS("Affinion3BOnlineMonitoringAlerts"),
    @XmlEnumValue("AffinionCXProducts")
    AFFINION_CX_PRODUCTS("AffinionCXProducts"),
    @XmlEnumValue("AffinionCreditAssistSubscription")
    AFFINION_CREDIT_ASSIST_SUBSCRIPTION("AffinionCreditAssistSubscription"),
    @XmlEnumValue("AffinionCreditAssistSubscriptionAck")
    AFFINION_CREDIT_ASSIST_SUBSCRIPTION_ACK("AffinionCreditAssistSubscriptionAck"),
    @XmlEnumValue("AffinionOfflineCreditReportAcknowledgement")
    AFFINION_OFFLINE_CREDIT_REPORT_ACKNOWLEDGEMENT("AffinionOfflineCreditReportAcknowledgement"),
    @XmlEnumValue("AffinionOfflineCreditReportFeedback")
    AFFINION_OFFLINE_CREDIT_REPORT_FEEDBACK("AffinionOfflineCreditReportFeedback"),
    @XmlEnumValue("AffinionOfflineCreditReportRequest")
    AFFINION_OFFLINE_CREDIT_REPORT_REQUEST("AffinionOfflineCreditReportRequest"),
    @XmlEnumValue("AffinionScoreTrackerSubscription")
    AFFINION_SCORE_TRACKER_SUBSCRIPTION("AffinionScoreTrackerSubscription"),
    @XmlEnumValue("AffinionScoreTrackerSubscriptionAck")
    AFFINION_SCORE_TRACKER_SUBSCRIPTION_ACK("AffinionScoreTrackerSubscriptionAck"),
    @XmlEnumValue("AffinionScoreTrackerSubscriptionFeedback")
    AFFINION_SCORE_TRACKER_SUBSCRIPTION_FEEDBACK("AffinionScoreTrackerSubscriptionFeedback"),
    @XmlEnumValue("CESCancel")
    CES_CANCEL("CESCancel"),
    @XmlEnumValue("CESCancelConfirmation")
    CES_CANCEL_CONFIRMATION("CESCancelConfirmation"),
    @XmlEnumValue("CESDailyAlertFile")
    CES_DAILY_ALERT_FILE("CESDailyAlertFile"),
    @XmlEnumValue("CESFailedIV")
    CES_FAILED_IV("CESFailedIV"),
    @XmlEnumValue("CPPCancel")
    CPP_CANCEL("CPPCancel"),
    @XmlEnumValue("CPPCancelConfirmation")
    CPP_CANCEL_CONFIRMATION("CPPCancelConfirmation"),
    @XmlEnumValue("Cancel")
    CANCEL("Cancel"),
    @XmlEnumValue("CancelConfirmation")
    CANCEL_CONFIRMATION("CancelConfirmation"),
    @XmlEnumValue("CardAcctValIn")
    CARD_ACCT_VAL_IN("CardAcctValIn"),
    @XmlEnumValue("CardAcctValOut")
    CARD_ACCT_VAL_OUT("CardAcctValOut"),
    @XmlEnumValue("CardDispFile")
    CARD_DISP_FILE("CardDispFile"),
    @XmlEnumValue("CardLeadFile")
    CARD_LEAD_FILE("CardLeadFile"),
    @XmlEnumValue("CardMemHist")
    CARD_MEM_HIST("CardMemHist"),
    @XmlEnumValue("EACancel")
    EA_CANCEL("EACancel"),
    @XmlEnumValue("EACancelConfirmation")
    EA_CANCEL_CONFIRMATION("EACancelConfirmation"),
    @XmlEnumValue("Feedback")
    FEEDBACK("Feedback"),
    @XmlEnumValue("FeedbackRetail")
    FEEDBACK_RETAIL("FeedbackRetail"),
    @XmlEnumValue("IDWCancel")
    IDW_CANCEL("IDWCancel"),
    @XmlEnumValue("IDWCancelConfirmation")
    IDW_CANCEL_CONFIRMATION("IDWCancelConfirmation"),
    @XmlEnumValue("IDWDailyAlertFile")
    IDW_DAILY_ALERT_FILE("IDWDailyAlertFile"),
    @XmlEnumValue("LLEnrollment")
    LL_ENROLLMENT("LLEnrollment"),
    @XmlEnumValue("LLFeedback")
    LL_FEEDBACK("LLFeedback"),
    OBTMTPE("OBTMTPE"),
    @XmlEnumValue("OTCancel")
    OT_CANCEL("OTCancel"),
    @XmlEnumValue("OTCancelConfirmation")
    OT_CANCEL_CONFIRMATION("OTCancelConfirmation"),
    @XmlEnumValue("OTDailyAlertFile")
    OT_DAILY_ALERT_FILE("OTDailyAlertFile"),
    @XmlEnumValue("OTFailedIV")
    OT_FAILED_IV("OTFailedIV"),
    @XmlEnumValue("PrimaryCardDataIn")
    PRIMARY_CARD_DATA_IN("PrimaryCardDataIn"),
    @XmlEnumValue("PrimaryCardDataOut")
    PRIMARY_CARD_DATA_OUT("PrimaryCardDataOut"),
    @XmlEnumValue("QPASSInbound")
    QPASS_INBOUND("QPASSInbound"),
    @XmlEnumValue("QPASSOutbound")
    QPASS_OUTBOUND("QPASSOutbound"),
    @XmlEnumValue("RetAcctValIn")
    RET_ACCT_VAL_IN("RetAcctValIn"),
    @XmlEnumValue("RetAcctValOut")
    RET_ACCT_VAL_OUT("RetAcctValOut"),
    @XmlEnumValue("RetBonusCheck")
    RET_BONUS_CHECK("RetBonusCheck"),
    @XmlEnumValue("RetMemHist")
    RET_MEM_HIST("RetMemHist"),
    @XmlEnumValue("SSNValIn")
    SSN_VAL_IN("SSNValIn"),
    @XmlEnumValue("SSNValOut")
    SSN_VAL_OUT("SSNValOut"),
    TPE("TPE"),
    @XmlEnumValue("TPERetail")
    TPE_RETAIL("TPERetail"),
    @XmlEnumValue("TUAutoPartner1Enrollment")
    TU_AUTO_PARTNER_1_ENROLLMENT("TUAutoPartner1Enrollment"),
    @XmlEnumValue("TUAutoPartner1Feedback")
    TU_AUTO_PARTNER_1_FEEDBACK("TUAutoPartner1Feedback"),
    @XmlEnumValue("TUAutoPartner2Enrollment")
    TU_AUTO_PARTNER_2_ENROLLMENT("TUAutoPartner2Enrollment"),
    @XmlEnumValue("TUAutoPartner2Feedback")
    TU_AUTO_PARTNER_2_FEEDBACK("TUAutoPartner2Feedback"),
    @XmlEnumValue("TUAutoPartner3Enrollment")
    TU_AUTO_PARTNER_3_ENROLLMENT("TUAutoPartner3Enrollment"),
    @XmlEnumValue("TUAutoPartner3Feedback")
    TU_AUTO_PARTNER_3_FEEDBACK("TUAutoPartner3Feedback"),
    @XmlEnumValue("TUEnrollment")
    TU_ENROLLMENT("TUEnrollment"),
    @XmlEnumValue("TUFeedback")
    TU_FEEDBACK("TUFeedback"),
    @XmlEnumValue("ToPaymenTech_TUCRSAccountUpdater")
    TO_PAYMEN_TECH_TUCRS_ACCOUNT_UPDATER("ToPaymenTech_TUCRSAccountUpdater"),
    @XmlEnumValue("VertruePartnerNotification")
    VERTRUE_PARTNER_NOTIFICATION("VertruePartnerNotification");
    private final String value;

    PartnerCommConfigType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PartnerCommConfigType fromValue(String v) {
        for (PartnerCommConfigType c: PartnerCommConfigType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
