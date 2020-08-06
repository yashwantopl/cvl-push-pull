
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReflectionStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReflectionStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abandoned"/>
 *     &lt;enumeration value="AcctValidationFailure"/>
 *     &lt;enumeration value="Complete"/>
 *     &lt;enumeration value="NeedPartnerCustCode"/>
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="PartnerCustCodeNotFound"/>
 *     &lt;enumeration value="ReadyToCallbackFailure"/>
 *     &lt;enumeration value="ReadyToCallbackSuccess"/>
 *     &lt;enumeration value="ReadyToProcess"/>
 *     &lt;enumeration value="ReflectionPending"/>
 *     &lt;enumeration value="RefundPending"/>
 *     &lt;enumeration value="ResponsePending"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReflectionStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ReflectionStatusType {

    @XmlEnumValue("Abandoned")
    ABANDONED("Abandoned"),
    @XmlEnumValue("AcctValidationFailure")
    ACCT_VALIDATION_FAILURE("AcctValidationFailure"),
    @XmlEnumValue("Complete")
    COMPLETE("Complete"),
    @XmlEnumValue("NeedPartnerCustCode")
    NEED_PARTNER_CUST_CODE("NeedPartnerCustCode"),
    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("PartnerCustCodeNotFound")
    PARTNER_CUST_CODE_NOT_FOUND("PartnerCustCodeNotFound"),
    @XmlEnumValue("ReadyToCallbackFailure")
    READY_TO_CALLBACK_FAILURE("ReadyToCallbackFailure"),
    @XmlEnumValue("ReadyToCallbackSuccess")
    READY_TO_CALLBACK_SUCCESS("ReadyToCallbackSuccess"),
    @XmlEnumValue("ReadyToProcess")
    READY_TO_PROCESS("ReadyToProcess"),
    @XmlEnumValue("ReflectionPending")
    REFLECTION_PENDING("ReflectionPending"),
    @XmlEnumValue("RefundPending")
    REFUND_PENDING("RefundPending"),
    @XmlEnumValue("ResponsePending")
    RESPONSE_PENDING("ResponsePending");
    private final String value;

    ReflectionStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReflectionStatusType fromValue(String v) {
        for (ReflectionStatusType c: ReflectionStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
