
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import com.opl.mudra.api.cibil.model.truelink.ds.sch.pii.v1.AddressType;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="CreditAddress" type="{com/truelink/ds/sch/pii/v1}AddressType"/>
 *         &lt;element name="Dwelling" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="Origin" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="Ownership" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Source"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dateReported" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="addressOrder" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="partitionSet" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creditAddress",
    "dwelling",
    "origin",
    "ownership",
    "source"
})
@XmlRootElement(name = "BorrowerAddress")
public class BorrowerAddress {

    @XmlElement(name = "CreditAddress", required = true)
    protected AddressType creditAddress;
    @XmlElement(name = "Dwelling", required = true)
    protected CodeRef dwelling;
    @XmlElement(name = "Origin", required = true)
    protected CodeRef origin;
    @XmlElement(name = "Ownership", required = true)
    protected CodeRef ownership;
    @XmlElement(name = "Source", required = true)
    protected Source source;
    @XmlAttribute(name = "dateReported")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateReported;
    @XmlAttribute(name = "dateUpdated")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateUpdated;
    @XmlAttribute(name = "addressOrder", required = true)
    protected int addressOrder;
    @XmlAttribute(name = "partitionSet", required = true)
    protected int partitionSet;

    /**
     * Gets the value of the creditAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getCreditAddress() {
        return creditAddress;
    }

    /**
     * Sets the value of the creditAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setCreditAddress(AddressType value) {
        this.creditAddress = value;
    }

    /**
     * Gets the value of the dwelling property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getDwelling() {
        return dwelling;
    }

    /**
     * Sets the value of the dwelling property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setDwelling(CodeRef value) {
        this.dwelling = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setOrigin(CodeRef value) {
        this.origin = value;
    }

    /**
     * Gets the value of the ownership property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getOwnership() {
        return ownership;
    }

    /**
     * Sets the value of the ownership property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setOwnership(CodeRef value) {
        this.ownership = value;
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
     * Gets the value of the dateReported property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateReported() {
        return dateReported;
    }

    /**
     * Sets the value of the dateReported property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateReported(XMLGregorianCalendar value) {
        this.dateReported = value;
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
     * Gets the value of the addressOrder property.
     * 
     */
    public int getAddressOrder() {
        return addressOrder;
    }

    /**
     * Sets the value of the addressOrder property.
     * 
     */
    public void setAddressOrder(int value) {
        this.addressOrder = value;
    }

    /**
     * Gets the value of the partitionSet property.
     * 
     */
    public int getPartitionSet() {
        return partitionSet;
    }

    /**
     * Sets the value of the partitionSet property.
     * 
     */
    public void setPartitionSet(int value) {
        this.partitionSet = value;
    }

}
