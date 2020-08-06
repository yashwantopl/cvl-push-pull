
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="AccountDesignator" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="Classification" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="IndustryCode" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="Status" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="Type" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Bankruptcy" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}FinancialCounseling" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}FinancingStatement" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Foreclosure" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Garnishment" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}LegalItem" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}MaritalItem" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}MiscPublicRecord" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}TaxLien" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}RegisteredItem" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Remark" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Source"/>
 *       &lt;/sequence>
 *       &lt;attribute name="courtName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dateFiled" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateVerified" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="referenceNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="subscriberCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="bureau" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "accountDesignator",
    "classification",
    "industryCode",
    "status",
    "type",
    "expirationDate",
    "bankruptcy",
    "financialCounseling",
    "financingStatement",
    "foreclosure",
    "garnishment",
    "legalItem",
    "maritalItem",
    "miscPublicRecord",
    "taxLien",
    "registeredItem",
    "remark",
    "source"
})
@XmlRootElement(name = "PublicRecord")
public class PublicRecord {

    @XmlElement(name = "AccountDesignator", required = true)
    protected CodeRef accountDesignator;
    @XmlElement(name = "Classification", required = true)
    protected CodeRef classification;
    @XmlElement(name = "IndustryCode", required = true)
    protected CodeRef industryCode;
    @XmlElement(name = "Status", required = true)
    protected CodeRef status;
    @XmlElement(name = "Type", required = true)
    protected CodeRef type;
    @XmlElement(name = "ExpirationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDate;
    @XmlElement(name = "Bankruptcy")
    protected Bankruptcy bankruptcy;
    @XmlElement(name = "FinancialCounseling")
    protected FinancialCounseling financialCounseling;
    @XmlElement(name = "FinancingStatement")
    protected FinancingStatement financingStatement;
    @XmlElement(name = "Foreclosure")
    protected Foreclosure foreclosure;
    @XmlElement(name = "Garnishment")
    protected Garnishment garnishment;
    @XmlElement(name = "LegalItem")
    protected LegalItem legalItem;
    @XmlElement(name = "MaritalItem")
    protected MaritalItem maritalItem;
    @XmlElement(name = "MiscPublicRecord")
    protected MiscPublicRecord miscPublicRecord;
    @XmlElement(name = "TaxLien")
    protected TaxLien taxLien;
    @XmlElement(name = "RegisteredItem")
    protected RegisteredItem registeredItem;
    @XmlElement(name = "Remark")
    protected List<Remark> remark;
    @XmlElement(name = "Source", required = true)
    protected Source source;
    @XmlAttribute(name = "courtName", required = true)
    protected String courtName;
    @XmlAttribute(name = "dateFiled")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateFiled;
    @XmlAttribute(name = "dateUpdated")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateUpdated;
    @XmlAttribute(name = "dateVerified")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateVerified;
    @XmlAttribute(name = "referenceNumber", required = true)
    protected String referenceNumber;
    @XmlAttribute(name = "subscriberCode", required = true)
    protected String subscriberCode;
    @XmlAttribute(name = "bureau", required = true)
    protected String bureau;

    /**
     * Gets the value of the accountDesignator property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getAccountDesignator() {
        return accountDesignator;
    }

    /**
     * Sets the value of the accountDesignator property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setAccountDesignator(CodeRef value) {
        this.accountDesignator = value;
    }

    /**
     * Gets the value of the classification property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getClassification() {
        return classification;
    }

    /**
     * Sets the value of the classification property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setClassification(CodeRef value) {
        this.classification = value;
    }

    /**
     * Gets the value of the industryCode property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getIndustryCode() {
        return industryCode;
    }

    /**
     * Sets the value of the industryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setIndustryCode(CodeRef value) {
        this.industryCode = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setStatus(CodeRef value) {
        this.status = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setType(CodeRef value) {
        this.type = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the bankruptcy property.
     * 
     * @return
     *     possible object is
     *     {@link Bankruptcy }
     *     
     */
    public Bankruptcy getBankruptcy() {
        return bankruptcy;
    }

    /**
     * Sets the value of the bankruptcy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bankruptcy }
     *     
     */
    public void setBankruptcy(Bankruptcy value) {
        this.bankruptcy = value;
    }

    /**
     * Gets the value of the financialCounseling property.
     * 
     * @return
     *     possible object is
     *     {@link FinancialCounseling }
     *     
     */
    public FinancialCounseling getFinancialCounseling() {
        return financialCounseling;
    }

    /**
     * Sets the value of the financialCounseling property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancialCounseling }
     *     
     */
    public void setFinancialCounseling(FinancialCounseling value) {
        this.financialCounseling = value;
    }

    /**
     * Gets the value of the financingStatement property.
     * 
     * @return
     *     possible object is
     *     {@link FinancingStatement }
     *     
     */
    public FinancingStatement getFinancingStatement() {
        return financingStatement;
    }

    /**
     * Sets the value of the financingStatement property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancingStatement }
     *     
     */
    public void setFinancingStatement(FinancingStatement value) {
        this.financingStatement = value;
    }

    /**
     * Gets the value of the foreclosure property.
     * 
     * @return
     *     possible object is
     *     {@link Foreclosure }
     *     
     */
    public Foreclosure getForeclosure() {
        return foreclosure;
    }

    /**
     * Sets the value of the foreclosure property.
     * 
     * @param value
     *     allowed object is
     *     {@link Foreclosure }
     *     
     */
    public void setForeclosure(Foreclosure value) {
        this.foreclosure = value;
    }

    /**
     * Gets the value of the garnishment property.
     * 
     * @return
     *     possible object is
     *     {@link Garnishment }
     *     
     */
    public Garnishment getGarnishment() {
        return garnishment;
    }

    /**
     * Sets the value of the garnishment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Garnishment }
     *     
     */
    public void setGarnishment(Garnishment value) {
        this.garnishment = value;
    }

    /**
     * Gets the value of the legalItem property.
     * 
     * @return
     *     possible object is
     *     {@link LegalItem }
     *     
     */
    public LegalItem getLegalItem() {
        return legalItem;
    }

    /**
     * Sets the value of the legalItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalItem }
     *     
     */
    public void setLegalItem(LegalItem value) {
        this.legalItem = value;
    }

    /**
     * Gets the value of the maritalItem property.
     * 
     * @return
     *     possible object is
     *     {@link MaritalItem }
     *     
     */
    public MaritalItem getMaritalItem() {
        return maritalItem;
    }

    /**
     * Sets the value of the maritalItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaritalItem }
     *     
     */
    public void setMaritalItem(MaritalItem value) {
        this.maritalItem = value;
    }

    /**
     * Gets the value of the miscPublicRecord property.
     * 
     * @return
     *     possible object is
     *     {@link MiscPublicRecord }
     *     
     */
    public MiscPublicRecord getMiscPublicRecord() {
        return miscPublicRecord;
    }

    /**
     * Sets the value of the miscPublicRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiscPublicRecord }
     *     
     */
    public void setMiscPublicRecord(MiscPublicRecord value) {
        this.miscPublicRecord = value;
    }

    /**
     * Gets the value of the taxLien property.
     * 
     * @return
     *     possible object is
     *     {@link TaxLien }
     *     
     */
    public TaxLien getTaxLien() {
        return taxLien;
    }

    /**
     * Sets the value of the taxLien property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxLien }
     *     
     */
    public void setTaxLien(TaxLien value) {
        this.taxLien = value;
    }

    /**
     * Gets the value of the registeredItem property.
     * 
     * @return
     *     possible object is
     *     {@link RegisteredItem }
     *     
     */
    public RegisteredItem getRegisteredItem() {
        return registeredItem;
    }

    /**
     * Sets the value of the registeredItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisteredItem }
     *     
     */
    public void setRegisteredItem(RegisteredItem value) {
        this.registeredItem = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Remark }
     * 
     * 
     */
    public List<Remark> getRemark() {
        if (remark == null) {
            remark = new ArrayList<Remark>();
        }
        return this.remark;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
    }

    /**
     * Gets the value of the courtName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourtName() {
        return courtName;
    }

    /**
     * Sets the value of the courtName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourtName(String value) {
        this.courtName = value;
    }

    /**
     * Gets the value of the dateFiled property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateFiled() {
        return dateFiled;
    }

    /**
     * Sets the value of the dateFiled property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateFiled(XMLGregorianCalendar value) {
        this.dateFiled = value;
    }

    /**
     * Gets the value of the dateUpdated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Sets the value of the dateUpdated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateUpdated(XMLGregorianCalendar value) {
        this.dateUpdated = value;
    }

    /**
     * Gets the value of the dateVerified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateVerified() {
        return dateVerified;
    }

    /**
     * Sets the value of the dateVerified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateVerified(XMLGregorianCalendar value) {
        this.dateVerified = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
    }

    /**
     * Gets the value of the subscriberCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberCode() {
        return subscriberCode;
    }

    /**
     * Sets the value of the subscriberCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberCode(String value) {
        this.subscriberCode = value;
    }

    /**
     * Gets the value of the bureau property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureau() {
        return bureau;
    }

    /**
     * Sets the value of the bureau property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureau(String value) {
        this.bureau = value;
    }

}
