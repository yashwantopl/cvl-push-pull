
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for BUSINESS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSINESS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BureauAddDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BusinessName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BusinessShortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LegalDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndustryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndustryTypeDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BUALIASNAM" type="{http://webservice.commcons.experian.com}BUALIASNAM" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="BUSBIDCARDS" type="{http://webservice.commcons.experian.com}BUSBIDCARDS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BUSBIDADDRS" type="{http://webservice.commcons.experian.com}BUSBIDADDRS" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="BUSBIDPHONE" type="{http://webservice.commcons.experian.com}BUSBIDPHONE" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BUSSFIRM" type="{http://webservice.commcons.experian.com}BUSSFIRM" minOccurs="0"/>
 *         &lt;element name="BUSOTH" type="{http://webservice.commcons.experian.com}BUSOTH" minOccurs="0"/>
 *         &lt;element name="BUSHIST" type="{http://webservice.commcons.experian.com}BUSHIST" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSINESS", propOrder = {
    "segmentCode",
    "bureauAddDate",
    "businessName",
    "businessShortName",
    "legalDescription",
    "industryType",
    "industryTypeDetail",
    "bualiasnam",
    "busbidcards",
    "busbidaddrs",
    "busbidphone",
    "bussfirm",
    "busoth",
    "bushist"
})
public class BUSINESS {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "BureauAddDate")
    protected String bureauAddDate;
    @XmlElement(name = "BusinessName")
    protected String businessName;
    @XmlElement(name = "BusinessShortName")
    protected String businessShortName;
    @XmlElement(name = "LegalDescription")
    protected String legalDescription;
    @XmlElement(name = "IndustryType")
    protected String industryType;
    @XmlElement(name = "IndustryTypeDetail")
    protected String industryTypeDetail;
    @XmlElement(name = "BUALIASNAM")
    protected List<BUALIASNAM> bualiasnam;
    @XmlElement(name = "BUSBIDCARDS")
    protected List<BUSBIDCARDS> busbidcards;
    @XmlElement(name = "BUSBIDADDRS")
    protected List<BUSBIDADDRS> busbidaddrs;
    @XmlElement(name = "BUSBIDPHONE")
    protected List<BUSBIDPHONE> busbidphone;
    @XmlElement(name = "BUSSFIRM")
    protected BUSSFIRM bussfirm;
    @XmlElement(name = "BUSOTH")
    protected BUSOTH busoth;
    @XmlElement(name = "BUSHIST")
    protected List<BUSHIST> bushist;

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
     * Gets the value of the bureauAddDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureauAddDate() {
        return bureauAddDate;
    }

    /**
     * Sets the value of the bureauAddDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureauAddDate(String value) {
        this.bureauAddDate = value;
    }

    /**
     * Gets the value of the businessName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Sets the value of the businessName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessName(String value) {
        this.businessName = value;
    }

    /**
     * Gets the value of the businessShortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessShortName() {
        return businessShortName;
    }

    /**
     * Sets the value of the businessShortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessShortName(String value) {
        this.businessShortName = value;
    }

    /**
     * Gets the value of the legalDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalDescription() {
        return legalDescription;
    }

    /**
     * Sets the value of the legalDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalDescription(String value) {
        this.legalDescription = value;
    }

    /**
     * Gets the value of the industryType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustryType() {
        return industryType;
    }

    /**
     * Sets the value of the industryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustryType(String value) {
        this.industryType = value;
    }

    /**
     * Gets the value of the industryTypeDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndustryTypeDetail() {
        return industryTypeDetail;
    }

    /**
     * Sets the value of the industryTypeDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndustryTypeDetail(String value) {
        this.industryTypeDetail = value;
    }

    /**
     * Gets the value of the bualiasnam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bualiasnam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUALIASNAM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUALIASNAM }
     * 
     * 
     */
    public List<BUALIASNAM> getBUALIASNAM() {
        if (bualiasnam == null) {
            bualiasnam = new ArrayList<BUALIASNAM>();
        }
        return this.bualiasnam;
    }

    /**
     * Gets the value of the busbidcards property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busbidcards property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSBIDCARDS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSBIDCARDS }
     * 
     * 
     */
    public List<BUSBIDCARDS> getBUSBIDCARDS() {
        if (busbidcards == null) {
            busbidcards = new ArrayList<BUSBIDCARDS>();
        }
        return this.busbidcards;
    }

    /**
     * Gets the value of the busbidaddrs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busbidaddrs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSBIDADDRS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSBIDADDRS }
     * 
     * 
     */
    public List<BUSBIDADDRS> getBUSBIDADDRS() {
        if (busbidaddrs == null) {
            busbidaddrs = new ArrayList<BUSBIDADDRS>();
        }
        return this.busbidaddrs;
    }

    /**
     * Gets the value of the busbidphone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busbidphone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSBIDPHONE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSBIDPHONE }
     * 
     * 
     */
    public List<BUSBIDPHONE> getBUSBIDPHONE() {
        if (busbidphone == null) {
            busbidphone = new ArrayList<BUSBIDPHONE>();
        }
        return this.busbidphone;
    }

    /**
     * Gets the value of the bussfirm property.
     * 
     * @return
     *     possible object is
     *     {@link BUSSFIRM }
     *     
     */
    public BUSSFIRM getBUSSFIRM() {
        return bussfirm;
    }

    /**
     * Sets the value of the bussfirm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSSFIRM }
     *     
     */
    public void setBUSSFIRM(BUSSFIRM value) {
        this.bussfirm = value;
    }

    /**
     * Gets the value of the busoth property.
     * 
     * @return
     *     possible object is
     *     {@link BUSOTH }
     *     
     */
    public BUSOTH getBUSOTH() {
        return busoth;
    }

    /**
     * Sets the value of the busoth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSOTH }
     *     
     */
    public void setBUSOTH(BUSOTH value) {
        this.busoth = value;
    }

    /**
     * Gets the value of the bushist property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bushist property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSHIST().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSHIST }
     * 
     * 
     */
    public List<BUSHIST> getBUSHIST() {
        if (bushist == null) {
            bushist = new ArrayList<BUSHIST>();
        }
        return this.bushist;
    }

}
