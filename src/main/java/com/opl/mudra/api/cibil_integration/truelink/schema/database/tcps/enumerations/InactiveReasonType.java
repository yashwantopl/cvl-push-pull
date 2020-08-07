
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InactiveReasonType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InactiveReasonType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AbandonedOnlineEnrollment"/>
 *     &lt;enumeration value="AgeRestriction"/>
 *     &lt;enumeration value="Billing"/>
 *     &lt;enumeration value="CustomerInitiatedOnline"/>
 *     &lt;enumeration value="CustomerRequest"/>
 *     &lt;enumeration value="Deceased"/>
 *     &lt;enumeration value="EndOfBreach"/>
 *     &lt;enumeration value="FailedIV"/>
 *     &lt;enumeration value="FailedPINVerification"/>
 *     &lt;enumeration value="NoEquifaxMonitoring"/>
 *     &lt;enumeration value="NoExperianMonitoring"/>
 *     &lt;enumeration value="NoHit"/>
 *     &lt;enumeration value="NoTUMonitoring"/>
 *     &lt;enumeration value="NotApplicable"/>
 *     &lt;enumeration value="PartnerInitiated"/>
 *     &lt;enumeration value="PrimaryCardholderInitiated"/>
 *     &lt;enumeration value="SSNMismatch"/>
 *     &lt;enumeration value="ThinFile"/>
 *     &lt;enumeration value="TrueCreditInitiated"/>
 *     &lt;enumeration value="TwoPhaseBlacklist"/>
 *     &lt;enumeration value="TwoPhaseDuplicate"/>
 *     &lt;enumeration value="NoTUEnhancedMonitoring"/>
 *     &lt;enumeration value="NoCSIDIdentityMonitoring"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InactiveReasonType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum InactiveReasonType {

    @XmlEnumValue("AbandonedOnlineEnrollment")
    ABANDONED_ONLINE_ENROLLMENT("AbandonedOnlineEnrollment"),
    @XmlEnumValue("AgeRestriction")
    AGE_RESTRICTION("AgeRestriction"),
    @XmlEnumValue("Billing")
    BILLING("Billing"),
    @XmlEnumValue("CustomerInitiatedOnline")
    CUSTOMER_INITIATED_ONLINE("CustomerInitiatedOnline"),
    @XmlEnumValue("CustomerRequest")
    CUSTOMER_REQUEST("CustomerRequest"),
    @XmlEnumValue("Deceased")
    DECEASED("Deceased"),
    @XmlEnumValue("EndOfBreach")
    END_OF_BREACH("EndOfBreach"),
    @XmlEnumValue("FailedIV")
    FAILED_IV("FailedIV"),
    @XmlEnumValue("FailedPINVerification")
    FAILED_PIN_VERIFICATION("FailedPINVerification"),
    @XmlEnumValue("NoEquifaxMonitoring")
    NO_EQUIFAX_MONITORING("NoEquifaxMonitoring"),
    @XmlEnumValue("NoExperianMonitoring")
    NO_EXPERIAN_MONITORING("NoExperianMonitoring"),
    @XmlEnumValue("NoHit")
    NO_HIT("NoHit"),
    @XmlEnumValue("NoTUMonitoring")
    NO_TU_MONITORING("NoTUMonitoring"),
    @XmlEnumValue("NotApplicable")
    NOT_APPLICABLE("NotApplicable"),
    @XmlEnumValue("PartnerInitiated")
    PARTNER_INITIATED("PartnerInitiated"),
    @XmlEnumValue("PrimaryCardholderInitiated")
    PRIMARY_CARDHOLDER_INITIATED("PrimaryCardholderInitiated"),
    @XmlEnumValue("SSNMismatch")
    SSN_MISMATCH("SSNMismatch"),
    @XmlEnumValue("ThinFile")
    THIN_FILE("ThinFile"),
    @XmlEnumValue("TrueCreditInitiated")
    TRUE_CREDIT_INITIATED("TrueCreditInitiated"),
    @XmlEnumValue("TwoPhaseBlacklist")
    TWO_PHASE_BLACKLIST("TwoPhaseBlacklist"),
    @XmlEnumValue("TwoPhaseDuplicate")
    TWO_PHASE_DUPLICATE("TwoPhaseDuplicate"),
    @XmlEnumValue("NoTUEnhancedMonitoring")
    NO_TU_ENHANCED_MONITORING("NoTUEnhancedMonitoring"),
    @XmlEnumValue("NoCSIDIdentityMonitoring")
    NO_CSID_IDENTITY_MONITORING("NoCSIDIdentityMonitoring");
    private final String value;

    InactiveReasonType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InactiveReasonType fromValue(String v) {
        for (InactiveReasonType c: InactiveReasonType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
