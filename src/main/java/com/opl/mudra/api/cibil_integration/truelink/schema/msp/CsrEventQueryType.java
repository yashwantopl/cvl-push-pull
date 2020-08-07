
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SiteType;


/**
 * <p>Java class for CsrEventQueryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CsrEventQueryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StartDate" type="{com/truelink/schema/msp}DateType"/>
 *         &lt;element name="StopDate" type="{com/truelink/schema/msp}DateType"/>
 *         &lt;element name="Sites">
 *           &lt;complexType>	
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Site" type="{com/truelink/schema/database/tcps/enumerations}SiteType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CsrId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CsrEventQueryType", propOrder = {
    "startDate",
    "stopDate",
    "sites",
    "csrId"
})
public class CsrEventQueryType {

    @XmlElement(name = "StartDate", required = true)
    protected DateType startDate;
    @XmlElement(name = "StopDate", required = true)
    protected DateType stopDate;
    @XmlElement(name = "Sites", required = true)
    protected CsrEventQueryType.Sites sites;
    @XmlElement(name = "CsrId", type = Long.class)
    protected List<Long> csrId;

    /**
     * Gets the value of the startDate property.
     *
     * @return
     *     possible object is
     *     {@link DateType }
     *
     */
    public DateType getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     *
     * @param value
     *     allowed object is
     *     {@link DateType }
     *
     */
    public void setStartDate(DateType value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the stopDate property.
     *
     * @return
     *     possible object is
     *     {@link DateType }
     *
     */
    public DateType getStopDate() {
        return stopDate;
    }

    /**
     * Sets the value of the stopDate property.
     *
     * @param value
     *     allowed object is
     *     {@link DateType }
     *
     */
    public void setStopDate(DateType value) {
        this.stopDate = value;
    }

    /**
     * Gets the value of the sites property.
     *
     * @return
     *     possible object is
     *     {@link CsrEventQueryType.Sites }
     *
     */
    public CsrEventQueryType.Sites getSites() {
        return sites;
    }

    /**
     * Sets the value of the sites property.
     *
     * @param value
     *     allowed object is
     *     {@link CsrEventQueryType.Sites }
     *
     */
    public void setSites(CsrEventQueryType.Sites value) {
        this.sites = value;
    }

    /**
     * Gets the value of the csrId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the csrId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCsrId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getCsrId() {
        if (csrId == null) {
            csrId = new ArrayList<Long>();
        }
        return this.csrId;
    }


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
     *         &lt;element name="Site" type="{com/truelink/schema/database/tcps/enumerations}SiteType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "site"
    })
    public static class Sites {

        @XmlElement(name = "Site", required = true)
        @XmlSchemaType(name = "string")
        protected List<SiteType> site;

        /**
         * Gets the value of the site property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the site property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSite().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SiteType }
         * 
         * 
         */
        public List<SiteType> getSite() {
            if (site == null) {
                site = new ArrayList<SiteType>();
            }
            return this.site;
        }

    }

}
