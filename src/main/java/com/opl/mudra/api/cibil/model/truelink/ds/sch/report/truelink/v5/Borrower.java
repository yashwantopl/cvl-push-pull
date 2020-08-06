
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;



import com.opl.mudra.api.cibil.model.truelink.ds.sch.pii.v1.GenderEnum;

import javax.xml.bind.annotation.*;
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
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerAddress" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}PreviousAddress" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Birth" maxOccurs="unbounded"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}CreditStatement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CreditScore" type="{com/truelink/ds/sch/report/truelink/v5}CreditScoreType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Employer" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerName" maxOccurs="unbounded"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerTelephone" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EmailAddress" type="{com/truelink/ds/sch/report/truelink/v5}EmailAddressType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Gender" type="{com/truelink/ds/sch/pii/v1}GenderEnum" minOccurs="0"/>
 *         &lt;element name="IdentifierPartition" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Identifier" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="borrowerKey" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "borrowerAddress",
    "previousAddress",
    "birth",
    "creditStatement",
    "creditScore",
    "employer",
    "borrowerName",
    "borrowerTelephone",
    "emailAddress",
    "gender",
    "identifierPartition"
})
@XmlRootElement(name = "Borrower")
public class Borrower {

    @XmlElement(name = "BorrowerAddress")
    protected List<BorrowerAddress> borrowerAddress;
    @XmlElement(name = "PreviousAddress")
    protected List<PreviousAddress> previousAddress;
    @XmlElement(name = "Birth", required = true)
    protected List<Birth> birth;
    @XmlElement(name = "CreditStatement")
    protected List<CreditStatement> creditStatement;
    @XmlElement(name = "CreditScore")
    protected List<CreditScoreType> creditScore;
    @XmlElement(name = "Employer")
    protected List<Employer> employer;
    @XmlElement(name = "BorrowerName", required = true)
    protected List<BorrowerName> borrowerName;
    @XmlElement(name = "BorrowerTelephone")
    protected List<BorrowerTelephone> borrowerTelephone;
    @XmlElement(name = "EmailAddress")
    protected List<EmailAddressType> emailAddress;
    @XmlElement(name = "Gender")
    @XmlSchemaType(name = "string")
    protected GenderEnum gender;
    @XmlElement(name = "IdentifierPartition", required = true)
    protected List<IdentifierPartition> identifierPartition;
    @XmlAttribute(name = "borrowerKey")
    protected String borrowerKey;

    /**
     * Gets the value of the borrowerAddress property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrowerAddress property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBorrowerAddress().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerAddress }
     *
     *
     */
    public List<BorrowerAddress> getBorrowerAddress() {
        if (borrowerAddress == null) {
            borrowerAddress = new ArrayList<BorrowerAddress>();
        }
        return this.borrowerAddress;
    }

    /**
     * Gets the value of the previousAddress property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the previousAddress property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreviousAddress().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreviousAddress }
     *
     *
     */
    public List<PreviousAddress> getPreviousAddress() {
        if (previousAddress == null) {
            previousAddress = new ArrayList<PreviousAddress>();
        }
        return this.previousAddress;
    }

    /**
     * Gets the value of the birth property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the birth property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBirth().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Birth }
     *
     *
     */
    public List<Birth> getBirth() {
        if (birth == null) {
            birth = new ArrayList<Birth>();
        }
        return this.birth;
    }

    /**
     * Gets the value of the creditStatement property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditStatement property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditStatement().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditStatement }
     *
     *
     */
    public List<CreditStatement> getCreditStatement() {
        if (creditStatement == null) {
            creditStatement = new ArrayList<CreditStatement>();
        }
        return this.creditStatement;
    }

    /**
     * Gets the value of the creditScore property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditScore property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditScore().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditScoreType }
     *
     *
     */
    public List<CreditScoreType> getCreditScore() {
        if (creditScore == null) {
            creditScore = new ArrayList<CreditScoreType>();
        }
        return this.creditScore;
    }

    /**
     * Gets the value of the employer property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employer property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployer().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Employer }
     *
     *
     */
    public List<Employer> getEmployer() {
        if (employer == null) {
            employer = new ArrayList<Employer>();
        }
        return this.employer;
    }

    /**
     * Gets the value of the borrowerName property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrowerName property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBorrowerName().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerName }
     *
     *
     */
    public List<BorrowerName> getBorrowerName() {
        if (borrowerName == null) {
            borrowerName = new ArrayList<BorrowerName>();
        }
        return this.borrowerName;
    }

    /**
     * Gets the value of the borrowerTelephone property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrowerTelephone property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBorrowerTelephone().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerTelephone }
     *
     *
     */
    public List<BorrowerTelephone> getBorrowerTelephone() {
        if (borrowerTelephone == null) {
            borrowerTelephone = new ArrayList<BorrowerTelephone>();
        }
        return this.borrowerTelephone;
    }

    /**
     * Gets the value of the emailAddress property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emailAddress property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmailAddress().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmailAddressType }
     *
     *
     */
    public List<EmailAddressType> getEmailAddress() {
        if (emailAddress == null) {
            emailAddress = new ArrayList<EmailAddressType>();
        }
        return this.emailAddress;
    }

    /**
     * Gets the value of the gender property.
     *
     * @return
     *     possible object is
     *     {@link GenderEnum }
     *
     */
    public GenderEnum getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     *
     * @param value
     *     allowed object is
     *     {@link GenderEnum }
     *
     */
    public void setGender(GenderEnum value) {
        this.gender = value;
    }

    /**
     * Gets the value of the identifierPartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifierPartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifierPartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Borrower.IdentifierPartition }
     *
     *
     */
    public List<IdentifierPartition> getIdentifierPartition() {
        if (identifierPartition == null) {
            identifierPartition = new ArrayList<IdentifierPartition>();
        }
        return this.identifierPartition;
    }

    /**
     * Gets the value of the borrowerKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerKey() {
        return borrowerKey;
    }

    /**
     * Sets the value of the borrowerKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerKey(String value) {
        this.borrowerKey = value;
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
     *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Identifier" maxOccurs="unbounded"/>
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
        "identifier"
    })
    public static class IdentifierPartition {

        @XmlElement(name = "Identifier", required = true)
        protected List<Identifier> identifier;

        /**
         * Gets the value of the identifier property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the identifier property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIdentifier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Identifier }
         * 
         * 
         */
        public List<Identifier> getIdentifier() {
            if (identifier == null) {
                identifier = new ArrayList<Identifier>();
            }
            return this.identifier;
        }

    }

}
