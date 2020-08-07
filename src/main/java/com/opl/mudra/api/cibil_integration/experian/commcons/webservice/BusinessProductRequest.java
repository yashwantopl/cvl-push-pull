
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for BusinessProductRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessProductRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDENTIFICATION" type="{http://webservice.commcons.experian.com}IDENTIFICATION"/>
 *         &lt;element name="STARTENQ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ENQHEADR" type="{http://webservice.commcons.experian.com}ENQHEADR"/>
 *         &lt;element name="USERPREF" type="{http://webservice.commcons.experian.com}USERPREF" minOccurs="0"/>
 *         &lt;element name="ADDLPROD" type="{http://webservice.commcons.experian.com}ADDLPROD" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BUSNSRCH" type="{http://webservice.commcons.experian.com}BUSNSRCH"/>
 *         &lt;element name="BUSNESACCT" type="{http://webservice.commcons.experian.com}BUSNESACCT" minOccurs="0"/>
 *         &lt;element name="BUSNADDR" type="{http://webservice.commcons.experian.com}BUSNADDR" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="BUSNPHONE" type="{http://webservice.commcons.experian.com}BUSNPHONE" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BINID" type="{http://webservice.commcons.experian.com}BINID" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="BUSPAN" type="{http://webservice.commcons.experian.com}BUSPAN" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="BUSLEGAL" type="{http://webservice.commcons.experian.com}BUSLEGAL"/>
 *         &lt;element name="PRSNSRCH" type="{http://webservice.commcons.experian.com}PRSNSRCH" maxOccurs="15" minOccurs="0"/>
 *         &lt;element name="ENDOFENQ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessProductRequest", propOrder = {
    "identification",
    "startenq",
    "enqheadr",
    "userpref",
    "addlprod",
    "busnsrch",
    "busnesacct",
    "busnaddr",
    "busnphone",
    "binid",
    "buspan",
    "buslegal",
    "prsnsrch",
    "endofenq"
})
@XmlRootElement
public class BusinessProductRequest {

    @XmlElement(name = "IDENTIFICATION", required = true)
    protected IDENTIFICATION identification;
    @XmlElement(name = "STARTENQ", required = true)
    protected String startenq;
    @XmlElement(name = "ENQHEADR", required = true)
    protected ENQHEADR enqheadr;
    @XmlElement(name = "USERPREF")
    protected USERPREF userpref;
    @XmlElement(name = "ADDLPROD")
    protected List<ADDLPROD> addlprod;
    @XmlElement(name = "BUSNSRCH", required = true)
    protected BUSNSRCH busnsrch;
    @XmlElement(name = "BUSNESACCT")
    protected BUSNESACCT busnesacct;
    @XmlElement(name = "BUSNADDR")
    protected List<BUSNADDR> busnaddr;
    @XmlElement(name = "BUSNPHONE")
    protected List<BUSNPHONE> busnphone;
    @XmlElement(name = "BINID")
    protected List<BINID> binid;
    @XmlElement(name = "BUSPAN")
    protected List<BUSPAN> buspan;
    @XmlElement(name = "BUSLEGAL", required = true)
    protected BUSLEGAL buslegal;
    @XmlElement(name = "PRSNSRCH")
    protected List<PRSNSRCH> prsnsrch;
    @XmlElement(name = "ENDOFENQ", required = true)
    protected String endofenq;

    /**
     * Gets the value of the identification property.
     * 
     * @return
     *     possible object is
     *     {@link IDENTIFICATION }
     *     
     */
    public IDENTIFICATION getIDENTIFICATION() {
        return identification;
    }

    /**
     * Sets the value of the identification property.
     * 
     * @param value
     *     allowed object is
     *     {@link IDENTIFICATION }
     *     
     */
    public void setIDENTIFICATION(IDENTIFICATION value) {
        this.identification = value;
    }

    /**
     * Gets the value of the startenq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTARTENQ() {
        return startenq;
    }

    /**
     * Sets the value of the startenq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTARTENQ(String value) {
        this.startenq = value;
    }

    /**
     * Gets the value of the enqheadr property.
     * 
     * @return
     *     possible object is
     *     {@link ENQHEADR }
     *     
     */
    public ENQHEADR getENQHEADR() {
        return enqheadr;
    }

    /**
     * Sets the value of the enqheadr property.
     * 
     * @param value
     *     allowed object is
     *     {@link ENQHEADR }
     *     
     */
    public void setENQHEADR(ENQHEADR value) {
        this.enqheadr = value;
    }

    /**
     * Gets the value of the userpref property.
     * 
     * @return
     *     possible object is
     *     {@link USERPREF }
     *     
     */
    public USERPREF getUSERPREF() {
        return userpref;
    }

    /**
     * Sets the value of the userpref property.
     * 
     * @param value
     *     allowed object is
     *     {@link USERPREF }
     *     
     */
    public void setUSERPREF(USERPREF value) {
        this.userpref = value;
    }

    /**
     * Gets the value of the addlprod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addlprod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getADDLPROD().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ADDLPROD }
     * 
     * 
     */
    public List<ADDLPROD> getADDLPROD() {
        if (addlprod == null) {
            addlprod = new ArrayList<ADDLPROD>();
        }
        return this.addlprod;
    }
    
    public void setADDLPROD(List<ADDLPROD> value) {
        this.addlprod = value;
    }

    /**
     * Gets the value of the busnsrch property.
     * 
     * @return
     *     possible object is
     *     {@link BUSNSRCH }
     *     
     */
    public BUSNSRCH getBUSNSRCH() {
        return busnsrch;
    }

    /**
     * Sets the value of the busnsrch property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSNSRCH }
     *     
     */
    public void setBUSNSRCH(BUSNSRCH value) {
        this.busnsrch = value;
    }

    /**
     * Gets the value of the busnesacct property.
     * 
     * @return
     *     possible object is
     *     {@link BUSNESACCT }
     *     
     */
    public BUSNESACCT getBUSNESACCT() {
        return busnesacct;
    }

    /**
     * Sets the value of the busnesacct property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSNESACCT }
     *     
     */
    public void setBUSNESACCT(BUSNESACCT value) {
        this.busnesacct = value;
    }

    /**
     * Gets the value of the busnaddr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busnaddr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSNADDR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSNADDR }
     * 
     * 
     */
    public List<BUSNADDR> getBUSNADDR() {
        if (busnaddr == null) {
            busnaddr = new ArrayList<BUSNADDR>();
        }
        return this.busnaddr;
    }
    
    public void setBUSNADDR(List<BUSNADDR> value) {
        this.busnaddr = value;
    }

    /**
     * Gets the value of the busnphone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busnphone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSNPHONE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSNPHONE }
     * 
     * 
     */
    public List<BUSNPHONE> getBUSNPHONE() {
        if (busnphone == null) {
            busnphone = new ArrayList<BUSNPHONE>();
        }
        return this.busnphone;
    }
    
    public void setBUSNPHONE(List<BUSNPHONE> value) {
        this.busnphone = value;
    }

    /**
     * Gets the value of the binid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the binid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBINID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BINID }
     * 
     * 
     */
    public List<BINID> getBINID() {
        if (binid == null) {
            binid = new ArrayList<BINID>();
        }
        return this.binid;
    }

    public void setBINID(List<BINID> value) {
        this.binid = value;
    }
    
    /**
     * Gets the value of the buspan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buspan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSPAN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSPAN }
     * 
     * 
     */
    public List<BUSPAN> getBUSPAN() {
        if (buspan == null) {
            buspan = new ArrayList<BUSPAN>();
        }
        return this.buspan;
    }
    
    public void setBUSPAN(List<BUSPAN> value) {
        this.buspan = value;
    }
    
    /**
     * Gets the value of the buslegal property.
     * 
     * @return
     *     possible object is
     *     {@link BUSLEGAL }
     *     
     */
    public BUSLEGAL getBUSLEGAL() {
        return buslegal;
    }

    /**
     * Sets the value of the buslegal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSLEGAL }
     *     
     */
    public void setBUSLEGAL(BUSLEGAL value) {
        this.buslegal = value;
    }

    /**
     * Gets the value of the prsnsrch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prsnsrch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPRSNSRCH().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PRSNSRCH }
     * 
     * 
     */
    public List<PRSNSRCH> getPRSNSRCH() {
        if (prsnsrch == null) {
            prsnsrch = new ArrayList<PRSNSRCH>();
        }
        return this.prsnsrch;
    }

    /**
     * Gets the value of the endofenq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENDOFENQ() {
        return endofenq;
    }

    /**
     * Sets the value of the endofenq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENDOFENQ(String value) {
        this.endofenq = value;
    }

}
