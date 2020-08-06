
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ScoreModelType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ScoreModelType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Transrisk"/>
 *     &lt;enumeration value="Vantage"/>
 *     &lt;enumeration value="DSTransRisk"/>
 *     &lt;enumeration value="DSVantage"/>
 *     &lt;enumeration value="AutoInsurance"/>
 *     &lt;enumeration value="PropertyInsurance"/>
 *     &lt;enumeration value="CVAccountManagement"/>
 *     &lt;enumeration value="DSCVAccountManagement"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ScoreModelType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ScoreModelType {

    @XmlEnumValue("Transrisk")
    TRANSRISK("Transrisk"),
    @XmlEnumValue("Vantage")
    VANTAGE("Vantage"),
    @XmlEnumValue("DSTransRisk")
    DS_TRANS_RISK("DSTransRisk"),
    @XmlEnumValue("DSVantage")
    DS_VANTAGE("DSVantage"),
    @XmlEnumValue("AutoInsurance")
    AUTO_INSURANCE("AutoInsurance"),
    @XmlEnumValue("PropertyInsurance")
    PROPERTY_INSURANCE("PropertyInsurance"),
    @XmlEnumValue("CVAccountManagement")
    CV_ACCOUNT_MANAGEMENT("CVAccountManagement"),
    @XmlEnumValue("DSCVAccountManagement")
    DSCV_ACCOUNT_MANAGEMENT("DSCVAccountManagement");
    private final String value;

    ScoreModelType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ScoreModelType fromValue(String v) {
        for (ScoreModelType c: ScoreModelType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
