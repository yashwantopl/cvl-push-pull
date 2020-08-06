
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for CONSCRED complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CONSCRED">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountPortfolioType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountFinRespTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountStatusDetailCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountOpenDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountClosedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CREDITOR" type="{http://webservice.commcons.experian.com}CREDITOR" minOccurs="0"/>
 *         &lt;element name="PAYTERMS" type="{http://webservice.commcons.experian.com}PAYTERMS" minOccurs="0"/>
 *         &lt;element name="BHISTORY" type="{http://webservice.commcons.experian.com}BHISTORY" minOccurs="0"/>
 *         &lt;element name="BPAYGRID" type="{http://webservice.commcons.experian.com}BPAYGRID" maxOccurs="36" minOccurs="0"/>
 *         &lt;element name="BORROWER" type="{http://webservice.commcons.experian.com}BORROWER" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PIDRELTN" type="{http://webservice.commcons.experian.com}PIDRELTN" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CRPLUSHST" type="{http://webservice.commcons.experian.com}CRPLUSHST" maxOccurs="6" minOccurs="0"/>
 *         &lt;element name="STMTALRT" type="{http://webservice.commcons.experian.com}STMTALRT" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DISPUTE" type="{http://webservice.commcons.experian.com}DISPUTE" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CONSCRED", propOrder = {
    "segmentCode",
    "accountNumber",
    "accountPortfolioType",
    "accountType",
    "accountFinRespTypeCd",
    "accountStatus",
    "accountStatusDetailCode",
    "accountOpenDate",
    "accountClosedDate",
    "currentBalance",
    "creditor",
    "payterms",
    "bhistory",
    "bpaygrid",
    "borrower",
    "pidreltn",
    "crplushst",
    "stmtalrt",
    "dispute"
})
public class CONSCRED {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "AccountNumber")
    protected String accountNumber;
    @XmlElement(name = "AccountPortfolioType")
    protected String accountPortfolioType;
    @XmlElement(name = "AccountType")
    protected String accountType;
    @XmlElement(name = "AccountFinRespTypeCd")
    protected String accountFinRespTypeCd;
    @XmlElement(name = "AccountStatus")
    protected String accountStatus;
    @XmlElement(name = "AccountStatusDetailCode")
    protected String accountStatusDetailCode;
    @XmlElement(name = "AccountOpenDate")
    protected String accountOpenDate;
    @XmlElement(name = "AccountClosedDate")
    protected String accountClosedDate;
    @XmlElement(name = "CurrentBalance")
    protected String currentBalance;
    @XmlElement(name = "CREDITOR")
    protected CREDITOR creditor;
    @XmlElement(name = "PAYTERMS")
    protected PAYTERMS payterms;
    @XmlElement(name = "BHISTORY")
    protected BHISTORY bhistory;
    @XmlElement(name = "BPAYGRID")
    protected List<BPAYGRID> bpaygrid;
    @XmlElement(name = "BORROWER")
    protected List<BORROWER> borrower;
    @XmlElement(name = "PIDRELTN")
    protected List<PIDRELTN> pidreltn;
    @XmlElement(name = "CRPLUSHST")
    protected List<CRPLUSHST> crplushst;
    @XmlElement(name = "STMTALRT")
    protected List<STMTALRT> stmtalrt;
    @XmlElement(name = "DISPUTE")
    protected List<DISPUTE> dispute;

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
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the accountPortfolioType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountPortfolioType() {
        return accountPortfolioType;
    }

    /**
     * Sets the value of the accountPortfolioType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountPortfolioType(String value) {
        this.accountPortfolioType = value;
    }

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountType(String value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the accountFinRespTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountFinRespTypeCd() {
        return accountFinRespTypeCd;
    }

    /**
     * Sets the value of the accountFinRespTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountFinRespTypeCd(String value) {
        this.accountFinRespTypeCd = value;
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
     * Gets the value of the accountStatusDetailCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountStatusDetailCode() {
        return accountStatusDetailCode;
    }

    /**
     * Sets the value of the accountStatusDetailCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountStatusDetailCode(String value) {
        this.accountStatusDetailCode = value;
    }

    /**
     * Gets the value of the accountOpenDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountOpenDate() {
        return accountOpenDate;
    }

    /**
     * Sets the value of the accountOpenDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountOpenDate(String value) {
        this.accountOpenDate = value;
    }

    /**
     * Gets the value of the accountClosedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountClosedDate() {
        return accountClosedDate;
    }

    /**
     * Sets the value of the accountClosedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountClosedDate(String value) {
        this.accountClosedDate = value;
    }

    /**
     * Gets the value of the currentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets the value of the currentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentBalance(String value) {
        this.currentBalance = value;
    }

    /**
     * Gets the value of the creditor property.
     * 
     * @return
     *     possible object is
     *     {@link CREDITOR }
     *     
     */
    public CREDITOR getCREDITOR() {
        return creditor;
    }

    /**
     * Sets the value of the creditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link CREDITOR }
     *     
     */
    public void setCREDITOR(CREDITOR value) {
        this.creditor = value;
    }

    /**
     * Gets the value of the payterms property.
     * 
     * @return
     *     possible object is
     *     {@link PAYTERMS }
     *     
     */
    public PAYTERMS getPAYTERMS() {
        return payterms;
    }

    /**
     * Sets the value of the payterms property.
     * 
     * @param value
     *     allowed object is
     *     {@link PAYTERMS }
     *     
     */
    public void setPAYTERMS(PAYTERMS value) {
        this.payterms = value;
    }

    /**
     * Gets the value of the bhistory property.
     * 
     * @return
     *     possible object is
     *     {@link BHISTORY }
     *     
     */
    public BHISTORY getBHISTORY() {
        return bhistory;
    }

    /**
     * Sets the value of the bhistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link BHISTORY }
     *     
     */
    public void setBHISTORY(BHISTORY value) {
        this.bhistory = value;
    }

    /**
     * Gets the value of the bpaygrid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bpaygrid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBPAYGRID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BPAYGRID }
     * 
     * 
     */
    public List<BPAYGRID> getBPAYGRID() {
        if (bpaygrid == null) {
            bpaygrid = new ArrayList<BPAYGRID>();
        }
        return this.bpaygrid;
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
     *    getBORROWER().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BORROWER }
     * 
     * 
     */
    public List<BORROWER> getBORROWER() {
        if (borrower == null) {
            borrower = new ArrayList<BORROWER>();
        }
        return this.borrower;
    }

    /**
     * Gets the value of the pidreltn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pidreltn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPIDRELTN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PIDRELTN }
     * 
     * 
     */
    public List<PIDRELTN> getPIDRELTN() {
        if (pidreltn == null) {
            pidreltn = new ArrayList<PIDRELTN>();
        }
        return this.pidreltn;
    }

    /**
     * Gets the value of the crplushst property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crplushst property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCRPLUSHST().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CRPLUSHST }
     * 
     * 
     */
    public List<CRPLUSHST> getCRPLUSHST() {
        if (crplushst == null) {
            crplushst = new ArrayList<CRPLUSHST>();
        }
        return this.crplushst;
    }

    /**
     * Gets the value of the stmtalrt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stmtalrt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSTMTALRT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link STMTALRT }
     * 
     * 
     */
    public List<STMTALRT> getSTMTALRT() {
        if (stmtalrt == null) {
            stmtalrt = new ArrayList<STMTALRT>();
        }
        return this.stmtalrt;
    }

    /**
     * Gets the value of the dispute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dispute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDISPUTE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DISPUTE }
     * 
     * 
     */
    public List<DISPUTE> getDISPUTE() {
        if (dispute == null) {
            dispute = new ArrayList<DISPUTE>();
        }
        return this.dispute;
    }

}
