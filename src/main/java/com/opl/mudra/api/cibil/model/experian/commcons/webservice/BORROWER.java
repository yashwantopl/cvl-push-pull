
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for BORROWER complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BORROWER">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FinResp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctResp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TerminationReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerLastReportedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerDOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerGender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerMaritalStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerOccupationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerIncomeAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerMonthlyFamilyExpenseAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerPINCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerLocationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerPhoneNumberType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerWebAddrType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerWebAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NetMonthlyIncome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OccYearEmployed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OccMonthsEmployed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Income_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Income_Frequency_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BORROWERNM" type="{http://webservice.commcons.experian.com}BORROWERNM" maxOccurs="9" minOccurs="0"/>
 *         &lt;element name="BORROWERAD" type="{http://webservice.commcons.experian.com}BORROWERAD" maxOccurs="9" minOccurs="0"/>
 *         &lt;element name="BORROWERPH" type="{http://webservice.commcons.experian.com}BORROWERPH" maxOccurs="9" minOccurs="0"/>
 *         &lt;element name="BIDCARDS" type="{http://webservice.commcons.experian.com}BIDCARDS" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BORROWER", propOrder = {
    "segmentCode",
    "borrowerName",
    "borrowerTypeCd",
    "finResp",
    "pctResp",
    "startDate",
    "endDate",
    "terminationReason",
    "borrowerLastReportedDate",
    "borrowerDOB",
    "borrowerGender",
    "borrowerMaritalStatus",
    "borrowerOccupationCode",
    "borrowerIncomeAmt",
    "borrowerMonthlyFamilyExpenseAmt",
    "borrowerAddress",
    "borrowerCity",
    "borrowerPINCode",
    "borrowerLocationType",
    "borrowerPhoneNumberType",
    "borrowerPhoneNumber",
    "borrowerWebAddrType",
    "borrowerWebAddress",
    "netMonthlyIncome",
    "occYearEmployed",
    "occMonthsEmployed",
    "incomeIndicator",
    "incomeFrequencyIndicator",
    "borrowernm",
    "borrowerad",
    "borrowerph",
    "bidcards"
})
public class BORROWER {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "BorrowerName")
    protected String borrowerName;
    @XmlElement(name = "BorrowerTypeCd")
    protected String borrowerTypeCd;
    @XmlElement(name = "FinResp")
    protected String finResp;
    @XmlElement(name = "PctResp")
    protected String pctResp;
    @XmlElement(name = "StartDate")
    protected String startDate;
    @XmlElement(name = "EndDate")
    protected String endDate;
    @XmlElement(name = "TerminationReason")
    protected String terminationReason;
    @XmlElement(name = "BorrowerLastReportedDate")
    protected String borrowerLastReportedDate;
    @XmlElement(name = "BorrowerDOB")
    protected String borrowerDOB;
    @XmlElement(name = "BorrowerGender")
    protected String borrowerGender;
    @XmlElement(name = "BorrowerMaritalStatus")
    protected String borrowerMaritalStatus;
    @XmlElement(name = "BorrowerOccupationCode")
    protected String borrowerOccupationCode;
    @XmlElement(name = "BorrowerIncomeAmt")
    protected String borrowerIncomeAmt;
    @XmlElement(name = "BorrowerMonthlyFamilyExpenseAmt")
    protected String borrowerMonthlyFamilyExpenseAmt;
    @XmlElement(name = "BorrowerAddress")
    protected String borrowerAddress;
    @XmlElement(name = "BorrowerCity")
    protected String borrowerCity;
    @XmlElement(name = "BorrowerPINCode")
    protected String borrowerPINCode;
    @XmlElement(name = "BorrowerLocationType")
    protected String borrowerLocationType;
    @XmlElement(name = "BorrowerPhoneNumberType")
    protected String borrowerPhoneNumberType;
    @XmlElement(name = "BorrowerPhoneNumber")
    protected String borrowerPhoneNumber;
    @XmlElement(name = "BorrowerWebAddrType")
    protected String borrowerWebAddrType;
    @XmlElement(name = "BorrowerWebAddress")
    protected String borrowerWebAddress;
    @XmlElement(name = "NetMonthlyIncome")
    protected String netMonthlyIncome;
    @XmlElement(name = "OccYearEmployed")
    protected String occYearEmployed;
    @XmlElement(name = "OccMonthsEmployed")
    protected String occMonthsEmployed;
    @XmlElement(name = "Income_Indicator")
    protected String incomeIndicator;
    @XmlElement(name = "Income_Frequency_Indicator")
    protected String incomeFrequencyIndicator;
    @XmlElement(name = "BORROWERNM")
    protected List<BORROWERNM> borrowernm;
    @XmlElement(name = "BORROWERAD")
    protected List<BORROWERAD> borrowerad;
    @XmlElement(name = "BORROWERPH")
    protected List<BORROWERPH> borrowerph;
    @XmlElement(name = "BIDCARDS")
    protected List<BIDCARDS> bidcards;

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
     * Gets the value of the borrowerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerName() {
        return borrowerName;
    }

    /**
     * Sets the value of the borrowerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerName(String value) {
        this.borrowerName = value;
    }

    /**
     * Gets the value of the borrowerTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerTypeCd() {
        return borrowerTypeCd;
    }

    /**
     * Sets the value of the borrowerTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerTypeCd(String value) {
        this.borrowerTypeCd = value;
    }

    /**
     * Gets the value of the finResp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinResp() {
        return finResp;
    }

    /**
     * Sets the value of the finResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinResp(String value) {
        this.finResp = value;
    }

    /**
     * Gets the value of the pctResp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctResp() {
        return pctResp;
    }

    /**
     * Sets the value of the pctResp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctResp(String value) {
        this.pctResp = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the terminationReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminationReason() {
        return terminationReason;
    }

    /**
     * Sets the value of the terminationReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminationReason(String value) {
        this.terminationReason = value;
    }

    /**
     * Gets the value of the borrowerLastReportedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerLastReportedDate() {
        return borrowerLastReportedDate;
    }

    /**
     * Sets the value of the borrowerLastReportedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerLastReportedDate(String value) {
        this.borrowerLastReportedDate = value;
    }

    /**
     * Gets the value of the borrowerDOB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerDOB() {
        return borrowerDOB;
    }

    /**
     * Sets the value of the borrowerDOB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerDOB(String value) {
        this.borrowerDOB = value;
    }

    /**
     * Gets the value of the borrowerGender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerGender() {
        return borrowerGender;
    }

    /**
     * Sets the value of the borrowerGender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerGender(String value) {
        this.borrowerGender = value;
    }

    /**
     * Gets the value of the borrowerMaritalStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerMaritalStatus() {
        return borrowerMaritalStatus;
    }

    /**
     * Sets the value of the borrowerMaritalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerMaritalStatus(String value) {
        this.borrowerMaritalStatus = value;
    }

    /**
     * Gets the value of the borrowerOccupationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerOccupationCode() {
        return borrowerOccupationCode;
    }

    /**
     * Sets the value of the borrowerOccupationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerOccupationCode(String value) {
        this.borrowerOccupationCode = value;
    }

    /**
     * Gets the value of the borrowerIncomeAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerIncomeAmt() {
        return borrowerIncomeAmt;
    }

    /**
     * Sets the value of the borrowerIncomeAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerIncomeAmt(String value) {
        this.borrowerIncomeAmt = value;
    }

    /**
     * Gets the value of the borrowerMonthlyFamilyExpenseAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerMonthlyFamilyExpenseAmt() {
        return borrowerMonthlyFamilyExpenseAmt;
    }

    /**
     * Sets the value of the borrowerMonthlyFamilyExpenseAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerMonthlyFamilyExpenseAmt(String value) {
        this.borrowerMonthlyFamilyExpenseAmt = value;
    }

    /**
     * Gets the value of the borrowerAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerAddress() {
        return borrowerAddress;
    }

    /**
     * Sets the value of the borrowerAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerAddress(String value) {
        this.borrowerAddress = value;
    }

    /**
     * Gets the value of the borrowerCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerCity() {
        return borrowerCity;
    }

    /**
     * Sets the value of the borrowerCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerCity(String value) {
        this.borrowerCity = value;
    }

    /**
     * Gets the value of the borrowerPINCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerPINCode() {
        return borrowerPINCode;
    }

    /**
     * Sets the value of the borrowerPINCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerPINCode(String value) {
        this.borrowerPINCode = value;
    }

    /**
     * Gets the value of the borrowerLocationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerLocationType() {
        return borrowerLocationType;
    }

    /**
     * Sets the value of the borrowerLocationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerLocationType(String value) {
        this.borrowerLocationType = value;
    }

    /**
     * Gets the value of the borrowerPhoneNumberType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerPhoneNumberType() {
        return borrowerPhoneNumberType;
    }

    /**
     * Sets the value of the borrowerPhoneNumberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerPhoneNumberType(String value) {
        this.borrowerPhoneNumberType = value;
    }

    /**
     * Gets the value of the borrowerPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerPhoneNumber() {
        return borrowerPhoneNumber;
    }

    /**
     * Sets the value of the borrowerPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerPhoneNumber(String value) {
        this.borrowerPhoneNumber = value;
    }

    /**
     * Gets the value of the borrowerWebAddrType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerWebAddrType() {
        return borrowerWebAddrType;
    }

    /**
     * Sets the value of the borrowerWebAddrType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerWebAddrType(String value) {
        this.borrowerWebAddrType = value;
    }

    /**
     * Gets the value of the borrowerWebAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerWebAddress() {
        return borrowerWebAddress;
    }

    /**
     * Sets the value of the borrowerWebAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerWebAddress(String value) {
        this.borrowerWebAddress = value;
    }

    /**
     * Gets the value of the netMonthlyIncome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetMonthlyIncome() {
        return netMonthlyIncome;
    }

    /**
     * Sets the value of the netMonthlyIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetMonthlyIncome(String value) {
        this.netMonthlyIncome = value;
    }

    /**
     * Gets the value of the occYearEmployed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccYearEmployed() {
        return occYearEmployed;
    }

    /**
     * Sets the value of the occYearEmployed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccYearEmployed(String value) {
        this.occYearEmployed = value;
    }

    /**
     * Gets the value of the occMonthsEmployed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccMonthsEmployed() {
        return occMonthsEmployed;
    }

    /**
     * Sets the value of the occMonthsEmployed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccMonthsEmployed(String value) {
        this.occMonthsEmployed = value;
    }

    /**
     * Gets the value of the incomeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncomeIndicator() {
        return incomeIndicator;
    }

    /**
     * Sets the value of the incomeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncomeIndicator(String value) {
        this.incomeIndicator = value;
    }

    /**
     * Gets the value of the incomeFrequencyIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncomeFrequencyIndicator() {
        return incomeFrequencyIndicator;
    }

    /**
     * Sets the value of the incomeFrequencyIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncomeFrequencyIndicator(String value) {
        this.incomeFrequencyIndicator = value;
    }

    /**
     * Gets the value of the borrowernm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrowernm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBORROWERNM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BORROWERNM }
     * 
     * 
     */
    public List<BORROWERNM> getBORROWERNM() {
        if (borrowernm == null) {
            borrowernm = new ArrayList<BORROWERNM>();
        }
        return this.borrowernm;
    }

    /**
     * Gets the value of the borrowerad property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrowerad property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBORROWERAD().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BORROWERAD }
     * 
     * 
     */
    public List<BORROWERAD> getBORROWERAD() {
        if (borrowerad == null) {
            borrowerad = new ArrayList<BORROWERAD>();
        }
        return this.borrowerad;
    }

    /**
     * Gets the value of the borrowerph property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrowerph property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBORROWERPH().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BORROWERPH }
     * 
     * 
     */
    public List<BORROWERPH> getBORROWERPH() {
        if (borrowerph == null) {
            borrowerph = new ArrayList<BORROWERPH>();
        }
        return this.borrowerph;
    }

    /**
     * Gets the value of the bidcards property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bidcards property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBIDCARDS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BIDCARDS }
     * 
     * 
     */
    public List<BIDCARDS> getBIDCARDS() {
        if (bidcards == null) {
            bidcards = new ArrayList<BIDCARDS>();
        }
        return this.bidcards;
    }

}
