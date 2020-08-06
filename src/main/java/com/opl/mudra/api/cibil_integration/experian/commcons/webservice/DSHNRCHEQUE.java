
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DSHNR_CHEQUE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DSHNR_CHEQUE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DSHNR_CHEQUE_DT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DSHNR_CHEQUE_AM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DSHNR_CHEQUE_NB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DSHNR_CHEQUE_CT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DSHNR_CHEQUE_ISSUE_DT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DSHNR_RSN_CD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DSHNR_CHEQUE", propOrder = {
    "segmentCode",
    "dshnrchequedt",
    "dshnrchequeam",
    "dshnrchequenb",
    "dshnrchequect",
    "dshnrchequeissuedt",
    "dshnrrsncd"
})
public class DSHNRCHEQUE {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "DSHNR_CHEQUE_DT")
    protected String dshnrchequedt;
    @XmlElement(name = "DSHNR_CHEQUE_AM")
    protected String dshnrchequeam;
    @XmlElement(name = "DSHNR_CHEQUE_NB")
    protected String dshnrchequenb;
    @XmlElement(name = "DSHNR_CHEQUE_CT")
    protected String dshnrchequect;
    @XmlElement(name = "DSHNR_CHEQUE_ISSUE_DT")
    protected String dshnrchequeissuedt;
    @XmlElement(name = "DSHNR_RSN_CD")
    protected String dshnrrsncd;

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
     * Gets the value of the dshnrchequedt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDSHNRCHEQUEDT() {
        return dshnrchequedt;
    }

    /**
     * Sets the value of the dshnrchequedt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDSHNRCHEQUEDT(String value) {
        this.dshnrchequedt = value;
    }

    /**
     * Gets the value of the dshnrchequeam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDSHNRCHEQUEAM() {
        return dshnrchequeam;
    }

    /**
     * Sets the value of the dshnrchequeam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDSHNRCHEQUEAM(String value) {
        this.dshnrchequeam = value;
    }

    /**
     * Gets the value of the dshnrchequenb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDSHNRCHEQUENB() {
        return dshnrchequenb;
    }

    /**
     * Sets the value of the dshnrchequenb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDSHNRCHEQUENB(String value) {
        this.dshnrchequenb = value;
    }

    /**
     * Gets the value of the dshnrchequect property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDSHNRCHEQUECT() {
        return dshnrchequect;
    }

    /**
     * Sets the value of the dshnrchequect property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDSHNRCHEQUECT(String value) {
        this.dshnrchequect = value;
    }

    /**
     * Gets the value of the dshnrchequeissuedt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDSHNRCHEQUEISSUEDT() {
        return dshnrchequeissuedt;
    }

    /**
     * Sets the value of the dshnrchequeissuedt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDSHNRCHEQUEISSUEDT(String value) {
        this.dshnrchequeissuedt = value;
    }

    /**
     * Gets the value of the dshnrrsncd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDSHNRRSNCD() {
        return dshnrrsncd;
    }

    /**
     * Sets the value of the dshnrrsncd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDSHNRRSNCD(String value) {
        this.dshnrrsncd = value;
    }

}
