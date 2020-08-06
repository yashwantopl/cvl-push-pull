
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CatalogItemFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CatalogItemFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CatalogItemInfo" type="{com/truelink/schema/msp}CatalogItemType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogItemFullType", propOrder = {
    "id",
    "catalogItemInfo"
})
public class CatalogItemFullType {

    protected long id;
    @XmlElement(name = "CatalogItemInfo", required = true)
    protected CatalogItemType catalogItemInfo;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the catalogItemInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogItemType }
     *     
     */
    public CatalogItemType getCatalogItemInfo() {
        return catalogItemInfo;
    }

    /**
     * Sets the value of the catalogItemInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogItemType }
     *     
     */
    public void setCatalogItemInfo(CatalogItemType value) {
        this.catalogItemInfo = value;
    }

}
