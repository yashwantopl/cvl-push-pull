
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LockReasonType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LockReasonType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AddressMismatch"/>
 *     &lt;enumeration value="AgeRestriction"/>
 *     &lt;enumeration value="Blacklist"/>
 *     &lt;enumeration value="CoreFailure"/>
 *     &lt;enumeration value="Deceased"/>
 *     &lt;enumeration value="EquifaxFraud"/>
 *     &lt;enumeration value="ExperianFraud"/>
 *     &lt;enumeration value="Identification"/>
 *     &lt;enumeration value="Login"/>
 *     &lt;enumeration value="Manual"/>
 *     &lt;enumeration value="OfflineToOnline"/>
 *     &lt;enumeration value="ReturnedMail"/>
 *     &lt;enumeration value="TransUnionFraud"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LockReasonType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum LockReasonType {

    @XmlEnumValue("AddressMismatch")
    ADDRESS_MISMATCH("AddressMismatch"),
    @XmlEnumValue("AgeRestriction")
    AGE_RESTRICTION("AgeRestriction"),
    @XmlEnumValue("Blacklist")
    BLACKLIST("Blacklist"),
    @XmlEnumValue("CoreFailure")
    CORE_FAILURE("CoreFailure"),
    @XmlEnumValue("Deceased")
    DECEASED("Deceased"),
    @XmlEnumValue("EquifaxFraud")
    EQUIFAX_FRAUD("EquifaxFraud"),
    @XmlEnumValue("ExperianFraud")
    EXPERIAN_FRAUD("ExperianFraud"),
    @XmlEnumValue("Identification")
    IDENTIFICATION("Identification"),
    @XmlEnumValue("Login")
    LOGIN("Login"),
    @XmlEnumValue("Manual")
    MANUAL("Manual"),
    @XmlEnumValue("OfflineToOnline")
    OFFLINE_TO_ONLINE("OfflineToOnline"),
    @XmlEnumValue("ReturnedMail")
    RETURNED_MAIL("ReturnedMail"),
    @XmlEnumValue("TransUnionFraud")
    TRANS_UNION_FRAUD("TransUnionFraud");
    private final String value;

    LockReasonType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LockReasonType fromValue(String v) {
        for (LockReasonType c: LockReasonType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
