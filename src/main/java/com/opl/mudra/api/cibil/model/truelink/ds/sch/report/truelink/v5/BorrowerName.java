
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;


import com.opl.mudra.api.cibil.model.truelink.ds.sch.pii.v1.FullNameType;

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
 *         &lt;element name="Name" type="{com/truelink/ds/sch/pii/v1}FullNameType"/>
 *         &lt;element name="NameType" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Source"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}DisputeRemarks" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dateReported" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}date" />
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
    "name",
    "nameType",
    "source",
    "disputeRemarks"
})
@XmlRootElement(name = "BorrowerName")
public class BorrowerName {

    @XmlElement(name = "Name", required = true)
    protected FullNameType name;
    @XmlElement(name = "NameType", required = true)
    protected CodeRef nameType;
    @XmlElement(name = "Source", required = true)
    protected Source source;
    @XmlElement(name = "DisputeRemarks")
    protected DisputeRemarks disputeRemarks;
    @XmlAttribute(name = "dateReported")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateReported;
    @XmlAttribute(name = "dateUpdated")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateUpdated;
    @XmlAttribute(name = "partitionSet", required = true)
    protected int partitionSet;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link FullNameType }
     *     
     */
    public FullNameType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullNameType }
     *     
     */
    public void setName(FullNameType value) {
        this.name = value;
    }

    /**
     * Gets the value of the nameType property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getNameType() {
        return nameType;
    }

    /**
     * Sets the value of the nameType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setNameType(CodeRef value) {
        this.nameType = value;
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
     * Gets the value of the disputeRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link DisputeRemarks }
     *     
     */
    public DisputeRemarks getDisputeRemarks() {
        return disputeRemarks;
    }

    /**
     * Sets the value of the disputeRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisputeRemarks }
     *     
     */
    public void setDisputeRemarks(DisputeRemarks value) {
        this.disputeRemarks = value;
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
