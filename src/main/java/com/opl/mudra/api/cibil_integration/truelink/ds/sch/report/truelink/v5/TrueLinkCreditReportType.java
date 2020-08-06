
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for TrueLinkCreditReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrueLinkCreditReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Frozen" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="bureau" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Borrower" maxOccurs="unbounded"/>
 *         &lt;element name="TradeLinePartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Tradeline" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="accountTypeDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="accountTypeSymbol" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="accountTypeAbbreviation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="InquiryPartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Inquiry" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="BankingRecordPartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BankingRecord" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PublicRecordPartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}PublicRecord" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Subscriber" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Message" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Summary"/>
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
 *                             &lt;element name="InquiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
 *         &lt;element name="ReferenceKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CreditVision" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
@XmlType(name = "TrueLinkCreditReportType", propOrder = {
    "frozen",
    "borrower",
    "tradeLinePartition",
    "inquiryPartition",
    "bankingRecordPartition",
    "publicRecordPartition",
    "subscriber",
    "message",
    "summary",
    "sources",
    "safetyCheckPassed",
    "referenceKey"
})
public class TrueLinkCreditReportType {

    @XmlElement(name = "Frozen")
    protected List<Frozen> frozen;
    @XmlElement(name = "Borrower", required = true)
    protected List<Borrower> borrower;
    @XmlElement(name = "TradeLinePartition")
    protected List<TradeLinePartition> tradeLinePartition;
    @XmlElement(name = "InquiryPartition")
    protected List<InquiryPartition> inquiryPartition;
    @XmlElement(name = "BankingRecordPartition")
    protected List<BankingRecordPartition> bankingRecordPartition;
    @XmlElement(name = "PublicRecordPartition")
    protected List<PublicRecordPartition> publicRecordPartition;
    @XmlElement(name = "Subscriber")
    protected List<Subscriber> subscriber;
    @XmlElement(name = "Message")
    protected List<Message> message;
    @XmlElement(name = "Summary", required = true)
    protected Summary summary;
    @XmlElement(name = "Sources", required = true)
    protected TrueLinkCreditReportType.Sources sources;
    @XmlElement(name = "SafetyCheckPassed")
    protected boolean safetyCheckPassed;
    @XmlElement(name = "ReferenceKey")
    protected String referenceKey;
    @XmlAttribute(name = "CreditVision")
    protected Boolean creditVision;
    @XmlAttribute(name = "FraudIndicator")
    protected Boolean fraudIndicator;
    @XmlAttribute(name = "DeceasedIndicator")
    protected Boolean deceasedIndicator;
    @XmlAttribute(name = "currentversion", namespace = "com/truelink/ds/sch/report/truelink/v5", required = true)
    protected String currentversion;

    /**
     * Gets the value of the frozen property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frozen property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrozen().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrueLinkCreditReportType.Frozen }
     *
     *
     */
    public List<Frozen> getFrozen() {
        if (frozen == null) {
            frozen = new ArrayList<Frozen>();
        }
        return this.frozen;
    }

    /**
     * Gets the value of the borrower property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrower property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBorrower().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Borrower }
     *
     *
     */
    public List<Borrower> getBorrower() {
        if (borrower == null) {
            borrower = new ArrayList<Borrower>();
        }
        return this.borrower;
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
     * {@link TrueLinkCreditReportType.TradeLinePartition }
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
     * {@link TrueLinkCreditReportType.InquiryPartition }
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
     * Gets the value of the bankingRecordPartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bankingRecordPartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBankingRecordPartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrueLinkCreditReportType.BankingRecordPartition }
     *
     *
     */
    public List<BankingRecordPartition> getBankingRecordPartition() {
        if (bankingRecordPartition == null) {
            bankingRecordPartition = new ArrayList<BankingRecordPartition>();
        }
        return this.bankingRecordPartition;
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
     * {@link TrueLinkCreditReportType.PublicRecordPartition }
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
     * Gets the value of the subscriber property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscriber property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscriber().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Subscriber }
     *
     *
     */
    public List<Subscriber> getSubscriber() {
        if (subscriber == null) {
            subscriber = new ArrayList<Subscriber>();
        }
        return this.subscriber;
    }

    /**
     * Gets the value of the message property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the message property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessage().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Message }
     *
     *
     */
    public List<Message> getMessage() {
        if (message == null) {
            message = new ArrayList<Message>();
        }
        return this.message;
    }

