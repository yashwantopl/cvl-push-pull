
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CustomerStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ActiveEnrollment"/>
 *     &lt;enumeration value="CreateFailure"/>
 *     &lt;enumeration value="DOBUpdateRequired"/>
 *     &lt;enumeration value="DoubleAcceptRequired"/>
 *     &lt;enumeration value="IVPullRequired"/>
 *     &lt;enumeration value="IdentityVerificationRequired"/>
 *     &lt;enumeration value="Locked"/>
 *     &lt;enumeration value="LoginUpdateRequired"/>
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="NoActiveEnrollment"/>
 *     &lt;enumeration value="PINVerificationPending"/>
 *     &lt;enumeration value="PINVerificationRequired"/>
 *     &lt;enumeration value="PasswordUpdateRequired"/>
 *     &lt;enumeration value="PendingEnrollment"/>
 *     &lt;enumeration value="SecretQuestionDOBUpdateRequired"/>
 *     &lt;enumeration value="SecretQuestionUpdateRequired"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CustomerStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum CustomerStatusType {

    @XmlEnumValue("ActiveEnrollment")
    ACTIVE_ENROLLMENT("ActiveEnrollment"),
    @XmlEnumValue("CreateFailure")
    CREATE_FAILURE("CreateFailure"),
    @XmlEnumValue("DOBUpdateRequired")
    DOB_UPDATE_REQUIRED("DOBUpdateRequired"),
    @XmlEnumValue("DoubleAcceptRequired")
    DOUBLE_ACCEPT_REQUIRED("DoubleAcceptRequired"),
    @XmlEnumValue("IVPullRequired")
    IV_PULL_REQUIRED("IVPullRequired"),
    @XmlEnumValue("IdentityVerificationRequired")
    IDENTITY_VERIFICATION_REQUIRED("IdentityVerificationRequired"),
    @XmlEnumValue("Locked")
    LOCKED("Locked"),
    @XmlEnumValue("LoginUpdateRequired")
    LOGIN_UPDATE_REQUIRED("LoginUpdateRequired"),
    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("NoActiveEnrollment")
    NO_ACTIVE_ENROLLMENT("NoActiveEnrollment"),
    @XmlEnumValue("PINVerificationPending")
    PIN_VERIFICATION_PENDING("PINVerificationPending"),
    @XmlEnumValue("PINVerificationRequired")
    PIN_VERIFICATION_REQUIRED("PINVerificationRequired"),
    @XmlEnumValue("PasswordUpdateRequired")
    PASSWORD_UPDATE_REQUIRED("PasswordUpdateRequired"),
    @XmlEnumValue("PendingEnrollment")
    PENDING_ENROLLMENT("PendingEnrollment"),
    @XmlEnumValue("SecretQuestionDOBUpdateRequired")
    SECRET_QUESTION_DOB_UPDATE_REQUIRED("SecretQuestionDOBUpdateRequired"),
    @XmlEnumValue("SecretQuestionUpdateRequired")
    SECRET_QUESTION_UPDATE_REQUIRED("SecretQuestionUpdateRequired");
    private final String value;

    CustomerStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CustomerStatusType fromValue(String v) {
        for (CustomerStatusType c: CustomerStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
