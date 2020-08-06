
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for BUSPSUMMARY complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSPSUMMARY">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GRANTOR" type="{http://webservice.commcons.experian.com}GRANTOR" minOccurs="0"/>
 *         &lt;element name="ACCSUM" type="{http://webservice.commcons.experian.com}ACCSUM" minOccurs="0"/>
 *         &lt;element name="GUARANTO" type="{http://webservice.commcons.experian.com}GUARANTO" minOccurs="0"/>
 *         &lt;element name="CURRCYSTAT" type="{http://webservice.commcons.experian.com}CURRCYSTAT" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CURRCYCNT" type="{http://webservice.commcons.experian.com}CURRCYCNT" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CREDTYPE" type="{http://webservice.commcons.experian.com}CREDTYPE" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CREDTYPEDERIVATIVES" type="{http://webservice.commcons.experian.com}CREDTYPEDERIVATIVES" minOccurs="0"/>
 *         &lt;element name="ACTTYPEDPDBALDerivativesSelf" type="{http://webservice.commcons.experian.com}ACTTYPEDPDBALDerivativesSelf" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ACTTYPEDPDBALDerivativesNonSelf" type="{http://webservice.commcons.experian.com}ACTTYPEDPDBALDerivativesNonSelf" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BUSENQATTR" type="{http://webservice.commcons.experian.com}BUSENQATTR" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSPSUMMARY", propOrder = {
    "segmentCode",
    "grantor",
    "accsum",
    "guaranto",
    "currcystat",
    "currcycnt",
    "credtype",
    "credtypederivatives",
    "acttypedpdbalDerivativesSelf",
    "acttypedpdbalDerivativesNonSelf",
    "busenqattr"
})
public class BUSPSUMMARY {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "GRANTOR")
    protected GRANTOR grantor;
    @XmlElement(name = "ACCSUM")
    protected ACCSUM accsum;
    @XmlElement(name = "GUARANTO")
    protected GUARANTO guaranto;
    @XmlElement(name = "CURRCYSTAT")
    protected List<CURRCYSTAT> currcystat;
    @XmlElement(name = "CURRCYCNT")
    protected List<CURRCYCNT> currcycnt;
    @XmlElement(name = "CREDTYPE")
    protected List<CREDTYPE> credtype;
    @XmlElement(name = "CREDTYPEDERIVATIVES")
    protected CREDTYPEDERIVATIVES credtypederivatives;
    @XmlElement(name = "ACTTYPEDPDBALDerivativesSelf")
    protected List<ACTTYPEDPDBALDerivativesSelf> acttypedpdbalDerivativesSelf;
    @XmlElement(name = "ACTTYPEDPDBALDerivativesNonSelf")
    protected List<ACTTYPEDPDBALDerivativesNonSelf> acttypedpdbalDerivativesNonSelf;
    @XmlElement(name = "BUSENQATTR")
    protected BUSENQATTR busenqattr;

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
     * Gets the value of the grantor property.
     * 
     * @return
     *     possible object is
     *     {@link GRANTOR }
     *     
     */
    public GRANTOR getGRANTOR() {
        return grantor;
    }

    /**
     * Sets the value of the grantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link GRANTOR }
     *     
     */
    public void setGRANTOR(GRANTOR value) {
        this.grantor = value;
    }

    /**
     * Gets the value of the accsum property.
     * 
     * @return
     *     possible object is
     *     {@link ACCSUM }
     *     
     */
    public ACCSUM getACCSUM() {
        return accsum;
    }

    /**
     * Sets the value of the accsum property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACCSUM }
     *     
     */
    public void setACCSUM(ACCSUM value) {
        this.accsum = value;
    }

    /**
     * Gets the value of the guaranto property.
     * 
     * @return
     *     possible object is
     *     {@link GUARANTO }
     *     
     */
    public GUARANTO getGUARANTO() {
        return guaranto;
    }

    /**
     * Sets the value of the guaranto property.
     * 
     * @param value
     *     allowed object is
     *     {@link GUARANTO }
     *     
     */
    public void setGUARANTO(GUARANTO value) {
        this.guaranto = value;
    }

    /**
     * Gets the value of the currcystat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the currcystat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCURRCYSTAT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CURRCYSTAT }
     * 
     * 
     */
    public List<CURRCYSTAT> getCURRCYSTAT() {
        if (currcystat == null) {
            currcystat = new ArrayList<CURRCYSTAT>();
        }
        return this.currcystat;
    }

    /**
     * Gets the value of the currcycnt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the currcycnt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCURRCYCNT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CURRCYCNT }
     * 
     * 
     */
    public List<CURRCYCNT> getCURRCYCNT() {
        if (currcycnt == null) {
            currcycnt = new ArrayList<CURRCYCNT>();
        }
        return this.currcycnt;
    }

    /**
     * Gets the value of the credtype property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the credtype property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCREDTYPE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CREDTYPE }
     * 
     * 
     */
    public List<CREDTYPE> getCREDTYPE() {
        if (credtype == null) {
            credtype = new ArrayList<CREDTYPE>();
        }
        return this.credtype;
    }

    /**
     * Gets the value of the credtypederivatives property.
     * 
     * @return
     *     possible object is
     *     {@link CREDTYPEDERIVATIVES }
     *     
     */
    public CREDTYPEDERIVATIVES getCREDTYPEDERIVATIVES() {
        return credtypederivatives;
    }

    /**
     * Sets the value of the credtypederivatives property.
     * 
     * @param value
     *     allowed object is
     *     {@link CREDTYPEDERIVATIVES }
     *     
     */
    public void setCREDTYPEDERIVATIVES(CREDTYPEDERIVATIVES value) {
        this.credtypederivatives = value;
    }

    /**
     * Gets the value of the acttypedpdbalDerivativesSelf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acttypedpdbalDerivativesSelf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getACTTYPEDPDBALDerivativesSelf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ACTTYPEDPDBALDerivativesSelf }
     * 
     * 
     */
    public List<ACTTYPEDPDBALDerivativesSelf> getACTTYPEDPDBALDerivativesSelf() {
        if (acttypedpdbalDerivativesSelf == null) {
            acttypedpdbalDerivativesSelf = new ArrayList<ACTTYPEDPDBALDerivativesSelf>();
        }
        return this.acttypedpdbalDerivativesSelf;
    }

    /**
     * Gets the value of the acttypedpdbalDerivativesNonSelf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acttypedpdbalDerivativesNonSelf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getACTTYPEDPDBALDerivativesNonSelf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ACTTYPEDPDBALDerivativesNonSelf }
     * 
     * 
     */
    public List<ACTTYPEDPDBALDerivativesNonSelf> getACTTYPEDPDBALDerivativesNonSelf() {
        if (acttypedpdbalDerivativesNonSelf == null) {
            acttypedpdbalDerivativesNonSelf = new ArrayList<ACTTYPEDPDBALDerivativesNonSelf>();
        }
        return this.acttypedpdbalDerivativesNonSelf;
    }

    /**
     * Gets the value of the busenqattr property.
     * 
     * @return
     *     possible object is
     *     {@link BUSENQATTR }
     *     
     */
    public BUSENQATTR getBUSENQATTR() {
        return busenqattr;
    }

    /**
     * Sets the value of the busenqattr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSENQATTR }
     *     
     */
    public void setBUSENQATTR(BUSENQATTR value) {
        this.busenqattr = value;
    }

}
