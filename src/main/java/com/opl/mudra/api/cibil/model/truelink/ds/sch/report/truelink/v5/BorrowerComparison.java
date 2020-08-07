
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
 *         &lt;element name="AddressPartition" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerAddressComparison"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerAddress"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PreviousAddressPartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerAddressComparison"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerAddress"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="EmailPartition" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="EmailAddress" type="{com/truelink/ds/sch/report/truelink/v5}EmailAddressType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="BirthPartition" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BirthComparison"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Birth"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="GenderPartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Gender" type="{com/truelink/ds/sch/pii/v1}GenderEnum" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="BorrowerNamePartition" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerName"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerNameComparison"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CreditStatementPartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}CreditStatementComparison"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}CreditStatement"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="EmployerPartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}EmployerComparison"/>
 *                     &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Employer"/>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Changed" use="required" type="{com/truelink/ds/sch/report/truelink/v5}ChangeIndicatorType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
 *         &lt;element name="CreditScorePartition" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CreditScore" type="{com/truelink/ds/sch/report/truelink/v5}CreditScoreType" maxOccurs="unbounded"/>
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
    "addressPartition",
    "previousAddressPartition",
    "emailPartition",
    "birthPartition",
    "genderPartition",
    "borrowerNamePartition",
    "creditStatementPartition",
    "employerPartition",
    "identifierPartition",
    "creditScorePartition"
})
@XmlRootElement(name = "BorrowerComparison")
public class BorrowerComparison {

    @XmlElement(name = "AddressPartition")
    protected BorrowerComparison.AddressPartition addressPartition;
    @XmlElement(name = "PreviousAddressPartition")
    protected List<PreviousAddressPartition> previousAddressPartition;
    @XmlElement(name = "EmailPartition")
    protected BorrowerComparison.EmailPartition emailPartition;
    @XmlElement(name = "BirthPartition", required = true)
    protected List<BirthPartition> birthPartition;
    @XmlElement(name = "GenderPartition")
    protected List<GenderPartition> genderPartition;
    @XmlElement(name = "BorrowerNamePartition", required = true)
    protected List<BorrowerNamePartition> borrowerNamePartition;
    @XmlElement(name = "CreditStatementPartition")
    protected List<CreditStatementPartition> creditStatementPartition;
    @XmlElement(name = "EmployerPartition")
    protected List<EmployerPartition> employerPartition;
    @XmlElement(name = "IdentifierPartition", required = true)
    protected List<IdentifierPartition> identifierPartition;
    @XmlElement(name = "CreditScorePartition")
    protected List<CreditScorePartition> creditScorePartition;
    @XmlAttribute(name = "borrowerKey")
    protected String borrowerKey;

    /**
     * Gets the value of the addressPartition property.
     *
     * @return
     *     possible object is
     *     {@link BorrowerComparison.AddressPartition }
     *
     */
    public BorrowerComparison.AddressPartition getAddressPartition() {
        return addressPartition;
    }

    /**
     * Sets the value of the addressPartition property.
     *
     * @param value
     *     allowed object is
     *     {@link BorrowerComparison.AddressPartition }
     *
     */
    public void setAddressPartition(BorrowerComparison.AddressPartition value) {
        this.addressPartition = value;
    }

    /**
     * Gets the value of the previousAddressPartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the previousAddressPartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreviousAddressPartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerComparison.PreviousAddressPartition }
     *
     *
     */
    public List<PreviousAddressPartition> getPreviousAddressPartition() {
        if (previousAddressPartition == null) {
            previousAddressPartition = new ArrayList<PreviousAddressPartition>();
        }
        return this.previousAddressPartition;
    }

    /**
     * Gets the value of the emailPartition property.
     *
     * @return
     *     possible object is
     *     {@link BorrowerComparison.EmailPartition }
     *
     */
    public BorrowerComparison.EmailPartition getEmailPartition() {
        return emailPartition;
    }

    /**
     * Sets the value of the emailPartition property.
     *
     * @param value
     *     allowed object is
     *     {@link BorrowerComparison.EmailPartition }
     *
     */
    public void setEmailPartition(BorrowerComparison.EmailPartition value) {
        this.emailPartition = value;
    }

    /**
     * Gets the value of the birthPartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the birthPartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBirthPartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerComparison.BirthPartition }
     *
     *
     */
    public List<BirthPartition> getBirthPartition() {
        if (birthPartition == null) {
            birthPartition = new ArrayList<BirthPartition>();
        }
        return this.birthPartition;
    }

