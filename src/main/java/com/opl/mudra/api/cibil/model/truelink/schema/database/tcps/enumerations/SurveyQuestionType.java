
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SurveyQuestionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SurveyQuestionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HowHearAbout"/>
 *     &lt;enumeration value="PurchaseTimeframe"/>
 *     &lt;enumeration value="WhyCheckingCredit"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SurveyQuestionType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum SurveyQuestionType {

    @XmlEnumValue("HowHearAbout")
    HOW_HEAR_ABOUT("HowHearAbout"),
    @XmlEnumValue("PurchaseTimeframe")
    PURCHASE_TIMEFRAME("PurchaseTimeframe"),
    @XmlEnumValue("WhyCheckingCredit")
    WHY_CHECKING_CREDIT("WhyCheckingCredit");
    private final String value;

    SurveyQuestionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SurveyQuestionType fromValue(String v) {
        for (SurveyQuestionType c: SurveyQuestionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
