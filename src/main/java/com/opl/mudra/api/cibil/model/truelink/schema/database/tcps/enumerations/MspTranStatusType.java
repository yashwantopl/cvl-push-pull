
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MspTranStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MspTranStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AgeRestriction"/>
 *     &lt;enumeration value="FailureCore"/>
 *     &lt;enumeration value="FailureCustomerLocked"/>
 *     &lt;enumeration value="FailureDeceased"/>
 *     &lt;enumeration value="FailureDuplicateTransactionID"/>
 *     &lt;enumeration value="FailureFraud"/>
 *     &lt;enumeration value="FailureIdentityNotVerified"/>
 *     &lt;enumeration value="FailureMultipleCustFound"/>
 *     &lt;enumeration value="FailureNameZipExists"/>
 *     &lt;enumeration value="FailureNoHit"/>
 *     &lt;enumeration value="FailurePartnerMembershipCanceled"/>
 *     &lt;enumeration value="FailureSSNexisitsActiveOffline"/>
 *     &lt;enumeration value="FailureSSNexisitsPendingOffline"/>
 *     &lt;enumeration value="FailureSSNexists"/>
 *     &lt;enumeration value="FailureSubsequentAlreadyRequested"/>
 *     &lt;enumeration value="FailureSubsequentNotAllowed"/>
 *     &lt;enumeration value="FailureThinFile"/>
 *     &lt;enumeration value="FailureUnknownAccount"/>
 *     &lt;enumeration value="FailureUnknownProdConfigID"/>
 *     &lt;enumeration value="FailureUnrecognizableRecord"/>
 *     &lt;enumeration value="FailureUnverifiedAddress"/>
 *     &lt;enumeration value="FailureVendor"/>
 *     &lt;enumeration value="Success"/>
 *     &lt;enumeration value="SystemError"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MspTranStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum MspTranStatusType {

    @XmlEnumValue("AgeRestriction")
    AGE_RESTRICTION("AgeRestriction"),
    @XmlEnumValue("FailureCore")
    FAILURE_CORE("FailureCore"),
    @XmlEnumValue("FailureCustomerLocked")
    FAILURE_CUSTOMER_LOCKED("FailureCustomerLocked"),
    @XmlEnumValue("FailureDeceased")
    FAILURE_DECEASED("FailureDeceased"),
    @XmlEnumValue("FailureDuplicateTransactionID")
    FAILURE_DUPLICATE_TRANSACTION_ID("FailureDuplicateTransactionID"),
    @XmlEnumValue("FailureFraud")
    FAILURE_FRAUD("FailureFraud"),
    @XmlEnumValue("FailureIdentityNotVerified")
    FAILURE_IDENTITY_NOT_VERIFIED("FailureIdentityNotVerified"),
    @XmlEnumValue("FailureMultipleCustFound")
    FAILURE_MULTIPLE_CUST_FOUND("FailureMultipleCustFound"),
    @XmlEnumValue("FailureNameZipExists")
    FAILURE_NAME_ZIP_EXISTS("FailureNameZipExists"),
    @XmlEnumValue("FailureNoHit")
    FAILURE_NO_HIT("FailureNoHit"),
    @XmlEnumValue("FailurePartnerMembershipCanceled")
    FAILURE_PARTNER_MEMBERSHIP_CANCELED("FailurePartnerMembershipCanceled"),
    @XmlEnumValue("FailureSSNexisitsActiveOffline")
    FAILURE_SS_NEXISITS_ACTIVE_OFFLINE("FailureSSNexisitsActiveOffline"),
    @XmlEnumValue("FailureSSNexisitsPendingOffline")
    FAILURE_SS_NEXISITS_PENDING_OFFLINE("FailureSSNexisitsPendingOffline"),
    @XmlEnumValue("FailureSSNexists")
    FAILURE_SS_NEXISTS("FailureSSNexists"),
    @XmlEnumValue("FailureSubsequentAlreadyRequested")
    FAILURE_SUBSEQUENT_ALREADY_REQUESTED("FailureSubsequentAlreadyRequested"),
    @XmlEnumValue("FailureSubsequentNotAllowed")
    FAILURE_SUBSEQUENT_NOT_ALLOWED("FailureSubsequentNotAllowed"),
    @XmlEnumValue("FailureThinFile")
    FAILURE_THIN_FILE("FailureThinFile"),
    @XmlEnumValue("FailureUnknownAccount")
    FAILURE_UNKNOWN_ACCOUNT("FailureUnknownAccount"),
    @XmlEnumValue("FailureUnknownProdConfigID")
    FAILURE_UNKNOWN_PROD_CONFIG_ID("FailureUnknownProdConfigID"),
    @XmlEnumValue("FailureUnrecognizableRecord")
    FAILURE_UNRECOGNIZABLE_RECORD("FailureUnrecognizableRecord"),
    @XmlEnumValue("FailureUnverifiedAddress")
    FAILURE_UNVERIFIED_ADDRESS("FailureUnverifiedAddress"),
    @XmlEnumValue("FailureVendor")
    FAILURE_VENDOR("FailureVendor"),
    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("SystemError")
    SYSTEM_ERROR("SystemError");
    private final String value;

    MspTranStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MspTranStatusType fromValue(String v) {
        for (MspTranStatusType c: MspTranStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
