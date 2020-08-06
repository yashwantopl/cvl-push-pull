
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="AccountDesignator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Classification" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IndustryCode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BankruptcyComparison" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}FinancialCounselingComparison" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}FinancingStatementComparsion" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}ForeclosureComparison" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}GarnishmentComparison" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}LegalItemComparison" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}MaritalItemComparison" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}MiscPublicRecordComparison" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}TaxLienComparison" minOccurs="0"/>
 *         &lt;element name="Remark" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="courtName" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="dateFiled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="dateVerified" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="referenceNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="subscriberCode" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="bureau" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
    "bankruptcyComparison",
    "financialCounselingComparison",
    "financingStatementComparsion",
    "foreclosureComparison",
    "garnishmentComparison",
    "legalItemComparison",
    "maritalItemComparison",
    "miscPublicRecordComparison",
    "taxLienComparison",
    "remark"
})
@XmlRootElement(name = "PublicRecordComparison")
public class PublicRecordComparison {

    @XmlElement(name = "AccountDesignator")
    protected boolean accountDesignator;
    @XmlElement(name = "Classification")
    protected boolean classification;
    @XmlElement(name = "IndustryCode")
    protected boolean industryCode;
    @XmlElement(name = "Status")
    protected boolean status;
    @XmlElement(name = "Type")
    protected boolean type;
    @XmlElement(name = "BankruptcyComparison")
    protected BankruptcyComparison bankruptcyComparison;
    @XmlElement(name = "FinancialCounselingComparison")
    protected FinancialCounselingComparison financialCounselingComparison;
    @XmlElement(name = "FinancingStatementComparsion")
    protected FinancingStatementComparsion financingStatementComparsion;
    @XmlElement(name = "ForeclosureComparison")
    protected ForeclosureComparison foreclosureComparison;
    @XmlElement(name = "GarnishmentComparison")
    protected GarnishmentComparison garnishmentComparison;
    @XmlElement(name = "LegalItemComparison")
    protected LegalItemComparison legalItemComparison;
    @XmlElement(name = "MaritalItemComparison")
    protected MaritalItemComparison maritalItemComparison;
    @XmlElement(name = "MiscPublicRecordComparison")
    protected MiscPublicRecordComparison miscPublicRecordComparison;
    @XmlElement(name = "TaxLienComparison")
    protected TaxLienComparison taxLienComparison;
    @XmlElement(name = "Remark")
    protected Boolean remark;
    @XmlAttribute(name = "courtName", required = true)
    protected boolean courtName;
    @XmlAttribute(name = "dateFiled")
    protected Boolean dateFiled;
    @XmlAttribute(name = "dateVerified")
    protected Boolean dateVerified;
    @XmlAttribute(name = "referenceNumber", required = true)
    protected boolean referenceNumber;
    @XmlAttribute(name = "subscriberCode", required = true)
    protected boolean subscriberCode;
    @XmlAttribute(name = "bureau", required = true)
    protected boolean bureau;

    /**
     * Gets the value of the accountDesignator property.
     * 
     */
    public boolean isAccountDesignator() {
        return accountDesignator;
    }

    /**
     * Sets the value of the accountDesignator property.
     * 
     */
    public void setAccountDesignator(boolean value) {
        this.accountDesignator = value;
    }

    /**
     * Gets the value of the classification property.
     * 
     */
    public boolean isClassification() {
        return classification;
    }

    /**
     * Sets the value of the classification property.
     * 
     */
    public void setClassification(boolean value) {
        this.classification = value;
    }

    /**
     * Gets the value of the industryCode property.
     * 
     */
    public boolean isIndustryCode() {
        return industryCode;
    }

