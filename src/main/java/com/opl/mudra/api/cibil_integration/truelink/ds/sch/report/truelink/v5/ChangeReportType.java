
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ChangeReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChangeReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SB168Frozen">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                 &lt;/sequence>
 *                 &lt;attribute name="old" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                 &lt;attribute name="new" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                 &lt;attribute name="changed" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerComparison" maxOccurs="unbounded"/>
 *         &lt;element name="TradeLinePartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Tradeline"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}TradelineComparison"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="accountTypeDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="accountTypeSymbol" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="accountTypeAbbreviation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="InquiryPartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}InquiryComparison"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Inquiry"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PublicRecordPartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}PublicRecordComparison"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}PublicRecord"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SubscriberPartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Subscriber"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}SubscriberComparison"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MessagePartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}MessageComparison"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Message"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SummaryPartition">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}SummaryComparison"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Summary"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Sources">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Source" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Bureau" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *                             &lt;element name="InquiryDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                             &lt;element name="OriginalData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SafetyCheckPassed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="FraudIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DeceasedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute ref="{com/truelink/ds/sch/report/truelink/v5}currentversion use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangeReportType", propOrder = {
    "sb168Frozen",
    "borrowerComparison",
    "tradeLinePartition",
    "inquiryPartition",
    "publicRecordPartition",
    "subscriberPartition",
    "messagePartition",
    "summaryPartition",
    "sources",
    "safetyCheckPassed"
})
public class ChangeReportType {

    @XmlElement(name = "SB168Frozen", required = true)
    protected ChangeReportType.SB168Frozen sb168Frozen;
    @XmlElement(name = "BorrowerComparison", required = true)
    protected List<BorrowerComparison> borrowerComparison;
    @XmlElement(name = "TradeLinePartition")
    protected List<TradeLinePartition> tradeLinePartition;
    @XmlElement(name = "InquiryPartition")
    protected List<InquiryPartition> inquiryPartition;
    @XmlElement(name = "PublicRecordPartition")
    protected List<PublicRecordPartition> publicRecordPartition;
    @XmlElement(name = "SubscriberPartition")
    protected List<SubscriberPartition> subscriberPartition;
    @XmlElement(name = "MessagePartition")
    protected List<MessagePartition> messagePartition;
    @XmlElement(name = "SummaryPartition", required = true)
    protected ChangeReportType.SummaryPartition summaryPartition;
    @XmlElement(name = "Sources", required = true)
    protected ChangeReportType.Sources sources;
    @XmlElement(name = "SafetyCheckPassed")
    protected boolean safetyCheckPassed;
    @XmlAttribute(name = "FraudIndicator")
    protected Boolean fraudIndicator;
    @XmlAttribute(name = "DeceasedIndicator")
    protected Boolean deceasedIndicator;
    @XmlAttribute(name = "currentversion", namespace = "com/truelink/ds/sch/report/truelink/v5", required = true)
    protected String currentversion;

    /**
     * Gets the value of the sb168Frozen property.
     *
     * @return
     *     possible object is
     *     {@link ChangeReportType.SB168Frozen }
     *
     */
    public ChangeReportType.SB168Frozen getSB168Frozen() {
        return sb168Frozen;
    }

    /**
     * Sets the value of the sb168Frozen property.
     *
     * @param value
     *     allowed object is
     *     {@link ChangeReportType.SB168Frozen }
     *
     */
    public void setSB168Frozen(ChangeReportType.SB168Frozen value) {
        this.sb168Frozen = value;
    }

    /**
     * Gets the value of the borrowerComparison property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrowerComparison property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBorrowerComparison().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerComparison }
     *
     *
     */
    public List<BorrowerComparison> getBorrowerComparison() {
        if (borrowerComparison == null) {
            borrowerComparison = new ArrayList<BorrowerComparison>();
        }
        return this.borrowerComparison;
    }

    /**
     * Gets the value of the tradeLinePartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tradeLinePartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTradeLinePartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChangeReportType.TradeLinePartition }
     *
     *
     */
    public List<TradeLinePartition> getTradeLinePartition() {
        if (tradeLinePartition == null) {
            tradeLinePartition = new ArrayList<TradeLinePartition>();
        }
        return this.tradeLinePartition;
    }

