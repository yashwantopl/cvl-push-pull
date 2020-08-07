
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransEntryResultType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransEntryResultType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Approved"/>
 *     &lt;enumeration value="ApprovedAddressMismatch"/>
 *     &lt;enumeration value="ApprovedAvsUnavailable"/>
 *     &lt;enumeration value="ApprovedDuplicate"/>
 *     &lt;enumeration value="BadAuthentication"/>
 *     &lt;enumeration value="Declined"/>
 *     &lt;enumeration value="DeclinedExpired"/>
 *     &lt;enumeration value="DeclinedInsufficientFunds"/>
 *     &lt;enumeration value="DeclinedInvalidCArdData"/>
 *     &lt;enumeration value="DuplicateReference"/>
 *     &lt;enumeration value="InvalidTransaction"/>
 *     &lt;enumeration value="NoAuthorization"/>
 *     &lt;enumeration value="Referral"/>
 *     &lt;enumeration value="StopPayment"/>
 *     &lt;enumeration value="UnableToAuthenticate"/>
 *     &lt;enumeration value="UnknownResponse"/>
 *     &lt;enumeration value="VendorUnavailable"/>
 *     &lt;enumeration value="Pickup"/>
 *     &lt;enumeration value="LostStolen"/>
 *     &lt;enumeration value="NoAccount"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransEntryResultType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum TransEntryResultType {

    @XmlEnumValue("Approved")
    APPROVED("Approved"),
    @XmlEnumValue("ApprovedAddressMismatch")
    APPROVED_ADDRESS_MISMATCH("ApprovedAddressMismatch"),
    @XmlEnumValue("ApprovedAvsUnavailable")
    APPROVED_AVS_UNAVAILABLE("ApprovedAvsUnavailable"),
    @XmlEnumValue("ApprovedDuplicate")
    APPROVED_DUPLICATE("ApprovedDuplicate"),
    @XmlEnumValue("BadAuthentication")
    BAD_AUTHENTICATION("BadAuthentication"),
    @XmlEnumValue("Declined")
    DECLINED("Declined"),
    @XmlEnumValue("DeclinedExpired")
    DECLINED_EXPIRED("DeclinedExpired"),
    @XmlEnumValue("DeclinedInsufficientFunds")
    DECLINED_INSUFFICIENT_FUNDS("DeclinedInsufficientFunds"),
    @XmlEnumValue("DeclinedInvalidCArdData")
    DECLINED_INVALID_C_ARD_DATA("DeclinedInvalidCArdData"),
    @XmlEnumValue("DuplicateReference")
    DUPLICATE_REFERENCE("DuplicateReference"),
    @XmlEnumValue("InvalidTransaction")
    INVALID_TRANSACTION("InvalidTransaction"),
    @XmlEnumValue("NoAuthorization")
    NO_AUTHORIZATION("NoAuthorization"),
    @XmlEnumValue("Referral")
    REFERRAL("Referral"),
    @XmlEnumValue("StopPayment")
    STOP_PAYMENT("StopPayment"),
    @XmlEnumValue("UnableToAuthenticate")
    UNABLE_TO_AUTHENTICATE("UnableToAuthenticate"),
    @XmlEnumValue("UnknownResponse")
    UNKNOWN_RESPONSE("UnknownResponse"),
    @XmlEnumValue("VendorUnavailable")
    VENDOR_UNAVAILABLE("VendorUnavailable"),
    @XmlEnumValue("Pickup")
    PICKUP("Pickup"),
    @XmlEnumValue("LostStolen")
    LOST_STOLEN("LostStolen"),
    @XmlEnumValue("NoAccount")
    NO_ACCOUNT("NoAccount");
    private final String value;

    TransEntryResultType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransEntryResultType fromValue(String v) {
        for (TransEntryResultType c: TransEntryResultType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
