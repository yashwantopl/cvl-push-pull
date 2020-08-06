
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.BTXTargetAppEnum;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.BatchStep;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.BatchStepFileFromVendor;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.BatchStepFileToVendor;


/**
 * <p>Java class for BatchProcessRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchProcessRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MSPApplication" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MSPCore" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ProcessMSPTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="ScheduledBilling" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="ScheduledFulfillments" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="ScheduledFailureReviews" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="ScheduledAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="DailyAlerts" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="OfflineIVUtiity" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="ProcessReflectionTransactions" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="ScheduledPaymentechAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ProcessAccountUpdaterToPaymentech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MSPPayment" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="FromPaymenTech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromACH" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="ToPaymenTech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToACH" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToFromConcord" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="PaymenTechResponseWaitPeriod" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="ACHResponseWaitPeriod" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MSPPrintAndMail" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="FromMetroGroup" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromLazon" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="ToMetroGroup" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToLazon" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="RetryToMetroGroup" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="RetryToLazon" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MSPChaseDataExchange" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="FromChaseTPE" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromChaseRetailTPE" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromChaseOBTMTPE" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromChasePrimaryUser" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromChaseCCAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromChaseRetailAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromChaseCCLeadFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromChaseRetailSSNValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromVertrueCancel" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromLifeLockTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromTUCRSTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromOneTechCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromActivationCodeCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromEuropAssistanceCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromCPPCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="FromTUAutoPartner1Transaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="ToChaseTPEFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToChaseTPERetailFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToChaseRetailOfflineCheck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToChasePrimaryUser" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToChaseCCAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToChaseRetailAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToChaseCCLeadDispositions" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToChaseRetailSSNValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToChaseCCMemberHistory" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToChaseRetailMemberHistory" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToVertrueCancelConfirmation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToTUCRSTransactionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToLifeLockTransactionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ProcessConsolidationRequests" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="ToOneTechCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToOneTechDailyAlertsFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToOneTechFailedIVFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToActivationCodeCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToEuropAssistanceCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToCPPCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToTUAutoPartner1TransactionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="FromCESCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="ToCESCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToCESDailyAlertsFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToCESFailedIVFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToVertruePartnerNotification" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="ProcessAccountUpdaterToPaymentech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MSPEmail" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SendEmail" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MSPBatchManager" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="BTXClean" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MSPMigration" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="GenerateEnrollments" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="GenerateCustomerUpdates" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="ToAlertMigrationService" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="FromAlertMigrationService" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MSPTxGenerator" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="GenerateTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStep"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MSPHealthCheck" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="DataIntegrityAssessments" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                             &lt;element name="HealthCheckPings" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MSPAGDataExchange" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="FromAffinion_1Band3BMonitoringSubscription" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="ToAffinion_1Band3BMonitoringSubscriptionAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToAffinion_1Band3BMonitoringSubscriptionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="Affinion1Band3BMonitoringHits" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="Affinion1BOnlineMonitoringAlerts" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="Affinion3BOnlineMonitoringAlerts" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="FromAffinion_CreditReport" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="ToAffinion_CreditReportFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToAffinion_CreditReportAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="FromAffinion_ScoreTrackerSubscription" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="ToAffinion_ScoreTrackerSubscriptionAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToAffinion_ScoreTrackerSubscriptionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="FromAffinion_CreditAssistSubscription" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
 *                             &lt;element name="ToAffinion_CreditAssistSubscriptionAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                             &lt;element name="ToAffinion_CXProducts" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="BatchProcess" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="TargetApp" use="required" type="{com/truelink/schema/database/tcps/enumerations}BTXTargetAppEnum" />
 *                 &lt;attribute name="SiteName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ProcessType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ConcurrentProcess" type="{com/truelink/schema/msp}ConcurrentProcessType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="FromDateAndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="ToDateAndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="RecordsPerDBFetch" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchProcessRequestType", propOrder = {
    "mspApplication",
    "processType",
    "concurrentProcess"
})
public class BatchProcessRequestType {

    @XmlElement(name = "MSPApplication")
    protected BatchProcessRequestType.MSPApplication mspApplication;
    @XmlElement(name = "ProcessType", required = true)
    protected String processType;
    @XmlElement(name = "ConcurrentProcess")
    protected ConcurrentProcessType concurrentProcess;
    @XmlAttribute(name = "FromDateAndTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fromDateAndTime;
    @XmlAttribute(name = "ToDateAndTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar toDateAndTime;
    @XmlAttribute(name = "RecordsPerDBFetch", required = true)
    protected int recordsPerDBFetch;

    /**
     * Gets the value of the mspApplication property.
     *
     * @return
     *     possible object is
     *     {@link BatchProcessRequestType.MSPApplication }
     *
     */
    public BatchProcessRequestType.MSPApplication getMSPApplication() {
        return mspApplication;
    }

    /**
     * Sets the value of the mspApplication property.
     *
     * @param value
     *     allowed object is
     *     {@link BatchProcessRequestType.MSPApplication }
     *
     */
    public void setMSPApplication(BatchProcessRequestType.MSPApplication value) {
        this.mspApplication = value;
    }

    /**
     * Gets the value of the processType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * Sets the value of the processType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProcessType(String value) {
        this.processType = value;
    }

    /**
     * Gets the value of the concurrentProcess property.
     *
     * @return
     *     possible object is
     *     {@link ConcurrentProcessType }
     *
     */
    public ConcurrentProcessType getConcurrentProcess() {
        return concurrentProcess;
    }

    /**
     * Sets the value of the concurrentProcess property.
     *
     * @param value
     *     allowed object is
     *     {@link ConcurrentProcessType }
     *
     */
    public void setConcurrentProcess(ConcurrentProcessType value) {
        this.concurrentProcess = value;
    }

    /**
     * Gets the value of the fromDateAndTime property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFromDateAndTime() {
        return fromDateAndTime;
    }

    /**
     * Sets the value of the fromDateAndTime property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setFromDateAndTime(XMLGregorianCalendar value) {
        this.fromDateAndTime = value;
    }

    /**
     * Gets the value of the toDateAndTime property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getToDateAndTime() {
        return toDateAndTime;
    }

    /**
     * Sets the value of the toDateAndTime property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setToDateAndTime(XMLGregorianCalendar value) {
        this.toDateAndTime = value;
    }

    /**
     * Gets the value of the recordsPerDBFetch property.
     *
     */
    public int getRecordsPerDBFetch() {
        return recordsPerDBFetch;
    }

    /**
     * Sets the value of the recordsPerDBFetch property.
     *
     */
    public void setRecordsPerDBFetch(int value) {
        this.recordsPerDBFetch = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="MSPCore" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ProcessMSPTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="ScheduledBilling" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="ScheduledFulfillments" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="ScheduledFailureReviews" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="ScheduledAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="DailyAlerts" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="OfflineIVUtiity" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="ProcessReflectionTransactions" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="ScheduledPaymentechAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ProcessAccountUpdaterToPaymentech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MSPPayment" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="FromPaymenTech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromACH" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="ToPaymenTech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToACH" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToFromConcord" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="PaymenTechResponseWaitPeriod" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="ACHResponseWaitPeriod" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MSPPrintAndMail" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="FromMetroGroup" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromLazon" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="ToMetroGroup" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToLazon" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="RetryToMetroGroup" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="RetryToLazon" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MSPChaseDataExchange" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="FromChaseTPE" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromChaseRetailTPE" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromChaseOBTMTPE" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromChasePrimaryUser" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromChaseCCAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromChaseRetailAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromChaseCCLeadFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromChaseRetailSSNValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromVertrueCancel" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromLifeLockTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromTUCRSTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromOneTechCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromActivationCodeCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromEuropAssistanceCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromCPPCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="FromTUAutoPartner1Transaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="ToChaseTPEFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToChaseTPERetailFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToChaseRetailOfflineCheck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToChasePrimaryUser" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToChaseCCAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToChaseRetailAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToChaseCCLeadDispositions" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToChaseRetailSSNValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToChaseCCMemberHistory" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToChaseRetailMemberHistory" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToVertrueCancelConfirmation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToTUCRSTransactionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToLifeLockTransactionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ProcessConsolidationRequests" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="ToOneTechCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToOneTechDailyAlertsFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToOneTechFailedIVFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToActivationCodeCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToEuropAssistanceCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToCPPCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToTUAutoPartner1TransactionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="FromCESCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="ToCESCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToCESDailyAlertsFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToCESFailedIVFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToVertruePartnerNotification" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="ProcessAccountUpdaterToPaymentech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MSPEmail" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="SendEmail" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MSPBatchManager" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="BTXClean" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MSPMigration" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="GenerateEnrollments" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="GenerateCustomerUpdates" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="ToAlertMigrationService" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="FromAlertMigrationService" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MSPTxGenerator" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="GenerateTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStep"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MSPHealthCheck" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="DataIntegrityAssessments" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                   &lt;element name="HealthCheckPings" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MSPAGDataExchange" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="FromAffinion_1Band3BMonitoringSubscription" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="ToAffinion_1Band3BMonitoringSubscriptionAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToAffinion_1Band3BMonitoringSubscriptionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="Affinion1Band3BMonitoringHits" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="Affinion1BOnlineMonitoringAlerts" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="Affinion3BOnlineMonitoringAlerts" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="FromAffinion_CreditReport" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="ToAffinion_CreditReportFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToAffinion_CreditReportAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="FromAffinion_ScoreTrackerSubscription" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="ToAffinion_ScoreTrackerSubscriptionAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToAffinion_ScoreTrackerSubscriptionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="FromAffinion_CreditAssistSubscription" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
     *                   &lt;element name="ToAffinion_CreditAssistSubscriptionAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                   &lt;element name="ToAffinion_CXProducts" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="BatchProcess" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="TargetApp" use="required" type="{com/truelink/schema/database/tcps/enumerations}BTXTargetAppEnum" />
     *       &lt;attribute name="SiteName" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "mspCore",
        "mspPayment",
        "mspPrintAndMail",
        "mspChaseDataExchange",
        "mspEmail",
        "mspBatchManager",
        "mspMigration",
        "mspTxGenerator",
        "mspHealthCheck",
        "mspagDataExchange"
    })
    public static class MSPApplication {

        @XmlElement(name = "MSPCore")
        protected BatchProcessRequestType.MSPApplication.MSPCore mspCore;
        @XmlElement(name = "MSPPayment")
        protected BatchProcessRequestType.MSPApplication.MSPPayment mspPayment;
        @XmlElement(name = "MSPPrintAndMail")
        protected BatchProcessRequestType.MSPApplication.MSPPrintAndMail mspPrintAndMail;
        @XmlElement(name = "MSPChaseDataExchange")
        protected BatchProcessRequestType.MSPApplication.MSPChaseDataExchange mspChaseDataExchange;
        @XmlElement(name = "MSPEmail")
        protected BatchProcessRequestType.MSPApplication.MSPEmail mspEmail;
        @XmlElement(name = "MSPBatchManager")
        protected BatchProcessRequestType.MSPApplication.MSPBatchManager mspBatchManager;
        @XmlElement(name = "MSPMigration")
        protected BatchProcessRequestType.MSPApplication.MSPMigration mspMigration;
        @XmlElement(name = "MSPTxGenerator")
        protected BatchProcessRequestType.MSPApplication.MSPTxGenerator mspTxGenerator;
        @XmlElement(name = "MSPHealthCheck")
        protected BatchProcessRequestType.MSPApplication.MSPHealthCheck mspHealthCheck;
        @XmlElement(name = "MSPAGDataExchange")
        protected BatchProcessRequestType.MSPApplication.MSPAGDataExchange mspagDataExchange;
        @XmlAttribute(name = "TargetApp", required = true)
        protected BTXTargetAppEnum targetApp;
        @XmlAttribute(name = "SiteName")
        protected String siteName;

        /**
         * Gets the value of the mspCore property.
         *
         * @return
         *     possible object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPCore }
         *
         */
        public BatchProcessRequestType.MSPApplication.MSPCore getMSPCore() {
            return mspCore;
        }

        /**
         * Sets the value of the mspCore property.
         *
         * @param value
         *     allowed object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPCore }
         *
         */
        public void setMSPCore(BatchProcessRequestType.MSPApplication.MSPCore value) {
            this.mspCore = value;
        }

        /**
         * Gets the value of the mspPayment property.
         *
         * @return
         *     possible object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPPayment }
         *
         */
        public BatchProcessRequestType.MSPApplication.MSPPayment getMSPPayment() {
            return mspPayment;
        }

        /**
         * Sets the value of the mspPayment property.
         *
         * @param value
         *     allowed object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPPayment }
         *
         */
        public void setMSPPayment(BatchProcessRequestType.MSPApplication.MSPPayment value) {
            this.mspPayment = value;
        }

        /**
         * Gets the value of the mspPrintAndMail property.
         *
         * @return
         *     possible object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPPrintAndMail }
         *
         */
        public BatchProcessRequestType.MSPApplication.MSPPrintAndMail getMSPPrintAndMail() {
            return mspPrintAndMail;
        }

        /**
         * Sets the value of the mspPrintAndMail property.
         *
         * @param value
         *     allowed object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPPrintAndMail }
         *
         */
        public void setMSPPrintAndMail(BatchProcessRequestType.MSPApplication.MSPPrintAndMail value) {
            this.mspPrintAndMail = value;
        }

        /**
         * Gets the value of the mspChaseDataExchange property.
         *
         * @return
         *     possible object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPChaseDataExchange }
         *
         */
        public BatchProcessRequestType.MSPApplication.MSPChaseDataExchange getMSPChaseDataExchange() {
            return mspChaseDataExchange;
        }

        /**
         * Sets the value of the mspChaseDataExchange property.
         *
         * @param value
         *     allowed object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPChaseDataExchange }
         *
         */
        public void setMSPChaseDataExchange(BatchProcessRequestType.MSPApplication.MSPChaseDataExchange value) {
            this.mspChaseDataExchange = value;
        }

        /**
         * Gets the value of the mspEmail property.
         *
         * @return
         *     possible object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPEmail }
         *
         */
        public BatchProcessRequestType.MSPApplication.MSPEmail getMSPEmail() {
            return mspEmail;
        }

        /**
         * Sets the value of the mspEmail property.
         *
         * @param value
         *     allowed object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPEmail }
         *
         */
        public void setMSPEmail(BatchProcessRequestType.MSPApplication.MSPEmail value) {
            this.mspEmail = value;
        }

        /**
         * Gets the value of the mspBatchManager property.
         *
         * @return
         *     possible object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPBatchManager }
         *
         */
        public BatchProcessRequestType.MSPApplication.MSPBatchManager getMSPBatchManager() {
            return mspBatchManager;
        }

        /**
         * Sets the value of the mspBatchManager property.
         *
         * @param value
         *     allowed object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPBatchManager }
         *
         */
        public void setMSPBatchManager(BatchProcessRequestType.MSPApplication.MSPBatchManager value) {
            this.mspBatchManager = value;
        }

        /**
         * Gets the value of the mspMigration property.
         *
         * @return
         *     possible object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPMigration }
         *
         */
        public BatchProcessRequestType.MSPApplication.MSPMigration getMSPMigration() {
            return mspMigration;
        }

        /**
         * Sets the value of the mspMigration property.
         *
         * @param value
         *     allowed object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPMigration }
         *
         */
        public void setMSPMigration(BatchProcessRequestType.MSPApplication.MSPMigration value) {
            this.mspMigration = value;
        }

        /**
         * Gets the value of the mspTxGenerator property.
         *
         * @return
         *     possible object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPTxGenerator }
         *
         */
        public BatchProcessRequestType.MSPApplication.MSPTxGenerator getMSPTxGenerator() {
            return mspTxGenerator;
        }

        /**
         * Sets the value of the mspTxGenerator property.
         *
         * @param value
         *     allowed object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPTxGenerator }
         *
         */
        public void setMSPTxGenerator(BatchProcessRequestType.MSPApplication.MSPTxGenerator value) {
            this.mspTxGenerator = value;
        }

        /**
         * Gets the value of the mspHealthCheck property.
         *
         * @return
         *     possible object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPHealthCheck }
         *
         */
        public BatchProcessRequestType.MSPApplication.MSPHealthCheck getMSPHealthCheck() {
            return mspHealthCheck;
        }

        /**
         * Sets the value of the mspHealthCheck property.
         *
         * @param value
         *     allowed object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPHealthCheck }
         *
         */
        public void setMSPHealthCheck(BatchProcessRequestType.MSPApplication.MSPHealthCheck value) {
            this.mspHealthCheck = value;
        }

        /**
         * Gets the value of the mspagDataExchange property.
         *
         * @return
         *     possible object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPAGDataExchange }
         *
         */
        public BatchProcessRequestType.MSPApplication.MSPAGDataExchange getMSPAGDataExchange() {
            return mspagDataExchange;
        }

        /**
         * Sets the value of the mspagDataExchange property.
         *
         * @param value
         *     allowed object is
         *     {@link BatchProcessRequestType.MSPApplication.MSPAGDataExchange }
         *
         */
        public void setMSPAGDataExchange(BatchProcessRequestType.MSPApplication.MSPAGDataExchange value) {
            this.mspagDataExchange = value;
        }

        /**
         * Gets the value of the targetApp property.
         * 
         * @return
         *     possible object is
         *     {@link BTXTargetAppEnum }
         *     
         */
        public BTXTargetAppEnum getTargetApp() {
            return targetApp;
        }

        /**
         * Sets the value of the targetApp property.
         * 
         * @param value
         *     allowed object is
         *     {@link BTXTargetAppEnum }
         *     
         */
        public void setTargetApp(BTXTargetAppEnum value) {
            this.targetApp = value;
        }

        /**
         * Gets the value of the siteName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSiteName() {
            return siteName;
        }

        /**
         * Sets the value of the siteName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSiteName(String value) {
            this.siteName = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="FromAffinion_1Band3BMonitoringSubscription" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="ToAffinion_1Band3BMonitoringSubscriptionAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToAffinion_1Band3BMonitoringSubscriptionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="Affinion1Band3BMonitoringHits" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="Affinion1BOnlineMonitoringAlerts" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="Affinion3BOnlineMonitoringAlerts" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="FromAffinion_CreditReport" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="ToAffinion_CreditReportFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToAffinion_CreditReportAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="FromAffinion_ScoreTrackerSubscription" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="ToAffinion_ScoreTrackerSubscriptionAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToAffinion_ScoreTrackerSubscriptionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="FromAffinion_CreditAssistSubscription" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="ToAffinion_CreditAssistSubscriptionAck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToAffinion_CXProducts" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="BatchProcess" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "fromAffinion1Band3BMonitoringSubscription",
            "toAffinion1Band3BMonitoringSubscriptionAck",
            "toAffinion1Band3BMonitoringSubscriptionFeedback",
            "affinion1Band3BMonitoringHits",
            "affinion1BOnlineMonitoringAlerts",
            "affinion3BOnlineMonitoringAlerts",
            "fromAffinionCreditReport",
            "toAffinionCreditReportFeedback",
            "toAffinionCreditReportAck",
            "fromAffinionScoreTrackerSubscription",
            "toAffinionScoreTrackerSubscriptionAck",
            "toAffinionScoreTrackerSubscriptionFeedback",
            "fromAffinionCreditAssistSubscription",
            "toAffinionCreditAssistSubscriptionAck",
            "toAffinionCXProducts"
        })
        public static class MSPAGDataExchange {

            @XmlElement(name = "FromAffinion_1Band3BMonitoringSubscription")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromAffinion1Band3BMonitoringSubscription;
            @XmlElement(name = "ToAffinion_1Band3BMonitoringSubscriptionAck")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toAffinion1Band3BMonitoringSubscriptionAck;
            @XmlElement(name = "ToAffinion_1Band3BMonitoringSubscriptionFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toAffinion1Band3BMonitoringSubscriptionFeedback;
            @XmlElement(name = "Affinion1Band3BMonitoringHits")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor affinion1Band3BMonitoringHits;
            @XmlElement(name = "Affinion1BOnlineMonitoringAlerts")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor affinion1BOnlineMonitoringAlerts;
            @XmlElement(name = "Affinion3BOnlineMonitoringAlerts")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor affinion3BOnlineMonitoringAlerts;
            @XmlElement(name = "FromAffinion_CreditReport")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromAffinionCreditReport;
            @XmlElement(name = "ToAffinion_CreditReportFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toAffinionCreditReportFeedback;
            @XmlElement(name = "ToAffinion_CreditReportAck")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toAffinionCreditReportAck;
            @XmlElement(name = "FromAffinion_ScoreTrackerSubscription")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromAffinionScoreTrackerSubscription;
            @XmlElement(name = "ToAffinion_ScoreTrackerSubscriptionAck")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toAffinionScoreTrackerSubscriptionAck;
            @XmlElement(name = "ToAffinion_ScoreTrackerSubscriptionFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toAffinionScoreTrackerSubscriptionFeedback;
            @XmlElement(name = "FromAffinion_CreditAssistSubscription")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromAffinionCreditAssistSubscription;
            @XmlElement(name = "ToAffinion_CreditAssistSubscriptionAck")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toAffinionCreditAssistSubscriptionAck;
            @XmlElement(name = "ToAffinion_CXProducts")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toAffinionCXProducts;
            @XmlAttribute(name = "BatchProcess")
            protected String batchProcess;

            /**
             * Gets the value of the fromAffinion1Band3BMonitoringSubscription property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromAffinion1Band3BMonitoringSubscription() {
                return fromAffinion1Band3BMonitoringSubscription;
            }

            /**
             * Sets the value of the fromAffinion1Band3BMonitoringSubscription property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromAffinion1Band3BMonitoringSubscription(BatchStepFileFromVendor value) {
                this.fromAffinion1Band3BMonitoringSubscription = value;
            }

            /**
             * Gets the value of the toAffinion1Band3BMonitoringSubscriptionAck property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToAffinion1Band3BMonitoringSubscriptionAck() {
                return toAffinion1Band3BMonitoringSubscriptionAck;
            }

            /**
             * Sets the value of the toAffinion1Band3BMonitoringSubscriptionAck property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToAffinion1Band3BMonitoringSubscriptionAck(BatchStepFileToVendor value) {
                this.toAffinion1Band3BMonitoringSubscriptionAck = value;
            }

            /**
             * Gets the value of the toAffinion1Band3BMonitoringSubscriptionFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToAffinion1Band3BMonitoringSubscriptionFeedback() {
                return toAffinion1Band3BMonitoringSubscriptionFeedback;
            }

            /**
             * Sets the value of the toAffinion1Band3BMonitoringSubscriptionFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToAffinion1Band3BMonitoringSubscriptionFeedback(BatchStepFileToVendor value) {
                this.toAffinion1Band3BMonitoringSubscriptionFeedback = value;
            }

            /**
             * Gets the value of the affinion1Band3BMonitoringHits property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getAffinion1Band3BMonitoringHits() {
                return affinion1Band3BMonitoringHits;
            }

            /**
             * Sets the value of the affinion1Band3BMonitoringHits property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setAffinion1Band3BMonitoringHits(BatchStepFileToVendor value) {
                this.affinion1Band3BMonitoringHits = value;
            }

            /**
             * Gets the value of the affinion1BOnlineMonitoringAlerts property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getAffinion1BOnlineMonitoringAlerts() {
                return affinion1BOnlineMonitoringAlerts;
            }

            /**
             * Sets the value of the affinion1BOnlineMonitoringAlerts property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setAffinion1BOnlineMonitoringAlerts(BatchStepFileToVendor value) {
                this.affinion1BOnlineMonitoringAlerts = value;
            }

            /**
             * Gets the value of the affinion3BOnlineMonitoringAlerts property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getAffinion3BOnlineMonitoringAlerts() {
                return affinion3BOnlineMonitoringAlerts;
            }

            /**
             * Sets the value of the affinion3BOnlineMonitoringAlerts property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setAffinion3BOnlineMonitoringAlerts(BatchStepFileToVendor value) {
                this.affinion3BOnlineMonitoringAlerts = value;
            }

            /**
             * Gets the value of the fromAffinionCreditReport property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromAffinionCreditReport() {
                return fromAffinionCreditReport;
            }

            /**
             * Sets the value of the fromAffinionCreditReport property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromAffinionCreditReport(BatchStepFileFromVendor value) {
                this.fromAffinionCreditReport = value;
            }

            /**
             * Gets the value of the toAffinionCreditReportFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToAffinionCreditReportFeedback() {
                return toAffinionCreditReportFeedback;
            }

            /**
             * Sets the value of the toAffinionCreditReportFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToAffinionCreditReportFeedback(BatchStepFileToVendor value) {
                this.toAffinionCreditReportFeedback = value;
            }

            /**
             * Gets the value of the toAffinionCreditReportAck property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToAffinionCreditReportAck() {
                return toAffinionCreditReportAck;
            }

            /**
             * Sets the value of the toAffinionCreditReportAck property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToAffinionCreditReportAck(BatchStepFileToVendor value) {
                this.toAffinionCreditReportAck = value;
            }

            /**
             * Gets the value of the fromAffinionScoreTrackerSubscription property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromAffinionScoreTrackerSubscription() {
                return fromAffinionScoreTrackerSubscription;
            }

            /**
             * Sets the value of the fromAffinionScoreTrackerSubscription property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromAffinionScoreTrackerSubscription(BatchStepFileFromVendor value) {
                this.fromAffinionScoreTrackerSubscription = value;
            }

            /**
             * Gets the value of the toAffinionScoreTrackerSubscriptionAck property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToAffinionScoreTrackerSubscriptionAck() {
                return toAffinionScoreTrackerSubscriptionAck;
            }

            /**
             * Sets the value of the toAffinionScoreTrackerSubscriptionAck property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToAffinionScoreTrackerSubscriptionAck(BatchStepFileToVendor value) {
                this.toAffinionScoreTrackerSubscriptionAck = value;
            }

            /**
             * Gets the value of the toAffinionScoreTrackerSubscriptionFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToAffinionScoreTrackerSubscriptionFeedback() {
                return toAffinionScoreTrackerSubscriptionFeedback;
            }

            /**
             * Sets the value of the toAffinionScoreTrackerSubscriptionFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToAffinionScoreTrackerSubscriptionFeedback(BatchStepFileToVendor value) {
                this.toAffinionScoreTrackerSubscriptionFeedback = value;
            }

            /**
             * Gets the value of the fromAffinionCreditAssistSubscription property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromAffinionCreditAssistSubscription() {
                return fromAffinionCreditAssistSubscription;
            }

            /**
             * Sets the value of the fromAffinionCreditAssistSubscription property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromAffinionCreditAssistSubscription(BatchStepFileFromVendor value) {
                this.fromAffinionCreditAssistSubscription = value;
            }

            /**
             * Gets the value of the toAffinionCreditAssistSubscriptionAck property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToAffinionCreditAssistSubscriptionAck() {
                return toAffinionCreditAssistSubscriptionAck;
            }

            /**
             * Sets the value of the toAffinionCreditAssistSubscriptionAck property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToAffinionCreditAssistSubscriptionAck(BatchStepFileToVendor value) {
                this.toAffinionCreditAssistSubscriptionAck = value;
            }

            /**
             * Gets the value of the toAffinionCXProducts property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToAffinionCXProducts() {
                return toAffinionCXProducts;
            }

            /**
             * Sets the value of the toAffinionCXProducts property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToAffinionCXProducts(BatchStepFileToVendor value) {
                this.toAffinionCXProducts = value;
            }

            /**
             * Gets the value of the batchProcess property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBatchProcess() {
                return batchProcess;
            }

            /**
             * Sets the value of the batchProcess property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBatchProcess(String value) {
                this.batchProcess = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="BTXClean" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "btxClean"
        })
        public static class MSPBatchManager {

            @XmlElement(name = "BTXClean")
            @XmlSchemaType(name = "string")
            protected BatchStep btxClean;

            /**
             * Gets the value of the btxClean property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getBTXClean() {
                return btxClean;
            }

            /**
             * Sets the value of the btxClean property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setBTXClean(BatchStep value) {
                this.btxClean = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="FromChaseTPE" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromChaseRetailTPE" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromChaseOBTMTPE" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromChasePrimaryUser" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromChaseCCAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromChaseRetailAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromChaseCCLeadFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromChaseRetailSSNValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromVertrueCancel" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromLifeLockTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromTUCRSTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromOneTechCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromActivationCodeCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromEuropAssistanceCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromCPPCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromTUAutoPartner1Transaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="ToChaseTPEFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToChaseTPERetailFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToChaseRetailOfflineCheck" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToChasePrimaryUser" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToChaseCCAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToChaseRetailAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToChaseCCLeadDispositions" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToChaseRetailSSNValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToChaseCCMemberHistory" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToChaseRetailMemberHistory" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToVertrueCancelConfirmation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToTUCRSTransactionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToLifeLockTransactionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ProcessConsolidationRequests" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="ToOneTechCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToOneTechDailyAlertsFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToOneTechFailedIVFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToActivationCodeCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToEuropAssistanceCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToCPPCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToTUAutoPartner1TransactionFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="FromCESCancellation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="ToCESCancellationFeedback" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToCESDailyAlertsFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToCESFailedIVFile" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToVertruePartnerNotification" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="ProcessAccountUpdaterToPaymentech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "fromChaseTPE",
            "fromChaseRetailTPE",
            "fromChaseOBTMTPE",
            "fromChasePrimaryUser",
            "fromChaseCCAccountValidation",
            "fromChaseRetailAccountValidation",
            "fromChaseCCLeadFile",
            "fromChaseRetailSSNValidation",
            "fromVertrueCancel",
            "fromLifeLockTransaction",
            "fromTUCRSTransaction",
            "fromOneTechCancellation",
            "fromActivationCodeCancellation",
            "fromEuropAssistanceCancellation",
            "fromCPPCancellation",
            "fromTUAutoPartner1Transaction",
            "toChaseTPEFeedback",
            "toChaseTPERetailFeedback",
            "toChaseRetailOfflineCheck",
            "toChasePrimaryUser",
            "toChaseCCAccountValidation",
            "toChaseRetailAccountValidation",
            "toChaseCCLeadDispositions",
            "toChaseRetailSSNValidation",
            "toChaseCCMemberHistory",
            "toChaseRetailMemberHistory",
            "toVertrueCancelConfirmation",
            "toTUCRSTransactionFeedback",
            "toLifeLockTransactionFeedback",
            "processConsolidationRequests",
            "toOneTechCancellationFeedback",
            "toOneTechDailyAlertsFile",
            "toOneTechFailedIVFile",
            "toActivationCodeCancellationFeedback",
            "toEuropAssistanceCancellationFeedback",
            "toCPPCancellationFeedback",
            "toTUAutoPartner1TransactionFeedback",
            "fromCESCancellation",
            "toCESCancellationFeedback",
            "toCESDailyAlertsFile",
            "toCESFailedIVFile",
            "toVertruePartnerNotification",
            "processAccountUpdaterToPaymentech"
        })
        public static class MSPChaseDataExchange {

            @XmlElement(name = "FromChaseTPE")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromChaseTPE;
            @XmlElement(name = "FromChaseRetailTPE")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromChaseRetailTPE;
            @XmlElement(name = "FromChaseOBTMTPE")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromChaseOBTMTPE;
            @XmlElement(name = "FromChasePrimaryUser")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromChasePrimaryUser;
            @XmlElement(name = "FromChaseCCAccountValidation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromChaseCCAccountValidation;
            @XmlElement(name = "FromChaseRetailAccountValidation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromChaseRetailAccountValidation;
            @XmlElement(name = "FromChaseCCLeadFile")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromChaseCCLeadFile;
            @XmlElement(name = "FromChaseRetailSSNValidation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromChaseRetailSSNValidation;
            @XmlElement(name = "FromVertrueCancel")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromVertrueCancel;
            @XmlElement(name = "FromLifeLockTransaction")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromLifeLockTransaction;
            @XmlElement(name = "FromTUCRSTransaction")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromTUCRSTransaction;
            @XmlElement(name = "FromOneTechCancellation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromOneTechCancellation;
            @XmlElement(name = "FromActivationCodeCancellation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromActivationCodeCancellation;
            @XmlElement(name = "FromEuropAssistanceCancellation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromEuropAssistanceCancellation;
            @XmlElement(name = "FromCPPCancellation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromCPPCancellation;
            @XmlElement(name = "FromTUAutoPartner1Transaction")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromTUAutoPartner1Transaction;
            @XmlElement(name = "ToChaseTPEFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toChaseTPEFeedback;
            @XmlElement(name = "ToChaseTPERetailFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toChaseTPERetailFeedback;
            @XmlElement(name = "ToChaseRetailOfflineCheck")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toChaseRetailOfflineCheck;
            @XmlElement(name = "ToChasePrimaryUser")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toChasePrimaryUser;
            @XmlElement(name = "ToChaseCCAccountValidation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toChaseCCAccountValidation;
            @XmlElement(name = "ToChaseRetailAccountValidation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toChaseRetailAccountValidation;
            @XmlElement(name = "ToChaseCCLeadDispositions")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toChaseCCLeadDispositions;
            @XmlElement(name = "ToChaseRetailSSNValidation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toChaseRetailSSNValidation;
            @XmlElement(name = "ToChaseCCMemberHistory")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toChaseCCMemberHistory;
            @XmlElement(name = "ToChaseRetailMemberHistory")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toChaseRetailMemberHistory;
            @XmlElement(name = "ToVertrueCancelConfirmation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toVertrueCancelConfirmation;
            @XmlElement(name = "ToTUCRSTransactionFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toTUCRSTransactionFeedback;
            @XmlElement(name = "ToLifeLockTransactionFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toLifeLockTransactionFeedback;
            @XmlElement(name = "ProcessConsolidationRequests")
            @XmlSchemaType(name = "string")
            protected BatchStep processConsolidationRequests;
            @XmlElement(name = "ToOneTechCancellationFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toOneTechCancellationFeedback;
            @XmlElement(name = "ToOneTechDailyAlertsFile")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toOneTechDailyAlertsFile;
            @XmlElement(name = "ToOneTechFailedIVFile")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toOneTechFailedIVFile;
            @XmlElement(name = "ToActivationCodeCancellationFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toActivationCodeCancellationFeedback;
            @XmlElement(name = "ToEuropAssistanceCancellationFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toEuropAssistanceCancellationFeedback;
            @XmlElement(name = "ToCPPCancellationFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toCPPCancellationFeedback;
            @XmlElement(name = "ToTUAutoPartner1TransactionFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toTUAutoPartner1TransactionFeedback;
            @XmlElement(name = "FromCESCancellation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromCESCancellation;
            @XmlElement(name = "ToCESCancellationFeedback")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toCESCancellationFeedback;
            @XmlElement(name = "ToCESDailyAlertsFile")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toCESDailyAlertsFile;
            @XmlElement(name = "ToCESFailedIVFile")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toCESFailedIVFile;
            @XmlElement(name = "ToVertruePartnerNotification")
            @XmlSchemaType(name = "string")
            protected BatchStep toVertruePartnerNotification;
            @XmlElement(name = "ProcessAccountUpdaterToPaymentech")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor processAccountUpdaterToPaymentech;
            @XmlAttribute(name = "BatchProcess", required = true)
            protected String batchProcess;

            /**
             * Gets the value of the fromChaseTPE property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromChaseTPE() {
                return fromChaseTPE;
            }

            /**
             * Sets the value of the fromChaseTPE property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromChaseTPE(BatchStepFileFromVendor value) {
                this.fromChaseTPE = value;
            }

            /**
             * Gets the value of the fromChaseRetailTPE property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromChaseRetailTPE() {
                return fromChaseRetailTPE;
            }

            /**
             * Sets the value of the fromChaseRetailTPE property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromChaseRetailTPE(BatchStepFileFromVendor value) {
                this.fromChaseRetailTPE = value;
            }

            /**
             * Gets the value of the fromChaseOBTMTPE property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromChaseOBTMTPE() {
                return fromChaseOBTMTPE;
            }

            /**
             * Sets the value of the fromChaseOBTMTPE property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromChaseOBTMTPE(BatchStepFileFromVendor value) {
                this.fromChaseOBTMTPE = value;
            }

            /**
             * Gets the value of the fromChasePrimaryUser property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromChasePrimaryUser() {
                return fromChasePrimaryUser;
            }

            /**
             * Sets the value of the fromChasePrimaryUser property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromChasePrimaryUser(BatchStepFileFromVendor value) {
                this.fromChasePrimaryUser = value;
            }

            /**
             * Gets the value of the fromChaseCCAccountValidation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromChaseCCAccountValidation() {
                return fromChaseCCAccountValidation;
            }

            /**
             * Sets the value of the fromChaseCCAccountValidation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromChaseCCAccountValidation(BatchStepFileFromVendor value) {
                this.fromChaseCCAccountValidation = value;
            }

            /**
             * Gets the value of the fromChaseRetailAccountValidation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromChaseRetailAccountValidation() {
                return fromChaseRetailAccountValidation;
            }

            /**
             * Sets the value of the fromChaseRetailAccountValidation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromChaseRetailAccountValidation(BatchStepFileFromVendor value) {
                this.fromChaseRetailAccountValidation = value;
            }

            /**
             * Gets the value of the fromChaseCCLeadFile property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromChaseCCLeadFile() {
                return fromChaseCCLeadFile;
            }

            /**
             * Sets the value of the fromChaseCCLeadFile property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromChaseCCLeadFile(BatchStepFileFromVendor value) {
                this.fromChaseCCLeadFile = value;
            }

            /**
             * Gets the value of the fromChaseRetailSSNValidation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromChaseRetailSSNValidation() {
                return fromChaseRetailSSNValidation;
            }

            /**
             * Sets the value of the fromChaseRetailSSNValidation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromChaseRetailSSNValidation(BatchStepFileFromVendor value) {
                this.fromChaseRetailSSNValidation = value;
            }

            /**
             * Gets the value of the fromVertrueCancel property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromVertrueCancel() {
                return fromVertrueCancel;
            }

            /**
             * Sets the value of the fromVertrueCancel property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromVertrueCancel(BatchStepFileFromVendor value) {
                this.fromVertrueCancel = value;
            }

            /**
             * Gets the value of the fromLifeLockTransaction property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromLifeLockTransaction() {
                return fromLifeLockTransaction;
            }

            /**
             * Sets the value of the fromLifeLockTransaction property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromLifeLockTransaction(BatchStepFileFromVendor value) {
                this.fromLifeLockTransaction = value;
            }

            /**
             * Gets the value of the fromTUCRSTransaction property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromTUCRSTransaction() {
                return fromTUCRSTransaction;
            }

            /**
             * Sets the value of the fromTUCRSTransaction property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromTUCRSTransaction(BatchStepFileFromVendor value) {
                this.fromTUCRSTransaction = value;
            }

            /**
             * Gets the value of the fromOneTechCancellation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromOneTechCancellation() {
                return fromOneTechCancellation;
            }

            /**
             * Sets the value of the fromOneTechCancellation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromOneTechCancellation(BatchStepFileFromVendor value) {
                this.fromOneTechCancellation = value;
            }

            /**
             * Gets the value of the fromActivationCodeCancellation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromActivationCodeCancellation() {
                return fromActivationCodeCancellation;
            }

            /**
             * Sets the value of the fromActivationCodeCancellation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromActivationCodeCancellation(BatchStepFileFromVendor value) {
                this.fromActivationCodeCancellation = value;
            }

            /**
             * Gets the value of the fromEuropAssistanceCancellation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromEuropAssistanceCancellation() {
                return fromEuropAssistanceCancellation;
            }

            /**
             * Sets the value of the fromEuropAssistanceCancellation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromEuropAssistanceCancellation(BatchStepFileFromVendor value) {
                this.fromEuropAssistanceCancellation = value;
            }

            /**
             * Gets the value of the fromCPPCancellation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromCPPCancellation() {
                return fromCPPCancellation;
            }

            /**
             * Sets the value of the fromCPPCancellation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromCPPCancellation(BatchStepFileFromVendor value) {
                this.fromCPPCancellation = value;
            }

            /**
             * Gets the value of the fromTUAutoPartner1Transaction property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromTUAutoPartner1Transaction() {
                return fromTUAutoPartner1Transaction;
            }

            /**
             * Sets the value of the fromTUAutoPartner1Transaction property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromTUAutoPartner1Transaction(BatchStepFileFromVendor value) {
                this.fromTUAutoPartner1Transaction = value;
            }

            /**
             * Gets the value of the toChaseTPEFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToChaseTPEFeedback() {
                return toChaseTPEFeedback;
            }

            /**
             * Sets the value of the toChaseTPEFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToChaseTPEFeedback(BatchStepFileToVendor value) {
                this.toChaseTPEFeedback = value;
            }

            /**
             * Gets the value of the toChaseTPERetailFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToChaseTPERetailFeedback() {
                return toChaseTPERetailFeedback;
            }

            /**
             * Sets the value of the toChaseTPERetailFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToChaseTPERetailFeedback(BatchStepFileToVendor value) {
                this.toChaseTPERetailFeedback = value;
            }

            /**
             * Gets the value of the toChaseRetailOfflineCheck property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToChaseRetailOfflineCheck() {
                return toChaseRetailOfflineCheck;
            }

            /**
             * Sets the value of the toChaseRetailOfflineCheck property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToChaseRetailOfflineCheck(BatchStepFileToVendor value) {
                this.toChaseRetailOfflineCheck = value;
            }

            /**
             * Gets the value of the toChasePrimaryUser property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToChasePrimaryUser() {
                return toChasePrimaryUser;
            }

            /**
             * Sets the value of the toChasePrimaryUser property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToChasePrimaryUser(BatchStepFileToVendor value) {
                this.toChasePrimaryUser = value;
            }

            /**
             * Gets the value of the toChaseCCAccountValidation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToChaseCCAccountValidation() {
                return toChaseCCAccountValidation;
            }

            /**
             * Sets the value of the toChaseCCAccountValidation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToChaseCCAccountValidation(BatchStepFileToVendor value) {
                this.toChaseCCAccountValidation = value;
            }

            /**
             * Gets the value of the toChaseRetailAccountValidation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToChaseRetailAccountValidation() {
                return toChaseRetailAccountValidation;
            }

            /**
             * Sets the value of the toChaseRetailAccountValidation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToChaseRetailAccountValidation(BatchStepFileToVendor value) {
                this.toChaseRetailAccountValidation = value;
            }

            /**
             * Gets the value of the toChaseCCLeadDispositions property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToChaseCCLeadDispositions() {
                return toChaseCCLeadDispositions;
            }

            /**
             * Sets the value of the toChaseCCLeadDispositions property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToChaseCCLeadDispositions(BatchStepFileToVendor value) {
                this.toChaseCCLeadDispositions = value;
            }

            /**
             * Gets the value of the toChaseRetailSSNValidation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToChaseRetailSSNValidation() {
                return toChaseRetailSSNValidation;
            }

            /**
             * Sets the value of the toChaseRetailSSNValidation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToChaseRetailSSNValidation(BatchStepFileToVendor value) {
                this.toChaseRetailSSNValidation = value;
            }

            /**
             * Gets the value of the toChaseCCMemberHistory property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToChaseCCMemberHistory() {
                return toChaseCCMemberHistory;
            }

            /**
             * Sets the value of the toChaseCCMemberHistory property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToChaseCCMemberHistory(BatchStepFileToVendor value) {
                this.toChaseCCMemberHistory = value;
            }

            /**
             * Gets the value of the toChaseRetailMemberHistory property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToChaseRetailMemberHistory() {
                return toChaseRetailMemberHistory;
            }

            /**
             * Sets the value of the toChaseRetailMemberHistory property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToChaseRetailMemberHistory(BatchStepFileToVendor value) {
                this.toChaseRetailMemberHistory = value;
            }

            /**
             * Gets the value of the toVertrueCancelConfirmation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToVertrueCancelConfirmation() {
                return toVertrueCancelConfirmation;
            }

            /**
             * Sets the value of the toVertrueCancelConfirmation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToVertrueCancelConfirmation(BatchStepFileToVendor value) {
                this.toVertrueCancelConfirmation = value;
            }

            /**
             * Gets the value of the toTUCRSTransactionFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToTUCRSTransactionFeedback() {
                return toTUCRSTransactionFeedback;
            }

            /**
             * Sets the value of the toTUCRSTransactionFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToTUCRSTransactionFeedback(BatchStepFileToVendor value) {
                this.toTUCRSTransactionFeedback = value;
            }

            /**
             * Gets the value of the toLifeLockTransactionFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToLifeLockTransactionFeedback() {
                return toLifeLockTransactionFeedback;
            }

            /**
             * Sets the value of the toLifeLockTransactionFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToLifeLockTransactionFeedback(BatchStepFileToVendor value) {
                this.toLifeLockTransactionFeedback = value;
            }

            /**
             * Gets the value of the processConsolidationRequests property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getProcessConsolidationRequests() {
                return processConsolidationRequests;
            }

            /**
             * Sets the value of the processConsolidationRequests property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setProcessConsolidationRequests(BatchStep value) {
                this.processConsolidationRequests = value;
            }

            /**
             * Gets the value of the toOneTechCancellationFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToOneTechCancellationFeedback() {
                return toOneTechCancellationFeedback;
            }

            /**
             * Sets the value of the toOneTechCancellationFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToOneTechCancellationFeedback(BatchStepFileToVendor value) {
                this.toOneTechCancellationFeedback = value;
            }

            /**
             * Gets the value of the toOneTechDailyAlertsFile property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToOneTechDailyAlertsFile() {
                return toOneTechDailyAlertsFile;
            }

            /**
             * Sets the value of the toOneTechDailyAlertsFile property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToOneTechDailyAlertsFile(BatchStepFileToVendor value) {
                this.toOneTechDailyAlertsFile = value;
            }

            /**
             * Gets the value of the toOneTechFailedIVFile property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToOneTechFailedIVFile() {
                return toOneTechFailedIVFile;
            }

            /**
             * Sets the value of the toOneTechFailedIVFile property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToOneTechFailedIVFile(BatchStepFileToVendor value) {
                this.toOneTechFailedIVFile = value;
            }

            /**
             * Gets the value of the toActivationCodeCancellationFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToActivationCodeCancellationFeedback() {
                return toActivationCodeCancellationFeedback;
            }

            /**
             * Sets the value of the toActivationCodeCancellationFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToActivationCodeCancellationFeedback(BatchStepFileToVendor value) {
                this.toActivationCodeCancellationFeedback = value;
            }

            /**
             * Gets the value of the toEuropAssistanceCancellationFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToEuropAssistanceCancellationFeedback() {
                return toEuropAssistanceCancellationFeedback;
            }

            /**
             * Sets the value of the toEuropAssistanceCancellationFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToEuropAssistanceCancellationFeedback(BatchStepFileToVendor value) {
                this.toEuropAssistanceCancellationFeedback = value;
            }

            /**
             * Gets the value of the toCPPCancellationFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToCPPCancellationFeedback() {
                return toCPPCancellationFeedback;
            }

            /**
             * Sets the value of the toCPPCancellationFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToCPPCancellationFeedback(BatchStepFileToVendor value) {
                this.toCPPCancellationFeedback = value;
            }

            /**
             * Gets the value of the toTUAutoPartner1TransactionFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToTUAutoPartner1TransactionFeedback() {
                return toTUAutoPartner1TransactionFeedback;
            }

            /**
             * Sets the value of the toTUAutoPartner1TransactionFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToTUAutoPartner1TransactionFeedback(BatchStepFileToVendor value) {
                this.toTUAutoPartner1TransactionFeedback = value;
            }

            /**
             * Gets the value of the fromCESCancellation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromCESCancellation() {
                return fromCESCancellation;
            }

            /**
             * Sets the value of the fromCESCancellation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromCESCancellation(BatchStepFileFromVendor value) {
                this.fromCESCancellation = value;
            }

            /**
             * Gets the value of the toCESCancellationFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToCESCancellationFeedback() {
                return toCESCancellationFeedback;
            }

            /**
             * Sets the value of the toCESCancellationFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToCESCancellationFeedback(BatchStepFileToVendor value) {
                this.toCESCancellationFeedback = value;
            }

            /**
             * Gets the value of the toCESDailyAlertsFile property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToCESDailyAlertsFile() {
                return toCESDailyAlertsFile;
            }

            /**
             * Sets the value of the toCESDailyAlertsFile property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToCESDailyAlertsFile(BatchStepFileToVendor value) {
                this.toCESDailyAlertsFile = value;
            }

            /**
             * Gets the value of the toCESFailedIVFile property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToCESFailedIVFile() {
                return toCESFailedIVFile;
            }

            /**
             * Sets the value of the toCESFailedIVFile property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToCESFailedIVFile(BatchStepFileToVendor value) {
                this.toCESFailedIVFile = value;
            }

            /**
             * Gets the value of the toVertruePartnerNotification property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getToVertruePartnerNotification() {
                return toVertruePartnerNotification;
            }

            /**
             * Sets the value of the toVertruePartnerNotification property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setToVertruePartnerNotification(BatchStep value) {
                this.toVertruePartnerNotification = value;
            }

            /**
             * Gets the value of the processAccountUpdaterToPaymentech property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getProcessAccountUpdaterToPaymentech() {
                return processAccountUpdaterToPaymentech;
            }

            /**
             * Sets the value of the processAccountUpdaterToPaymentech property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setProcessAccountUpdaterToPaymentech(BatchStepFileToVendor value) {
                this.processAccountUpdaterToPaymentech = value;
            }

            /**
             * Gets the value of the batchProcess property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBatchProcess() {
                return batchProcess;
            }

            /**
             * Sets the value of the batchProcess property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBatchProcess(String value) {
                this.batchProcess = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="ProcessMSPTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="ScheduledBilling" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="ScheduledFulfillments" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="ScheduledFailureReviews" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="ScheduledAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="DailyAlerts" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="OfflineIVUtiity" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="ProcessReflectionTransactions" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="ScheduledPaymentechAccountValidation" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ProcessAccountUpdaterToPaymentech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "processMSPTransaction",
            "scheduledBilling",
            "scheduledFulfillments",
            "scheduledFailureReviews",
            "scheduledAccountValidation",
            "dailyAlerts",
            "offlineIVUtiity",
            "processReflectionTransactions",
            "scheduledPaymentechAccountValidation",
            "processAccountUpdaterToPaymentech"
        })
        public static class MSPCore {

            @XmlElement(name = "ProcessMSPTransaction")
            @XmlSchemaType(name = "string")
            protected BatchStep processMSPTransaction;
            @XmlElement(name = "ScheduledBilling")
            @XmlSchemaType(name = "string")
            protected BatchStep scheduledBilling;
            @XmlElement(name = "ScheduledFulfillments")
            @XmlSchemaType(name = "string")
            protected BatchStep scheduledFulfillments;
            @XmlElement(name = "ScheduledFailureReviews")
            @XmlSchemaType(name = "string")
            protected BatchStep scheduledFailureReviews;
            @XmlElement(name = "ScheduledAccountValidation")
            @XmlSchemaType(name = "string")
            protected BatchStep scheduledAccountValidation;
            @XmlElement(name = "DailyAlerts")
            @XmlSchemaType(name = "string")
            protected BatchStep dailyAlerts;
            @XmlElement(name = "OfflineIVUtiity")
            @XmlSchemaType(name = "string")
            protected BatchStep offlineIVUtiity;
            @XmlElement(name = "ProcessReflectionTransactions")
            @XmlSchemaType(name = "string")
            protected BatchStep processReflectionTransactions;
            @XmlElement(name = "ScheduledPaymentechAccountValidation")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor scheduledPaymentechAccountValidation;
            @XmlElement(name = "ProcessAccountUpdaterToPaymentech")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor processAccountUpdaterToPaymentech;
            @XmlAttribute(name = "BatchProcess", required = true)
            protected String batchProcess;

            /**
             * Gets the value of the processMSPTransaction property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getProcessMSPTransaction() {
                return processMSPTransaction;
            }

            /**
             * Sets the value of the processMSPTransaction property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setProcessMSPTransaction(BatchStep value) {
                this.processMSPTransaction = value;
            }

            /**
             * Gets the value of the scheduledBilling property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getScheduledBilling() {
                return scheduledBilling;
            }

            /**
             * Sets the value of the scheduledBilling property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setScheduledBilling(BatchStep value) {
                this.scheduledBilling = value;
            }

            /**
             * Gets the value of the scheduledFulfillments property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getScheduledFulfillments() {
                return scheduledFulfillments;
            }

            /**
             * Sets the value of the scheduledFulfillments property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setScheduledFulfillments(BatchStep value) {
                this.scheduledFulfillments = value;
            }

            /**
             * Gets the value of the scheduledFailureReviews property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getScheduledFailureReviews() {
                return scheduledFailureReviews;
            }

            /**
             * Sets the value of the scheduledFailureReviews property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setScheduledFailureReviews(BatchStep value) {
                this.scheduledFailureReviews = value;
            }

            /**
             * Gets the value of the scheduledAccountValidation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getScheduledAccountValidation() {
                return scheduledAccountValidation;
            }

            /**
             * Sets the value of the scheduledAccountValidation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setScheduledAccountValidation(BatchStep value) {
                this.scheduledAccountValidation = value;
            }

            /**
             * Gets the value of the dailyAlerts property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getDailyAlerts() {
                return dailyAlerts;
            }

            /**
             * Sets the value of the dailyAlerts property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setDailyAlerts(BatchStep value) {
                this.dailyAlerts = value;
            }

            /**
             * Gets the value of the offlineIVUtiity property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getOfflineIVUtiity() {
                return offlineIVUtiity;
            }

            /**
             * Sets the value of the offlineIVUtiity property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setOfflineIVUtiity(BatchStep value) {
                this.offlineIVUtiity = value;
            }

            /**
             * Gets the value of the processReflectionTransactions property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getProcessReflectionTransactions() {
                return processReflectionTransactions;
            }

            /**
             * Sets the value of the processReflectionTransactions property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setProcessReflectionTransactions(BatchStep value) {
                this.processReflectionTransactions = value;
            }

            /**
             * Gets the value of the scheduledPaymentechAccountValidation property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getScheduledPaymentechAccountValidation() {
                return scheduledPaymentechAccountValidation;
            }

            /**
             * Sets the value of the scheduledPaymentechAccountValidation property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setScheduledPaymentechAccountValidation(BatchStepFileToVendor value) {
                this.scheduledPaymentechAccountValidation = value;
            }

            /**
             * Gets the value of the processAccountUpdaterToPaymentech property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getProcessAccountUpdaterToPaymentech() {
                return processAccountUpdaterToPaymentech;
            }

            /**
             * Sets the value of the processAccountUpdaterToPaymentech property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setProcessAccountUpdaterToPaymentech(BatchStepFileToVendor value) {
                this.processAccountUpdaterToPaymentech = value;
            }

            /**
             * Gets the value of the batchProcess property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBatchProcess() {
                return batchProcess;
            }

            /**
             * Sets the value of the batchProcess property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBatchProcess(String value) {
                this.batchProcess = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="SendEmail" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "sendEmail"
        })
        public static class MSPEmail {

            @XmlElement(name = "SendEmail")
            @XmlSchemaType(name = "string")
            protected BatchStep sendEmail;
            @XmlAttribute(name = "BatchProcess", required = true)
            protected String batchProcess;

            /**
             * Gets the value of the sendEmail property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getSendEmail() {
                return sendEmail;
            }

            /**
             * Sets the value of the sendEmail property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setSendEmail(BatchStep value) {
                this.sendEmail = value;
            }

            /**
             * Gets the value of the batchProcess property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBatchProcess() {
                return batchProcess;
            }

            /**
             * Sets the value of the batchProcess property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBatchProcess(String value) {
                this.batchProcess = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="DataIntegrityAssessments" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="HealthCheckPings" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "dataIntegrityAssessments",
            "healthCheckPings"
        })
        public static class MSPHealthCheck {

            @XmlElement(name = "DataIntegrityAssessments")
            @XmlSchemaType(name = "string")
            protected BatchStep dataIntegrityAssessments;
            @XmlElement(name = "HealthCheckPings")
            @XmlSchemaType(name = "string")
            protected BatchStep healthCheckPings;
            @XmlAttribute(name = "BatchProcess", required = true)
            protected String batchProcess;

            /**
             * Gets the value of the dataIntegrityAssessments property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getDataIntegrityAssessments() {
                return dataIntegrityAssessments;
            }

            /**
             * Sets the value of the dataIntegrityAssessments property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setDataIntegrityAssessments(BatchStep value) {
                this.dataIntegrityAssessments = value;
            }

            /**
             * Gets the value of the healthCheckPings property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getHealthCheckPings() {
                return healthCheckPings;
            }

            /**
             * Sets the value of the healthCheckPings property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setHealthCheckPings(BatchStep value) {
                this.healthCheckPings = value;
            }

            /**
             * Gets the value of the batchProcess property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBatchProcess() {
                return batchProcess;
            }

            /**
             * Sets the value of the batchProcess property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBatchProcess(String value) {
                this.batchProcess = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="GenerateEnrollments" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="GenerateCustomerUpdates" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="ToAlertMigrationService" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="FromAlertMigrationService" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "generateEnrollments",
            "generateCustomerUpdates",
            "toAlertMigrationService",
            "fromAlertMigrationService"
        })
        public static class MSPMigration {

            @XmlElement(name = "GenerateEnrollments")
            @XmlSchemaType(name = "string")
            protected BatchStep generateEnrollments;
            @XmlElement(name = "GenerateCustomerUpdates")
            @XmlSchemaType(name = "string")
            protected BatchStep generateCustomerUpdates;
            @XmlElement(name = "ToAlertMigrationService")
            @XmlSchemaType(name = "string")
            protected BatchStep toAlertMigrationService;
            @XmlElement(name = "FromAlertMigrationService")
            @XmlSchemaType(name = "string")
            protected BatchStep fromAlertMigrationService;
            @XmlAttribute(name = "BatchProcess", required = true)
            protected String batchProcess;

            /**
             * Gets the value of the generateEnrollments property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getGenerateEnrollments() {
                return generateEnrollments;
            }

            /**
             * Sets the value of the generateEnrollments property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setGenerateEnrollments(BatchStep value) {
                this.generateEnrollments = value;
            }

            /**
             * Gets the value of the generateCustomerUpdates property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getGenerateCustomerUpdates() {
                return generateCustomerUpdates;
            }

            /**
             * Sets the value of the generateCustomerUpdates property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setGenerateCustomerUpdates(BatchStep value) {
                this.generateCustomerUpdates = value;
            }

            /**
             * Gets the value of the toAlertMigrationService property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getToAlertMigrationService() {
                return toAlertMigrationService;
            }

            /**
             * Sets the value of the toAlertMigrationService property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setToAlertMigrationService(BatchStep value) {
                this.toAlertMigrationService = value;
            }

            /**
             * Gets the value of the fromAlertMigrationService property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getFromAlertMigrationService() {
                return fromAlertMigrationService;
            }

            /**
             * Sets the value of the fromAlertMigrationService property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setFromAlertMigrationService(BatchStep value) {
                this.fromAlertMigrationService = value;
            }

            /**
             * Gets the value of the batchProcess property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBatchProcess() {
                return batchProcess;
            }

            /**
             * Sets the value of the batchProcess property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBatchProcess(String value) {
                this.batchProcess = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="FromPaymenTech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromACH" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="ToPaymenTech" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToACH" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToFromConcord" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="PaymenTechResponseWaitPeriod" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *         &lt;element name="ACHResponseWaitPeriod" type="{com/truelink/schema/database/tcps/enumerations}BatchStep" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "fromPaymenTech",
            "fromACH",
            "toPaymenTech",
            "toACH",
            "toFromConcord",
            "paymenTechResponseWaitPeriod",
            "achResponseWaitPeriod"
        })
        public static class MSPPayment {

            @XmlElement(name = "FromPaymenTech")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromPaymenTech;
            @XmlElement(name = "FromACH")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromACH;
            @XmlElement(name = "ToPaymenTech")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toPaymenTech;
            @XmlElement(name = "ToACH")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toACH;
            @XmlElement(name = "ToFromConcord")
            @XmlSchemaType(name = "string")
            protected BatchStep toFromConcord;
            @XmlElement(name = "PaymenTechResponseWaitPeriod")
            @XmlSchemaType(name = "string")
            protected BatchStep paymenTechResponseWaitPeriod;
            @XmlElement(name = "ACHResponseWaitPeriod")
            @XmlSchemaType(name = "string")
            protected BatchStep achResponseWaitPeriod;
            @XmlAttribute(name = "BatchProcess", required = true)
            protected String batchProcess;

            /**
             * Gets the value of the fromPaymenTech property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromPaymenTech() {
                return fromPaymenTech;
            }

            /**
             * Sets the value of the fromPaymenTech property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromPaymenTech(BatchStepFileFromVendor value) {
                this.fromPaymenTech = value;
            }

            /**
             * Gets the value of the fromACH property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromACH() {
                return fromACH;
            }

            /**
             * Sets the value of the fromACH property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromACH(BatchStepFileFromVendor value) {
                this.fromACH = value;
            }

            /**
             * Gets the value of the toPaymenTech property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToPaymenTech() {
                return toPaymenTech;
            }

            /**
             * Sets the value of the toPaymenTech property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToPaymenTech(BatchStepFileToVendor value) {
                this.toPaymenTech = value;
            }

            /**
             * Gets the value of the toACH property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToACH() {
                return toACH;
            }

            /**
             * Sets the value of the toACH property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToACH(BatchStepFileToVendor value) {
                this.toACH = value;
            }

            /**
             * Gets the value of the toFromConcord property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getToFromConcord() {
                return toFromConcord;
            }

            /**
             * Sets the value of the toFromConcord property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setToFromConcord(BatchStep value) {
                this.toFromConcord = value;
            }

            /**
             * Gets the value of the paymenTechResponseWaitPeriod property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getPaymenTechResponseWaitPeriod() {
                return paymenTechResponseWaitPeriod;
            }

            /**
             * Sets the value of the paymenTechResponseWaitPeriod property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setPaymenTechResponseWaitPeriod(BatchStep value) {
                this.paymenTechResponseWaitPeriod = value;
            }

            /**
             * Gets the value of the achResponseWaitPeriod property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getACHResponseWaitPeriod() {
                return achResponseWaitPeriod;
            }

            /**
             * Sets the value of the achResponseWaitPeriod property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setACHResponseWaitPeriod(BatchStep value) {
                this.achResponseWaitPeriod = value;
            }

            /**
             * Gets the value of the batchProcess property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBatchProcess() {
                return batchProcess;
            }

            /**
             * Sets the value of the batchProcess property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBatchProcess(String value) {
                this.batchProcess = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="FromMetroGroup" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="FromLazon" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileFromVendor" minOccurs="0"/>
         *         &lt;element name="ToMetroGroup" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="ToLazon" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="RetryToMetroGroup" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *         &lt;element name="RetryToLazon" type="{com/truelink/schema/database/tcps/enumerations}BatchStepFileToVendor" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "fromMetroGroup",
            "fromLazon",
            "toMetroGroup",
            "toLazon",
            "retryToMetroGroup",
            "retryToLazon"
        })
        public static class MSPPrintAndMail {

            @XmlElement(name = "FromMetroGroup")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromMetroGroup;
            @XmlElement(name = "FromLazon")
            @XmlSchemaType(name = "string")
            protected BatchStepFileFromVendor fromLazon;
            @XmlElement(name = "ToMetroGroup")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toMetroGroup;
            @XmlElement(name = "ToLazon")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor toLazon;
            @XmlElement(name = "RetryToMetroGroup")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor retryToMetroGroup;
            @XmlElement(name = "RetryToLazon")
            @XmlSchemaType(name = "string")
            protected BatchStepFileToVendor retryToLazon;
            @XmlAttribute(name = "BatchProcess", required = true)
            protected String batchProcess;

            /**
             * Gets the value of the fromMetroGroup property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromMetroGroup() {
                return fromMetroGroup;
            }

            /**
             * Sets the value of the fromMetroGroup property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromMetroGroup(BatchStepFileFromVendor value) {
                this.fromMetroGroup = value;
            }

            /**
             * Gets the value of the fromLazon property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public BatchStepFileFromVendor getFromLazon() {
                return fromLazon;
            }

            /**
             * Sets the value of the fromLazon property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileFromVendor }
             *     
             */
            public void setFromLazon(BatchStepFileFromVendor value) {
                this.fromLazon = value;
            }

            /**
             * Gets the value of the toMetroGroup property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToMetroGroup() {
                return toMetroGroup;
            }

            /**
             * Sets the value of the toMetroGroup property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToMetroGroup(BatchStepFileToVendor value) {
                this.toMetroGroup = value;
            }

            /**
             * Gets the value of the toLazon property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getToLazon() {
                return toLazon;
            }

            /**
             * Sets the value of the toLazon property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setToLazon(BatchStepFileToVendor value) {
                this.toLazon = value;
            }

            /**
             * Gets the value of the retryToMetroGroup property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getRetryToMetroGroup() {
                return retryToMetroGroup;
            }

            /**
             * Sets the value of the retryToMetroGroup property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setRetryToMetroGroup(BatchStepFileToVendor value) {
                this.retryToMetroGroup = value;
            }

            /**
             * Gets the value of the retryToLazon property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public BatchStepFileToVendor getRetryToLazon() {
                return retryToLazon;
            }

            /**
             * Sets the value of the retryToLazon property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStepFileToVendor }
             *     
             */
            public void setRetryToLazon(BatchStepFileToVendor value) {
                this.retryToLazon = value;
            }

            /**
             * Gets the value of the batchProcess property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBatchProcess() {
                return batchProcess;
            }

            /**
             * Sets the value of the batchProcess property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBatchProcess(String value) {
                this.batchProcess = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="GenerateTransaction" type="{com/truelink/schema/database/tcps/enumerations}BatchStep"/>
         *       &lt;/sequence>
         *       &lt;attribute name="BatchProcess" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "generateTransaction"
        })
        public static class MSPTxGenerator {

            @XmlElement(name = "GenerateTransaction", required = true)
            @XmlSchemaType(name = "string")
            protected BatchStep generateTransaction;
            @XmlAttribute(name = "BatchProcess", required = true)
            protected String batchProcess;

            /**
             * Gets the value of the generateTransaction property.
             * 
             * @return
             *     possible object is
             *     {@link BatchStep }
             *     
             */
            public BatchStep getGenerateTransaction() {
                return generateTransaction;
            }

            /**
             * Sets the value of the generateTransaction property.
             * 
             * @param value
             *     allowed object is
             *     {@link BatchStep }
             *     
             */
            public void setGenerateTransaction(BatchStep value) {
                this.generateTransaction = value;
            }

            /**
             * Gets the value of the batchProcess property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBatchProcess() {
                return batchProcess;
            }

            /**
             * Sets the value of the batchProcess property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBatchProcess(String value) {
                this.batchProcess = value;
            }

        }

    }

}