    /**
     * Gets the value of the inquiryPartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inquiryPartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInquiryPartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChangeReportType.InquiryPartition }
     *
     *
     */
    public List<InquiryPartition> getInquiryPartition() {
        if (inquiryPartition == null) {
            inquiryPartition = new ArrayList<InquiryPartition>();
        }
        return this.inquiryPartition;
    }

    /**
     * Gets the value of the publicRecordPartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the publicRecordPartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPublicRecordPartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChangeReportType.PublicRecordPartition }
     *
     *
     */
    public List<PublicRecordPartition> getPublicRecordPartition() {
        if (publicRecordPartition == null) {
            publicRecordPartition = new ArrayList<PublicRecordPartition>();
        }
        return this.publicRecordPartition;
    }

    /**
     * Gets the value of the subscriberPartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscriberPartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscriberPartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChangeReportType.SubscriberPartition }
     *
     *
     */
    public List<SubscriberPartition> getSubscriberPartition() {
        if (subscriberPartition == null) {
            subscriberPartition = new ArrayList<SubscriberPartition>();
        }
        return this.subscriberPartition;
    }

    /**
     * Gets the value of the messagePartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messagePartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessagePartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChangeReportType.MessagePartition }
     *
     *
     */
    public List<MessagePartition> getMessagePartition() {
        if (messagePartition == null) {
            messagePartition = new ArrayList<MessagePartition>();
        }
        return this.messagePartition;
    }

    /**
     * Gets the value of the summaryPartition property.
     *
     * @return
     *     possible object is
     *     {@link ChangeReportType.SummaryPartition }
     *
     */
    public ChangeReportType.SummaryPartition getSummaryPartition() {
        return summaryPartition;
    }

    /**
     * Sets the value of the summaryPartition property.
     *
     * @param value
     *     allowed object is
     *     {@link ChangeReportType.SummaryPartition }
     *
     */
    public void setSummaryPartition(ChangeReportType.SummaryPartition value) {
        this.summaryPartition = value;
    }

    /**
     * Gets the value of the sources property.
     *
     * @return
     *     possible object is
     *     {@link ChangeReportType.Sources }
     *
     */
    public ChangeReportType.Sources getSources() {
        return sources;
    }

    /**
     * Sets the value of the sources property.
     *
     * @param value
     *     allowed object is
     *     {@link ChangeReportType.Sources }
     *
     */
    public void setSources(ChangeReportType.Sources value) {
        this.sources = value;
    }

    /**
     * Gets the value of the safetyCheckPassed property.
     *
     */
    public boolean isSafetyCheckPassed() {
        return safetyCheckPassed;
    }

    /**
     * Sets the value of the safetyCheckPassed property.
     *
     */
    public void setSafetyCheckPassed(boolean value) {
        this.safetyCheckPassed = value;
    }

    /**
     * Gets the value of the fraudIndicator property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isFraudIndicator() {
        return fraudIndicator;
    }

    /**
     * Sets the value of the fraudIndicator property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setFraudIndicator(Boolean value) {
        this.fraudIndicator = value;
    }

    /**
     * Gets the value of the deceasedIndicator property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isDeceasedIndicator() {
        return deceasedIndicator;
    }

    /**
     * Sets the value of the deceasedIndicator property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setDeceasedIndicator(Boolean value) {
        this.deceasedIndicator = value;
    }

    /**
     * Gets the value of the currentversion property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCurrentversion() {
        return currentversion;
    }

    /**
     * Sets the value of the currentversion property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCurrentversion(String value) {
        this.currentversion = value;
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
     *         &lt;choice maxOccurs="unbounded" minOccurs="0">
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}InquiryComparison"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Inquiry"/>
     *         &lt;/choice>
     *       &lt;/sequence>
     *       &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "inquiryComparisonOrInquiry"
    })
    public static class InquiryPartition {

        @XmlElements({
            @XmlElement(name = "InquiryComparison", type = InquiryComparison.class),
            @XmlElement(name = "Inquiry", type = Inquiry.class)
        })
        protected List<Object> inquiryComparisonOrInquiry;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the inquiryComparisonOrInquiry property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the inquiryComparisonOrInquiry property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInquiryComparisonOrInquiry().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InquiryComparison }
         * {@link Inquiry }
         *
         *
         */
        public List<Object> getInquiryComparisonOrInquiry() {
            if (inquiryComparisonOrInquiry == null) {
                inquiryComparisonOrInquiry = new ArrayList<Object>();
            }
            return this.inquiryComparisonOrInquiry;
        }

