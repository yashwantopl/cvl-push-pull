
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RELATIONS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RELATIONS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityPINCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityRelationship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityPAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityRelatedType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityDOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityVoter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PercentageOfControl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityGender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityPassport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityRationCardno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityDIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityCIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityTIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityBussCat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityBussInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityRegNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityServiceTaxno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntityIncorpDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastReportedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RELATIONS", propOrder = {
    "segmentCode",
    "entityName",
    "entityAddress",
    "entityCity",
    "entityPINCode",
    "entityRelationship",
    "entityPAN",
    "entityPhone",
    "entityRelatedType",
    "entityDOB",
    "entityVoter",
    "percentageOfControl",
    "entityGender",
    "entityPassport",
    "entityUID",
    "entityRationCardno",
    "entityDIN",
    "entityCIN",
    "entityTIN",
    "entityBussCat",
    "entityBussInd",
    "entityRegNO",
    "entityServiceTaxno",
    "entityIncorpDate",
    "lastReportedDate"
})
public class RELATIONS {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "EntityName")
    protected String entityName;
    @XmlElement(name = "EntityAddress")
    protected String entityAddress;
    @XmlElement(name = "EntityCity")
    protected String entityCity;
    @XmlElement(name = "EntityPINCode")
    protected String entityPINCode;
    @XmlElement(name = "EntityRelationship")
    protected String entityRelationship;
    @XmlElement(name = "EntityPAN")
    protected String entityPAN;
    @XmlElement(name = "EntityPhone")
    protected String entityPhone;
    @XmlElement(name = "EntityRelatedType")
    protected String entityRelatedType;
    @XmlElement(name = "EntityDOB")
    protected String entityDOB;
    @XmlElement(name = "EntityVoter")
    protected String entityVoter;
    @XmlElement(name = "PercentageOfControl")
    protected String percentageOfControl;
    @XmlElement(name = "EntityGender")
    protected String entityGender;
    @XmlElement(name = "EntityPassport")
    protected String entityPassport;
    @XmlElement(name = "EntityUID")
    protected String entityUID;
    @XmlElement(name = "EntityRationCardno")
    protected String entityRationCardno;
    @XmlElement(name = "EntityDIN")
    protected String entityDIN;
    @XmlElement(name = "EntityCIN")
    protected String entityCIN;
    @XmlElement(name = "EntityTIN")
    protected String entityTIN;
    @XmlElement(name = "EntityBussCat")
    protected String entityBussCat;
    @XmlElement(name = "EntityBussInd")
    protected String entityBussInd;
    @XmlElement(name = "EntityRegNO")
    protected String entityRegNO;
    @XmlElement(name = "EntityServiceTaxno")
    protected String entityServiceTaxno;
    @XmlElement(name = "EntityIncorpDate")
    protected String entityIncorpDate;
    @XmlElement(name = "LastReportedDate")
    protected String lastReportedDate;

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
     * Gets the value of the entityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Sets the value of the entityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityName(String value) {
        this.entityName = value;
    }

    /**
     * Gets the value of the entityAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityAddress() {
        return entityAddress;
    }

    /**
     * Sets the value of the entityAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityAddress(String value) {
        this.entityAddress = value;
    }

    /**
     * Gets the value of the entityCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityCity() {
        return entityCity;
    }

    /**
     * Sets the value of the entityCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityCity(String value) {
        this.entityCity = value;
    }

    /**
     * Gets the value of the entityPINCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityPINCode() {
        return entityPINCode;
    }

    /**
     * Sets the value of the entityPINCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityPINCode(String value) {
        this.entityPINCode = value;
    }

    /**
     * Gets the value of the entityRelationship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityRelationship() {
        return entityRelationship;
    }

    /**
     * Sets the value of the entityRelationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityRelationship(String value) {
        this.entityRelationship = value;
    }

    /**
     * Gets the value of the entityPAN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityPAN() {
        return entityPAN;
    }

    /**
     * Sets the value of the entityPAN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityPAN(String value) {
        this.entityPAN = value;
    }

    /**
     * Gets the value of the entityPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityPhone() {
        return entityPhone;
    }

    /**
     * Sets the value of the entityPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityPhone(String value) {
        this.entityPhone = value;
    }

    /**
     * Gets the value of the entityRelatedType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityRelatedType() {
        return entityRelatedType;
    }

    /**
     * Sets the value of the entityRelatedType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityRelatedType(String value) {
        this.entityRelatedType = value;
    }

    /**
     * Gets the value of the entityDOB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityDOB() {
        return entityDOB;
    }

    /**
     * Sets the value of the entityDOB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityDOB(String value) {
        this.entityDOB = value;
    }

    /**
     * Gets the value of the entityVoter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityVoter() {
        return entityVoter;
    }

    /**
     * Sets the value of the entityVoter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityVoter(String value) {
        this.entityVoter = value;
    }

    /**
     * Gets the value of the percentageOfControl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercentageOfControl() {
        return percentageOfControl;
    }

    /**
     * Sets the value of the percentageOfControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercentageOfControl(String value) {
        this.percentageOfControl = value;
    }

    /**
     * Gets the value of the entityGender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityGender() {
        return entityGender;
    }

    /**
     * Sets the value of the entityGender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityGender(String value) {
        this.entityGender = value;
    }

    /**
     * Gets the value of the entityPassport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityPassport() {
        return entityPassport;
    }

    /**
     * Sets the value of the entityPassport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityPassport(String value) {
        this.entityPassport = value;
    }

    /**
     * Gets the value of the entityUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityUID() {
        return entityUID;
    }

    /**
     * Sets the value of the entityUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityUID(String value) {
        this.entityUID = value;
    }

    /**
     * Gets the value of the entityRationCardno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityRationCardno() {
        return entityRationCardno;
    }

    /**
     * Sets the value of the entityRationCardno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityRationCardno(String value) {
        this.entityRationCardno = value;
    }

    /**
     * Gets the value of the entityDIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityDIN() {
        return entityDIN;
    }

    /**
     * Sets the value of the entityDIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityDIN(String value) {
        this.entityDIN = value;
    }

    /**
     * Gets the value of the entityCIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityCIN() {
        return entityCIN;
    }

    /**
     * Sets the value of the entityCIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityCIN(String value) {
        this.entityCIN = value;
    }

    /**
     * Gets the value of the entityTIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityTIN() {
        return entityTIN;
    }

    /**
     * Sets the value of the entityTIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityTIN(String value) {
        this.entityTIN = value;
    }

    /**
     * Gets the value of the entityBussCat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityBussCat() {
        return entityBussCat;
    }

    /**
     * Sets the value of the entityBussCat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityBussCat(String value) {
        this.entityBussCat = value;
    }

    /**
     * Gets the value of the entityBussInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityBussInd() {
        return entityBussInd;
    }

    /**
     * Sets the value of the entityBussInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityBussInd(String value) {
        this.entityBussInd = value;
    }

    /**
     * Gets the value of the entityRegNO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityRegNO() {
        return entityRegNO;
    }

    /**
     * Sets the value of the entityRegNO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityRegNO(String value) {
        this.entityRegNO = value;
    }

    /**
     * Gets the value of the entityServiceTaxno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityServiceTaxno() {
        return entityServiceTaxno;
    }

    /**
     * Sets the value of the entityServiceTaxno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityServiceTaxno(String value) {
        this.entityServiceTaxno = value;
    }

    /**
     * Gets the value of the entityIncorpDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityIncorpDate() {
        return entityIncorpDate;
    }

    /**
     * Sets the value of the entityIncorpDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityIncorpDate(String value) {
        this.entityIncorpDate = value;
    }

    /**
     * Gets the value of the lastReportedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastReportedDate() {
        return lastReportedDate;
    }

    /**
     * Sets the value of the lastReportedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastReportedDate(String value) {
        this.lastReportedDate = value;
    }

}
