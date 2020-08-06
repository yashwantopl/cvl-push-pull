
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.ScheduleType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.TimePeriodUnitType;


/**
 * <p>Java class for BundleComponentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BundleComponentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{com/truelink/schema/database/tcps/enumerations}ComponentType"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ScheduleType" type="{com/truelink/schema/database/tcps/enumerations}ScheduleType"/>
 *         &lt;element name="FulfillmentScheduleType" type="{com/truelink/schema/database/tcps/enumerations}TimePeriodUnitType"/>
 *         &lt;element name="FulfillmentPeriods" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FulfillmentDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AssetShelfLife" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ComponentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BundleComponentType", propOrder = {
    "name",
    "description",
    "scheduleType",
    "fulfillmentScheduleType",
    "fulfillmentPeriods",
    "fulfillmentDuration",
    "assetShelfLife",
    "componentId"
})
public class BundleComponentType {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "ScheduleType", required = true)
    @XmlSchemaType(name = "string")
    protected ScheduleType scheduleType;
    @XmlElement(name = "FulfillmentScheduleType", required = true)
    @XmlSchemaType(name = "string")
    protected TimePeriodUnitType fulfillmentScheduleType;
    @XmlElement(name = "FulfillmentPeriods")
    protected int fulfillmentPeriods;
    @XmlElement(name = "FulfillmentDuration")
    protected int fulfillmentDuration;
    @XmlElement(name = "AssetShelfLife")
    protected long assetShelfLife;
    @XmlElement(name = "ComponentId")
    protected long componentId;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the scheduleType property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleType }
     *     
     */
    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    /**
     * Sets the value of the scheduleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleType }
     *     
     */
    public void setScheduleType(ScheduleType value) {
        this.scheduleType = value;
    }

    /**
     * Gets the value of the fulfillmentScheduleType property.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriodUnitType }
     *     
     */
    public TimePeriodUnitType getFulfillmentScheduleType() {
        return fulfillmentScheduleType;
    }

    /**
     * Sets the value of the fulfillmentScheduleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriodUnitType }
     *     
     */
    public void setFulfillmentScheduleType(TimePeriodUnitType value) {
        this.fulfillmentScheduleType = value;
    }

    /**
     * Gets the value of the fulfillmentPeriods property.
     * 
     */
    public int getFulfillmentPeriods() {
        return fulfillmentPeriods;
    }

    /**
     * Sets the value of the fulfillmentPeriods property.
     * 
     */
    public void setFulfillmentPeriods(int value) {
        this.fulfillmentPeriods = value;
    }

    /**
     * Gets the value of the fulfillmentDuration property.
     * 
     */
    public int getFulfillmentDuration() {
        return fulfillmentDuration;
    }

    /**
     * Sets the value of the fulfillmentDuration property.
     * 
     */
    public void setFulfillmentDuration(int value) {
        this.fulfillmentDuration = value;
    }

    /**
     * Gets the value of the assetShelfLife property.
     * 
     */
    public long getAssetShelfLife() {
        return assetShelfLife;
    }

    /**
     * Sets the value of the assetShelfLife property.
     * 
     */
    public void setAssetShelfLife(long value) {
        this.assetShelfLife = value;
    }

    /**
     * Gets the value of the componentId property.
     * 
     */
    public long getComponentId() {
        return componentId;
    }

    /**
     * Sets the value of the componentId property.
     * 
     */
    public void setComponentId(long value) {
        this.componentId = value;
    }

}
