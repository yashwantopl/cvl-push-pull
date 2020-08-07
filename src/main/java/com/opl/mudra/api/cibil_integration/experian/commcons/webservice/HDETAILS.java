
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastPaymentDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaBalanceAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaSanctionedAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaAmountPastDue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaCreditLimitAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaCashLimitAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaActualPayAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaOrigChargOffAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaEMIAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaTotWriteOffAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaPrincWriteOffAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaSettlAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaRepaymTenure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuitFiledWilfulDefaultWrittenOffStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuitFiledWilfulDefault" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WrittenOffSettledStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DefaultStatusDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LitigationStatusDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WriteOffStatusDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditExtendedAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HighCreditAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastPaymentAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HDETAILS", propOrder = {
    "segmentCode",
    "accountDate",
    "accountStatus",
    "paymentStatus",
    "lastPaymentDate",
    "indiaBalanceAmt",
    "indiaSanctionedAmt",
    "indiaAmountPastDue",
    "indiaCreditLimitAmt",
    "indiaCashLimitAmt",
    "indiaActualPayAmt",
    "indiaOrigChargOffAmt",
    "indiaEMIAmt",
    "indiaTotWriteOffAmt",
    "indiaPrincWriteOffAmt",
    "indiaSettlAmt",
    "indiaRepaymTenure",
    "suitFiledWilfulDefaultWrittenOffStatus",
    "suitFiledWilfulDefault",
    "writtenOffSettledStatus",
    "defaultStatusDate",
    "litigationStatusDate",
    "writeOffStatusDate",
    "creditExtendedAmt",
    "highCreditAmt",
    "lastPaymentAmt"
})
public class HDETAILS {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "AccountDate")
    protected String accountDate;
    @XmlElement(name = "AccountStatus")
    protected String accountStatus;
    @XmlElement(name = "PaymentStatus")
    protected String paymentStatus;
    @XmlElement(name = "LastPaymentDate")
    protected String lastPaymentDate;
    @XmlElement(name = "IndiaBalanceAmt")
    protected String indiaBalanceAmt;
    @XmlElement(name = "IndiaSanctionedAmt")
    protected String indiaSanctionedAmt;
    @XmlElement(name = "IndiaAmountPastDue")
    protected String indiaAmountPastDue;
    @XmlElement(name = "IndiaCreditLimitAmt")
    protected String indiaCreditLimitAmt;
    @XmlElement(name = "IndiaCashLimitAmt")
    protected String indiaCashLimitAmt;
    @XmlElement(name = "IndiaActualPayAmt")
    protected String indiaActualPayAmt;
    @XmlElement(name = "IndiaOrigChargOffAmt")
    protected String indiaOrigChargOffAmt;
    @XmlElement(name = "IndiaEMIAmt")
    protected String indiaEMIAmt;
    @XmlElement(name = "IndiaTotWriteOffAmt")
    protected String indiaTotWriteOffAmt;
    @XmlElement(name = "IndiaPrincWriteOffAmt")
    protected String indiaPrincWriteOffAmt;
    @XmlElement(name = "IndiaSettlAmt")
    protected String indiaSettlAmt;
    @XmlElement(name = "IndiaRepaymTenure")
    protected String indiaRepaymTenure;
    @XmlElement(name = "SuitFiledWilfulDefaultWrittenOffStatus")
    protected String suitFiledWilfulDefaultWrittenOffStatus;
    @XmlElement(name = "SuitFiledWilfulDefault")
    protected String suitFiledWilfulDefault;
    @XmlElement(name = "WrittenOffSettledStatus")
    protected String writtenOffSettledStatus;
    @XmlElement(name = "DefaultStatusDate")
    protected String defaultStatusDate;
    @XmlElement(name = "LitigationStatusDate")
    protected String litigationStatusDate;
    @XmlElement(name = "WriteOffStatusDate")
    protected String writeOffStatusDate;
    @XmlElement(name = "CreditExtendedAmt")
    protected String creditExtendedAmt;
    @XmlElement(name = "HighCreditAmt")
    protected String highCreditAmt;
    @XmlElement(name = "LastPaymentAmt")
    protected String lastPaymentAmt;

    /**
     * Gets the value of the segmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentCode() {
        return segmentCode;
    }

    /**
     * Sets the value of the segmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentCode(String value) {
        this.segmentCode = value;
    }

    /**
     * Gets the value of the accountDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountDate() {
        return accountDate;
    }

    /**
     * Sets the value of the accountDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountDate(String value) {
        this.accountDate = value;
    }

    /**
     * Gets the value of the accountStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the value of the accountStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountStatus(String value) {
        this.accountStatus = value;
    }

    /**
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatus(String value) {
        this.paymentStatus = value;
    }

    /**
     * Gets the value of the lastPaymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    /**
     * Sets the value of the lastPaymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastPaymentDate(String value) {
        this.lastPaymentDate = value;
    }

    /**
     * Gets the value of the indiaBalanceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaBalanceAmt() {
        return indiaBalanceAmt;
    }

    /**
     * Sets the value of the indiaBalanceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaBalanceAmt(String value) {
        this.indiaBalanceAmt = value;
    }

    /**
     * Gets the value of the indiaSanctionedAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaSanctionedAmt() {
        return indiaSanctionedAmt;
    }

    /**
     * Sets the value of the indiaSanctionedAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaSanctionedAmt(String value) {
        this.indiaSanctionedAmt = value;
    }

    /**
     * Gets the value of the indiaAmountPastDue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaAmountPastDue() {
        return indiaAmountPastDue;
    }

    /**
     * Sets the value of the indiaAmountPastDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaAmountPastDue(String value) {
        this.indiaAmountPastDue = value;
    }

    /**
     * Gets the value of the indiaCreditLimitAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaCreditLimitAmt() {
        return indiaCreditLimitAmt;
    }

    /**
     * Sets the value of the indiaCreditLimitAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaCreditLimitAmt(String value) {
        this.indiaCreditLimitAmt = value;
    }

    /**
     * Gets the value of the indiaCashLimitAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaCashLimitAmt() {
        return indiaCashLimitAmt;
    }

    /**
     * Sets the value of the indiaCashLimitAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaCashLimitAmt(String value) {
        this.indiaCashLimitAmt = value;
    }

    /**
     * Gets the value of the indiaActualPayAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaActualPayAmt() {
        return indiaActualPayAmt;
    }

    /**
     * Sets the value of the indiaActualPayAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaActualPayAmt(String value) {
        this.indiaActualPayAmt = value;
    }

    /**
     * Gets the value of the indiaOrigChargOffAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaOrigChargOffAmt() {
        return indiaOrigChargOffAmt;
    }

    /**
     * Sets the value of the indiaOrigChargOffAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaOrigChargOffAmt(String value) {
        this.indiaOrigChargOffAmt = value;
    }

    /**
     * Gets the value of the indiaEMIAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaEMIAmt() {
        return indiaEMIAmt;
    }

    /**
     * Sets the value of the indiaEMIAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaEMIAmt(String value) {
        this.indiaEMIAmt = value;
    }

    /**
     * Gets the value of the indiaTotWriteOffAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaTotWriteOffAmt() {
        return indiaTotWriteOffAmt;
    }

    /**
     * Sets the value of the indiaTotWriteOffAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaTotWriteOffAmt(String value) {
        this.indiaTotWriteOffAmt = value;
    }

    /**
     * Gets the value of the indiaPrincWriteOffAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaPrincWriteOffAmt() {
        return indiaPrincWriteOffAmt;
    }

    /**
     * Sets the value of the indiaPrincWriteOffAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaPrincWriteOffAmt(String value) {
        this.indiaPrincWriteOffAmt = value;
    }

    /**
     * Gets the value of the indiaSettlAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaSettlAmt() {
        return indiaSettlAmt;
    }

    /**
     * Sets the value of the indiaSettlAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaSettlAmt(String value) {
        this.indiaSettlAmt = value;
    }

    /**
     * Gets the value of the indiaRepaymTenure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaRepaymTenure() {
        return indiaRepaymTenure;
    }

    /**
     * Sets the value of the indiaRepaymTenure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaRepaymTenure(String value) {
        this.indiaRepaymTenure = value;
    }

    /**
     * Gets the value of the suitFiledWilfulDefaultWrittenOffStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuitFiledWilfulDefaultWrittenOffStatus() {
        return suitFiledWilfulDefaultWrittenOffStatus;
    }

    /**
     * Sets the value of the suitFiledWilfulDefaultWrittenOffStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuitFiledWilfulDefaultWrittenOffStatus(String value) {
        this.suitFiledWilfulDefaultWrittenOffStatus = value;
    }

    /**
     * Gets the value of the suitFiledWilfulDefault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuitFiledWilfulDefault() {
        return suitFiledWilfulDefault;
    }

    /**
     * Sets the value of the suitFiledWilfulDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuitFiledWilfulDefault(String value) {
        this.suitFiledWilfulDefault = value;
    }

    /**
     * Gets the value of the writtenOffSettledStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWrittenOffSettledStatus() {
        return writtenOffSettledStatus;
    }

    /**
     * Sets the value of the writtenOffSettledStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWrittenOffSettledStatus(String value) {
        this.writtenOffSettledStatus = value;
    }

    /**
     * Gets the value of the defaultStatusDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultStatusDate() {
        return defaultStatusDate;
    }

    /**
     * Sets the value of the defaultStatusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultStatusDate(String value) {
        this.defaultStatusDate = value;
    }

    /**
     * Gets the value of the litigationStatusDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLitigationStatusDate() {
        return litigationStatusDate;
    }

    /**
     * Sets the value of the litigationStatusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLitigationStatusDate(String value) {
        this.litigationStatusDate = value;
    }

    /**
     * Gets the value of the writeOffStatusDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWriteOffStatusDate() {
        return writeOffStatusDate;
    }

    /**
     * Sets the value of the writeOffStatusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWriteOffStatusDate(String value) {
        this.writeOffStatusDate = value;
    }

    /**
     * Gets the value of the creditExtendedAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditExtendedAmt() {
        return creditExtendedAmt;
    }

    /**
     * Sets the value of the creditExtendedAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditExtendedAmt(String value) {
        this.creditExtendedAmt = value;
    }

    /**
     * Gets the value of the highCreditAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHighCreditAmt() {
        return highCreditAmt;
    }

    /**
     * Sets the value of the highCreditAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHighCreditAmt(String value) {
        this.highCreditAmt = value;
    }

    /**
     * Gets the value of the lastPaymentAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastPaymentAmt() {
        return lastPaymentAmt;
    }

    /**
     * Sets the value of the lastPaymentAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastPaymentAmt(String value) {
        this.lastPaymentAmt = value;
    }

}
