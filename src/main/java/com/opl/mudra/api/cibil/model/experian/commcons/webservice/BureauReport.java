
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for BureauReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BureauReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NGSYSMSG" type="{http://webservice.commcons.experian.com}NGSYSMSG"/>
 *         &lt;element name="BURERROR" type="{http://webservice.commcons.experian.com}BURERROR" minOccurs="0"/>
 *         &lt;element name="NGINQUIRY" type="{http://webservice.commcons.experian.com}NGINQUIRY" minOccurs="0"/>
 *         &lt;element name="PERDETAILS" type="{http://webservice.commcons.experian.com}PERDETAILS" maxOccurs="15" minOccurs="0"/>
 *         &lt;element name="COMMBRPT" type="{http://webservice.commcons.experian.com}COMMBRPT" minOccurs="0"/>
 *         &lt;element name="ExperianSCORE" type="{http://webservice.commcons.experian.com}ExperianSCORE" minOccurs="0"/>
 *         &lt;element name="Category" type="{http://webservice.commcons.experian.com}Category" minOccurs="0"/>
 *         &lt;element name="BUSINESS" type="{http://webservice.commcons.experian.com}BUSINESS" minOccurs="0"/>
 *         &lt;element name="BUSSTMTALRT" type="{http://webservice.commcons.experian.com}BUSSTMTALRT" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SCORECOMBOCOMM" type="{http://webservice.commcons.experian.com}SCORECOMBOCOMM" minOccurs="0"/>
 *         &lt;element name="BUSPSUMMARY" type="{http://webservice.commcons.experian.com}BUSPSUMMARY" minOccurs="0"/>
 *         &lt;element name="COMMCRED" type="{http://webservice.commcons.experian.com}COMMCRED" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RELATIONS" type="{http://webservice.commcons.experian.com}RELATIONS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BUSENQRHIST" type="{http://webservice.commcons.experian.com}BUSENQRHIST" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PERRESPONSE" type="{http://webservice.commcons.experian.com}PERRESPONSE" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BureauReport", propOrder = {
    "ngsysmsg",
    "burerror",
    "nginquiry",
    "perdetails",
    "commbrpt",
    "experianSCORE",
    "category",
    "business",
    "busstmtalrt",
    "scorecombocomm",
    "buspsummary",
    "commcred",
    "relations",
    "busenqrhist",
    "perresponse"
})
public class BureauReport {

    @XmlElement(name = "NGSYSMSG", required = true)
    protected NGSYSMSG ngsysmsg;
    @XmlElement(name = "BURERROR")
    protected BURERROR burerror;
    @XmlElement(name = "NGINQUIRY")
    protected NGINQUIRY nginquiry;
    @XmlElement(name = "PERDETAILS")
    protected List<PERDETAILS> perdetails;
    @XmlElement(name = "COMMBRPT")
    protected COMMBRPT commbrpt;
    @XmlElement(name = "ExperianSCORE")
    protected ExperianSCORE experianSCORE;
    @XmlElement(name = "Category")
    protected Category category;
    @XmlElement(name = "BUSINESS")
    protected BUSINESS business;
    @XmlElement(name = "BUSSTMTALRT")
    protected List<BUSSTMTALRT> busstmtalrt;
    @XmlElement(name = "SCORECOMBOCOMM")
    protected SCORECOMBOCOMM scorecombocomm;
    @XmlElement(name = "BUSPSUMMARY")
    protected BUSPSUMMARY buspsummary;
    @XmlElement(name = "COMMCRED")
    protected List<COMMCRED> commcred;
    @XmlElement(name = "RELATIONS")
    protected List<RELATIONS> relations;
    @XmlElement(name = "BUSENQRHIST")
    protected List<BUSENQRHIST> busenqrhist;
    @XmlElement(name = "PERRESPONSE")
    protected List<PERRESPONSE> perresponse;

    /**
     * Gets the value of the ngsysmsg property.
     * 
     * @return
     *     possible object is
     *     {@link NGSYSMSG }
     *     
     */
    public NGSYSMSG getNGSYSMSG() {
        return ngsysmsg;
    }

    /**
     * Sets the value of the ngsysmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link NGSYSMSG }
     *     
     */
    public void setNGSYSMSG(NGSYSMSG value) {
        this.ngsysmsg = value;
    }

    /**
     * Gets the value of the burerror property.
     * 
     * @return
     *     possible object is
     *     {@link BURERROR }
     *     
     */
    public BURERROR getBURERROR() {
        return burerror;
    }

    /**
     * Sets the value of the burerror property.
     * 
     * @param value
     *     allowed object is
     *     {@link BURERROR }
     *     
     */
    public void setBURERROR(BURERROR value) {
        this.burerror = value;
    }

    /**
     * Gets the value of the nginquiry property.
     * 
     * @return
     *     possible object is
     *     {@link NGINQUIRY }
     *     
     */
    public NGINQUIRY getNGINQUIRY() {
        return nginquiry;
    }

    /**
     * Sets the value of the nginquiry property.
     * 
     * @param value
     *     allowed object is
     *     {@link NGINQUIRY }
     *     
     */
    public void setNGINQUIRY(NGINQUIRY value) {
        this.nginquiry = value;
    }

    /**
     * Gets the value of the perdetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the perdetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPERDETAILS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PERDETAILS }
     * 
     * 
     */
    public List<PERDETAILS> getPERDETAILS() {
        if (perdetails == null) {
            perdetails = new ArrayList<PERDETAILS>();
        }
        return this.perdetails;
    }

    /**
     * Gets the value of the commbrpt property.
     * 
     * @return
     *     possible object is
     *     {@link COMMBRPT }
     *     
     */
    public COMMBRPT getCOMMBRPT() {
        return commbrpt;
    }

    /**
     * Sets the value of the commbrpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link COMMBRPT }
     *     
     */
    public void setCOMMBRPT(COMMBRPT value) {
        this.commbrpt = value;
    }

    /**
     * Gets the value of the experianSCORE property.
     * 
     * @return
     *     possible object is
     *     {@link ExperianSCORE }
     *     
     */
    public ExperianSCORE getExperianSCORE() {
        return experianSCORE;
    }

    /**
     * Sets the value of the experianSCORE property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExperianSCORE }
     *     
     */
    public void setExperianSCORE(ExperianSCORE value) {
        this.experianSCORE = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Category }
     *     
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategory(Category value) {
        this.category = value;
    }

    /**
     * Gets the value of the business property.
     * 
     * @return
     *     possible object is
     *     {@link BUSINESS }
     *     
     */
    public BUSINESS getBUSINESS() {
        return business;
    }

    /**
     * Sets the value of the business property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSINESS }
     *     
     */
    public void setBUSINESS(BUSINESS value) {
        this.business = value;
    }

    /**
     * Gets the value of the busstmtalrt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busstmtalrt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSSTMTALRT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSSTMTALRT }
     * 
     * 
     */
    public List<BUSSTMTALRT> getBUSSTMTALRT() {
        if (busstmtalrt == null) {
            busstmtalrt = new ArrayList<BUSSTMTALRT>();
        }
        return this.busstmtalrt;
    }

    /**
     * Gets the value of the scorecombocomm property.
     * 
     * @return
     *     possible object is
     *     {@link SCORECOMBOCOMM }
     *     
     */
    public SCORECOMBOCOMM getSCORECOMBOCOMM() {
        return scorecombocomm;
    }

    /**
     * Sets the value of the scorecombocomm property.
     * 
     * @param value
     *     allowed object is
     *     {@link SCORECOMBOCOMM }
     *     
     */
    public void setSCORECOMBOCOMM(SCORECOMBOCOMM value) {
        this.scorecombocomm = value;
    }

    /**
     * Gets the value of the buspsummary property.
     * 
     * @return
     *     possible object is
     *     {@link BUSPSUMMARY }
     *     
     */
    public BUSPSUMMARY getBUSPSUMMARY() {
        return buspsummary;
    }

    /**
     * Sets the value of the buspsummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSPSUMMARY }
     *     
     */
    public void setBUSPSUMMARY(BUSPSUMMARY value) {
        this.buspsummary = value;
    }

    /**
     * Gets the value of the commcred property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commcred property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOMMCRED().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COMMCRED }
     * 
     * 
     */
    public List<COMMCRED> getCOMMCRED() {
        if (commcred == null) {
            commcred = new ArrayList<COMMCRED>();
        }
        return this.commcred;
    }

    /**
     * Gets the value of the relations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRELATIONS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RELATIONS }
     * 
     * 
     */
    public List<RELATIONS> getRELATIONS() {
        if (relations == null) {
            relations = new ArrayList<RELATIONS>();
        }
        return this.relations;
    }

    /**
     * Gets the value of the busenqrhist property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busenqrhist property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSENQRHIST().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSENQRHIST }
     * 
     * 
     */
    public List<BUSENQRHIST> getBUSENQRHIST() {
        if (busenqrhist == null) {
            busenqrhist = new ArrayList<BUSENQRHIST>();
        }
        return this.busenqrhist;
    }

    /**
     * Gets the value of the perresponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the perresponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPERRESPONSE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PERRESPONSE }
     * 
     * 
     */
    public List<PERRESPONSE> getPERRESPONSE() {
        if (perresponse == null) {
            perresponse = new ArrayList<PERRESPONSE>();
        }
        return this.perresponse;
    }

}