    /**
     * Gets the value of the summary property.
     *
     * @return
     *     possible object is
     *     {@link Summary }
     *
     */
    public Summary getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     *
     * @param value
     *     allowed object is
     *     {@link Summary }
     *
     */
    public void setSummary(Summary value) {
        this.summary = value;
    }

    /**
     * Gets the value of the sources property.
     *
     * @return
     *     possible object is
     *     {@link TrueLinkCreditReportType.Sources }
     *
     */
    public TrueLinkCreditReportType.Sources getSources() {
        return sources;
    }

    /**
     * Sets the value of the sources property.
     *
     * @param value
     *     allowed object is
     *     {@link TrueLinkCreditReportType.Sources }
     *
     */
    public void setSources(TrueLinkCreditReportType.Sources value) {
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
     * Gets the value of the referenceKey property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReferenceKey() {
        return referenceKey;
    }

    /**
     * Sets the value of the referenceKey property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReferenceKey(String value) {
        this.referenceKey = value;
    }

    /**
     * Gets the value of the creditVision property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isCreditVision() {
        return creditVision;
    }

    /**
     * Sets the value of the creditVision property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setCreditVision(Boolean value) {
        this.creditVision = value;
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
     *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BankingRecord" maxOccurs="unbounded"/>
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
        "bankingRecord"
    })
    public static class BankingRecordPartition {

        @XmlElement(name = "BankingRecord", required = true)
        protected List<BankingRecord> bankingRecord;

        /**
         * Gets the value of the bankingRecord property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the bankingRecord property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBankingRecord().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BankingRecord }
         *
         *
         */
        public List<BankingRecord> getBankingRecord() {
            if (bankingRecord == null) {
                bankingRecord = new ArrayList<BankingRecord>();
            }
            return this.bankingRecord;
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
     *         &lt;element name="bureau" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" maxOccurs="unbounded" minOccurs="0"/>
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
        "bureau"
    })
    public static class Frozen {

        @XmlElement(nillable = true)
        protected List<CodeRef> bureau;

        /**
         * Gets the value of the bureau property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the bureau property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBureau().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CodeRef }
         *
         *
         */
        public List<CodeRef> getBureau() {
            if (bureau == null) {
                bureau = new ArrayList<CodeRef>();
            }
            return this.bureau;
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
     *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Inquiry" maxOccurs="unbounded"/>
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
        "inquiry"
    })
    public static class InquiryPartition {

        @XmlElement(name = "Inquiry", required = true)
        protected List<Inquiry> inquiry;

        /**
         * Gets the value of the inquiry property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the inquiry property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInquiry().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Inquiry }
         *
         *
         */
        public List<Inquiry> getInquiry() {
            if (inquiry == null) {
                inquiry = new ArrayList<Inquiry>();
            }
            return this.inquiry;
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
     *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}PublicRecord" maxOccurs="unbounded"/>
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
        "publicRecord"
    })
    public static class PublicRecordPartition {

        @XmlElement(name = "PublicRecord", required = true)
        protected List<PublicRecord> publicRecord;

        /**
         * Gets the value of the publicRecord property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the publicRecord property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPublicRecord().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PublicRecord }
         *
         *
         */
        public List<PublicRecord> getPublicRecord() {
            if (publicRecord == null) {
                publicRecord = new ArrayList<PublicRecord>();
            }
            return this.publicRecord;
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
     *                   &lt;element name="InquiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
         * {@link TrueLinkCreditReportType.Sources.Source }
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
         *         &lt;element name="InquiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
            @XmlSchemaType(name = "dateTime")
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
     *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Tradeline" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *       &lt;attribute name="accountTypeDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="accountTypeSymbol" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="accountTypeAbbreviation" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tradeline"
    })
    public static class TradeLinePartition {

        @XmlElement(name = "Tradeline", required = true)
        protected List<Tradeline> tradeline;
        @XmlAttribute(name = "accountTypeDescription")
        protected String accountTypeDescription;
        @XmlAttribute(name = "accountTypeSymbol")
        protected String accountTypeSymbol;
        @XmlAttribute(name = "accountTypeAbbreviation")
        protected String accountTypeAbbreviation;

        /**
         * Gets the value of the tradeline property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tradeline property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTradeline().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Tradeline }
         * 
         * 
         */
        public List<Tradeline> getTradeline() {
            if (tradeline == null) {
                tradeline = new ArrayList<Tradeline>();
            }
            return this.tradeline;
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

    }

}
