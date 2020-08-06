
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


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
 *       &lt;attribute name="courtNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="division" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="assetAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="dateResolved" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="exemptAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="liabilityAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="trustee" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="company" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="thirdParty" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Bankruptcy")
public class Bankruptcy {

    @XmlAttribute(name = "courtNumber")
    protected String courtNumber;
    @XmlAttribute(name = "division")
    protected String division;
    @XmlAttribute(name = "assetAmount")
    protected BigDecimal assetAmount;
    @XmlAttribute(name = "dateResolved")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateResolved;
    @XmlAttribute(name = "exemptAmount")
    protected BigDecimal exemptAmount;
    @XmlAttribute(name = "liabilityAmount")
    protected BigDecimal liabilityAmount;
    @XmlAttribute(name = "trustee")
    protected String trustee;
    @XmlAttribute(name = "company")
    protected String company;
    @XmlAttribute(name = "thirdParty")
    protected String thirdParty;

    /**
     * Gets the value of the courtNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourtNumber() {
        return courtNumber;
    }

    /**
     * Sets the value of the courtNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourtNumber(String value) {
        this.courtNumber = value;
    }

    /**
     * Gets the value of the division property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets the value of the division property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivision(String value) {
        this.division = value;
    }

    /**
     * Gets the value of the assetAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAssetAmount() {
        return assetAmount;
    }

    /**
     * Sets the value of the assetAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAssetAmount(BigDecimal value) {
        this.assetAmount = value;
    }

    /**
     * Gets the value of the dateResolved property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateResolved() {
        return dateResolved;
    }

    /**
     * Sets the value of the dateResolved property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateResolved(XMLGregorianCalendar value) {
        this.dateResolved = value;
    }

    /**
     * Gets the value of the exemptAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExemptAmount() {
        return exemptAmount;
    }

    /**
     * Sets the value of the exemptAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExemptAmount(BigDecimal value) {
        this.exemptAmount = value;
    }

    /**
     * Gets the value of the liabilityAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLiabilityAmount() {
        return liabilityAmount;
    }

    /**
     * Sets the value of the liabilityAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLiabilityAmount(BigDecimal value) {
        this.liabilityAmount = value;
    }

    /**
     * Gets the value of the trustee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustee() {
        return trustee;
    }

    /**
     * Sets the value of the trustee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustee(String value) {
        this.trustee = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the thirdParty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdParty() {
        return thirdParty;
    }

    /**
     * Sets the value of the thirdParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdParty(String value) {
        this.thirdParty = value;
    }

}
