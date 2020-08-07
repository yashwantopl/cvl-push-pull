
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for PERRESPONSE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PERRESPONSE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PERSON" type="{http://webservice.commcons.experian.com}PERSON" minOccurs="0"/>
 *         &lt;element name="PIDNAME" type="{http://webservice.commcons.experian.com}PIDNAME" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="PIDCARDS" type="{http://webservice.commcons.experian.com}PIDCARDS" maxOccurs="6" minOccurs="0"/>
 *         &lt;element name="PIDADDRS" type="{http://webservice.commcons.experian.com}PIDADDRS" maxOccurs="9" minOccurs="0"/>
 *         &lt;element name="PIDPHONE" type="{http://webservice.commcons.experian.com}PIDPHONE" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="PIDEMAIL" type="{http://webservice.commcons.experian.com}PIDEMAIL" minOccurs="0"/>
 *         &lt;element name="PERSTMTALRT" type="{http://webservice.commcons.experian.com}PERSTMTALRT" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PERNGSYSMSG" type="{http://webservice.commcons.experian.com}PERNGSYSMSG" minOccurs="0"/>
 *         &lt;element name="PERBURERROR" type="{http://webservice.commcons.experian.com}PERBURERROR" minOccurs="0"/>
 *         &lt;element name="SCOREPERSON" type="{http://webservice.commcons.experian.com}SCOREPERSON" minOccurs="0"/>
 *         &lt;element name="PERSUMMARY" type="{http://webservice.commcons.experian.com}PERSUMMARY" minOccurs="0"/>
 *         &lt;element name="CONSCRED" type="{http://webservice.commcons.experian.com}CONSCRED" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PERENQRHIST" type="{http://webservice.commcons.experian.com}PERENQRHIST" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PERRESPONSE", propOrder = {
    "person",
    "pidname",
    "pidcards",
    "pidaddrs",
    "pidphone",
    "pidemail",
    "perstmtalrt",
    "perngsysmsg",
    "perburerror",
    "scoreperson",
    "persummary",
    "conscred",
    "perenqrhist"
})
public class PERRESPONSE {

    @XmlElement(name = "PERSON")
    protected PERSON person;
    @XmlElement(name = "PIDNAME")
    protected List<PIDNAME> pidname;
    @XmlElement(name = "PIDCARDS")
    protected List<PIDCARDS> pidcards;
    @XmlElement(name = "PIDADDRS")
    protected List<PIDADDRS> pidaddrs;
    @XmlElement(name = "PIDPHONE")
    protected List<PIDPHONE> pidphone;
    @XmlElement(name = "PIDEMAIL")
    protected PIDEMAIL pidemail;
    @XmlElement(name = "PERSTMTALRT")
    protected List<PERSTMTALRT> perstmtalrt;
    @XmlElement(name = "PERNGSYSMSG")
    protected PERNGSYSMSG perngsysmsg;
    @XmlElement(name = "PERBURERROR")
    protected PERBURERROR perburerror;
    @XmlElement(name = "SCOREPERSON")
    protected SCOREPERSON scoreperson;
    @XmlElement(name = "PERSUMMARY")
    protected PERSUMMARY persummary;
    @XmlElement(name = "CONSCRED")
    protected List<CONSCRED> conscred;
    @XmlElement(name = "PERENQRHIST")
    protected List<PERENQRHIST> perenqrhist;

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link PERSON }
     *     
     */
    public PERSON getPERSON() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link PERSON }
     *     
     */
    public void setPERSON(PERSON value) {
        this.person = value;
    }

    /**
     * Gets the value of the pidname property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pidname property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPIDNAME().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PIDNAME }
     * 
     * 
     */
    public List<PIDNAME> getPIDNAME() {
        if (pidname == null) {
            pidname = new ArrayList<PIDNAME>();
        }
        return this.pidname;
    }

    /**
     * Gets the value of the pidcards property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pidcards property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPIDCARDS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PIDCARDS }
     * 
     * 
     */
    public List<PIDCARDS> getPIDCARDS() {
        if (pidcards == null) {
            pidcards = new ArrayList<PIDCARDS>();
        }
        return this.pidcards;
    }

    /**
     * Gets the value of the pidaddrs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pidaddrs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPIDADDRS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PIDADDRS }
     * 
     * 
     */
    public List<PIDADDRS> getPIDADDRS() {
        if (pidaddrs == null) {
            pidaddrs = new ArrayList<PIDADDRS>();
        }
        return this.pidaddrs;
    }

    /**
     * Gets the value of the pidphone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pidphone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPIDPHONE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PIDPHONE }
     * 
     * 
     */
    public List<PIDPHONE> getPIDPHONE() {
        if (pidphone == null) {
            pidphone = new ArrayList<PIDPHONE>();
        }
        return this.pidphone;
    }

    /**
     * Gets the value of the pidemail property.
     * 
     * @return
     *     possible object is
     *     {@link PIDEMAIL }
     *     
     */
    public PIDEMAIL getPIDEMAIL() {
        return pidemail;
    }

    /**
     * Sets the value of the pidemail property.
     * 
     * @param value
     *     allowed object is
     *     {@link PIDEMAIL }
     *     
     */
    public void setPIDEMAIL(PIDEMAIL value) {
        this.pidemail = value;
    }

    /**
     * Gets the value of the perstmtalrt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the perstmtalrt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPERSTMTALRT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PERSTMTALRT }
     * 
     * 
     */
    public List<PERSTMTALRT> getPERSTMTALRT() {
        if (perstmtalrt == null) {
            perstmtalrt = new ArrayList<PERSTMTALRT>();
        }
        return this.perstmtalrt;
    }

    /**
     * Gets the value of the perngsysmsg property.
     * 
     * @return
     *     possible object is
     *     {@link PERNGSYSMSG }
     *     
     */
    public PERNGSYSMSG getPERNGSYSMSG() {
        return perngsysmsg;
    }

    /**
     * Sets the value of the perngsysmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link PERNGSYSMSG }
     *     
     */
    public void setPERNGSYSMSG(PERNGSYSMSG value) {
        this.perngsysmsg = value;
    }

    /**
     * Gets the value of the perburerror property.
     * 
     * @return
     *     possible object is
     *     {@link PERBURERROR }
     *     
     */
    public PERBURERROR getPERBURERROR() {
        return perburerror;
    }

    /**
     * Sets the value of the perburerror property.
     * 
     * @param value
     *     allowed object is
     *     {@link PERBURERROR }
     *     
     */
    public void setPERBURERROR(PERBURERROR value) {
        this.perburerror = value;
    }

    /**
     * Gets the value of the scoreperson property.
     * 
     * @return
     *     possible object is
     *     {@link SCOREPERSON }
     *     
     */
    public SCOREPERSON getSCOREPERSON() {
        return scoreperson;
    }

    /**
     * Sets the value of the scoreperson property.
     * 
     * @param value
     *     allowed object is
     *     {@link SCOREPERSON }
     *     
     */
    public void setSCOREPERSON(SCOREPERSON value) {
        this.scoreperson = value;
    }

    /**
     * Gets the value of the persummary property.
     * 
     * @return
     *     possible object is
     *     {@link PERSUMMARY }
     *     
     */
    public PERSUMMARY getPERSUMMARY() {
        return persummary;
    }

    /**
     * Sets the value of the persummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link PERSUMMARY }
     *     
     */
    public void setPERSUMMARY(PERSUMMARY value) {
        this.persummary = value;
    }

    /**
     * Gets the value of the conscred property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conscred property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCONSCRED().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CONSCRED }
     * 
     * 
     */
    public List<CONSCRED> getCONSCRED() {
        if (conscred == null) {
            conscred = new ArrayList<CONSCRED>();
        }
        return this.conscred;
    }

    /**
     * Gets the value of the perenqrhist property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the perenqrhist property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPERENQRHIST().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PERENQRHIST }
     * 
     * 
     */
    public List<PERENQRHIST> getPERENQRHIST() {
        if (perenqrhist == null) {
            perenqrhist = new ArrayList<PERENQRHIST>();
        }
        return this.perenqrhist;
    }

}
