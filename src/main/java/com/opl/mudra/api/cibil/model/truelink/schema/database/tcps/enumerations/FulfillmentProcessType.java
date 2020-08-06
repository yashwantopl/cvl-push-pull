
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FulfillmentProcessType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FulfillmentProcessType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AddWatchCustomer"/>
 *     &lt;enumeration value="Fulfill3in1AndCreditScore "/>
 *     &lt;enumeration value="FulfillCreditScore "/>
 *     &lt;enumeration value="FulfillReportTrending"/>
 *     &lt;enumeration value="FulfillScoreTrending"/>
 *     &lt;enumeration value="PullTUCreditReportForTCS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FulfillmentProcessType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum FulfillmentProcessType {

    @XmlEnumValue("AddWatchCustomer")
    ADD_WATCH_CUSTOMER("AddWatchCustomer"),
    @XmlEnumValue("Fulfill3in1AndCreditScore ")
    FULFILL_3_IN_1_AND_CREDIT_SCORE("Fulfill3in1AndCreditScore "),
    @XmlEnumValue("FulfillCreditScore ")
    FULFILL_CREDIT_SCORE("FulfillCreditScore "),
    @XmlEnumValue("FulfillReportTrending")
    FULFILL_REPORT_TRENDING("FulfillReportTrending"),
    @XmlEnumValue("FulfillScoreTrending")
    FULFILL_SCORE_TRENDING("FulfillScoreTrending"),
    @XmlEnumValue("PullTUCreditReportForTCS")
    PULL_TU_CREDIT_REPORT_FOR_TCS("PullTUCreditReportForTCS");
    private final String value;

    FulfillmentProcessType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FulfillmentProcessType fromValue(String v) {
        for (FulfillmentProcessType c: FulfillmentProcessType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
