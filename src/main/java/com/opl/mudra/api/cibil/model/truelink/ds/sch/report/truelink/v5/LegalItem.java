
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
 *         &lt;element name="CourtLocation" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *         &lt;element name="CourtType" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="actionAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="balance" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="dateSatisfied" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="plaintiff" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lawyer" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="thirdParty" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "courtLocation",
    "courtType"
})
@XmlRootElement(name = "LegalItem")
public class LegalItem {

    @XmlElement(name = "CourtLocation")
    protected CodeRef courtLocation;
    @XmlElement(name = "CourtType")
    protected CodeRef courtType;
    @XmlAttribute(name = "actionAmount")
    protected BigDecimal actionAmount;
    @XmlAttribute(name = "balance")
    protected BigDecimal balance;
    @XmlAttribute(name = "dateSatisfied")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateSatisfied;
    @XmlAttribute(name = "plaintiff")
    protected String plaintiff;
    @XmlAttribute(name = "lawyer")
    protected String lawyer;
    @XmlAttribute(name = "thirdParty")
    protected String thirdParty;

    /**
     * Gets the value of the courtLocation property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getCourtLocation() {
        return courtLocation;
    }

    /**
     * Sets the value of the courtLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setCourtLocation(CodeRef value) {
        this.courtLocation = value;
    }

    /**
     * Gets the value of the courtType property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getCourtType() {
        return courtType;
    }

    /**
     * Sets the value of the courtType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setCourtType(CodeRef value) {
        this.courtType = value;
    }

    /**
     * Gets the value of the actionAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getActionAmount() {
        return actionAmount;
    }

    /**
     * Sets the value of the actionAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setActionAmount(BigDecimal value) {
        this.actionAmount = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBalance(BigDecimal value) {
        this.balance = value;
    }

    /**
     * Gets the value of the dateSatisfied property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateSatisfied() {
        return dateSatisfied;
    }

    /**
     * Sets the value of the dateSatisfied property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateSatisfied(XMLGregorianCalendar value) {
        this.dateSatisfied = value;
    }

    /**
     * Gets the value of the plaintiff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaintiff() {
        return plaintiff;
    }

    /**
     * Sets the value of the plaintiff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaintiff(String value) {
        this.plaintiff = value;
    }

    /**
     * Gets the value of the lawyer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLawyer() {
        return lawyer;
    }

    /**
     * Sets the value of the lawyer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLawyer(String value) {
        this.lawyer = value;
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
