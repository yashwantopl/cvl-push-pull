
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for CHQDIS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CHQDIS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountInLast3Months" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountInLast6Months" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountInLast9Months" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountInLast12Months" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DSHNR_CHEQUE" type="{http://webservice.commcons.experian.com}DSHNR_CHEQUE" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CHQDIS", propOrder = {
    "segmentCode",
    "reasonCode",
    "countInLast3Months",
    "countInLast6Months",
    "countInLast9Months",
    "countInLast12Months",
    "dshnrcheque"
})
public class CHQDIS {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "ReasonCode")
    protected String reasonCode;
    @XmlElement(name = "CountInLast3Months")
    protected String countInLast3Months;
    @XmlElement(name = "CountInLast6Months")
    protected String countInLast6Months;
    @XmlElement(name = "CountInLast9Months")
    protected String countInLast9Months;
    @XmlElement(name = "CountInLast12Months")
    protected String countInLast12Months;
    @XmlElement(name = "DSHNR_CHEQUE")
    protected List<DSHNRCHEQUE> dshnrcheque;

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
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonCode(String value) {
        this.reasonCode = value;
    }

    /**
     * Gets the value of the countInLast3Months property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountInLast3Months() {
        return countInLast3Months;
    }

    /**
     * Sets the value of the countInLast3Months property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountInLast3Months(String value) {
        this.countInLast3Months = value;
    }

    /**
     * Gets the value of the countInLast6Months property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountInLast6Months() {
        return countInLast6Months;
    }

    /**
     * Sets the value of the countInLast6Months property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountInLast6Months(String value) {
        this.countInLast6Months = value;
    }

    /**
     * Gets the value of the countInLast9Months property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountInLast9Months() {
        return countInLast9Months;
    }

    /**
     * Sets the value of the countInLast9Months property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountInLast9Months(String value) {
        this.countInLast9Months = value;
    }

    /**
     * Gets the value of the countInLast12Months property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountInLast12Months() {
        return countInLast12Months;
    }

    /**
     * Sets the value of the countInLast12Months property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountInLast12Months(String value) {
        this.countInLast12Months = value;
    }

    /**
     * Gets the value of the dshnrcheque property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dshnrcheque property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDSHNRCHEQUE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DSHNRCHEQUE }
     * 
     * 
     */
    public List<DSHNRCHEQUE> getDSHNRCHEQUE() {
        if (dshnrcheque == null) {
            dshnrcheque = new ArrayList<DSHNRCHEQUE>();
        }
        return this.dshnrcheque;
    }

}
