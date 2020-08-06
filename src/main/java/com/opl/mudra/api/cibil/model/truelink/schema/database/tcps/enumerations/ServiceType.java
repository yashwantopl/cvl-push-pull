
package com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ServiceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ApplicationDataStoring"/>
 *     &lt;enumeration value="ApplicationReporting"/>
 *     &lt;enumeration value="Blacklisting"/>
 *     &lt;enumeration value="CatalogManagement"/>
 *     &lt;enumeration value="Compliance"/>
 *     &lt;enumeration value="CreditMonitoring"/>
 *     &lt;enumeration value="CreditReporting"/>
 *     &lt;enumeration value="CreditScoring"/>
 *     &lt;enumeration value="CSPTLogging"/>
 *     &lt;enumeration value="CSRManagement"/>
 *     &lt;enumeration value="CustomerLookup"/>
 *     &lt;enumeration value="CustomerManagement"/>
 *     &lt;enumeration value="DebtAnalysis"/>
 *     &lt;enumeration value="Emailing"/>
 *     &lt;enumeration value="FulfillmentTracking"/>
 *     &lt;enumeration value="IdentityVerification"/>
 *     &lt;enumeration value="Invoicing"/>
 *     &lt;enumeration value="Marketing"/>
 *     &lt;enumeration value="Ordering"/>
 *     &lt;enumeration value="PaymentProcessing"/>
 *     &lt;enumeration value="SecurityMailer"/>
 *     &lt;enumeration value="SimulatedCreditScoring"/>
 *     &lt;enumeration value="TrendingAnalysis"/>
 *     &lt;enumeration value="Watch"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ServiceType", namespace = "com/truelink/schema/database/tcps/enumerations")
@XmlEnum
public enum ServiceType {

    @XmlEnumValue("ApplicationDataStoring")
    APPLICATION_DATA_STORING("ApplicationDataStoring"),
    @XmlEnumValue("ApplicationReporting")
    APPLICATION_REPORTING("ApplicationReporting"),
    @XmlEnumValue("Blacklisting")
    BLACKLISTING("Blacklisting"),
    @XmlEnumValue("CatalogManagement")
    CATALOG_MANAGEMENT("CatalogManagement"),
    @XmlEnumValue("Compliance")
    COMPLIANCE("Compliance"),
    @XmlEnumValue("CreditMonitoring")
    CREDIT_MONITORING("CreditMonitoring"),
    @XmlEnumValue("CreditReporting")
    CREDIT_REPORTING("CreditReporting"),
    @XmlEnumValue("CreditScoring")
    CREDIT_SCORING("CreditScoring"),
    @XmlEnumValue("CSPTLogging")
    CSPT_LOGGING("CSPTLogging"),
    @XmlEnumValue("CSRManagement")
    CSR_MANAGEMENT("CSRManagement"),
    @XmlEnumValue("CustomerLookup")
    CUSTOMER_LOOKUP("CustomerLookup"),
    @XmlEnumValue("CustomerManagement")
    CUSTOMER_MANAGEMENT("CustomerManagement"),
    @XmlEnumValue("DebtAnalysis")
    DEBT_ANALYSIS("DebtAnalysis"),
    @XmlEnumValue("Emailing")
    EMAILING("Emailing"),
    @XmlEnumValue("FulfillmentTracking")
    FULFILLMENT_TRACKING("FulfillmentTracking"),
    @XmlEnumValue("IdentityVerification")
    IDENTITY_VERIFICATION("IdentityVerification"),
    @XmlEnumValue("Invoicing")
    INVOICING("Invoicing"),
    @XmlEnumValue("Marketing")
    MARKETING("Marketing"),
    @XmlEnumValue("Ordering")
    ORDERING("Ordering"),
    @XmlEnumValue("PaymentProcessing")
    PAYMENT_PROCESSING("PaymentProcessing"),
    @XmlEnumValue("SecurityMailer")
    SECURITY_MAILER("SecurityMailer"),
    @XmlEnumValue("SimulatedCreditScoring")
    SIMULATED_CREDIT_SCORING("SimulatedCreditScoring"),
    @XmlEnumValue("TrendingAnalysis")
    TRENDING_ANALYSIS("TrendingAnalysis"),
    @XmlEnumValue("Watch")
    WATCH("Watch");
    private final String value;

    ServiceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ServiceType fromValue(String v) {
        for (ServiceType c: ServiceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