    /**
     * Sets the value of the industryCode property.
     * 
     */
    public void setIndustryCode(boolean value) {
        this.industryCode = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(boolean value) {
        this.status = value;
    }

    /**
     * Gets the value of the type property.
     * 
     */
    public boolean isType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     */
    public void setType(boolean value) {
        this.type = value;
    }

    /**
     * Gets the value of the bankruptcyComparison property.
     * 
     * @return
     *     possible object is
     *     {@link BankruptcyComparison }
     *     
     */
    public BankruptcyComparison getBankruptcyComparison() {
        return bankruptcyComparison;
    }

    /**
     * Sets the value of the bankruptcyComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankruptcyComparison }
     *     
     */
    public void setBankruptcyComparison(BankruptcyComparison value) {
        this.bankruptcyComparison = value;
    }

    /**
     * Gets the value of the financialCounselingComparison property.
     * 
     * @return
     *     possible object is
     *     {@link FinancialCounselingComparison }
     *     
     */
    public FinancialCounselingComparison getFinancialCounselingComparison() {
        return financialCounselingComparison;
    }

    /**
     * Sets the value of the financialCounselingComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancialCounselingComparison }
     *     
     */
    public void setFinancialCounselingComparison(FinancialCounselingComparison value) {
        this.financialCounselingComparison = value;
    }

    /**
     * Gets the value of the financingStatementComparsion property.
     * 
     * @return
     *     possible object is
     *     {@link FinancingStatementComparsion }
     *     
     */
    public FinancingStatementComparsion getFinancingStatementComparsion() {
        return financingStatementComparsion;
    }

    /**
     * Sets the value of the financingStatementComparsion property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancingStatementComparsion }
     *     
     */
    public void setFinancingStatementComparsion(FinancingStatementComparsion value) {
        this.financingStatementComparsion = value;
    }

    /**
     * Gets the value of the foreclosureComparison property.
     * 
     * @return
     *     possible object is
     *     {@link ForeclosureComparison }
     *     
     */
    public ForeclosureComparison getForeclosureComparison() {
        return foreclosureComparison;
    }

    /**
     * Sets the value of the foreclosureComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link ForeclosureComparison }
     *     
     */
    public void setForeclosureComparison(ForeclosureComparison value) {
        this.foreclosureComparison = value;
    }

    /**
     * Gets the value of the garnishmentComparison property.
     * 
     * @return
     *     possible object is
     *     {@link GarnishmentComparison }
     *     
     */
    public GarnishmentComparison getGarnishmentComparison() {
        return garnishmentComparison;
    }

    /**
     * Sets the value of the garnishmentComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link GarnishmentComparison }
     *     
     */
    public void setGarnishmentComparison(GarnishmentComparison value) {
        this.garnishmentComparison = value;
    }

    /**
     * Gets the value of the legalItemComparison property.
     * 
     * @return
     *     possible object is
     *     {@link LegalItemComparison }
     *     
     */
    public LegalItemComparison getLegalItemComparison() {
        return legalItemComparison;
    }

    /**
     * Sets the value of the legalItemComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalItemComparison }
     *     
     */
    public void setLegalItemComparison(LegalItemComparison value) {
        this.legalItemComparison = value;
    }

    /**
     * Gets the value of the maritalItemComparison property.
     * 
     * @return
     *     possible object is
     *     {@link MaritalItemComparison }
     *     
     */
    public MaritalItemComparison getMaritalItemComparison() {
        return maritalItemComparison;
    }

    /**
     * Sets the value of the maritalItemComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaritalItemComparison }
     *     
     */
    public void setMaritalItemComparison(MaritalItemComparison value) {
        this.maritalItemComparison = value;
    }

    /**
     * Gets the value of the miscPublicRecordComparison property.
     * 
     * @return
     *     possible object is
     *     {@link MiscPublicRecordComparison }
     *     
     */
    public MiscPublicRecordComparison getMiscPublicRecordComparison() {
        return miscPublicRecordComparison;
    }

    /**
     * Sets the value of the miscPublicRecordComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link MiscPublicRecordComparison }
     *     
     */
    public void setMiscPublicRecordComparison(MiscPublicRecordComparison value) {
        this.miscPublicRecordComparison = value;
    }

    /**
     * Gets the value of the taxLienComparison property.
     * 
     * @return
     *     possible object is
     *     {@link TaxLienComparison }
     *     
     */
    public TaxLienComparison getTaxLienComparison() {
        return taxLienComparison;
    }

    /**
     * Sets the value of the taxLienComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxLienComparison }
     *     
     */
    public void setTaxLienComparison(TaxLienComparison value) {
        this.taxLienComparison = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRemark(Boolean value) {
        this.remark = value;
    }

    /**
     * Gets the value of the courtName property.
     * 
     */
    public boolean isCourtName() {
        return courtName;
    }

    /**
     * Sets the value of the courtName property.
     * 
     */
    public void setCourtName(boolean value) {
        this.courtName = value;
    }

    /**
     * Gets the value of the dateFiled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateFiled() {
        return dateFiled;
    }

    /**
     * Sets the value of the dateFiled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateFiled(Boolean value) {
        this.dateFiled = value;
    }

    /**
     * Gets the value of the dateVerified property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateVerified() {
        return dateVerified;
    }

    /**
     * Sets the value of the dateVerified property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateVerified(Boolean value) {
        this.dateVerified = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     */
    public boolean isReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     */
    public void setReferenceNumber(boolean value) {
        this.referenceNumber = value;
    }

    /**
     * Gets the value of the subscriberCode property.
     * 
     */
    public boolean isSubscriberCode() {
        return subscriberCode;
    }

    /**
     * Sets the value of the subscriberCode property.
     * 
     */
    public void setSubscriberCode(boolean value) {
        this.subscriberCode = value;
    }

    /**
     * Gets the value of the bureau property.
     * 
     */
    public boolean isBureau() {
        return bureau;
    }

    /**
     * Sets the value of the bureau property.
     * 
     */
    public void setBureau(boolean value) {
        this.bureau = value;
    }

}
