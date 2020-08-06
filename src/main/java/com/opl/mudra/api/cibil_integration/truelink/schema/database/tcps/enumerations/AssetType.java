
package com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssetType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AssetType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AutoInsuranceScoreFile"/>
 *     &lt;enumeration value="AutoInsuranceScoreReport"/>
 *     &lt;enumeration value="CXProducts"/>
 *     &lt;enumeration value="CXScoreSimulator"/>
 *     &lt;enumeration value="CreditMonitoring"/>
 *     &lt;enumeration value="EquifaxCreditFile"/>
 *     &lt;enumeration value="EquifaxTRScoreFile"/>
 *     &lt;enumeration value="EquifaxVantageScoreFile"/>
 *     &lt;enumeration value="ExperianCreditFile"/>
 *     &lt;enumeration value="ExperianTRScoreFile"/>
 *     &lt;enumeration value="ExperianVantageScoreFile"/>
 *     &lt;enumeration value="HomeInsuranceScoreFile"/>
 *     &lt;enumeration value="HomeInsuranceScoreReport"/>
 *     &lt;enumeration value="ScoreTracker"/>
 *     &lt;enumeration value="SingleCXScoreReport"/>
 *     &lt;enumeration value="SingleCreditReport"/>
 *     &lt;enumeration value="SingleHighRiskFraudAlerts"/>
 *     &lt;enumeration value="SingleMSBScoreReport"/>
 *     &lt;enumeration value="SingleTransRiskScoreReport"/>
 *     &lt;enumeration value="SingleVantageScoreReport"/>
 *     &lt;enumeration value="ThreeBureauCXScoreFile"/>
 *     &lt;enumeration value="ThreeBureauCXScoreReport"/>
 *     &lt;enumeration value="ThreeBureauCXScoreSimulator"/>
 *     &lt;enumeration value="ThreeBureauCreditReport"/>
 *     &lt;enumeration value="ThreeBureauHighRiskFraudAlerts"/>
 *     &lt;enumeration value="ThreeBureauTransRiskScoreReport"/>
 *     &lt;enumeration value="ThreeBureauVantageScoreReport"/>
 *     &lt;enumeration value="TransRiskScoreSimulator"/>
 *     &lt;enumeration value="TransUnionCXScoreFile"/>
 *     &lt;enumeration value="TransUnionCreditFile"/>
 *     &lt;enumeration value="TransUnionMSBScoreFile"/>
 *     &lt;enumeration value="TransUnionTRScoreFile"/>
 *     &lt;enumeration value="TransUnionVantageScoreFile"/>
 *     &lt;enumeration value="TrendPoint"/>
 *     &lt;enumeration value="TwoBureauTuEqfCreditReport"/>
 *     &lt;enumeration value="TwoBureauTuEqfTransRiskScoreReport"/>
 *     &lt;enumeration value="TwoBureauTuExpCreditReport"/>
 *     &lt;enumeration value="TwoBureauTuExpTransRiskScoreReport"/>
 *     &lt;enumeration value="TwoEBureauCXScoreFile"/>
 *     &lt;enumeration value="TwoEBureauCXScoreReport"/>
 *     &lt;enumeration value="TwoEBureauCXScoreSimulator"/>
 *     &lt;enumeration value="TwoEBureauCreditReport"/>
 *     &lt;enumeration value="TwoEBureauTransRiskScoreReport"/>
 *     &lt;enumeration value="IdentityMonitoring"/>
 *     &lt;enumeration value="CreditLock"/>
 *     &lt;enumeration value="AccountManagementScoreReport"/>
 *     &lt;enumeration value="AccountManagementScoreFile"/>
 *     &lt;enumeration value="CVAccountManagementScoreFile"/>
 *     &lt;enumeration value="CVAccountManagementScoreReport"/>
 *     &lt;enumeration value="CreditVisionScoreSimulator"/>
 *     &lt;enumeration value="SingleCibilScoreReport"/>
 *     &lt;enumeration value="CibilScoreFile"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AssetType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum AssetType {

    @XmlEnumValue("AutoInsuranceScoreFile")
    AUTO_INSURANCE_SCORE_FILE("AutoInsuranceScoreFile"),
    @XmlEnumValue("AutoInsuranceScoreReport")
    AUTO_INSURANCE_SCORE_REPORT("AutoInsuranceScoreReport"),
    @XmlEnumValue("CXProducts")
    CX_PRODUCTS("CXProducts"),
    @XmlEnumValue("CXScoreSimulator")
    CX_SCORE_SIMULATOR("CXScoreSimulator"),
    @XmlEnumValue("CreditMonitoring")
    CREDIT_MONITORING("CreditMonitoring"),
    @XmlEnumValue("EquifaxCreditFile")
    EQUIFAX_CREDIT_FILE("EquifaxCreditFile"),
    @XmlEnumValue("EquifaxTRScoreFile")
    EQUIFAX_TR_SCORE_FILE("EquifaxTRScoreFile"),
    @XmlEnumValue("EquifaxVantageScoreFile")
    EQUIFAX_VANTAGE_SCORE_FILE("EquifaxVantageScoreFile"),
    @XmlEnumValue("ExperianCreditFile")
    EXPERIAN_CREDIT_FILE("ExperianCreditFile"),
    @XmlEnumValue("ExperianTRScoreFile")
    EXPERIAN_TR_SCORE_FILE("ExperianTRScoreFile"),
    @XmlEnumValue("ExperianVantageScoreFile")
    EXPERIAN_VANTAGE_SCORE_FILE("ExperianVantageScoreFile"),
    @XmlEnumValue("HomeInsuranceScoreFile")
    HOME_INSURANCE_SCORE_FILE("HomeInsuranceScoreFile"),
    @XmlEnumValue("HomeInsuranceScoreReport")
    HOME_INSURANCE_SCORE_REPORT("HomeInsuranceScoreReport"),
    @XmlEnumValue("ScoreTracker")
    SCORE_TRACKER("ScoreTracker"),
    @XmlEnumValue("SingleCXScoreReport")
    SINGLE_CX_SCORE_REPORT("SingleCXScoreReport"),
    @XmlEnumValue("SingleCreditReport")
    SINGLE_CREDIT_REPORT("SingleCreditReport"),
    @XmlEnumValue("SingleHighRiskFraudAlerts")
    SINGLE_HIGH_RISK_FRAUD_ALERTS("SingleHighRiskFraudAlerts"),
    @XmlEnumValue("SingleMSBScoreReport")
    SINGLE_MSB_SCORE_REPORT("SingleMSBScoreReport"),
    @XmlEnumValue("SingleTransRiskScoreReport")
    SINGLE_TRANS_RISK_SCORE_REPORT("SingleTransRiskScoreReport"),
    @XmlEnumValue("SingleVantageScoreReport")
    SINGLE_VANTAGE_SCORE_REPORT("SingleVantageScoreReport"),
    @XmlEnumValue("ThreeBureauCXScoreFile")
    THREE_BUREAU_CX_SCORE_FILE("ThreeBureauCXScoreFile"),
    @XmlEnumValue("ThreeBureauCXScoreReport")
    THREE_BUREAU_CX_SCORE_REPORT("ThreeBureauCXScoreReport"),
    @XmlEnumValue("ThreeBureauCXScoreSimulator")
    THREE_BUREAU_CX_SCORE_SIMULATOR("ThreeBureauCXScoreSimulator"),
    @XmlEnumValue("ThreeBureauCreditReport")
    THREE_BUREAU_CREDIT_REPORT("ThreeBureauCreditReport"),
    @XmlEnumValue("ThreeBureauHighRiskFraudAlerts")
    THREE_BUREAU_HIGH_RISK_FRAUD_ALERTS("ThreeBureauHighRiskFraudAlerts"),
    @XmlEnumValue("ThreeBureauTransRiskScoreReport")
    THREE_BUREAU_TRANS_RISK_SCORE_REPORT("ThreeBureauTransRiskScoreReport"),
    @XmlEnumValue("ThreeBureauVantageScoreReport")
    THREE_BUREAU_VANTAGE_SCORE_REPORT("ThreeBureauVantageScoreReport"),
    @XmlEnumValue("TransRiskScoreSimulator")
    TRANS_RISK_SCORE_SIMULATOR("TransRiskScoreSimulator"),
    @XmlEnumValue("TransUnionCXScoreFile")
    TRANS_UNION_CX_SCORE_FILE("TransUnionCXScoreFile"),
    @XmlEnumValue("TransUnionCreditFile")
    TRANS_UNION_CREDIT_FILE("TransUnionCreditFile"),
    @XmlEnumValue("TransUnionMSBScoreFile")
    TRANS_UNION_MSB_SCORE_FILE("TransUnionMSBScoreFile"),
    @XmlEnumValue("TransUnionTRScoreFile")
    TRANS_UNION_TR_SCORE_FILE("TransUnionTRScoreFile"),
    @XmlEnumValue("TransUnionVantageScoreFile")
    TRANS_UNION_VANTAGE_SCORE_FILE("TransUnionVantageScoreFile"),
    @XmlEnumValue("TrendPoint")
    TREND_POINT("TrendPoint"),
    @XmlEnumValue("TwoBureauTuEqfCreditReport")
    TWO_BUREAU_TU_EQF_CREDIT_REPORT("TwoBureauTuEqfCreditReport"),
    @XmlEnumValue("TwoBureauTuEqfTransRiskScoreReport")
    TWO_BUREAU_TU_EQF_TRANS_RISK_SCORE_REPORT("TwoBureauTuEqfTransRiskScoreReport"),
    @XmlEnumValue("TwoBureauTuExpCreditReport")
    TWO_BUREAU_TU_EXP_CREDIT_REPORT("TwoBureauTuExpCreditReport"),
    @XmlEnumValue("TwoBureauTuExpTransRiskScoreReport")
    TWO_BUREAU_TU_EXP_TRANS_RISK_SCORE_REPORT("TwoBureauTuExpTransRiskScoreReport"),
    @XmlEnumValue("TwoEBureauCXScoreFile")
    TWO_E_BUREAU_CX_SCORE_FILE("TwoEBureauCXScoreFile"),
    @XmlEnumValue("TwoEBureauCXScoreReport")
    TWO_E_BUREAU_CX_SCORE_REPORT("TwoEBureauCXScoreReport"),
    @XmlEnumValue("TwoEBureauCXScoreSimulator")
    TWO_E_BUREAU_CX_SCORE_SIMULATOR("TwoEBureauCXScoreSimulator"),
    @XmlEnumValue("TwoEBureauCreditReport")
    TWO_E_BUREAU_CREDIT_REPORT("TwoEBureauCreditReport"),
    @XmlEnumValue("TwoEBureauTransRiskScoreReport")
    TWO_E_BUREAU_TRANS_RISK_SCORE_REPORT("TwoEBureauTransRiskScoreReport"),
    @XmlEnumValue("IdentityMonitoring")
    IDENTITY_MONITORING("IdentityMonitoring"),
    @XmlEnumValue("CreditLock")
    CREDIT_LOCK("CreditLock"),
    @XmlEnumValue("AccountManagementScoreReport")
    ACCOUNT_MANAGEMENT_SCORE_REPORT("AccountManagementScoreReport"),
    @XmlEnumValue("AccountManagementScoreFile")
    ACCOUNT_MANAGEMENT_SCORE_FILE("AccountManagementScoreFile"),
    @XmlEnumValue("CVAccountManagementScoreFile")
    CV_ACCOUNT_MANAGEMENT_SCORE_FILE("CVAccountManagementScoreFile"),
    @XmlEnumValue("CVAccountManagementScoreReport")
    CV_ACCOUNT_MANAGEMENT_SCORE_REPORT("CVAccountManagementScoreReport"),
    @XmlEnumValue("CreditVisionScoreSimulator")
    CREDIT_VISION_SCORE_SIMULATOR("CreditVisionScoreSimulator"),
    @XmlEnumValue("SingleCibilScoreReport")
    SINGLE_CIBIL_SCORE_REPORT("SingleCibilScoreReport"),
    @XmlEnumValue("CibilScoreFile")
    CIBIL_SCORE_FILE("CibilScoreFile");
    private final String value;

    AssetType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AssetType fromValue(String v) {
        for (AssetType c: AssetType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