    /**
     * Gets the value of the genderPartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genderPartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenderPartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerComparison.GenderPartition }
     *
     *
     */
    public List<GenderPartition> getGenderPartition() {
        if (genderPartition == null) {
            genderPartition = new ArrayList<GenderPartition>();
        }
        return this.genderPartition;
    }

    /**
     * Gets the value of the borrowerNamePartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the borrowerNamePartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBorrowerNamePartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerComparison.BorrowerNamePartition }
     *
     *
     */
    public List<BorrowerNamePartition> getBorrowerNamePartition() {
        if (borrowerNamePartition == null) {
            borrowerNamePartition = new ArrayList<BorrowerNamePartition>();
        }
        return this.borrowerNamePartition;
    }

    /**
     * Gets the value of the creditStatementPartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditStatementPartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditStatementPartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerComparison.CreditStatementPartition }
     *
     *
     */
    public List<CreditStatementPartition> getCreditStatementPartition() {
        if (creditStatementPartition == null) {
            creditStatementPartition = new ArrayList<CreditStatementPartition>();
        }
        return this.creditStatementPartition;
    }

    /**
     * Gets the value of the employerPartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employerPartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployerPartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerComparison.EmployerPartition }
     *
     *
     */
    public List<EmployerPartition> getEmployerPartition() {
        if (employerPartition == null) {
            employerPartition = new ArrayList<EmployerPartition>();
        }
        return this.employerPartition;
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
     * {@link BorrowerComparison.IdentifierPartition }
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
     * Gets the value of the creditScorePartition property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditScorePartition property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditScorePartition().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BorrowerComparison.CreditScorePartition }
     *
     *
     */
    public List<CreditScorePartition> getCreditScorePartition() {
        if (creditScorePartition == null) {
            creditScorePartition = new ArrayList<CreditScorePartition>();
        }
        return this.creditScorePartition;
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
     *         &lt;choice maxOccurs="unbounded" minOccurs="0">
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerAddressComparison"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerAddress"/>
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
        "borrowerAddressComparisonOrBorrowerAddress"
    })
    public static class AddressPartition {

        @XmlElements({
            @XmlElement(name = "BorrowerAddressComparison", type = BorrowerAddressComparison.class),
            @XmlElement(name = "BorrowerAddress", type = BorrowerAddress.class)
        })
        protected List<Object> borrowerAddressComparisonOrBorrowerAddress;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the borrowerAddressComparisonOrBorrowerAddress property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the borrowerAddressComparisonOrBorrowerAddress property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBorrowerAddressComparisonOrBorrowerAddress().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BorrowerAddressComparison }
         * {@link BorrowerAddress }
         * 
         * 
         */
        public List<Object> getBorrowerAddressComparisonOrBorrowerAddress() {
            if (borrowerAddressComparisonOrBorrowerAddress == null) {
                borrowerAddressComparisonOrBorrowerAddress = new ArrayList<Object>();
            }
            return this.borrowerAddressComparisonOrBorrowerAddress;
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
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BirthComparison"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Birth"/>
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
        "birthComparisonOrBirth"
    })
    public static class BirthPartition {

        @XmlElements({
            @XmlElement(name = "BirthComparison", type = BirthComparison.class),
            @XmlElement(name = "Birth", type = Birth.class)
        })
        protected List<Object> birthComparisonOrBirth;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the birthComparisonOrBirth property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the birthComparisonOrBirth property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBirthComparisonOrBirth().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BirthComparison }
         * {@link Birth }
         * 
         * 
         */
        public List<Object> getBirthComparisonOrBirth() {
            if (birthComparisonOrBirth == null) {
                birthComparisonOrBirth = new ArrayList<Object>();
            }
            return this.birthComparisonOrBirth;
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
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerName"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerNameComparison"/>
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
        "borrowerNameOrBorrowerNameComparison"
    })
    public static class BorrowerNamePartition {

        @XmlElements({
            @XmlElement(name = "BorrowerName", type = BorrowerName.class),
            @XmlElement(name = "BorrowerNameComparison", type = BorrowerNameComparison.class)
        })
        protected List<Object> borrowerNameOrBorrowerNameComparison;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the borrowerNameOrBorrowerNameComparison property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the borrowerNameOrBorrowerNameComparison property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBorrowerNameOrBorrowerNameComparison().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BorrowerName }
         * {@link BorrowerNameComparison }
         * 
         * 
         */
        public List<Object> getBorrowerNameOrBorrowerNameComparison() {
            if (borrowerNameOrBorrowerNameComparison == null) {
                borrowerNameOrBorrowerNameComparison = new ArrayList<Object>();
            }
            return this.borrowerNameOrBorrowerNameComparison;
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
     *         &lt;element name="CreditScore" type="{com/truelink/ds/sch/report/truelink/v5}CreditScoreType" maxOccurs="unbounded"/>
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
        "creditScore"
    })
    public static class CreditScorePartition {

        @XmlElement(name = "CreditScore", required = true)
        protected List<CreditScoreType> creditScore;

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
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}CreditStatementComparison"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}CreditStatement"/>
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
        "creditStatementComparisonOrCreditStatement"
    })
    public static class CreditStatementPartition {

        @XmlElements({
            @XmlElement(name = "CreditStatementComparison", type = CreditStatementComparison.class),
            @XmlElement(name = "CreditStatement", type = CreditStatement.class)
        })
        protected List<Object> creditStatementComparisonOrCreditStatement;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the creditStatementComparisonOrCreditStatement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the creditStatementComparisonOrCreditStatement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCreditStatementComparisonOrCreditStatement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CreditStatementComparison }
         * {@link CreditStatement }
         * 
         * 
         */
        public List<Object> getCreditStatementComparisonOrCreditStatement() {
            if (creditStatementComparisonOrCreditStatement == null) {
                creditStatementComparisonOrCreditStatement = new ArrayList<Object>();
            }
            return this.creditStatementComparisonOrCreditStatement;
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
     *         &lt;element name="EmailAddress" type="{com/truelink/ds/sch/report/truelink/v5}EmailAddressType" maxOccurs="unbounded" minOccurs="0"/>
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
        "emailAddress"
    })
    public static class EmailPartition {

        @XmlElement(name = "EmailAddress")
        protected List<EmailAddressType> emailAddress;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

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
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}EmployerComparison"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Employer"/>
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
        "employerComparisonOrEmployer"
    })
    public static class EmployerPartition {

        @XmlElements({
            @XmlElement(name = "EmployerComparison", type = EmployerComparison.class),
            @XmlElement(name = "Employer", type = Employer.class)
        })
        protected List<Object> employerComparisonOrEmployer;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the employerComparisonOrEmployer property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the employerComparisonOrEmployer property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEmployerComparisonOrEmployer().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EmployerComparison }
         * {@link Employer }
         * 
         * 
         */
        public List<Object> getEmployerComparisonOrEmployer() {
            if (employerComparisonOrEmployer == null) {
                employerComparisonOrEmployer = new ArrayList<Object>();
            }
            return this.employerComparisonOrEmployer;
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
     *         &lt;element name="Gender" type="{com/truelink/ds/sch/pii/v1}GenderEnum" maxOccurs="unbounded" minOccurs="0"/>
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
        "gender"
    })
    public static class GenderPartition {

        @XmlElement(name = "Gender")
        @XmlSchemaType(name = "string")
        protected List<GenderEnum> gender;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the gender property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the gender property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGender().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GenderEnum }
         * 
         * 
         */
        public List<GenderEnum> getGender() {
            if (gender == null) {
                gender = new ArrayList<GenderEnum>();
            }
            return this.gender;
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
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerAddressComparison"/>
     *           &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}BorrowerAddress"/>
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
        "borrowerAddressComparisonOrBorrowerAddress"
    })
    public static class PreviousAddressPartition {

        @XmlElements({
            @XmlElement(name = "BorrowerAddressComparison", type = BorrowerAddressComparison.class),
            @XmlElement(name = "BorrowerAddress", type = BorrowerAddress.class)
        })
        protected List<Object> borrowerAddressComparisonOrBorrowerAddress;
        @XmlAttribute(name = "Changed", required = true)
        protected ChangeIndicatorType changed;

        /**
         * Gets the value of the borrowerAddressComparisonOrBorrowerAddress property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the borrowerAddressComparisonOrBorrowerAddress property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBorrowerAddressComparisonOrBorrowerAddress().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BorrowerAddressComparison }
         * {@link BorrowerAddress }
         * 
         * 
         */
        public List<Object> getBorrowerAddressComparisonOrBorrowerAddress() {
            if (borrowerAddressComparisonOrBorrowerAddress == null) {
                borrowerAddressComparisonOrBorrowerAddress = new ArrayList<Object>();
            }
            return this.borrowerAddressComparisonOrBorrowerAddress;
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
