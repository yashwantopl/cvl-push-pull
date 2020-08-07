
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MatchStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MatchStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CreditReportDOBMatch"/>
 *     &lt;enumeration value="CustomerDOBMatch"/>
 *     &lt;enumeration value="NoDOBMatch"/>
 *     &lt;enumeration value="NoSecretQuestionMatch"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="SecretQuestionMatch"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MatchStatusType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum MatchStatusType {

    @XmlEnumValue("CreditReportDOBMatch")
    CREDIT_REPORT_DOB_MATCH("CreditReportDOBMatch"),
    @XmlEnumValue("CustomerDOBMatch")
    CUSTOMER_DOB_MATCH("CustomerDOBMatch"),
    @XmlEnumValue("NoDOBMatch")
    NO_DOB_MATCH("NoDOBMatch"),
    @XmlEnumValue("NoSecretQuestionMatch")
    NO_SECRET_QUESTION_MATCH("NoSecretQuestionMatch"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("SecretQuestionMatch")
    SECRET_QUESTION_MATCH("SecretQuestionMatch");
    private final String value;

    MatchStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MatchStatusType fromValue(String v) {
        for (MatchStatusType c: MatchStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
