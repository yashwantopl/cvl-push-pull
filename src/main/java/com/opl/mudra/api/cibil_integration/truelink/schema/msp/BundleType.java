
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for BundleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BundleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Bundle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BundleComponent" type="{com/truelink/schema/msp}BundleComponentType" maxOccurs="unbounded"/>
 *         &lt;element name="RenewBundle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BundleType", propOrder = {
    "bundle",
    "description",
    "bundleComponent",
    "renewBundle"
})
public class BundleType {

    @XmlElement(name = "Bundle", required = true)
    protected String bundle;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "BundleComponent", required = true)
    protected List<BundleComponentType> bundleComponent;
    @XmlElement(name = "RenewBundle")
    protected String renewBundle;

    /**
     * Gets the value of the bundle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBundle() {
        return bundle;
    }

    /**
     * Sets the value of the bundle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBundle(String value) {
        this.bundle = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the bundleComponent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bundleComponent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBundleComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BundleComponentType }
     * 
     * 
     */
    public List<BundleComponentType> getBundleComponent() {
        if (bundleComponent == null) {
            bundleComponent = new ArrayList<BundleComponentType>();
        }
        return this.bundleComponent;
    }

    /**
     * Gets the value of the renewBundle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenewBundle() {
        return renewBundle;
    }

    /**
     * Sets the value of the renewBundle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenewBundle(String value) {
        this.renewBundle = value;
    }

}
