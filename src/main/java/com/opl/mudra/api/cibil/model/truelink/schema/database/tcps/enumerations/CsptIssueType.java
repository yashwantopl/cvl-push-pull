
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CsptIssueType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CsptIssueType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AccountStatus"/>
 *     &lt;enumeration value="ActivationCode"/>
 *     &lt;enumeration value="Authentication"/>
 *     &lt;enumeration value="Billing"/>
 *     &lt;enumeration value="Cancel"/>
 *     &lt;enumeration value="CustomerFeedback"/>
 *     &lt;enumeration value="Dispute"/>
 *     &lt;enumeration value="Enrollment"/>
 *     &lt;enumeration value="ExceptionLetter"/>
 *     &lt;enumeration value="FraudulentActivity"/>
 *     &lt;enumeration value="Fulfillment"/>
 *     &lt;enumeration value="Fullerton"/>
 *     &lt;enumeration value="History"/>
 *     &lt;enumeration value="Legal"/>
 *     &lt;enumeration value="Log"/>
 *     &lt;enumeration value="Login"/>
 *     &lt;enumeration value="Navigation"/>
 *     &lt;enumeration value="OrderProcess"/>
 *     &lt;enumeration value="ProductInformation"/>
 *     &lt;enumeration value="ProductMode"/>
 *     &lt;enumeration value="ProgramDescription"/>
 *     &lt;enumeration value="Refund"/>
 *     &lt;enumeration value="Retention"/>
 *     &lt;enumeration value="SystemError"/>
 *     &lt;enumeration value="Transfer"/>
 *     &lt;enumeration value="UserConfiguration"/>
 *     &lt;enumeration value="WrongNumber"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CsptIssueType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CsptIssueType {

    @XmlEnumValue("AccountStatus")
    ACCOUNT_STATUS("AccountStatus"),
    @XmlEnumValue("ActivationCode")
    ACTIVATION_CODE("ActivationCode"),
    @XmlEnumValue("Authentication")
    AUTHENTICATION("Authentication"),
    @XmlEnumValue("Billing")
    BILLING("Billing"),
    @XmlEnumValue("Cancel")
    CANCEL("Cancel"),
    @XmlEnumValue("CustomerFeedback")
    CUSTOMER_FEEDBACK("CustomerFeedback"),
    @XmlEnumValue("Dispute")
    DISPUTE("Dispute"),
    @XmlEnumValue("Enrollment")
    ENROLLMENT("Enrollment"),
    @XmlEnumValue("ExceptionLetter")
    EXCEPTION_LETTER("ExceptionLetter"),
    @XmlEnumValue("FraudulentActivity")
    FRAUDULENT_ACTIVITY("FraudulentActivity"),
    @XmlEnumValue("Fulfillment")
    FULFILLMENT("Fulfillment"),
    @XmlEnumValue("Fullerton")
    FULLERTON("Fullerton"),
    @XmlEnumValue("History")
    HISTORY("History"),
    @XmlEnumValue("Legal")
    LEGAL("Legal"),
    @XmlEnumValue("Log")
    LOG("Log"),
    @XmlEnumValue("Login")
    LOGIN("Login"),
    @XmlEnumValue("Navigation")
    NAVIGATION("Navigation"),
    @XmlEnumValue("OrderProcess")
    ORDER_PROCESS("OrderProcess"),
    @XmlEnumValue("ProductInformation")
    PRODUCT_INFORMATION("ProductInformation"),
    @XmlEnumValue("ProductMode")
    PRODUCT_MODE("ProductMode"),
    @XmlEnumValue("ProgramDescription")
    PROGRAM_DESCRIPTION("ProgramDescription"),
    @XmlEnumValue("Refund")
    REFUND("Refund"),
    @XmlEnumValue("Retention")
    RETENTION("Retention"),
    @XmlEnumValue("SystemError")
    SYSTEM_ERROR("SystemError"),
    @XmlEnumValue("Transfer")
    TRANSFER("Transfer"),
    @XmlEnumValue("UserConfiguration")
    USER_CONFIGURATION("UserConfiguration"),
    @XmlEnumValue("WrongNumber")
    WRONG_NUMBER("WrongNumber");
    private final String value;

    CsptIssueType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CsptIssueType fromValue(String v) {
        for (CsptIssueType c: CsptIssueType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