        /**
         * Gets the value of the changed property.
         *
         * @return
         *     possible object is
         *     {@link ChangeIndicatorType }
         *
         */
        public ChangeIndicatorType getChanged() {
            return changed;
        }

        /**
         * Sets the value of the changed property.
         *
         * @param value
         *     allowed object is
         *     {@link ChangeIndicatorType }
         *
         */
        public void setChanged(ChangeIndicatorType value) {
            this.changed = value;
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
     *         &lt;choice maxOccurs="unbounded" minOccurs="0">
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}MessageComparison"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Message"/>
     *         &lt;/choice>
     *       &lt;/sequence>
     *       &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "messageComparisonOrMessage"
    })
    public static class MessagePartition {

        @XmlElements({
            @XmlElement(name = "MessageComparison", type = MessageComparison.class),
            @XmlElement(name = "Message", type = Message.class)
        })
        protected List<Object> messageComparisonOrMessage;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the messageComparisonOrMessage property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the messageComparisonOrMessage property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMessageComparisonOrMessage().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MessageComparison }
         * {@link Message }
         *
         *
         */
        public List<Object> getMessageComparisonOrMessage() {
            if (messageComparisonOrMessage == null) {
                messageComparisonOrMessage = new ArrayList<Object>();
            }
            return this.messageComparisonOrMessage;
        }

        /**
         * Gets the value of the changed property.
         *
         * @return
         *     possible object is
         *     {@link ChangeIndicatorType }
         *
         */
        public ChangeIndicatorType getChanged() {
            return changed;
        }

        /**
         * Sets the value of the changed property.
         *
         * @param value
         *     allowed object is
         *     {@link ChangeIndicatorType }
         *
         */
        public void setChanged(ChangeIndicatorType value) {
            this.changed = value;
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
     *         &lt;choice maxOccurs="unbounded" minOccurs="0">
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}PublicRecordComparison"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}PublicRecord"/>
     *         &lt;/choice>
     *       &lt;/sequence>
     *       &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "publicRecordComparisonOrPublicRecord"
    })
    public static class PublicRecordPartition {

        @XmlElements({
            @XmlElement(name = "PublicRecordComparison", type = PublicRecordComparison.class),
            @XmlElement(name = "PublicRecord", type = PublicRecord.class)
        })
        protected List<Object> publicRecordComparisonOrPublicRecord;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the publicRecordComparisonOrPublicRecord property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the publicRecordComparisonOrPublicRecord property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPublicRecordComparisonOrPublicRecord().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PublicRecordComparison }
         * {@link PublicRecord }
         *
         *
         */
        public List<Object> getPublicRecordComparisonOrPublicRecord() {
            if (publicRecordComparisonOrPublicRecord == null) {
                publicRecordComparisonOrPublicRecord = new ArrayList<Object>();
            }
            return this.publicRecordComparisonOrPublicRecord;
        }

        /**
         * Gets the value of the changed property.
         *
         * @return
         *     possible object is
         *     {@link ChangeIndicatorType }
         *
         */
        public ChangeIndicatorType getChanged() {
            return changed;
        }

        /**
         * Sets the value of the changed property.
         *
         * @param value
         *     allowed object is
         *     {@link ChangeIndicatorType }
         *
         */
        public void setChanged(ChangeIndicatorType value) {
            this.changed = value;
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
     *       &lt;/sequence>
     *       &lt;attribute name="old" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *       &lt;attribute name="new" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *       &lt;attribute name="changed" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class SB168Frozen {

        @XmlAttribute(name = "old", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String old;
        @XmlAttribute(name = "new", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String _new;
        @XmlAttribute(name = "changed", required = true)
        protected boolean changed;

        /**
         * Gets the value of the old property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getOld() {
            return old;
        }

        /**
         * Sets the value of the old property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setOld(String value) {
            this.old = value;
        }

        /**
         * Gets the value of the new property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getNew() {
            return _new;
        }

        /**
         * Sets the value of the new property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setNew(String value) {
            this._new = value;
        }

        /**
         * Gets the value of the changed property.
         *
         */
        public boolean isChanged() {
            return changed;
        }

        /**
         * Sets the value of the changed property.
         *
         */
        public void setChanged(boolean value) {
            this.changed = value;
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
     *         &lt;element name="Source" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Bureau" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
     *                   &lt;element name="InquiryDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *                   &lt;element name="OriginalData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "source"
    })
    public static class Sources {

        @XmlElement(name = "Source", required = true)
        protected List<Source> source;

        /**
         * Gets the value of the source property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the source property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSource().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ChangeReportType.Sources.Source }
         *
         *
         */
        public List<Source> getSource() {
            if (source == null) {
                source = new ArrayList<Source>();
            }
            return this.source;
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
         *         &lt;element name="Bureau" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
         *         &lt;element name="InquiryDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
         *         &lt;element name="OriginalData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
            "bureau",
            "inquiryDate",
            "originalData"
        })
        public static class Source {

            @XmlElement(name = "Bureau", required = true)
            protected CodeRef bureau;
            @XmlElement(name = "InquiryDate", required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar inquiryDate;
            @XmlElement(name = "OriginalData", required = true)
            protected byte[] originalData;

            /**
             * Gets the value of the bureau property.
             * 
             * @return
             *     possible object is
             *     {@link CodeRef }
             *     
             */
            public CodeRef getBureau() {
                return bureau;
            }

            /**
             * Sets the value of the bureau property.
             * 
             * @param value
             *     allowed object is
             *     {@link CodeRef }
             *     
             */
            public void setBureau(CodeRef value) {
                this.bureau = value;
            }

            /**
             * Gets the value of the inquiryDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getInquiryDate() {
                return inquiryDate;
            }

            /**
             * Sets the value of the inquiryDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setInquiryDate(XMLGregorianCalendar value) {
                this.inquiryDate = value;
            }

            /**
             * Gets the value of the originalData property.
             * 
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getOriginalData() {
                return originalData;
            }

            /**
             * Sets the value of the originalData property.
             * 
             * @param value
             *     allowed object is
             *     byte[]
             */
            public void setOriginalData(byte[] value) {
                this.originalData = value;
            }

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
     *         &lt;choice maxOccurs="unbounded" minOccurs="0">
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Subscriber"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}SubscriberComparison"/>
     *         &lt;/choice>
     *       &lt;/sequence>
     *       &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "subscriberOrSubscriberComparison"
    })
    public static class SubscriberPartition {

        @XmlElements({
            @XmlElement(name = "Subscriber", type = Subscriber.class),
            @XmlElement(name = "SubscriberComparison", type = SubscriberComparison.class)
        })
        protected List<Object> subscriberOrSubscriberComparison;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the subscriberOrSubscriberComparison property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the subscriberOrSubscriberComparison property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSubscriberOrSubscriberComparison().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Subscriber }
         * {@link SubscriberComparison }
         * 
         * 
         */
        public List<Object> getSubscriberOrSubscriberComparison() {
            if (subscriberOrSubscriberComparison == null) {
                subscriberOrSubscriberComparison = new ArrayList<Object>();
            }
            return this.subscriberOrSubscriberComparison;
        }

        /**
         * Gets the value of the changed property.
         * 
         * @return
         *     possible object is
         *     {@link ChangeIndicatorType }
         *     
         */
        public ChangeIndicatorType getChanged() {
            return changed;
        }

        /**
         * Sets the value of the changed property.
         * 
         * @param value
         *     allowed object is
         *     {@link ChangeIndicatorType }
         *     
         */
        public void setChanged(ChangeIndicatorType value) {
            this.changed = value;
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
     *         &lt;choice maxOccurs="unbounded" minOccurs="0">
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}SummaryComparison"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Summary"/>
     *         &lt;/choice>
     *       &lt;/sequence>
     *       &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "summaryComparisonOrSummary"
    })
    public static class SummaryPartition {

        @XmlElements({
            @XmlElement(name = "SummaryComparison", type = SummaryComparison.class),
            @XmlElement(name = "Summary", type = Summary.class)
        })
        protected List<Object> summaryComparisonOrSummary;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the summaryComparisonOrSummary property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the summaryComparisonOrSummary property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSummaryComparisonOrSummary().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SummaryComparison }
         * {@link Summary }
         * 
         * 
         */
        public List<Object> getSummaryComparisonOrSummary() {
            if (summaryComparisonOrSummary == null) {
                summaryComparisonOrSummary = new ArrayList<Object>();
            }
            return this.summaryComparisonOrSummary;
        }

        /**
         * Gets the value of the changed property.
         * 
         * @return
         *     possible object is
         *     {@link ChangeIndicatorType }
         *     
         */
        public ChangeIndicatorType getChanged() {
            return changed;
        }

        /**
         * Sets the value of the changed property.
         * 
         * @param value
         *     allowed object is
         *     {@link ChangeIndicatorType }
         *     
         */
        public void setChanged(ChangeIndicatorType value) {
            this.changed = value;
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
     *         &lt;choice maxOccurs="unbounded" minOccurs="0">
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Tradeline"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}TradelineComparison"/>
     *         &lt;/choice>
     *       &lt;/sequence>
     *       &lt;attribute name="accountTypeDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="accountTypeSymbol" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="accountTypeAbbreviation" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tradelineOrTradelineComparison"
    })
    public static class TradeLinePartition {

        @XmlElements({
            @XmlElement(name = "Tradeline", type = Tradeline.class),
            @XmlElement(name = "TradelineComparison", type = TradelineComparison.class)
        })
        protected List<Object> tradelineOrTradelineComparison;
        @XmlAttribute(name = "accountTypeDescription")
        protected String accountTypeDescription;
        @XmlAttribute(name = "accountTypeSymbol")
        protected String accountTypeSymbol;
        @XmlAttribute(name = "accountTypeAbbreviation")
        protected String accountTypeAbbreviation;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the tradelineOrTradelineComparison property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tradelineOrTradelineComparison property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTradelineOrTradelineComparison().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Tradeline }
         * {@link TradelineComparison }
         * 
         * 
         */
        public List<Object> getTradelineOrTradelineComparison() {
            if (tradelineOrTradelineComparison == null) {
                tradelineOrTradelineComparison = new ArrayList<Object>();
            }
            return this.tradelineOrTradelineComparison;
        }

        /**
         * Gets the value of the accountTypeDescription property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccountTypeDescription() {
            return accountTypeDescription;
        }

        /**
         * Sets the value of the accountTypeDescription property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccountTypeDescription(String value) {
            this.accountTypeDescription = value;
        }

        /**
         * Gets the value of the accountTypeSymbol property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccountTypeSymbol() {
            return accountTypeSymbol;
        }

        /**
         * Sets the value of the accountTypeSymbol property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccountTypeSymbol(String value) {
            this.accountTypeSymbol = value;
        }

        /**
         * Gets the value of the accountTypeAbbreviation property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccountTypeAbbreviation() {
            return accountTypeAbbreviation;
        }

        /**
         * Sets the value of the accountTypeAbbreviation property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccountTypeAbbreviation(String value) {
            this.accountTypeAbbreviation = value;
        }

        /**
         * Gets the value of the changed property.
         * 
         * @return
         *     possible object is
         *     {@link ChangeIndicatorType }
         *     
         */
        public ChangeIndicatorType getChanged() {
            return changed;
        }

        /**
         * Sets the value of the changed property.
         * 
         * @param value
         *     allowed object is
         *     {@link ChangeIndicatorType }
         *     
         */
        public void setChanged(ChangeIndicatorType value) {
            this.changed = value;
        }

    }

}
